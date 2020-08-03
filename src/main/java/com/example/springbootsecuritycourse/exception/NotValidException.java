package com.example.springbootsecuritycourse.exception;

public class NotValidException extends ApiRequestException {
	public NotValidException(String message) {
		super(message);
	}
}
