package br.com.fiap.techchallenge.infrastructure.repository;

import br.com.fiap.techchallenge.domain.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SpringDataClienteRepository extends JpaRepository<ClienteEntity, UUID> {
    Optional<ClienteEntity> findByCpfCnpj(String cpfCnpj);
}
