package com.in.fmc.userservice.exceptionhandler;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.in.fmc.userservice.constants.ErrorConstants;
import com.in.fmc.userservice.controllers.LoginAndRegisterController;
import com.in.fmc.userservice.exceptions.InvalidCredentialException;
import com.in.fmc.userservice.exceptions.UsernameOccupiedException;
import com.in.fmc.userservice.models.ErrorResource;
import com.in.fmc.userservice.models.ErrorResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice(basePackageClasses = LoginAndRegisterController.class)

public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		log.error("Exception Occured - {}", ex);
		Set<String> errors = ex.getBindingResult().getFieldErrors().stream().map(fe -> fe.getDefaultMessage())
				.collect(Collectors.toSet());

		errors.addAll(ex.getAllErrors().stream().map(oe -> oe.getDefaultMessage()).collect(Collectors.toSet()));

		String errString = errors.stream().sorted().collect(Collectors.joining(", "));
		errString = ErrorConstants.INVALID_ATTRIBUTES_MSG + errString;

		String title = HttpStatus.BAD_REQUEST.name();
		Integer statusCode = HttpStatus.BAD_REQUEST.value();

		return createErrorResponse(title, statusCode, errString, request);

	}

	@ExceptionHandler
	private ResponseEntity<Object> handleInvalidCredentialException(InvalidCredentialException icex,
			WebRequest webRequest) {

		log.error("Exception Occured - {}", icex);
		String message = icex.getMessage();
		String title = HttpStatus.UNAUTHORIZED.name();
		Integer status = HttpStatus.UNAUTHORIZED.value();
		return createErrorResponse(title, status, message, webRequest);

	}

	@ExceptionHandler
	private ResponseEntity<Object> handleUsernameOccupiedException(UsernameOccupiedException uoex,
			WebRequest webRequest) {

		log.error("Exception Occured - {}", uoex);
		return createErrorResponse(HttpStatus.NOT_ACCEPTABLE.name(), HttpStatus.NOT_ACCEPTABLE.value(),
				uoex.getMessage(), webRequest);
	}

	private ResponseEntity<Object> createErrorResponse(String title, Integer status, String message,
			WebRequest request) {
		ErrorResource errorResource = new ErrorResource();
		errorResource.setType(ErrorConstants.ERROR_RESPONSE_DEFINITION);
		errorResource.setTitle(title);
		errorResource.setCode(status);
		errorResource.setMessage(message);
		String uri = ((ServletWebRequest) request).getRequest().getRequestURI();
		errorResource.setUri(uri);

		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setErrorResource(errorResource);

		return new ResponseEntity<Object>(errorResponse, HttpStatus.valueOf(status));
	}
}
