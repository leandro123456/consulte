package com.lgg.nticxs.web.controller;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@ComponentScan("org.springframework.security.samples.mvc")
public class InicioSeguroController implements WebMvcConfigurer{

	
	@Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/inicio").setViewName("manejosession");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }
}
