package com.example.springbootsecuritycourse.exception;

public class NotFoundException extends ApiRequestException {
	public NotFoundException(String message) {
		super(message);
	}
}
