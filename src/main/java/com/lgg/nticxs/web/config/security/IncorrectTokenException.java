package com.lgg.nticxs.web.config.security;

import org.springframework.security.core.AuthenticationException;

public class IncorrectTokenException extends AuthenticationException {

	private static final long serialVersionUID = 8123006082904143052L;

	public IncorrectTokenException() {
		super("Incorrect Token");
	}
}
