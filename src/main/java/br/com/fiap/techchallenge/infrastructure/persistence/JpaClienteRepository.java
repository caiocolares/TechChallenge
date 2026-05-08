package br.com.fiap.techchallenge.infrastructure.persistence;

import br.com.fiap.techchallenge.domain.model.Cliente;
import br.com.fiap.techchallenge.domain.model.CpfCnpj;
import br.com.fiap.techchallenge.domain.repository.ClienteRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class JpaClienteRepository implements ClienteRepository {

    private final SpringDataClienteRepository springRepository;

    public JpaClienteRepository(SpringDataClienteRepository springRepository) {
        this.springRepository = springRepository;
    }

    @Override
    public Cliente salvar(Cliente cliente) {
        springRepository.save(toEntity(cliente));
        return cliente;
    }

    @Override
    public Optional<Cliente> buscarPorId(UUID id) {
        return springRepository.findById(id).map(this::toDomain);
    }

    @Override
    public Optional<Cliente> buscarPorCpfCnpj(CpfCnpj cpfCnpj) {
        return springRepository.findByCpfCnpj(cpfCnpj.valor()).map(this::toDomain);
    }

    private ClienteEntity toEntity(Cliente cliente) {
        ClienteEntity entity = new ClienteEntity();
        entity.setId(cliente.getId());
        entity.setCpfCnpj(cliente.getCpfCnpj().valor());
        entity.setNome(cliente.getNome());
        entity.setEmail(cliente.getEmail());
        entity.setTelefone(cliente.getTelefone());
        entity.setDataCadastro(cliente.getDataCadastro());
        return entity;
    }

    private Cliente toDomain(ClienteEntity entity) {
        return Cliente.reconstituir(
                entity.getId(),
                new CpfCnpj(entity.getCpfCnpj()),
                entity.getNome(),
                entity.getEmail(),
                entity.getTelefone(),
                entity.getDataCadastro()
        );
    }
}