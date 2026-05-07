package br.com.fiap.techchallenge.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface SpringDataOrdemServicoRepository extends JpaRepository<OrdemServicoEntity, UUID> {
}
