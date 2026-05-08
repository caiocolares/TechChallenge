package br.com.fiap.techchallenge.application.port.in;

import br.com.fiap.techchallenge.domain.model.Cliente;
import br.com.fiap.techchallenge.domain.model.CpfCnpj;

public interface CadastrarClienteUseCase {
    Cliente cadastrar(CpfCnpj cpfCnpj, String nome, String email, String telefone);
}