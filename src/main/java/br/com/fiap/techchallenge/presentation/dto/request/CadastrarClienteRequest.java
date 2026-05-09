package br.com.fiap.techchallenge.presentation.dto.request;

public record CadastrarClienteRequest(
        String cpfCnpj,
        String nome,
        String email,
        String telefone
) {}
