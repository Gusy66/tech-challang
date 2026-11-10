package com.postech.techchallenge.infrastructure.exception;

// Lancada quando tentam cadastrar um login que ja existe no sistema
public class LoginAlreadyExistsException extends RuntimeException {

    public LoginAlreadyExistsException(String login) {
        super("O login '" + login + "' ja esta cadastrado no sistema");
    }
}
