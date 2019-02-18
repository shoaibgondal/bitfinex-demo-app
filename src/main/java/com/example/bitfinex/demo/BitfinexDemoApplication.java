package com.example.bitfinex.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@SpringBootApplication
@EnableSwagger2
@EnableScheduling
public class BitfinexDemoApplication {

    public static void main(String[] args) {

        SpringApplication.run(BitfinexDemoApplication.class, args);
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("non-versioned").apiInfo(apiInfo()).select()
                .apis(RequestHandlerSelectors.basePackage("com.example.bitfinex.demo")).paths(PathSelectors.any())
                .build();
    }

    @Bean
    public Docket swaggerUserApi1() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("user-api-1")
                .select()
                .apis(u -> {
                    if (u.produces() != null) {
                        for (MediaType mt : u.produces()) {
                            if (mt.toString().equals("application/vnd.demo.app-v1+json")) {
                                return true;
                            }
                        }
                    }
                    return false;
                })
                .build()
                .produces(Collections.singleton("application/vnd.demo.app-v1+json"))
                .apiInfo(new ApiInfoBuilder().version("1").title("User API")
                        .description("Documentation User API v1").build());
    }

    @Bean
    public Docket swaggerUserApi2() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("user-api-2")
                .select()
                .apis(u -> {
                    if (u.produces() != null) {
                        for (MediaType mt : u.produces()) {
                            if (mt.toString().equals("application/vnd.demo.app-v2+json")) {
                                return true;
                            }
                        }
                    }
                    return false;
                })
                .build()
                .produces(Collections.singleton("application/vnd.demo.app-v2+json"))
                .apiInfo(new ApiInfoBuilder().version("2").title("User API")
                        .description("Documentation User API v2").build());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("BitFinex Demo App").description("Demo Application for BitFinex data pull")
                .contact(new Contact("Shoaib Ahmad Gondal", "URL", "")).version("0.1").build();
    }

}

