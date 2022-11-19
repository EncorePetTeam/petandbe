package com.encore.petandbe.exception;

public class WrongRequestException extends SecurityException {
	public WrongRequestException(String s) {
		super(s);
	}
}
