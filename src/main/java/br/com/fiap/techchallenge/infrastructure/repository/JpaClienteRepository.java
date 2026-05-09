package br.com.fiap.techchallenge.infrastructure.repository;

import br.com.fiap.techchallenge.domain.entity.ClienteEntity;
import br.com.fiap.techchallenge.domain.entity.CpfCnpj;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class JpaClienteRepository implements ClienteRepository {

    private final SpringDataClienteRepository springRepository;

    public JpaClienteRepository(SpringDataClienteRepository springRepository) {
        this.springRepository = springRepository;
    }

    @Override
    public ClienteEntity salvar(ClienteEntity cliente) {
        return springRepository.save(cliente);
    }

    @Override
    public Optional<ClienteEntity> buscarPorId(UUID id) {
        return springRepository.findById(id);
    }

    @Override
    public Optional<ClienteEntity> buscarPorCpfCnpj(CpfCnpj cpfCnpj) {
        return springRepository.findByCpfCnpj(cpfCnpj.valor());
    }

    @Override
    public List<ClienteEntity> buscaTodos() {
        return springRepository.findAll();
    }
}
