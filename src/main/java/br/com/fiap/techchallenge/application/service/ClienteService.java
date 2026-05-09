package br.com.fiap.techchallenge.application.service;

import br.com.fiap.techchallenge.domain.entity.ClienteEntity;
import br.com.fiap.techchallenge.domain.entity.CpfCnpj;
import br.com.fiap.techchallenge.infrastructure.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class ClienteService {

    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public ClienteEntity cadastrar(CpfCnpj cpfCnpj, String nome, String email, String telefone) {
        repository.buscarPorCpfCnpj(cpfCnpj).ifPresent(c -> {
            throw new IllegalArgumentException("Já existe um cliente com o CPF/CNPJ informado");
        });
        return repository.salvar(new ClienteEntity(cpfCnpj, nome, email, telefone));
    }

    public ClienteEntity buscarPorId(UUID id) {
        return repository.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado: " + id));
    }

    public ClienteEntity buscarPorCpfCnpj(CpfCnpj cpfCnpj) {
        return repository.buscarPorCpfCnpj(cpfCnpj)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado para o CPF/CNPJ informado"));
    }

    public List<ClienteEntity> buscarTodos() {
        return repository.buscaTodos();
    }
}
