package com.example.springbootsecuritycourse.exception;

public class NullValueException extends ApiRequestException {
	public NullValueException(String message) {
		super(message);
	}
}
