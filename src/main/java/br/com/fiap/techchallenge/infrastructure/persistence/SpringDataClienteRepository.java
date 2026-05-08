package br.com.fiap.techchallenge.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SpringDataClienteRepository extends JpaRepository<ClienteEntity, UUID> {
    Optional<ClienteEntity> findByCpfCnpj(String cpfCnpj);
}