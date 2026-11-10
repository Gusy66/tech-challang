package com.postech.techchallenge.presentation.controller;

import com.postech.techchallenge.application.dto.LoginDTO;
import com.postech.techchallenge.application.dto.UserResponseDTO;
import com.postech.techchallenge.application.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Controller REST para autenticacao de usuarios
@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "Autenticacao", description = "Endpoint para validacao de login do usuario")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @Operation(summary = "Validar login", description = "Verifica se login e senha sao validos e retorna os dados do usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login validado com sucesso"),
            @ApiResponse(responseCode = "401", description = "Credenciais invalidas",
                    content = @Content(schema = @Schema(implementation = ProblemDetail.class)))
    })
    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO> login(@Valid @RequestBody LoginDTO dto) {
        UserResponseDTO response = authService.validateLogin(dto);
        return ResponseEntity.ok(response);
    }
}
