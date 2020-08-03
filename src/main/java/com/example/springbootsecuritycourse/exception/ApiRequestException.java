package com.example.springbootsecuritycourse.exception;

public class ApiRequestException extends RuntimeException {
	public ApiRequestException(String message) {
		super(message);
	}
}
