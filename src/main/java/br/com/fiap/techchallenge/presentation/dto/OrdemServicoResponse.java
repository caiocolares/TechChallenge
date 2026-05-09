package br.com.fiap.techchallenge.presentation.dto;

import br.com.fiap.techchallenge.domain.entity.OrdemServicoEntity;

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
    public static OrdemServicoResponse de(OrdemServicoEntity os) {
        return new OrdemServicoResponse(
                os.getId(),
                os.getStatus().name(),
                os.getClienteCpfCnpj(),
                os.getVeiculo().getPlaca(),
                os.getVeiculo().getMarca(),
                os.getVeiculo().getModelo(),
                os.getVeiculo().getAno(),
                os.getValorTotal(),
                os.getDataCriacao()
        );
    }
}
