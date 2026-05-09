package br.com.fiap.techchallenge.presentation.dto;

import java.math.BigDecimal;

public record AdicionarPecaRequest(
        String nome,
        BigDecimal precoUnitario,
        int quantidade
) {}
