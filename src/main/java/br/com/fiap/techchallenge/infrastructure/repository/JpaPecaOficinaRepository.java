package br.com.fiap.techchallenge.infrastructure.repository;

import br.com.fiap.techchallenge.domain.entity.PecaOficinaEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class JpaPecaOficinaRepository implements PecaOficinaRepository {

    private final SpringDataPecaOficinaRepository springRepository;

    public JpaPecaOficinaRepository(SpringDataPecaOficinaRepository springRepository) {
        this.springRepository = springRepository;
    }

    @Override
    public PecaOficinaEntity salvar(PecaOficinaEntity peca) {
        return springRepository.save(peca);
    }

    @Override
    public Optional<PecaOficinaEntity> buscarPorId(UUID id) {
        return springRepository.findById(id);
    }

    @Override
    public Optional<PecaOficinaEntity> buscarPorCodigoReferencia(String codigoReferencia) {
        return springRepository.findByCodigoReferencia(codigoReferencia);
    }

    @Override
    public List<PecaOficinaEntity> listarTodas() {
        return springRepository.findAll();
    }
}
