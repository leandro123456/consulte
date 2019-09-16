package com.lgg.nticxs.web.config.security;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.ExceptionMappingAuthenticationFailureHandler;

import com.lgg.nticxs.web.DAO.RolesDAO;
import com.lgg.nticxs.web.config.security.cookies.MySimpleUrlAuthenticationSuccessHandler;
import com.lgg.nticxs.web.model.Role;



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
	    	
//	    	
//	    	RolesDAO roledao = new RolesDAO();
//	    	List<Role> listRole = roledao.retrieveAll();
//	    	String access = "";
//	    	
//	    	for (Role role : listRole) {
//	    		access = access + "hasAuthority('" + role.getNameRole() + "') or ";
//	    	}
	    	//http.servletApi();
//	        http
//	        .authorizeRequests()
//	        .antMatchers("/").permitAll()
//	        .antMatchers("/signup").permitAll()
//	        .antMatchers("/home/").access(access.substring(0, (access.length() - 4)))
//	        .and().formLogin().defaultSuccessUrl("/home/").loginPage("/login")
//            .usernameParameter("user").passwordParameter("password")
//	        .and().exceptionHandling().accessDeniedPage ("/logoutsession")
//	        .and().csrf().disable();
	        
	        
	  //funciono
//	        .authorizeRequests()
//	            .anyRequest().authenticated()
//	            .and()
//	        .formLogin().loginPage("/login")
//	        .permitAll()
//	            .and()
//	        .logout()
//	        .permitAll();
	        
	    	
//	    	RolesDAO roledao = new RolesDAO();
//	    	List<Role> listRole = roledao.retrieveAll();
//	    	String access = "";
//	    	
//	    	for (Role role : listRole) {
//	    		access = access + "hasAuthority('" + role.getNameRole() + "') or ";
//	    	}
	        
	    	//http.servletApi();
			http.authorizeRequests()
				.antMatchers("/").permitAll()
				.antMatchers("/signup").permitAll()
		        .antMatchers("/home/").permitAll()
		        .and().formLogin().defaultSuccessUrl("/home/").loginPage("/login")
	            .usernameParameter("user").passwordParameter("password")
	            .successHandler(myAuthenticationSuccessHandler())
	            .and().exceptionHandling().accessDeniedPage ("/logoutsession")
		        .and()
	            .logout().deleteCookies("JSESSIONID")
	            .and()
	            .rememberMe().key("uniqueAndSecret").tokenValiditySeconds(86400)
		        .and().csrf().disable();
   }
	    
	    @Bean
	    public AuthenticationSuccessHandler myAuthenticationSuccessHandler(){
	        return new MySimpleUrlAuthenticationSuccessHandler();
	    }
	    
	    @Autowired
	    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	        auth
	            .inMemoryAuthentication()
	                .withUser("user").password("password").roles("USER").and()
	                .withUser("user").password("password").roles("USER", "ADMIN");
	    }
	

	    @Bean
	    public AuthenticationFailureHandler authenticationFailureHandler() {
	        ExceptionMappingAuthenticationFailureHandler exceptionMappingAuthenticationFailureHandler = new ExceptionMappingAuthenticationFailureHandler();
	        DefaultRedirectStrategy redirect = new DefaultRedirectStrategy() {
	        	
	        	@Override
	        	public void sendRedirect(HttpServletRequest request, HttpServletResponse response, String url) throws IOException {
//	        		if(url.equals("/login/token")) {
	        		if(url.equals("/login")) {
	        			try {
	            			HttpSession session = request.getSession(false);
	            			url = super.calculateRedirectUrl(request.getContextPath(), url);
	    					session.getServletContext().getRequestDispatcher(url).forward(request, response);
	    					return;
	    				} catch (ServletException e) {
	    					e.printStackTrace();
	    				}
	        		}
	        		
	        		super.sendRedirect(request, response, url);
	        	}
	        };
	        
	        Map<String, String> exceptionMap = new HashMap<String, String>();
	        exceptionMap.put(IncorrectLoginCredentialsException.class.getName(), "/login?incorrectcredentials=true");
	        exceptionMap.put(IncorrectTokenException.class.getName(), "/login?incorrecttoken=true");
	        exceptionMap.put(TokenRequiredException.class.getName(), "/login");
	        exceptionMappingAuthenticationFailureHandler.setExceptionMappings(exceptionMap);
	        exceptionMappingAuthenticationFailureHandler.setRedirectStrategy(redirect);
	        
	        return exceptionMappingAuthenticationFailureHandler;
	    }
	
	
	
}
