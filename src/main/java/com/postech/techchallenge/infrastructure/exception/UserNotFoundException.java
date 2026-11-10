package com.postech.techchallenge.infrastructure.exception;

// Lancada quando o usuario nao e encontrado pelo ID informado
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(Long id) {
        super("Usuario com ID " + id + " nao encontrado");
    }
}
