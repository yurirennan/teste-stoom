package br.com.stoom.store.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Response;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.stoom.store.controller"))
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false)
                .globalResponses(HttpMethod.GET, responseMessageForGET())
                .globalResponses(HttpMethod.POST, responseMessageForGET())
                .globalResponses(HttpMethod.PATCH, responseMessageForGET())
                .globalResponses(HttpMethod.DELETE, responseMessageForGET())
                .globalResponses(HttpMethod.PUT, responseMessageForGET())
                .apiInfo(apiInfo());
    }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("DESAFIO STOOM")
                .description("Essa é a documentação da API da empresa Stoom")
                .version("1.0.0")
                .build();
    }

    private List<Response> responseMessageForGET()
    {
        return new ArrayList<Response>() {{
            add(new ResponseBuilder()
                    .code("500")
                    .description("Internal Server Error")
                    .build());
            add(new ResponseBuilder()
                    .code("403")
                    .description("Forbidden")
                    .build());
            add(new ResponseBuilder()
                    .code("401")
                    .description("Unauthorized")
                    .build());
            add(new ResponseBuilder()
                    .code("400")
                    .description("Bad Request")
                    .build());
            add(new ResponseBuilder()
                    .code("200")
                    .description("Ok")
                    .build());
        }};
    }
}
