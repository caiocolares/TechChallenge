package br.com.fiap.techchallenge.application.port.in;

import br.com.fiap.techchallenge.domain.model.ItemPeca;
import br.com.fiap.techchallenge.domain.model.ItemServico;

import java.util.UUID;

public interface AdicionarItemUseCase {
    void adicionarServico(UUID osId, ItemServico servico);
    void adicionarPeca(UUID osId, ItemPeca peca);
}