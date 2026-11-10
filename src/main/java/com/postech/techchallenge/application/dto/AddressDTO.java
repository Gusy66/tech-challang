package com.postech.techchallenge.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

// DTO para dados de endereco
@Schema(description = "Dados do endereco do usuario")
public record AddressDTO(

        @NotBlank(message = "A rua e obrigatoria")
        @Schema(description = "Nome da rua", example = "Rua das Flores")
        String street,

        @NotBlank(message = "O numero e obrigatorio")
        @Schema(description = "Numero do endereco", example = "123")
        String number,

        @NotBlank(message = "A cidade e obrigatoria")
        @Schema(description = "Nome da cidade", example = "Sao Paulo")
        String city,

        @NotBlank(message = "O estado e obrigatorio")
        @Schema(description = "Sigla do estado", example = "SP")
        String state,

        @NotBlank(message = "O CEP e obrigatorio")
        @Schema(description = "CEP do endereco", example = "01001-000")
        String zipCode
) {
}
