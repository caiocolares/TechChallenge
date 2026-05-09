package br.com.fiap.techchallenge.presentation.dto;

import java.math.BigDecimal;

public record AdicionarServicoRequest(
        String descricao,
        BigDecimal precoUnitario
) {}
