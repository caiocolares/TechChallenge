package br.com.fiap.techchallenge.presentation.dto;

public record CriarOrdemServicoRequest(
        String cpfCnpj,
        String placa,
        String marca,
        String modelo,
        int ano
) {}
