package com.in.fmc.userservice.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.in.fmc.userservice.dto.RegistrationDto;
import com.in.fmc.userservice.entities.Login;
import com.in.fmc.userservice.services.LoginAndRegisterService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(path = "/v1/users")
@Slf4j
@Validated
public class LoginAndRegisterController {

	@Autowired
	LoginAndRegisterService loginAndRegisterService;

	@PostMapping(path = "/login")
	public ResponseEntity<String> login(@RequestBody Login login) {
		return loginAndRegisterService.login(login);
	}

	@PostMapping(path = "/register")
	public ResponseEntity<String> register(@Valid @RequestBody RegistrationDto registrationDto) {

		log.info("In register with registerDto");

		return loginAndRegisterService.register(RegistrationDto.mapToRegister(registrationDto));
	}
}