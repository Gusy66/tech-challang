package com.postech.techchallenge.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

// DTO para atualizacao dos dados do usuario (sem a senha - endpoint separado)
@Schema(description = "Dados para atualizacao do usuario (exceto senha)")
public record UpdateUserDTO(

        @NotBlank(message = "O nome e obrigatorio")
        @Schema(description = "Nome completo do usuario", example = "Joao Silva Atualizado")
        String name,

        @NotBlank(message = "O e-mail e obrigatorio")
        @Email(message = "O e-mail deve ser valido")
        @Schema(description = "E-mail unico do usuario", example = "joao.novo@email.com")
        String email,

        @NotNull(message = "O endereco e obrigatorio")
        @Valid
        @Schema(description = "Endereco atualizado do usuario")
        AddressDTO address
) {
}
