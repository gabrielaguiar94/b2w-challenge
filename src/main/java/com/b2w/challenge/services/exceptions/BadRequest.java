package com.b2w.challenge.services.exceptions;

public class BadRequest extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public BadRequest(String msg) {
		super(msg);
	}
}
 