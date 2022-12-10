package com.encore.petandbe.exception;

public class AuthenticationException extends RuntimeException{
	public AuthenticationException(String message) {
		super(message);
	}
}
