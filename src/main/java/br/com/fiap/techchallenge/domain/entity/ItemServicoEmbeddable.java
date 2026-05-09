package br.com.fiap.techchallenge.domain.entity;

import jakarta.persistence.Embeddable;
import java.math.BigDecimal;

@Embeddable
public class ItemServicoEmbeddable {
    private String descricao;
    private BigDecimal precoUnitario;

    protected ItemServicoEmbeddable() {}

    public ItemServicoEmbeddable(String descricao, BigDecimal precoUnitario) {
        if (descricao == null || descricao.isBlank()) throw new IllegalArgumentException("Descrição do serviço é obrigatória");
        if (precoUnitario == null || precoUnitario.compareTo(BigDecimal.ZERO) < 0) throw new IllegalArgumentException("Preço inválido");
        this.descricao = descricao;
        this.precoUnitario = precoUnitario;
    }

    public String getDescricao() { return descricao; }
    public BigDecimal getPrecoUnitario() { return precoUnitario; }
}
