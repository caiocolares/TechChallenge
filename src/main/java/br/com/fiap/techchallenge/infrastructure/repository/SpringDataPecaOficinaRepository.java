package br.com.fiap.techchallenge.infrastructure.repository;

import br.com.fiap.techchallenge.domain.entity.PecaOficinaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SpringDataPecaOficinaRepository extends JpaRepository<PecaOficinaEntity, UUID> {
    Optional<PecaOficinaEntity> findByCodigoReferencia(String codigoReferencia);
}
