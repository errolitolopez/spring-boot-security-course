package com.example.springbootsecuritycourse.controller;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.springbootsecuritycourse.exception.ApiRequestException;
import com.example.springbootsecuritycourse.model.ApiException;

@ControllerAdvice
public class ApiExceptionHandler {

	@ExceptionHandler(value = { ApiRequestException.class })
	public ResponseEntity<Object> handleApiRequestException(ApiRequestException e) {

		HttpStatus badRequest = HttpStatus.BAD_REQUEST;
		ApiException apiException = new ApiException(
				e.getMessage(),
				badRequest,
				ZonedDateTime.now(ZoneId.of("+08:00"))
		);

		return new ResponseEntity<>(apiException, badRequest);
	}
}
