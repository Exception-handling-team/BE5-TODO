package com.example.todolist.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("TodoList API")
                        .version("1.0")
                        .description("할 일 관리 API 문서")
                        .license(new License().name("깃허브 URL").url("https://github.com/hmhmchm")))
                .externalDocs(new ExternalDocumentation()
                        .description("")
                        .url("https://github.com/example/todolist"));
    }
}
