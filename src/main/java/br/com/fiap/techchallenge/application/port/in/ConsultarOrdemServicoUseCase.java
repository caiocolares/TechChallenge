package br.com.fiap.techchallenge.application.port.in;

import br.com.fiap.techchallenge.domain.model.OrdemServico;

import java.util.UUID;

public interface ConsultarOrdemServicoUseCase {
    OrdemServico consultarStatus(UUID osId);
}