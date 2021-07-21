package com.in.fmc.userservice.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonInclude(value = Include.NON_NULL)
public class ErrorResponse {

	@JsonProperty(value = "error")
	private ErrorResource errorResource;
}
