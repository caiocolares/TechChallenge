package br.com.fiap.techchallenge.presentation.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record ClienteResponse(
        UUID id,
        String cpfCnpj,
        String nome,
        String email,
        String telefone,
        LocalDateTime dataCadastro
) {}
