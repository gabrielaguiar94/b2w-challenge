package com.b2w.challenge.services.exceptions;

public class ServiceUnavailable extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ServiceUnavailable(String msg) {
		super(msg);
	}

}
