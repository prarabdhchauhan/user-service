package com.in.fmc.userservice.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.in.fmc.userservice.constants.ErrorConstants;
import com.in.fmc.userservice.constants.ValidationConstants;
import com.in.fmc.userservice.entities.Login;
import com.in.fmc.userservice.entities.Register;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class RegistrationDto {

	@Size(max = 256, message = ErrorConstants.INVALID_NAME)
	private String name;

	@Email(message = ErrorConstants.INVALID_EMAIL_ID)
	private String emailId;

	// @Pattern(regexp = "[0-9]{10}",message = "10 digits are required")
	private Long mobileNo;

	@Size(min = 8, max = 32, message = ErrorConstants.INVALID_USERNAME)
	private String username;

	@Pattern(regexp = ValidationConstants.PASSWORD_REGEX, message = ErrorConstants.INVALID_PASSWORD)
	private String password;

	public static Register mapToRegister(RegistrationDto registrationDto) {
		Register register = new Register();

		register.setName(registrationDto.getName());
		register.setEmailId(registrationDto.getEmailId());
		register.setMobileNo(registrationDto.getMobileNo());

		Login login = new Login();
		login.setUsername(registrationDto.getUsername());
		login.setPassword(registrationDto.getPassword());

		register.setLogin(login);

		log.info("In mapToRegister, register");

		return register;
	}
}
