package com.in.fmc.userservice.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonInclude(value = Include.NON_NULL)
public class ErrorResource {

	@JsonProperty(value = "definition", index = 0)
	private String type;

	@JsonProperty(value = "title", index = 1, defaultValue = "")
	private String title;

	@JsonProperty(value = "status", index = 2)
	private Integer code;

	@JsonProperty(value = "message", index = 3)
	private String message;

	@JsonProperty(value = "path", index = 4)
	private String uri;

}
