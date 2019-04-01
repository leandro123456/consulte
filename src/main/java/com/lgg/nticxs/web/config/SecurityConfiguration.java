package com.lgg.nticxs.web.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.lgg.nticxs.web.DAO.RolesDAO;
import com.lgg.nticxs.web.model.Role;
import com.lgg.nticxs.web.config.CustomAuthenticationProvider;

/**
 * Created by leandro Guzman 22/01/2018.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomAuthenticationProvider authProvider;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) {
		auth.authenticationProvider(authProvider);
	}


	    @Override
	    public void configure(HttpSecurity http) throws Exception {
	    	
	    	
	    	RolesDAO roledao = new RolesDAO();
	    	List<Role> listRole = roledao.retrieveAll();
	    	String access = "";
	    	
	    	for (Role role : listRole) {
	    		access = access + "hasAuthority('" + role.getNameRole() + "') or ";
	    	}
	        http.authorizeRequests()
	        .antMatchers("/").permitAll()
	        .antMatchers("/signup").permitAll()
	        .antMatchers("/homepage/").access(access.substring(0, (access.length() - 4)))
	        .and().formLogin().defaultSuccessUrl("/homepage/").loginPage("/login")
            .usernameParameter("user").passwordParameter("password")
	        .and().exceptionHandling().accessDeniedPage ("/logoutsession")
	        .and().csrf().disable();
   }
	
	
	
	
}
