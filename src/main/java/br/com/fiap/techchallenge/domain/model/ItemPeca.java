package br.com.fiap.techchallenge.domain.model;

import java.math.BigDecimal;

public record ItemPeca(String nome, BigDecimal precoUnitario, int quantidade) {
    public ItemPeca {
        if (nome == null || nome.isBlank()) throw new IllegalArgumentException("Nome da peça é obrigatório");
        if (precoUnitario == null || precoUnitario.compareTo(BigDecimal.ZERO) < 0) throw new IllegalArgumentException("Preço inválido");
        if (quantidade <= 0) throw new IllegalArgumentException("Quantidade deve ser maior que zero");
    }

    public BigDecimal total() {
        return precoUnitario.multiply(BigDecimal.valueOf(quantidade));
    }
}
