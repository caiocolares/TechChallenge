package br.com.fiap.techchallenge.infrastructure.persistence;

import br.com.fiap.techchallenge.domain.model.*;
import br.com.fiap.techchallenge.domain.repository.OrdemServicoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class JpaOrdemServicoRepository implements OrdemServicoRepository {

    private final SpringDataOrdemServicoRepository springRepository;

    public JpaOrdemServicoRepository(SpringDataOrdemServicoRepository springRepository) {
        this.springRepository = springRepository;
    }

    @Override
    public OrdemServico salvar(OrdemServico os) {
        springRepository.save(toEntity(os));
        return os;
    }

    @Override
    public Optional<OrdemServico> buscarPorId(UUID id) {
        return springRepository.findById(id).map(this::toDomain);
    }

    private OrdemServicoEntity toEntity(OrdemServico os) {
        OrdemServicoEntity entity = new OrdemServicoEntity();
        entity.setId(os.getId());
        entity.setClienteCpfCnpj(os.getClienteId().valor());

        VeiculoEmbeddable veiculoEmb = new VeiculoEmbeddable();
        veiculoEmb.setPlaca(os.getVeiculo().placa());
        veiculoEmb.setMarca(os.getVeiculo().marca());
        veiculoEmb.setModelo(os.getVeiculo().modelo());
        veiculoEmb.setAno(os.getVeiculo().ano());
        entity.setVeiculo(veiculoEmb);

        entity.setServicos(os.getServicos().stream().map(s -> {
            ItemServicoEmbeddable emb = new ItemServicoEmbeddable();
            emb.setDescricao(s.descricao());
            emb.setPrecoUnitario(s.precoUnitario());
            return emb;
        }).collect(Collectors.toList()));

        entity.setPecas(os.getPecas().stream().map(p -> {
            ItemPecaEmbeddable emb = new ItemPecaEmbeddable();
            emb.setNome(p.nome());
            emb.setPrecoUnitario(p.precoUnitario());
            emb.setQuantidade(p.quantidade());
            return emb;
        }).collect(Collectors.toList()));

        entity.setStatus(OrdemServicoEntity.StatusOrdemServico.valueOf(os.getStatus().name()));
        entity.setDataCriacao(os.getDataCriacao());
        entity.setValorTotal(os.getValorTotal());

        return entity;
    }

    private OrdemServico toDomain(OrdemServicoEntity entity) {
        List<ItemServico> servicos = entity.getServicos().stream()
                .map(s -> new ItemServico(s.getDescricao(), s.getPrecoUnitario()))
                .collect(Collectors.toList());

        List<ItemPeca> pecas = entity.getPecas().stream()
                .map(p -> new ItemPeca(p.getNome(), p.getPrecoUnitario(), p.getQuantidade()))
                .collect(Collectors.toList());

        return OrdemServico.reconstituir(
                entity.getId(),
                new CpfCnpj(entity.getClienteCpfCnpj()),
                new Veiculo(entity.getVeiculo().getPlaca(), entity.getVeiculo().getMarca(),
                        entity.getVeiculo().getModelo(), entity.getVeiculo().getAno()),
                servicos,
                pecas,
                OrdemServico.StatusOrdemServico.valueOf(entity.getStatus().name()),
                entity.getDataCriacao(),
                entity.getValorTotal()
        );
    }
}
