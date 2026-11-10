package com.postech.techchallenge.infrastructure.exception;

// Lancada quando tentam cadastrar um e-mail que ja existe no sistema
public class EmailAlreadyExistsException extends RuntimeException {

    public EmailAlreadyExistsException(String email) {
        super("O e-mail '" + email + "' ja esta cadastrado no sistema");
    }
}
