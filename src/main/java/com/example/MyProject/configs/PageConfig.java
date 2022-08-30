package com.example.MyProject.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

@Configuration
public class PageConfig implements WebMvcConfigurer {
	
	@Bean
	public BCryptPasswordEncoder passEncoder () {
		
		BCryptPasswordEncoder byCryptPassowrdEncoder = new BCryptPasswordEncoder();
	
		return  byCryptPassowrdEncoder;
	}
	
	public void addViewControlles(ViewControllerRegistry registry) {
			
			registry.addViewController("notes").setViewName("notes");
			registry.addViewController("/").setViewName("notes");
			registry.addViewController("login").setViewName("login");
			
			
		}

		
		
	}


