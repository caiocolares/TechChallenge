package br.com.fiap.techchallenge.presentation.web.dto;

import br.com.fiap.techchallenge.domain.model.OrdemServico;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record OrdemServicoResponse(
        UUID id,
        String status,
        String cpfCnpjCliente,
        String placaVeiculo,
        String marca,
        String modelo,
        int anoVeiculo,
        BigDecimal valorTotal,
        LocalDateTime dataCriacao
) {
    public static OrdemServicoResponse de(OrdemServico os) {
        return new OrdemServicoResponse(
                os.getId(),
                os.getStatus().name(),
                os.getClienteId().valor(),
                os.getVeiculo().placa(),
                os.getVeiculo().marca(),
                os.getVeiculo().modelo(),
                os.getVeiculo().ano(),
                os.getValorTotal(),
                os.getDataCriacao()
        );
    }
}