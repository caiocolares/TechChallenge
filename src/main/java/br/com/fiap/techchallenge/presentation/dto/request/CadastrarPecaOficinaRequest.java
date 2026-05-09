package br.com.fiap.techchallenge.presentation.dto.request;

import java.math.BigDecimal;

public record CadastrarPecaOficinaRequest(
        String nome,
        String descricao,
        String codigoReferencia,
        BigDecimal precoUnitario,
        int quantidadeEstoque
) {}
