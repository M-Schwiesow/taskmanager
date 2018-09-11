package com.mws.examtwo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	private UserDetailsService userDetailsService;
	
	public WebSecurityConfig(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}
	
	//adding a bean for Bcrypt
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.
			authorizeRequests()
				//allow all requests to access css and js resources, and the /registration route
				.antMatchers("/static/**", "/registration").permitAll()
				//adding in a route accessible only to Admins
				.antMatchers("/admin/**").access("hasRole('ADMIN')")
				.anyRequest().authenticated()
				.and()
			//specify login using a form
			.formLogin()
				//this default URL overrides the default behavior of sending to "/" or the last page attempted.
				//it also seems to prevent the behavior of serving up a raw css page.
				.defaultSuccessUrl("/tasks", true)
				//specify the URL to send to if login is required
				.loginPage("/login")
//				.usernameParameter("email") //changes the username parameter that SpringSecurity NEEDS to something else
				.permitAll()
				.and()
			.logout()
				.permitAll();
	}
	
	//this configures Spring Security to use my custom implementation of the UserDetailsService... where is that, again?
	@Autowired
	public void configureGlobale(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}

}