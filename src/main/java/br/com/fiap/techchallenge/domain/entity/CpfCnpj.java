package br.com.fiap.techchallenge.domain.entity;

public record CpfCnpj(String valor) {
    public CpfCnpj {
        if (valor == null || (!valor.matches("\\d{11}") && !valor.matches("\\d{14}"))) {
            throw new IllegalArgumentException("CPF ou CNPJ inválido");
        }
    }
}
