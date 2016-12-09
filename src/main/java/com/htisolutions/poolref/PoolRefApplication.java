package com.htisolutions.poolref;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

@SpringBootApplication
public class PoolRefApplication {

	public static void main(String[] args) {
        SpringApplication.run(PoolRefApplication.class, args);
	}

}
