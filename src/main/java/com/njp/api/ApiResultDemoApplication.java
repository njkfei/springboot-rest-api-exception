package com.njp.api;

import com.njp.api.config.WebMvcConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

//@SpringBootApplication(exclude = {ErrorMvcAutoConfiguration.class})
@SpringBootApplication
@EnableWebMvc
@Import(WebMvcConfig.class)
public class ApiResultDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiResultDemoApplication.class, args);
	}
}
