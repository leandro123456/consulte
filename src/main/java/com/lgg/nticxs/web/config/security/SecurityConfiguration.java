package com.lgg.nticxs.web.config.security;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.ExceptionMappingAuthenticationFailureHandler;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import com.lgg.nticxs.web.config.security.cookies.MySimpleUrlAuthenticationSuccessHandler;


@Configuration
//@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	public SecurityConfiguration() {
        super();
    }
	
    @Bean("authenticationManager")
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
            return super.authenticationManagerBean();
    }
    
	@Autowired
	private CustomAuthenticationProvider authProvider;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) {
		auth.authenticationProvider(authProvider);
	}


	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
		.authorizeRequests()
		.antMatchers("/anonymous*").anonymous()
		.antMatchers("/","/login*","/signup").permitAll()
		.anyRequest().authenticated()
		//.antMatchers("/home/").permitAll()
		.and()
        .rememberMe().key("uniqueAndSecret").tokenValiditySeconds(86400)
        
		.and()
		.formLogin()
		//.defaultSuccessUrl("/home/")
		.loginPage("/login")
		.loginProcessingUrl("/login")
		.successHandler(successHandler())
		.failureUrl("/login.jsp?error=true")
		.usernameParameter("user").passwordParameter("password")
		.and()
		.logout().deleteCookies("JSESSIONID")

        
		
		.and()
		.sessionManagement()
		.sessionFixation().migrateSession()
		.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
		.invalidSessionUrl("/login")
		.maximumSessions(2)
		.expiredUrl("/login");
   }
	    
	    private AuthenticationSuccessHandler successHandler() {
	        return new MySimpleUrlAuthenticationSuccessHandler();
	    }
	    
 
		@Bean
		public HttpSessionEventPublisher httpSessionEventPublisher() {
		    return new HttpSessionEventPublisher();
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
