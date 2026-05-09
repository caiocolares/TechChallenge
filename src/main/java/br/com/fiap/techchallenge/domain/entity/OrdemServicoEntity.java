package br.com.fiap.techchallenge.domain.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
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

    protected OrdemServicoEntity() {}

    public OrdemServicoEntity(CpfCnpj clienteId, VeiculoEmbeddable veiculo) {
        this.id = UUID.randomUUID();
        this.clienteCpfCnpj = clienteId.valor();
        this.veiculo = veiculo;
        this.servicos = new ArrayList<>();
        this.pecas = new ArrayList<>();
        this.status = StatusOrdemServico.RECEBIDA;
        this.dataCriacao = LocalDateTime.now();
        this.valorTotal = BigDecimal.ZERO;
    }

    public void iniciarDiagnostico() {
        if (this.status != StatusOrdemServico.RECEBIDA)
            throw new IllegalStateException("OS deve estar no estado RECEBIDA para iniciar o diagnóstico");
        this.status = StatusOrdemServico.EM_DIAGNOSTICO;
    }

    public void adicionarServico(ItemServicoEmbeddable servico) {
        if (this.status != StatusOrdemServico.EM_DIAGNOSTICO)
            throw new IllegalStateException("Serviços só podem ser adicionados durante o diagnóstico");
        this.servicos.add(servico);
        calcularTotal();
    }

    public void adicionarPeca(ItemPecaEmbeddable peca) {
        if (this.status != StatusOrdemServico.EM_DIAGNOSTICO)
            throw new IllegalStateException("Peças só podem ser adicionadas durante o diagnóstico");
        this.pecas.add(peca);
        calcularTotal();
    }

    public void enviarParaAprovacao() {
        if (this.status != StatusOrdemServico.EM_DIAGNOSTICO)
            throw new IllegalStateException("OS deve estar em diagnóstico para ser enviada para aprovação");
        if (this.servicos.isEmpty())
            throw new IllegalStateException("OS deve ter pelo menos um serviço para ser enviada");
        this.status = StatusOrdemServico.AGUARDANDO_APROVACAO;
    }

    public void aprovar() {
        if (this.status != StatusOrdemServico.AGUARDANDO_APROVACAO)
            throw new IllegalStateException("Somente OS aguardando aprovação podem ser aprovadas");
        this.status = StatusOrdemServico.EM_EXECUCAO;
    }

    public void finalizarExecucao() {
        if (this.status != StatusOrdemServico.EM_EXECUCAO)
            throw new IllegalStateException("Somente OS em execução podem ser finalizadas");
        this.status = StatusOrdemServico.FINALIZADA;
    }

    public void entregarAoCliente() {
        if (this.status != StatusOrdemServico.FINALIZADA)
            throw new IllegalStateException("Somente OS finalizadas podem ser entregues");
        this.status = StatusOrdemServico.ENTREGUE;
    }

    private void calcularTotal() {
        BigDecimal totalServicos = servicos.stream()
                .map(ItemServicoEmbeddable::getPrecoUnitario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal totalPecas = pecas.stream()
                .map(ItemPecaEmbeddable::total)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        this.valorTotal = totalServicos.add(totalPecas);
    }

    public UUID getId() { return id; }
    public String getClienteCpfCnpj() { return clienteCpfCnpj; }
    public VeiculoEmbeddable getVeiculo() { return veiculo; }
    public List<ItemServicoEmbeddable> getServicos() { return servicos != null ? Collections.unmodifiableList(servicos) : Collections.emptyList(); }
    public List<ItemPecaEmbeddable> getPecas() { return pecas != null ? Collections.unmodifiableList(pecas) : Collections.emptyList(); }
    public StatusOrdemServico getStatus() { return status; }
    public LocalDateTime getDataCriacao() { return dataCriacao; }
    public BigDecimal getValorTotal() { return valorTotal; }
}
