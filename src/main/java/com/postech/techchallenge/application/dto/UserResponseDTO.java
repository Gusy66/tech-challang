package com.postech.techchallenge.application.dto;

import com.postech.techchallenge.domain.entity.Address;
import com.postech.techchallenge.domain.entity.User;
import com.postech.techchallenge.domain.entity.UserType;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

// DTO de resposta com os dados do usuario (sem expor a senha)
@Schema(description = "Dados do usuario retornados pela API")
public record UserResponseDTO(

        @Schema(description = "ID do usuario", example = "1")
        Long id,

        @Schema(description = "Nome do usuario", example = "Joao Silva")
        String name,

        @Schema(description = "E-mail do usuario", example = "joao@email.com")
        String email,

        @Schema(description = "Login do usuario", example = "joaosilva")
        String login,

        @Schema(description = "Tipo do usuario", example = "CUSTOMER")
        UserType userType,

        @Schema(description = "Endereco do usuario")
        AddressDTO address,

        @Schema(description = "Data da ultima alteracao")
        LocalDateTime lastModifiedAt
) {

    // Converte a entidade User para o DTO de resposta
    public static UserResponseDTO fromEntity(User user) {
        Address addr = user.getAddress();
        AddressDTO addressDTO = addr != null
                ? new AddressDTO(addr.getStreet(), addr.getNumber(), addr.getCity(), addr.getState(), addr.getZipCode())
                : null;

        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getLogin(),
                user.getUserType(),
                addressDTO,
                user.getLastModifiedAt()
        );
    }
}
