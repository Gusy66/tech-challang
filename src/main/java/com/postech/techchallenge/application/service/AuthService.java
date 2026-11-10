package com.postech.techchallenge.application.service;

import com.postech.techchallenge.application.dto.LoginDTO;
import com.postech.techchallenge.application.dto.UserResponseDTO;
import com.postech.techchallenge.domain.entity.User;
import com.postech.techchallenge.domain.repository.UserRepository;
import com.postech.techchallenge.infrastructure.exception.InvalidCredentialsException;
import org.springframework.stereotype.Service;

// Servico de autenticacao que valida login e senha do usuario
@Service
public class AuthService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Valida as credenciais do usuario consultando o banco de dados
    public UserResponseDTO validateLogin(LoginDTO dto) {
        User user = userRepository.findByLogin(dto.login())
                .orElseThrow(InvalidCredentialsException::new);

        // Compara a senha informada com a armazenada no banco
        if (!user.getPassword().equals(dto.password())) {
            throw new InvalidCredentialsException();
        }

        return UserResponseDTO.fromEntity(user);
    }
}
