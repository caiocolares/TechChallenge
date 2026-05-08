package br.com.fiap.techchallenge.presentation.web.dto;

public record CadastrarClienteRequest(
        String cpfCnpj,
        String nome,
        String email,
        String telefone
) {}