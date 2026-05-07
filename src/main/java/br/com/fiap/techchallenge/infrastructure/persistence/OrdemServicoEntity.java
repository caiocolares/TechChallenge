package br.com.fiap.techchallenge.infrastructure.persistence;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "ordens_servico")
public class OrdemServicoEntity {

    @Id
    private UUID id;

    @Column(nullable = false)
    private String clienteCpfCnpj;

    @Embedded
    private VeiculoEmbeddable veiculo;

    @ElementCollection
    @CollectionTable(name = "os_servicos", joinColumns = @JoinColumn(name = "os_id"))
    private List<ItemServicoEmbeddable> servicos;

    @ElementCollection
    @CollectionTable(name = "os_pecas", joinColumns = @JoinColumn(name = "os_id"))
    private List<ItemPecaEmbeddable> pecas;

    @Enumerated(EnumType.STRING)
    private StatusOrdemServico status;

    private LocalDateTime dataCriacao;
    private BigDecimal valorTotal;

    public enum StatusOrdemServico {
        RECEBIDA, EM_DIAGNOSTICO, AGUARDANDO_APROVACAO, EM_EXECUCAO, FINALIZADA, ENTREGUE, CANCELADA
    }

    // Getters e Setters simplificados para brevidade
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getClienteCpfCnpj() { return clienteCpfCnpj; }
    public void setClienteCpfCnpj(String clienteCpfCnpj) { this.clienteCpfCnpj = clienteCpfCnpj; }
    public VeiculoEmbeddable getVeiculo() { return veiculo; }
    public void setVeiculo(VeiculoEmbeddable veiculo) { this.veiculo = veiculo; }
    public List<ItemServicoEmbeddable> getServicos() { return servicos; }
    public void setServicos(List<ItemServicoEmbeddable> servicos) { this.servicos = servicos; }
    public List<ItemPecaEmbeddable> getPecas() { return pecas; }
    public void setPecas(List<ItemPecaEmbeddable> pecas) { this.pecas = pecas; }
    public StatusOrdemServico getStatus() { return status; }
    public void setStatus(StatusOrdemServico status) { this.status = status; }
    public LocalDateTime getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(LocalDateTime dataCriacao) { this.dataCriacao = dataCriacao; }
    public BigDecimal getValorTotal() { return valorTotal; }
    public void setValorTotal(BigDecimal valorTotal) { this.valorTotal = valorTotal; }
}

@Embeddable
class VeiculoEmbeddable {
    private String placa;
    private String marca;
    private String modelo;
    private int ano;
    // Getters e Setters...
    public String getPlaca() { return placa; }
    public void setPlaca(String placa) { this.placa = placa; }
    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }
    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }
    public int getAno() { return ano; }
    public void setAno(int ano) { this.ano = ano; }
}

@Embeddable
class ItemServicoEmbeddable {
    private String descricao;
    private BigDecimal precoUnitario;
    // Getters e Setters...
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public BigDecimal getPrecoUnitario() { return precoUnitario; }
    public void setPrecoUnitario(BigDecimal precoUnitario) { this.precoUnitario = precoUnitario; }
}

@Embeddable
class ItemPecaEmbeddable {
    private String nome;
    private BigDecimal precoUnitario;
    private int quantidade;
    // Getters e Setters...
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public BigDecimal getPrecoUnitario() { return precoUnitario; }
    public void setPrecoUnitario(BigDecimal precoUnitario) { this.precoUnitario = precoUnitario; }
    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }
}
