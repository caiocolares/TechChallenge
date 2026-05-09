package br.com.fiap.techchallenge.infrastructure.repository;

import br.com.fiap.techchallenge.domain.entity.OrdemServicoEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class JpaOrdemServicoRepository implements OrdemServicoRepository {

    private final SpringDataOrdemServicoRepository springRepository;

    public JpaOrdemServicoRepository(SpringDataOrdemServicoRepository springRepository) {
        this.springRepository = springRepository;
    }

    @Override
    public OrdemServicoEntity salvar(OrdemServicoEntity os) {
        return springRepository.save(os);
    }

    @Override
    public Optional<OrdemServicoEntity> buscarPorId(UUID id) {
        return springRepository.findById(id);
    }
}
