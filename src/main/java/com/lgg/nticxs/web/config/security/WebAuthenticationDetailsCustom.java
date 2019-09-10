package com.lgg.nticxs.web.config.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.authentication.WebAuthenticationDetails;

public class WebAuthenticationDetailsCustom extends WebAuthenticationDetails {

	private static final long serialVersionUID = 2480134274095592559L;

	private final String tokenTSA;
	
	public WebAuthenticationDetailsCustom(HttpServletRequest request) {
		super(request);
		
		this.tokenTSA = request.getParameter("tokenTSA");
	}

	public String getTokenTSA() {
		return tokenTSA;
	}
}
