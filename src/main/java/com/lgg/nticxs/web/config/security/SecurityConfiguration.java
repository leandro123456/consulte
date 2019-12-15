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
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.ExceptionMappingAuthenticationFailureHandler;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import com.lgg.nticxs.web.DAO.UserDAO;
import com.lgg.nticxs.web.config.security.cookies.MySimpleUrlAuthenticationSuccessHandler;
import com.lgg.nticxs.web.config.security.cookies.MyUserDetailsService;
import com.lgg.nticxs.web.utils.Settings;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	public SecurityConfiguration() {
        super();
    }
	
//    @Bean("authenticationManager")
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//            return super.authenticationManagerBean();
//    }
    
	@Autowired
	private CustomAuthenticationProvider authProvider;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) {
		auth.authenticationProvider(authProvider);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		if(Settings.getInstance().isTLSenable()) {
			System.out.println("usa tls!!");
		http
		.requiresChannel().anyRequest().requiresSecure();
		}
		
		http
		.authorizeRequests()
		.antMatchers("/anonymous*").anonymous()

		.antMatchers("/","/home","/login*","/signup","forgot-password").permitAll()
//		.anyRequest().authenticated()
     
		.and()
		.formLogin()
		.loginPage("/login")
//		.loginProcessingUrl("/login")
		.successHandler(successHandler())
//		.failureUrl("/login")
		.failureUrl("/login?error=true")
		.usernameParameter("user").passwordParameter("password")
		.and().exceptionHandling().accessDeniedPage ("/logoutsession")

		.and()
        .rememberMe().key("uniqueAndSecret") 
        .userDetailsService(MyUserDetails())

        .and()
        .csrf().disable()
        ;
		
	    http.logout()
        .clearAuthentication(true)
        .invalidateHttpSession(true)
		.and()
		.logout().deleteCookies("JSESSIONID");
   }
	    
	    private AuthenticationSuccessHandler successHandler() {
	        return new MySimpleUrlAuthenticationSuccessHandler();
	    }
	    
	    private UserDetailsService MyUserDetails(){
	    	return new MyUserDetailsService();
	    }
 
	    
		@Bean
		public HttpSessionEventPublisher httpSessionEventPublisher() {
		    return new HttpSessionEventPublisher();
		}
	    

//	    @Bean
//	    public AuthenticationFailureHandler authenticationFailureHandler() {
//	        ExceptionMappingAuthenticationFailureHandler exceptionMappingAuthenticationFailureHandler = new ExceptionMappingAuthenticationFailureHandler();
//	        DefaultRedirectStrategy redirect = new DefaultRedirectStrategy() {
//	        	
//	        	@Override
//	        	public void sendRedirect(HttpServletRequest request, HttpServletResponse response, String url) throws IOException {
////	        		if(url.equals("/login/token")) {
//	        		if(url.equals("/login")) {
//	        			try {
//	            			HttpSession session = request.getSession(false);
//	            			url = super.calculateRedirectUrl(request.getContextPath(), url);
//	    					session.getServletContext().getRequestDispatcher(url).forward(request, response);
//	    					return;
//	    				} catch (ServletException e) {
//	    					e.printStackTrace();
//	    				}
//	        		}
//	        		
//	        		super.sendRedirect(request, response, url);
//	        	}
//	        };
//	        
//	        Map<String, String> exceptionMap = new HashMap<String, String>();
//	        exceptionMap.put(IncorrectLoginCredentialsException.class.getName(), "/login");
//	        exceptionMap.put(IncorrectTokenException.class.getName(), "/login");
//	        exceptionMap.put(TokenRequiredException.class.getName(), "/login");
//	        exceptionMappingAuthenticationFailureHandler.setExceptionMappings(exceptionMap);
//	        exceptionMappingAuthenticationFailureHandler.setRedirectStrategy(redirect);
//	        
//	        return exceptionMappingAuthenticationFailureHandler;
//	    }
	
	
	
}
