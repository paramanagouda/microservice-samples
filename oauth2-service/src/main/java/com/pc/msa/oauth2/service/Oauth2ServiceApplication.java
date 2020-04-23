package com.pc.msa.oauth2.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableResourceServer
@EnableEurekaClient // https://www.devglan.com/spring-security/spring-boot-security-oauth2-example
public class Oauth2ServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(Oauth2ServiceApplication.class, args);
	}

}
