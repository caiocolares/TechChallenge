package br.com.fiap.techchallenge.application.port.in;

import br.com.fiap.techchallenge.domain.model.CpfCnpj;
import br.com.fiap.techchallenge.domain.model.OrdemServico;
import br.com.fiap.techchallenge.domain.model.Veiculo;

public interface CriarOrdemServicoUseCase {
    OrdemServico criarOS(CpfCnpj clienteId, Veiculo veiculo);
}