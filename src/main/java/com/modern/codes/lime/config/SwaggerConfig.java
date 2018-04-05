package com.modern.codes.lime.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Main configuration class to enable the Swagger UI frontend. It registers all supported controller and describes the
 * API.
 *
 * @author jaroszk
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * Api docket.
     *
     * @return the docket
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                                                      .apis(RequestHandlerSelectors.any())
                                                      .paths(PathSelectors.any())
                                                      .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title(apiTitle)
                                   .description("The Lime application provides funcionalities  for ...")
                                   .termsOfServiceUrl("")
                                   .version(apiVersion)
                                   .build();
    }

    //    @Value("${info.build.version}")
    private String apiVersion;
    //    @Value("${info.build.name}")
    private String apiTitle;
    //    @Value("${info.build.artifact}")
    private String apiGroupName;
    /**
     * The constant SWAGGER_UI_ENDPOINT.
     */
    // Swagger UI
    public static final String SWAGGER_UI_ENDPOINT = "/swagger-ui.html";
    /**
     * The constant SWAGGER_RESOURCES_ENDPOINT.
     */
    public static final String SWAGGER_RESOURCES_ENDPOINT = "/swagger-resources";
    /**
     * The constant SWAGGER_API_DOCS_ENDPOINT.
     */
    public static final String SWAGGER_API_DOCS_ENDPOINT = "/v2/api-docs";

}
