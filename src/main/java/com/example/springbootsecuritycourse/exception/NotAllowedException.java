package com.example.springbootsecuritycourse.exception;

public class NotAllowedException extends ApiRequestException {
	public NotAllowedException(String message) {
		super(message);
	}
}
