package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CarrosNotFound extends RuntimeException {

	public CarrosNotFound(String exception) {
		super(exception);
	}
}
