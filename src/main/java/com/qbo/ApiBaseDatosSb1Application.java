package com.qbo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.qbo.security.FiltroJWTAutorizacion;

@SpringBootApplication
public class ApiBaseDatosSb1Application {

	public static void main(String[] args) {
		SpringApplication.run(ApiBaseDatosSb1Application.class, args);
	}
	
	@EnableGlobalMethodSecurity(prePostEnabled = true)
	@EnableWebSecurity
	@Configuration
	class WebSecurityConfig extends WebSecurityConfigurerAdapter{

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.csrf().disable()
			.addFilterAfter(new FiltroJWTAutorizacion(),
					UsernamePasswordAuthenticationFilter.class)
			.authorizeRequests()
			.antMatchers(HttpMethod.POST, "/api/v1/autenticacion")
			.permitAll()
			.anyRequest()
			.authenticated();
			}
		}

}
