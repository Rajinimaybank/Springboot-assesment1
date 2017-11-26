package com.intellect.user;

import java.time.LocalDate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;



/*
 * Spring Boot application trigger point
 */
@SpringBootApplication
@EntityScan("com.intellect.user.domain")
@ComponentScan(basePackages = "com.intellect") 
@PropertySource({ "classpath:application.properties" })
@Configuration
@EnableAutoConfiguration
public class UserApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
	}
	

	@EnableWebMvc
	@EnableSwagger2
	public class AppConfig extends WebMvcConfigurerAdapter {
		public AppConfig() {
			super();
		}

		@Bean
		public Docket swaggerApi() {
			return new Docket(DocumentationType.SWAGGER_2).groupName("user-restApi").apiInfo(apiInfo()).select()
					.apis(RequestHandlerSelectors.basePackage("com.intellect.user.controller"))
					// .apis(RequestHandlerSelectors.any())
					.paths(PathSelectors.any()).build().directModelSubstitute(LocalDate.class, String.class)
					.genericModelSubstitutes(new Class[] { ResponseEntity.class });

		}

		@Override
		public void addResourceHandlers(ResourceHandlerRegistry registry) {
			registry.addResourceHandler(new String[] { "/swagger-ui.html" })
					.addResourceLocations(new String[] { "classpath:/META-INF/resources/" });

			registry.addResourceHandler(new String[] { "/webjars/**" })
					.addResourceLocations(new String[] { "classpath:/META-INF/resources/webjars/" });
		}

		private ApiInfo apiInfo() {
			return new ApiInfoBuilder().title("User REST API").description(" ").build();
		}
	}
}
