package com.newland.oauth2.springbootoauth2server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableResourceServer
public class SpringbootOauth2ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootOauth2ServerApplication.class, args);
	}

}
