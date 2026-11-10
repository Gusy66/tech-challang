package com.postech.techchallenge.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

// DTO para troca de senha (endpoint exclusivo)
@Schema(description = "Dados para troca de senha do usuario")
public record ChangePasswordDTO(

        @NotBlank(message = "A senha atual e obrigatoria")
        @Schema(description = "Senha atual do usuario", example = "senha123")
        String currentPassword,

        @NotBlank(message = "A nova senha e obrigatoria")
        @Size(min = 6, message = "A nova senha deve ter no minimo 6 caracteres")
        @Schema(description = "Nova senha do usuario", example = "novaSenha456")
        String newPassword
) {
}
