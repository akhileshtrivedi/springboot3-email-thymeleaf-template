package com.at.springboot3emailthymeleaftemplate;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(info = @Info(title = "Send Email API", version = "1.0"))
@SpringBootApplication
public class Springboot3EmailThymeleafTemplateApplication {

	public static void main(String[] args) {
		SpringApplication.run(Springboot3EmailThymeleafTemplateApplication.class, args);
	}

}
