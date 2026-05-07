package br.com.fiap.techchallenge.domain.model;

import java.math.BigDecimal;

public record ItemServico(String descricao, BigDecimal precoUnitario) {
    public ItemServico {
        if (descricao == null || descricao.isBlank()) throw new IllegalArgumentException("Descrição do serviço é obrigatória");
        if (precoUnitario == null || precoUnitario.compareTo(BigDecimal.ZERO) < 0) throw new IllegalArgumentException("Preço inválido");
    }
}
