package br.com.fiap.techchallenge.application.usecase;

import br.com.fiap.techchallenge.application.port.in.CadastrarClienteUseCase;
import br.com.fiap.techchallenge.application.port.in.ConsultarClienteUseCase;
import br.com.fiap.techchallenge.domain.model.Cliente;
import br.com.fiap.techchallenge.domain.model.CpfCnpj;
import br.com.fiap.techchallenge.domain.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class GestaoClienteUseCase implements CadastrarClienteUseCase, ConsultarClienteUseCase {

    private final ClienteRepository repository;

    public GestaoClienteUseCase(ClienteRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public Cliente cadastrar(CpfCnpj cpfCnpj, String nome, String email, String telefone) {
        repository.buscarPorCpfCnpj(cpfCnpj).ifPresent(c -> {
            throw new IllegalArgumentException("Já existe um cliente com o CPF/CNPJ informado");
        });
        Cliente cliente = new Cliente(cpfCnpj, nome, email, telefone);
        return repository.salvar(cliente);
    }

    @Override
    public Cliente buscarPorId(UUID id) {
        return repository.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado: " + id));
    }

    @Override
    public Cliente buscarPorCpfCnpj(CpfCnpj cpfCnpj) {
        return repository.buscarPorCpfCnpj(cpfCnpj)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado para o CPF/CNPJ informado"));
    }
}