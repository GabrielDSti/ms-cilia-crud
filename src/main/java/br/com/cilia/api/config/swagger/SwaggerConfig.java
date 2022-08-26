package br.com.cilia.api.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;


@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket ciliaCrudApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.cilia"))
                .paths(PathSelectors.regex("/v1.*"))
                .build()
                .apiInfo(metaInfo());
    }

    private ApiInfo metaInfo(){
        ApiInfo apiInfo = new ApiInfo(
                "Cilia Produtos Api Rest",
                "API REST para cadastro de produtos",
                "1.0",
                "Terms of Service",
                new Contact("Gabriel Dias",
                        "https://www.linkedin.com/in/gabriel-dias-182979136",
                        "gabriel.sistemasti@gmail.com"),
                "Apache License Version 2.0",
                "https://www.apache.org/licensen.html", new ArrayList<VendorExtension>()
        );

        return  apiInfo;
    }
}
