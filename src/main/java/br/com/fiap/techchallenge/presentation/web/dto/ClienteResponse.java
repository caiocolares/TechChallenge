package br.com.fiap.techchallenge.presentation.web.dto;

import br.com.fiap.techchallenge.domain.model.Cliente;

import java.time.LocalDateTime;
import java.util.UUID;

public record ClienteResponse(
        UUID id,
        String cpfCnpj,
        String nome,
        String email,
        String telefone,
        LocalDateTime dataCadastro
) {
    public static ClienteResponse de(Cliente cliente) {
        return new ClienteResponse(
                cliente.getId(),
                cliente.getCpfCnpj().valor(),
                cliente.getNome(),
                cliente.getEmail(),
                cliente.getTelefone(),
                cliente.getDataCadastro()
        );
    }
}