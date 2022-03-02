package com.zensar.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value =HttpStatus.BAD_REQUEST)
public class InvalidStockIdException extends RuntimeException{

	
	
	 @Override
	public String toString() {
		return "InvalidStockIdException " + message ;
	}

	private String message;

	public InvalidStockIdException(String message) {
		super();
		this.message = message;
	}

	public InvalidStockIdException() {
		super();
	}
	
	
	 
	 
}
