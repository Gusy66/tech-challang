package com.postech.techchallenge.infrastructure.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

// Handler global de excecoes que retorna respostas no padrao ProblemDetail (RFC 7807)
@RestControllerAdvice
public class GlobalExceptionHandler {

    // Trata erro de usuario nao encontrado (404)
    @ExceptionHandler(UserNotFoundException.class)
    public ProblemDetail handleUserNotFound(UserNotFoundException ex) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        problem.setTitle("Usuario nao encontrado");
        problem.setType(URI.create("https://api.techchallenge.com/errors/user-not-found"));
        problem.setProperty("timestamp", Instant.now());
        return problem;
    }

    // Trata erro de e-mail duplicado (409)
    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ProblemDetail handleEmailAlreadyExists(EmailAlreadyExistsException ex) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, ex.getMessage());
        problem.setTitle("E-mail ja cadastrado");
        problem.setType(URI.create("https://api.techchallenge.com/errors/email-already-exists"));
        problem.setProperty("timestamp", Instant.now());
        return problem;
    }

    // Trata erro de login duplicado (409)
    @ExceptionHandler(LoginAlreadyExistsException.class)
    public ProblemDetail handleLoginAlreadyExists(LoginAlreadyExistsException ex) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, ex.getMessage());
        problem.setTitle("Login ja cadastrado");
        problem.setType(URI.create("https://api.techchallenge.com/errors/login-already-exists"));
        problem.setProperty("timestamp", Instant.now());
        return problem;
    }

    // Trata erro de senha incorreta na troca de senha (400)
    @ExceptionHandler(InvalidPasswordException.class)
    public ProblemDetail handleInvalidPassword(InvalidPasswordException ex) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
        problem.setTitle("Senha incorreta");
        problem.setType(URI.create("https://api.techchallenge.com/errors/invalid-password"));
        problem.setProperty("timestamp", Instant.now());
        return problem;
    }

    // Trata erro de credenciais invalidas no login (401)
    @ExceptionHandler(InvalidCredentialsException.class)
    public ProblemDetail handleInvalidCredentials(InvalidCredentialsException ex) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED, ex.getMessage());
        problem.setTitle("Credenciais invalidas");
        problem.setType(URI.create("https://api.techchallenge.com/errors/invalid-credentials"));
        problem.setProperty("timestamp", Instant.now());
        return problem;
    }

    // Trata erros de validacao dos campos (Bean Validation) (400)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidation(MethodArgumentNotValidException ex) {
        Map<String, String> fieldErrors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                fieldErrors.put(error.getField(), error.getDefaultMessage())
        );

        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST, "Erro de validacao nos campos informados");
        problem.setTitle("Erro de validacao");
        problem.setType(URI.create("https://api.techchallenge.com/errors/validation-error"));
        problem.setProperty("timestamp", Instant.now());
        problem.setProperty("fieldErrors", fieldErrors);
        return problem;
    }
}
