package com.emlebi.restwebservices.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;

import io.swagger.annotations.Api;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;
import static com.google.common.base.Predicates.or;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	   public Docket api() {
	       return new Docket(DocumentationType.SWAGGER_2).select()
	               .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
	               .paths(PathSelectors.any()).build().pathMapping("/")
	               .apiInfo(apiInfo()).useDefaultResponseMessages(false);
	   }

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("EML EBI Rest API")
				.description("EML EBI Rest APIreference for developers")
				.termsOfServiceUrl("http://emlebirestpoc.com")
				.contact("mayur.us@gmail.com").license("EML EBI License")
				.licenseUrl("mayur.us@gmail.com").version("1.0").build();
	}

}