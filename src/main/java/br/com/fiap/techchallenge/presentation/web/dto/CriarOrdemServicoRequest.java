package br.com.fiap.techchallenge.presentation.web.dto;

public record CriarOrdemServicoRequest(
        String cpfCnpj,
        String placa,
        String marca,
        String modelo,
        int ano
) {}