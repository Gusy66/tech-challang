package com.postech.techchallenge.domain.repository;

import com.postech.techchallenge.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// Repositorio JPA para operacoes de persistencia do usuario
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Busca usuarios cujo nome contenha o termo (case-insensitive)
    List<User> findByNameContainingIgnoreCase(String name);

    // Busca usuario pelo login (para autenticacao)
    Optional<User> findByLogin(String login);

    // Verifica se ja existe um usuario com o e-mail informado
    boolean existsByEmail(String email);

    // Verifica se ja existe um usuario com o login informado
    boolean existsByLogin(String login);
}
