package br.com.fiap.techchallenge.presentation.dto;

public record CadastrarClienteRequest(
        String cpfCnpj,
        String nome,
        String email,
        String telefone
) {}
