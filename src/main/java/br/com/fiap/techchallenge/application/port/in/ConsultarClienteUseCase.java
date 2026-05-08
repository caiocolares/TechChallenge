package br.com.fiap.techchallenge.application.port.in;

import br.com.fiap.techchallenge.domain.model.Cliente;
import br.com.fiap.techchallenge.domain.model.CpfCnpj;

import java.util.UUID;

public interface ConsultarClienteUseCase {
    Cliente buscarPorId(UUID id);
    Cliente buscarPorCpfCnpj(CpfCnpj cpfCnpj);
}