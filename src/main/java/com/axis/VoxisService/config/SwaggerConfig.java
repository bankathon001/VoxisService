package com.axis.VoxisService.config;

import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    public Predicate<String> findPredicate() {
        Predicate<String> predicate = null;
        predicate = PathSelectors.any();
        return predicate;
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo1())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.axis.VoxisService.controller"))
                .paths(findPredicate())
                .build();

    }

    public ApiInfo apiInfo1() {
        return new ApiInfoBuilder().title("Voxis Service")
                .license("1.0")
                .description("All api's for Voxis Service")
                .build();
    }
}
