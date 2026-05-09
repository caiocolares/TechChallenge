package br.com.fiap.techchallenge.presentation.dto.request;

import java.math.BigDecimal;

public record AdicionarPecaRequest(
        String nome,
        BigDecimal precoUnitario,
        int quantidade
) {}
