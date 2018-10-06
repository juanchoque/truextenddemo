package com.truextend.dev.recipes.swaggerConfig;

import com.truextend.dev.recipes.util.ConstantsRecipes;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@EnableSwagger2
@Configuration
public class SwaggerConfig {
    public Docket recipeApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.truextend.dev.recipes1"))
                .paths(regex("/.*"))
                .build()
                .apiInfo(metaInfo());
    }

    private ApiInfo metaInfo(){

        ApiInfo apiInfo = new ApiInfo(
                ConstantsRecipes.TITLE_API,
                ConstantsRecipes.DESCRIPTION_API,
                ConstantsRecipes.VERSION_API,
                ConstantsRecipes.TERM_SERVICE_URL_API,
                ConstantsRecipes.CONTACT_API,
                ConstantsRecipes.LICENCE_API,
                ConstantsRecipes.URL_LICENCE_API
        );

        return apiInfo;
    }
}
