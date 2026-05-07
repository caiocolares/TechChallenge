package br.com.fiap.techchallenge.application.usecase;

import br.com.fiap.techchallenge.application.port.in.AdicionarItemUseCase;
import br.com.fiap.techchallenge.application.port.in.AtualizarStatusOrdemServicoUseCase;
import br.com.fiap.techchallenge.application.port.in.ConsultarOrdemServicoUseCase;
import br.com.fiap.techchallenge.application.port.in.CriarOrdemServicoUseCase;
import br.com.fiap.techchallenge.domain.model.*;
import br.com.fiap.techchallenge.domain.repository.OrdemServicoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class GestaoOrdemServicoUseCase implements
        CriarOrdemServicoUseCase,
        ConsultarOrdemServicoUseCase,
        AtualizarStatusOrdemServicoUseCase,
        AdicionarItemUseCase {

    private final OrdemServicoRepository repository;

    public GestaoOrdemServicoUseCase(OrdemServicoRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public OrdemServico criarOS(CpfCnpj clienteId, Veiculo veiculo) {
        OrdemServico os = new OrdemServico(clienteId, veiculo);
        return repository.salvar(os);
    }

    @Override
    @Transactional
    public void adicionarServico(UUID osId, ItemServico servico) {
        OrdemServico os = buscarOuLancar(osId);
        os.adicionarServico(servico);
        repository.salvar(os);
    }

    @Override
    @Transactional
    public void adicionarPeca(UUID osId, ItemPeca peca) {
        OrdemServico os = buscarOuLancar(osId);
        os.adicionarPeca(peca);
        repository.salvar(os);
    }

    @Override
    @Transactional
    public void iniciarDiagnostico(UUID osId) {
        OrdemServico os = buscarOuLancar(osId);
        os.iniciarDiagnostico();
        repository.salvar(os);
    }

    @Override
    @Transactional
    public void enviarOrcamento(UUID osId) {
        OrdemServico os = buscarOuLancar(osId);
        os.enviarParaAprovacao();
        repository.salvar(os);
    }

    @Override
    @Transactional
    public void aprovarOS(UUID osId) {
        OrdemServico os = buscarOuLancar(osId);
        os.aprovar();
        repository.salvar(os);
    }

    @Override
    @Transactional
    public void finalizarExecucao(UUID osId) {
        OrdemServico os = buscarOuLancar(osId);
        os.finalizarExecucao();
        repository.salvar(os);
    }

    @Override
    @Transactional
    public void entregarOS(UUID osId) {
        OrdemServico os = buscarOuLancar(osId);
        os.entregarAoCliente();
        repository.salvar(os);
    }

    @Override
    public OrdemServico consultarStatus(UUID osId) {
        return buscarOuLancar(osId);
    }

    private OrdemServico buscarOuLancar(UUID osId) {
        return repository.buscarPorId(osId)
                .orElseThrow(() -> new IllegalArgumentException("OS não encontrada: " + osId));
    }
}
