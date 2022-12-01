package com.encore.petandbe.exception.responses;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ExceptionResponse {

	private String code;
	private String message;

	public ExceptionResponse(String code, String message) {
		this.code = code;
		this.message = message;
	}
}
