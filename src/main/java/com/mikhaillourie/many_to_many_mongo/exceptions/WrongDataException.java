package com.mikhaillourie.many_to_many_mongo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class WrongDataException extends RuntimeException {

	public WrongDataException(String msg) {
		super(msg);
	}
}
