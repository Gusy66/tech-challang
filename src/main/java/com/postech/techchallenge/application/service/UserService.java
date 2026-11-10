package com.postech.techchallenge.application.service;

import com.postech.techchallenge.application.dto.*;
import com.postech.techchallenge.domain.entity.Address;
import com.postech.techchallenge.domain.entity.User;
import com.postech.techchallenge.domain.repository.UserRepository;
import com.postech.techchallenge.infrastructure.exception.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// Servico que contem a logica de negocio para operacoes de usuario
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Cria um novo usuario validando unicidade de e-mail e login
    @Transactional
    public UserResponseDTO createUser(CreateUserDTO dto) {
        if (userRepository.existsByEmail(dto.email())) {
            throw new EmailAlreadyExistsException(dto.email());
        }
        if (userRepository.existsByLogin(dto.login())) {
            throw new LoginAlreadyExistsException(dto.login());
        }

        User user = new User();
        user.setName(dto.name());
        user.setEmail(dto.email());
        user.setLogin(dto.login());
        user.setPassword(dto.password());
        user.setUserType(dto.userType());
        user.setAddress(toAddress(dto.address()));

        User saved = userRepository.save(user);
        return UserResponseDTO.fromEntity(saved);
    }

    // Atualiza dados do usuario (exceto senha) - endpoint distinto
    @Transactional
    public UserResponseDTO updateUser(Long id, UpdateUserDTO dto) {
        User user = findUserById(id);

        // Verifica se o novo e-mail ja pertence a outro usuario
        if (!user.getEmail().equals(dto.email()) && userRepository.existsByEmail(dto.email())) {
            throw new EmailAlreadyExistsException(dto.email());
        }

        user.setName(dto.name());
        user.setEmail(dto.email());
        user.setAddress(toAddress(dto.address()));

        User saved = userRepository.save(user);
        return UserResponseDTO.fromEntity(saved);
    }

    // Altera a senha do usuario validando a senha atual - endpoint separado
    @Transactional
    public void changePassword(Long id, ChangePasswordDTO dto) {
        User user = findUserById(id);

        // Verifica se a senha atual esta correta
        if (!user.getPassword().equals(dto.currentPassword())) {
            throw new InvalidPasswordException();
        }

        user.setPassword(dto.newPassword());
        userRepository.save(user);
    }

    // Remove um usuario pelo ID
    @Transactional
    public void deleteUser(Long id) {
        User user = findUserById(id);
        userRepository.delete(user);
    }

    // Busca um usuario pelo ID
    public UserResponseDTO getUserById(Long id) {
        User user = findUserById(id);
        return UserResponseDTO.fromEntity(user);
    }

    // Lista todos os usuarios
    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserResponseDTO::fromEntity)
                .toList();
    }

    // Busca usuarios pelo nome (case-insensitive, busca parcial)
    public List<UserResponseDTO> searchUsersByName(String name) {
        return userRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(UserResponseDTO::fromEntity)
                .toList();
    }

    // Metodo auxiliar que busca o usuario ou lanca excecao
    private User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    // Converte AddressDTO para a entidade Address
    private Address toAddress(AddressDTO dto) {
        return new Address(dto.street(), dto.number(), dto.city(), dto.state(), dto.zipCode());
    }
}
