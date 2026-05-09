package br.com.fiap.techchallenge.presentation.dto;

import br.com.fiap.techchallenge.domain.entity.ClienteEntity;

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
    public static ClienteResponse de(ClienteEntity entity) {
        return new ClienteResponse(
                entity.getId(),
                entity.getCpfCnpj(),
                entity.getNome(),
                entity.getEmail(),
                entity.getTelefone(),
                entity.getDataCadastro()
        );
    }
}
