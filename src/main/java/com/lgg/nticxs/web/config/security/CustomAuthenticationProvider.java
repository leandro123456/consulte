package com.lgg.nticxs.web.config.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.lgg.nticxs.web.utils.EncryptorPassword;


import com.lgg.nticxs.web.DAO.UserDAO;
import com.lgg.nticxs.web.model.User;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {


	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		UserDAO userdao = new UserDAO();
		String name = authentication.getName();
		String password = authentication.getCredentials().toString();

		User user = userdao.retrieveByMail(name);
		if(user != null){
			try {
				if (name.equals(user.getEmail()) && password.equals(EncryptorPassword.decrypt(user.getPassword()))) {
					List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
					authorities.add(new SimpleGrantedAuthority(user.getRole()));
					return new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword(), authorities);
				} else {
					System.out.println("Login failed"+ "Invalid date. User name = " + user.getEmail());
				}
			} catch (Exception e) {
				System.out.println(" Error authenticate()"+ "Error to decrypt the password");
				e.printStackTrace();
			}
		}
		throw new IncorrectLoginCredentialsException();
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(
				UsernamePasswordAuthenticationToken.class);
	}
}
