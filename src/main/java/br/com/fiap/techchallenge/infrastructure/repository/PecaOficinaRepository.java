package br.com.fiap.techchallenge.infrastructure.repository;

import br.com.fiap.techchallenge.domain.entity.PecaOficinaEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PecaOficinaRepository {
    PecaOficinaEntity salvar(PecaOficinaEntity peca);
    Optional<PecaOficinaEntity> buscarPorId(UUID id);
    Optional<PecaOficinaEntity> buscarPorCodigoReferencia(String codigoReferencia);
    List<PecaOficinaEntity> listarTodas();
}
