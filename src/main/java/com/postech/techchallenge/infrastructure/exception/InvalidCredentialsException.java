package com.postech.techchallenge.infrastructure.exception;

// Lancada quando login ou senha sao invalidos na autenticacao
public class InvalidCredentialsException extends RuntimeException {

    public InvalidCredentialsException() {
        super("Login ou senha invalidos");
    }
}
