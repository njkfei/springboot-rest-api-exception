package com.njp.api;

import com.njp.api.config.SwaggerDocumentationConfig;
import com.njp.api.config.WebMvcConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//@SpringBootApplication(exclude = {ErrorMvcAutoConfiguration.class})
@SpringBootApplication
@EnableWebMvc
@Import({WebMvcConfig.class,SwaggerDocumentationConfig.class})
@EnableSwagger2
public class ApiResultDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiResultDemoApplication.class, args);
	}
}
