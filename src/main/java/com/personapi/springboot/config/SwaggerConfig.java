package com.personapi.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfig {

    @Bean
    OpenAPI openAPI() {
	  return new OpenAPI()
	          .info(new Info()
	                  .title("Person API Spring Boot")
	                  .description("Sistema de gerenciamento de pessoas em uma API REST com Spring Boot")
	                  .version("2.0")
	                  .termsOfService("Terms of Service")
	                  .license(new License()
	                          .name("Apache License Version 2.0")
	                          .url("https://www.apache.org/license.html")
	                  )
	          ).externalDocs(
	                  new ExternalDocumentation()
	                  .description("Hugo Almeida")
	                  .url("https://www.linkedin.com/in/hugo-almeida-72683488/"));
	}
	
}
