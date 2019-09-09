package com.lgg.nticxs.web.config.security;

import org.springframework.security.core.AuthenticationException;

public class TokenRequiredException extends AuthenticationException {

	private static final long serialVersionUID = -7393059235889820160L;

	private final String userName;
	private final String password;
	
	public TokenRequiredException(String userName, String password) {
		super("Token Required");
		
		this.userName = userName;
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}
}
