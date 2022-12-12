package com.encore.petandbe.exception;

public class NonExistResourceException extends NullPointerException{
	public NonExistResourceException(String s) {
		super(s);
	}
}