package br.com.fiap.techchallenge.application.port.in;

import java.util.UUID;

public interface AtualizarStatusOrdemServicoUseCase {
    void iniciarDiagnostico(UUID osId);
    void enviarOrcamento(UUID osId);
    void aprovarOS(UUID osId);
    void finalizarExecucao(UUID osId);
    void entregarOS(UUID osId);
}