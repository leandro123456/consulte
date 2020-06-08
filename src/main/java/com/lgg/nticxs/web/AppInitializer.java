package com.lgg.nticxs.web;


import org.apache.catalina.connector.Connector;
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

import org.springframework.boot.web.server.Ssl;

import com.lgg.nticxs.web.DAO.UserDAO;
import com.lgg.nticxs.web.utils.Settings;


@SpringBootApplication
@EnableAutoConfiguration(exclude= {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
//@SessionAttributes("authorizationRequest")
public class AppInitializer extends SpringBootServletInitializer{	
	
	
	 @Override
	  protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
	      return builder.sources(AppInitializer.class);
	  }
	 
	 
	public static void main(String[] args) {
		SpringApplication.run(AppInitializer.class, args);
	}
	
//	@Bean
	private Connector redirectConnector() {
		  Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
		  connector.setScheme("http");
		  connector.setPort(80);
		  connector.setSecure(false);
		  connector.setRedirectPort(443);
		  return connector;
		}

	@Bean
	public UserDAO userDao() {
		return new UserDAO();
	}
	
	
	@Bean
	public WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> setConfiguration() {
		return factory -> {
			if(Settings.getInstance().isTLSenable()) {
			factory.setPort(443);
			Ssl ssl = new Ssl();
	    	ssl.setKeyStore("/etc/letsencrypt/live/cdash.space-0002/keystore.p12");
	    	ssl.setKeyStorePassword("cleoscinc");
	    	ssl.setKeyAlias("tomcat");
		ssl.setKeyStoreType("PKCS12");
	    	factory.setSsl(ssl);
			}
			else {
	    	factory.setPort(80);
			}
		};
	}
	


}
