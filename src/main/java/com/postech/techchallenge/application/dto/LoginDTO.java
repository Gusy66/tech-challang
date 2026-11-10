package com.postech.techchallenge.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

// DTO para validacao de login
@Schema(description = "Dados para autenticacao do usuario")
public record LoginDTO(

        @NotBlank(message = "O login e obrigatorio")
        @Schema(description = "Login do usuario", example = "joaosilva")
        String login,

        @NotBlank(message = "A senha e obrigatoria")
        @Schema(description = "Senha do usuario", example = "senha123")
        String password
) {
}
