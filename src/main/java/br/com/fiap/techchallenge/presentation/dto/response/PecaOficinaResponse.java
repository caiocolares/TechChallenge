package br.com.fiap.techchallenge.presentation.dto.response;

import br.com.fiap.techchallenge.domain.entity.PecaOficinaEntity;

import java.math.BigDecimal;
import java.util.UUID;

public record PecaOficinaResponse(
        UUID id,
        String nome,
        String descricao,
        String codigoReferencia,
        BigDecimal precoUnitario,
        int quantidadeEstoque
) {
    public static PecaOficinaResponse de(PecaOficinaEntity peca) {
        return new PecaOficinaResponse(
                peca.getId(),
                peca.getNome(),
                peca.getDescricao(),
                peca.getCodigoReferencia(),
                peca.getPrecoUnitario(),
                peca.getQuantidadeEstoque()
        );
    }
}
