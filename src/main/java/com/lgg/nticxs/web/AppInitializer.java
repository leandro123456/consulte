package com.lgg.nticxs.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.lgg.nticxs.web.DAO.UserDAO;


@SpringBootApplication
@EnableAutoConfiguration(exclude= {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
@SessionAttributes("authorizationRequest")
public class AppInitializer extends SpringBootServletInitializer{	
	
	
	 @Override
	  protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
	      return builder.sources(AppInitializer.class);
	  }
	 
	 
	public static void main(String[] args) {
		SpringApplication.run(AppInitializer.class, args);
	}
	
	@Override
    public void onStartup(ServletContext sc) throws ServletException {
        sc.getSessionCookieConfig().setHttpOnly(true);        
        sc.getSessionCookieConfig().setSecure(true);        
    }

	@Bean
	public UserDAO userDao() {
		return new UserDAO();
	}
	
	
	@Bean
	public WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> setConfiguration() {
		return factory -> {
			factory.setPort(9090);
		};
	}
	


}