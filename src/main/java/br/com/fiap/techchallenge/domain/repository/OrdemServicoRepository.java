package br.com.fiap.techchallenge.domain.repository;

import br.com.fiap.techchallenge.domain.model.OrdemServico;
import java.util.Optional;
import java.util.UUID;

public interface OrdemServicoRepository {
    OrdemServico salvar(OrdemServico ordemServico);
    Optional<OrdemServico> buscarPorId(UUID id);
}
