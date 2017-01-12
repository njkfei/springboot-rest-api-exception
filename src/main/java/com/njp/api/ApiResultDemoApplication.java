package com.njp.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

//@SpringBootApplication(exclude = {ErrorMvcAutoConfiguration.class})
@SpringBootApplication
@EnableWebMvc
public class ApiResultDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiResultDemoApplication.class, args);
	}
}
