package com.GymManager.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class swaggerConfig {
    
	@Bean
	public Docket createRestApi()
	{
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(this.apiInfo()).select()
				.apis(RequestHandlerSelectors.basePackage("com.GymManager.controller"))
				.paths(PathSelectors.any())
				.build();
				
	}
	
	private ApiInfo apiInfo()
	{
		return new ApiInfoBuilder().title("测试文档").description("描述").version("1.0.0").contact("cf").build();
	}
}
