package com.lgg.nticxs.web.config.security;

import org.springframework.security.core.AuthenticationException;

public class IncorrectLoginCredentialsException extends AuthenticationException {

	private static final long serialVersionUID = 8123006082904143052L;

	public IncorrectLoginCredentialsException() {
		super("Incorrect Login Credentials");
	}
}
