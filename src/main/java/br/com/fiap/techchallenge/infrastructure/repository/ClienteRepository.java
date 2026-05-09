package br.com.fiap.techchallenge.infrastructure.repository;

import br.com.fiap.techchallenge.domain.entity.ClienteEntity;
import br.com.fiap.techchallenge.domain.entity.CpfCnpj;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClienteRepository {
    ClienteEntity salvar(ClienteEntity cliente);
    Optional<ClienteEntity> buscarPorId(UUID id);
    Optional<ClienteEntity> buscarPorCpfCnpj(CpfCnpj cpfCnpj);
    List<ClienteEntity> buscaTodos();
}
