package br.com.fiap.techchallenge.application.service;

import br.com.fiap.techchallenge.domain.entity.*;
import br.com.fiap.techchallenge.infrastructure.repository.OrdemServicoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class OrdemServicoService {

    private final OrdemServicoRepository repository;

    public OrdemServicoService(OrdemServicoRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public OrdemServicoEntity criarOS(CpfCnpj clienteId, VeiculoEmbeddable veiculo) {
        return repository.salvar(new OrdemServicoEntity(clienteId, veiculo));
    }

    @Transactional
    public void adicionarServico(UUID osId, ItemServicoEmbeddable servico) {
        OrdemServicoEntity os = buscarOuLancar(osId);
        os.adicionarServico(servico);
        repository.salvar(os);
    }

    @Transactional
    public void adicionarPeca(UUID osId, ItemPecaEmbeddable peca) {
        OrdemServicoEntity os = buscarOuLancar(osId);
        os.adicionarPeca(peca);
        repository.salvar(os);
    }

    @Transactional
    public void iniciarDiagnostico(UUID osId) {
        OrdemServicoEntity os = buscarOuLancar(osId);
        os.iniciarDiagnostico();
        repository.salvar(os);
    }

    @Transactional
    public void enviarOrcamento(UUID osId) {
        OrdemServicoEntity os = buscarOuLancar(osId);
        os.enviarParaAprovacao();
        repository.salvar(os);
    }

    @Transactional
    public void aprovarOS(UUID osId) {
        OrdemServicoEntity os = buscarOuLancar(osId);
        os.aprovar();
        repository.salvar(os);
    }

    @Transactional
    public void finalizarExecucao(UUID osId) {
        OrdemServicoEntity os = buscarOuLancar(osId);
        os.finalizarExecucao();
        repository.salvar(os);
    }

    @Transactional
    public void entregarOS(UUID osId) {
        OrdemServicoEntity os = buscarOuLancar(osId);
        os.entregarAoCliente();
        repository.salvar(os);
    }

    public OrdemServicoEntity consultarStatus(UUID osId) {
        return buscarOuLancar(osId);
    }

    private OrdemServicoEntity buscarOuLancar(UUID osId) {
        return repository.buscarPorId(osId)
                .orElseThrow(() -> new IllegalArgumentException("OS não encontrada: " + osId));
    }
}
