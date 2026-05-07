package br.com.fiap.techchallenge.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class OrdemServico {
    private final UUID id;
    private final CpfCnpj clienteId;
    private final Veiculo veiculo;
    private final List<ItemServico> servicos;
    private final List<ItemPeca> pecas;
    private StatusOrdemServico status;
    private final LocalDateTime dataCriacao;
    private BigDecimal valorTotal;

    public enum StatusOrdemServico {
        RECEBIDA,
        EM_DIAGNOSTICO,
        AGUARDANDO_APROVACAO,
        EM_EXECUCAO,
        FINALIZADA,
        ENTREGUE,
        CANCELADA
    }

    public OrdemServico(CpfCnpj clienteId, Veiculo veiculo) {
        this.id = UUID.randomUUID();
        this.clienteId = clienteId;
        this.veiculo = veiculo;
        this.servicos = new ArrayList<>();
        this.pecas = new ArrayList<>();
        this.status = StatusOrdemServico.RECEBIDA;
        this.dataCriacao = LocalDateTime.now();
        this.valorTotal = BigDecimal.ZERO;
    }

    private OrdemServico(UUID id, CpfCnpj clienteId, Veiculo veiculo,
            List<ItemServico> servicos, List<ItemPeca> pecas,
            StatusOrdemServico status, LocalDateTime dataCriacao, BigDecimal valorTotal) {
        this.id = id;
        this.clienteId = clienteId;
        this.veiculo = veiculo;
        this.servicos = new ArrayList<>(servicos);
        this.pecas = new ArrayList<>(pecas);
        this.status = status;
        this.dataCriacao = dataCriacao;
        this.valorTotal = valorTotal;
    }

    public static OrdemServico reconstituir(UUID id, CpfCnpj clienteId, Veiculo veiculo,
            List<ItemServico> servicos, List<ItemPeca> pecas,
            StatusOrdemServico status, LocalDateTime dataCriacao, BigDecimal valorTotal) {
        return new OrdemServico(id, clienteId, veiculo, servicos, pecas, status, dataCriacao, valorTotal);
    }

    public void iniciarDiagnostico() {
        if (this.status != StatusOrdemServico.RECEBIDA) {
            throw new IllegalStateException("OS deve estar no estado RECEBIDA para iniciar o diagnóstico");
        }
        this.status = StatusOrdemServico.EM_DIAGNOSTICO;
    }

    public void adicionarServico(ItemServico servico) {
        if (this.status != StatusOrdemServico.EM_DIAGNOSTICO) {
            throw new IllegalStateException("Serviços só podem ser adicionados durante o diagnóstico");
        }
        this.servicos.add(servico);
        calcularTotal();
    }

    public void adicionarPeca(ItemPeca peca) {
        if (this.status != StatusOrdemServico.EM_DIAGNOSTICO) {
            throw new IllegalStateException("Peças só podem ser adicionadas durante o diagnóstico");
        }
        this.pecas.add(peca);
        calcularTotal();
    }

    private void calcularTotal() {
        BigDecimal totalServicos = servicos.stream()
                .map(ItemServico::precoUnitario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal totalPecas = pecas.stream()
                .map(ItemPeca::total)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        this.valorTotal = totalServicos.add(totalPecas);
    }

    public void enviarParaAprovacao() {
        if (this.status != StatusOrdemServico.EM_DIAGNOSTICO) {
            throw new IllegalStateException("OS deve estar em diagnóstico para ser enviada para aprovação");
        }
        if (this.servicos.isEmpty()) {
            throw new IllegalStateException("OS deve ter pelo menos um serviço para ser enviada");
        }
        this.status = StatusOrdemServico.AGUARDANDO_APROVACAO;
    }

    public void aprovar() {
        if (this.status != StatusOrdemServico.AGUARDANDO_APROVACAO) {
            throw new IllegalStateException("Somente OS aguardando aprovação podem ser aprovadas");
        }
        this.status = StatusOrdemServico.EM_EXECUCAO;
    }

    public void finalizarExecucao() {
        if (this.status != StatusOrdemServico.EM_EXECUCAO) {
            throw new IllegalStateException("Somente OS em execução podem ser finalizadas");
        }
        this.status = StatusOrdemServico.FINALIZADA;
    }

    public void entregarAoCliente() {
        if (this.status != StatusOrdemServico.FINALIZADA) {
            throw new IllegalStateException("Somente OS finalizadas podem ser entregues");
        }
        this.status = StatusOrdemServico.ENTREGUE;
    }

    // Getters
    public UUID getId() { return id; }
    public CpfCnpj getClienteId() { return clienteId; }
    public Veiculo getVeiculo() { return veiculo; }
    public List<ItemServico> getServicos() { return Collections.unmodifiableList(servicos); }
    public List<ItemPeca> getPecas() { return Collections.unmodifiableList(pecas); }
    public StatusOrdemServico getStatus() { return status; }
    public LocalDateTime getDataCriacao() { return dataCriacao; }
    public BigDecimal getValorTotal() { return valorTotal; }
}
