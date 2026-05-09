package br.com.fiap.techchallenge.presentation.dto.request;

public record CriarOrdemServicoRequest(
        String cpfCnpj,
        String placa,
        String marca,
        String modelo,
        int ano
) {}
