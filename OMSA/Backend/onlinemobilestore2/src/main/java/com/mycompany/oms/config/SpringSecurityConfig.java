package com.mycompany.oms.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//Class for configuring Spring Security

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled=true,prePostEnabled=true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	private UserDetailsService userDetailsService;
	protected void configure(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.userDetailsService(userDetailsService);
	}
	protected void configure(HttpSecurity http) throws Exception
	{
		http.csrf().disable();
		
		//defining the urls which will be accessible by all and the ones which will require authentication
		/*http.authorizeRequests().antMatchers("/user/register","/mobile/showAll","/mobile/show/{id}","/mobile/byName/**"
				,"/mobile/count/**","/mobile/byPriceRange","/mobile/showByCompany/{name}","/mobile/showByCategory/{id}"
				,"/order/showMobile/{id}","/order/show/{id}","/order/showbyCustomer/{id}","/customer/show/{id}"
				,"/user/validate/{id}").permitAll().and().authorizeRequests()
        .antMatchers("/user/**","/cart/**","/customer/**","/order/**","/mobile/**").authenticated().and().httpBasic();*/
		http.authorizeRequests().antMatchers("/user/**","/cart/**","/customer/**","/order/**","/mobile/**","/category/**").permitAll();
	}
	@Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}