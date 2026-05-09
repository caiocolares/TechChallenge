package br.com.fiap.techchallenge.application.service;

import br.com.fiap.techchallenge.domain.entity.PecaOficinaEntity;
import br.com.fiap.techchallenge.infrastructure.repository.PecaOficinaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class PecaOficinaService {

    private final PecaOficinaRepository repository;

    public PecaOficinaService(PecaOficinaRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public PecaOficinaEntity cadastrar(String nome, String descricao, String codigoReferencia, BigDecimal precoUnitario, int quantidadeEstoque) {
        if (codigoReferencia != null && !codigoReferencia.isBlank()) {
            repository.buscarPorCodigoReferencia(codigoReferencia).ifPresent(p -> {
                throw new IllegalArgumentException("Já existe uma peça com o código de referência: " + codigoReferencia);
            });
        }
        return repository.salvar(new PecaOficinaEntity(nome, descricao, codigoReferencia, precoUnitario, quantidadeEstoque));
    }

    public PecaOficinaEntity buscarPorId(UUID id) {
        return buscarOuLancar(id);
    }

    public List<PecaOficinaEntity> listarTodas() {
        return repository.listarTodas();
    }

    @Transactional
    public void entradaEstoque(UUID id, int quantidade) {
        PecaOficinaEntity peca = buscarOuLancar(id);
        peca.entradaEstoque(quantidade);
        repository.salvar(peca);
    }

    @Transactional
    public void baixarEstoque(UUID id, int quantidade) {
        PecaOficinaEntity peca = buscarOuLancar(id);
        peca.baixarEstoque(quantidade);
        repository.salvar(peca);
    }

    private PecaOficinaEntity buscarOuLancar(UUID id) {
        return repository.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Peça não encontrada: " + id));
    }
}
