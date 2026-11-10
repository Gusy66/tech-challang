package com.postech.techchallenge.infrastructure.exception;

// Lancada quando a senha informada esta incorreta (login ou troca de senha)
public class InvalidPasswordException extends RuntimeException {

    public InvalidPasswordException() {
        super("Senha atual incorreta");
    }
}
