package br.com.fiap.techchallenge.infrastructure.repository;

import br.com.fiap.techchallenge.domain.entity.OrdemServicoEntity;

import java.util.Optional;
import java.util.UUID;

public interface OrdemServicoRepository {
    OrdemServicoEntity salvar(OrdemServicoEntity ordemServico);
    Optional<OrdemServicoEntity> buscarPorId(UUID id);
}
