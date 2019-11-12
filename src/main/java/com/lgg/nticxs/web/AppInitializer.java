package com.lgg.nticxs.web;

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
	
//	@Override
//    public void onStartup(ServletContext sc) throws ServletException {
//        sc.getSessionCookieConfig().setHttpOnly(true);        
//        sc.getSessionCookieConfig().setSecure(true);        
//    }

	@Bean
	public UserDAO userDao() {
		return new UserDAO();
	}
	
	
	@Bean
	public WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> setConfiguration() {
		return factory -> {
			factory.setPort(80);
			
			
			Ssl ssl = new Ssl();
	    	ssl.setKeyStore("/etc/letsencrypt/live/cdash.space/keystore.p12");
	    	ssl.setKeyStorePassword("cleoscinc");
	    	ssl.setKeyAlias("tomcat");
	    	factory.setSsl(ssl);
	    	//ssl.setKeyStore("KEYSTORE_TLS"); // por ejemplo /etc/certificates/server12_keystore.jks
	    	//ssl.setKeyStorePassword("KEYSTORE_TLS_PASSPHRASE"); // por ejemplo: YjZutw6V57F8WyOVU6lo
	    	//ssl.setKeyAlias("KEYSTORE_TLS_KEY_ALIAS"); //por ejemplo: 1
	    	//factory.setSsl(ssl);
		};
	}
	


}