package com.postech.techchallenge.domain.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Objeto de valor que representa o endereco do usuario, embutido na entidade User
@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    private String street;    // Rua
    private String number;    // Numero
    private String city;      // Cidade
    private String state;     // Estado
    private String zipCode;   // CEP
}
