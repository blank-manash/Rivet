package com.demo.application.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class RestExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(ServerException.class)
	protected ResponseEntity<Object> handleException(ServerException ex) {
		return ResponseEntity.badRequest().body(ex.getMessage());
	}
}


