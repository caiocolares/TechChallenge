package br.com.fiap.techchallenge.domain.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "pecas_oficina")
public class PecaOficinaEntity {

    @Id
    private UUID id;

    @Column(nullable = false)
    private String nome;

    private String descricao;

    @Column(unique = true)
    private String codigoReferencia;

    @Column(nullable = false)
    private BigDecimal precoUnitario;

    @Column(nullable = false)
    private int quantidadeEstoque;

    protected PecaOficinaEntity() {}

    public PecaOficinaEntity(String nome, String descricao, String codigoReferencia, BigDecimal precoUnitario, int quantidadeEstoque) {
        if (nome == null || nome.isBlank()) throw new IllegalArgumentException("Nome da peça é obrigatório");
        if (precoUnitario == null || precoUnitario.compareTo(BigDecimal.ZERO) < 0) throw new IllegalArgumentException("Preço unitário não pode ser negativo");
        if (quantidadeEstoque < 0) throw new IllegalArgumentException("Quantidade em estoque não pode ser negativa");
        this.id = UUID.randomUUID();
        this.nome = nome;
        this.descricao = descricao;
        this.codigoReferencia = codigoReferencia;
        this.precoUnitario = precoUnitario;
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public void atualizarDados(String nome, String descricao, String codigoReferencia, BigDecimal precoUnitario) {
        if (nome == null || nome.isBlank()) throw new IllegalArgumentException("Nome da peça é obrigatório");
        if (precoUnitario == null || precoUnitario.compareTo(BigDecimal.ZERO) < 0) throw new IllegalArgumentException("Preço unitário não pode ser negativo");
        this.nome = nome;
        this.descricao = descricao;
        this.codigoReferencia = codigoReferencia;
        this.precoUnitario = precoUnitario;
    }

    public void entradaEstoque(int quantidade) {
        if (quantidade <= 0) throw new IllegalArgumentException("Quantidade de entrada deve ser maior que zero");
        this.quantidadeEstoque += quantidade;
    }

    public void baixarEstoque(int quantidade) {
        if (quantidade <= 0) throw new IllegalArgumentException("Quantidade de baixa deve ser maior que zero");
        if (quantidade > this.quantidadeEstoque) throw new IllegalStateException("Estoque insuficiente para a peça: " + this.nome);
        this.quantidadeEstoque -= quantidade;
    }

    public UUID getId() { return id; }
    public String getNome() { return nome; }
    public String getDescricao() { return descricao; }
    public String getCodigoReferencia() { return codigoReferencia; }
    public BigDecimal getPrecoUnitario() { return precoUnitario; }
    public int getQuantidadeEstoque() { return quantidadeEstoque; }
}
