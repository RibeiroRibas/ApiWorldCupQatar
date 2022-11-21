package br.com.ribeiroribas.worldcupqatar.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfigurations {

    @Bean
    public GroupedOpenApi costumerApi() {
        return GroupedOpenApi.builder()
                .group("World Cup Qatar")
                .pathsToMatch("/qatar/all-matches","/qatar/matches-by-date/{date}","/qatar/final-match")
                .build();
    }

    @Bean
    public OpenAPI docApiRest() {
        return new OpenAPI()
                .info(new Info().title("World Cup Qatar")
                        .description("All football matches of FIFA World Cup Qatar.")
                        .version("v1.0")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("SpringShop Wiki Documentation")
                        .url("https://springshop.wiki.github.org/docs"));
    }

}
