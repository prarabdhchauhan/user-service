package com.in.fmc.userservice.dto;

import lombok.Data;

@Data
public class RegistrationDto {

	private String name;

	private String emailId;

	private Long mobileNo;

	private String username;

	private String password;

}
