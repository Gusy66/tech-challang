package com.postech.techchallenge.application.dto;

import com.postech.techchallenge.domain.entity.UserType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

// DTO para criacao de um novo usuario
@Schema(description = "Dados para cadastro de um novo usuario")
public record CreateUserDTO(

        @NotBlank(message = "O nome e obrigatorio")
        @Schema(description = "Nome completo do usuario", example = "Joao Silva")
        String name,

        @NotBlank(message = "O e-mail e obrigatorio")
        @Email(message = "O e-mail deve ser valido")
        @Schema(description = "E-mail unico do usuario", example = "joao@email.com")
        String email,

        @NotBlank(message = "O login e obrigatorio")
        @Size(min = 3, max = 50, message = "O login deve ter entre 3 e 50 caracteres")
        @Schema(description = "Login para autenticacao", example = "joaosilva")
        String login,

        @NotBlank(message = "A senha e obrigatoria")
        @Size(min = 6, message = "A senha deve ter no minimo 6 caracteres")
        @Schema(description = "Senha do usuario", example = "senha123")
        String password,

        @NotNull(message = "O tipo de usuario e obrigatorio")
        @Schema(description = "Tipo do usuario", example = "CUSTOMER")
        UserType userType,

        @NotNull(message = "O endereco e obrigatorio")
        @Valid
        @Schema(description = "Endereco do usuario")
        AddressDTO address
) {
}
