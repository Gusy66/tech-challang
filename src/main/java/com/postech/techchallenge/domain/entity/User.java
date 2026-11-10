package com.postech.techchallenge.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

// Entidade principal que representa um usuario do sistema (dono de restaurante ou cliente)
@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name; // Nome do usuario

    @Column(nullable = false, unique = true)
    private String email; // E-mail unico do usuario

    @Column(nullable = false, unique = true)
    private String login; // Login para autenticacao

    @Column(nullable = false)
    private String password; // Senha do usuario (armazenada com hash)

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserType userType; // Tipo: RESTAURANT_OWNER ou CUSTOMER

    @Embedded
    private Address address; // Endereco do usuario

    @Column(nullable = false)
    private LocalDateTime lastModifiedAt; // Data da ultima alteracao

    // Atualiza a data de ultima alteracao antes de persistir
    @PrePersist
    protected void onCreate() {
        this.lastModifiedAt = LocalDateTime.now();
    }

    // Atualiza a data de ultima alteracao antes de atualizar
    @PreUpdate
    protected void onUpdate() {
        this.lastModifiedAt = LocalDateTime.now();
    }
}
