package br.com.fiap.techchallenge.domain.entity;

import jakarta.persistence.Embeddable;
import java.math.BigDecimal;

@Embeddable
public class ItemPecaEmbeddable {
    private String nome;
    private BigDecimal precoUnitario;
    private int quantidade;

    protected ItemPecaEmbeddable() {}

    public ItemPecaEmbeddable(String nome, BigDecimal precoUnitario, int quantidade) {
        if (nome == null || nome.isBlank()) throw new IllegalArgumentException("Nome da peça é obrigatório");
        if (precoUnitario == null || precoUnitario.compareTo(BigDecimal.ZERO) < 0) throw new IllegalArgumentException("Preço inválido");
        if (quantidade <= 0) throw new IllegalArgumentException("Quantidade deve ser maior que zero");
        this.nome = nome;
        this.precoUnitario = precoUnitario;
        this.quantidade = quantidade;
    }

    public BigDecimal total() {
        return precoUnitario.multiply(BigDecimal.valueOf(quantidade));
    }

    public String getNome() { return nome; }
    public BigDecimal getPrecoUnitario() { return precoUnitario; }
    public int getQuantidade() { return quantidade; }
}
