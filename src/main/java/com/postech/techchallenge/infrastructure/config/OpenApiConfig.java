package com.postech.techchallenge.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Configuracao do Swagger/OpenAPI para documentacao dos endpoints
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Tech Challenge - Sistema de Gestao de Restaurantes")
                        .version("v1")
                        .description("API para gerenciamento de usuarios do sistema de gestao de restaurantes. "
                                + "Fase 1 do Tech Challenge - Pos-Tech FIAP.")
                        .contact(new Contact()
                                .name("Equipe Tech Challenge")
                                .email("contato@techchallenge.com")));
    }
}
