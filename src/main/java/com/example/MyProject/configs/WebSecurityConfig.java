package com.example.MyProject.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.MyProject.services.CustomUserDetailsService;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	
	
	@Autowired
	CustomizeAuthenticationSuccessHandler 	customizeAuthenticationSuccessHandler;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	
	
	@Bean 
	public UserDetailsService jpaUSerDetails () { 
		return new 	CustomUserDetailsService();
	}
	
	@Override 
	protected void configure ( AuthenticationManagerBuilder auth) throws Exception {
		UserDetailsService userDetailsService = jpaUSerDetails();
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
		
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests().antMatchers("/").hasAuthority("ADMIN").antMatchers("/h2/console").permitAll()
		.antMatchers("/login").permitAll().antMatchers("/singup").permitAll().antMatchers("/notes")
		.hasAuthority("ADMIN").antMatchers("/notes/**").hasAuthority("ADMIN").anyRequest().authenticated().and()
		.csrf().disable().formLogin().successHandler(customizeAuthenticationSuccessHandler).loginPage("/login")
		.failureUrl("/login?error=true").usernameParameter("email").passwordParameter("password").and().logout()
		.logoutRequestMatcher( new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/").and().exceptionHandling();
		
		
	}
	
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**","/static/**","/css/**", "/js/**", "images/**");
	}
	
	
}
