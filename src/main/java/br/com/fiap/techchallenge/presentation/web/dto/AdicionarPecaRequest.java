package br.com.fiap.techchallenge.presentation.web.dto;

import java.math.BigDecimal;

public record AdicionarPecaRequest(
        String nome,
        BigDecimal precoUnitario,
        int quantidade
) {}