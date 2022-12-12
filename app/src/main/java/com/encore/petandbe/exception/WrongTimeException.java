package com.encore.petandbe.exception;

public class WrongTimeException extends RuntimeException{
	public WrongTimeException(String s) {
		super(s + "잘못된 시간 형식 입니다.");
	}
}
