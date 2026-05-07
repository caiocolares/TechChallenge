package br.com.fiap.techchallenge.domain.model;

public record Veiculo(String placa, String marca, String modelo, int ano) {
    public Veiculo {
        if (placa == null || placa.isBlank()) throw new IllegalArgumentException("Placa é obrigatória");
        if (marca == null || marca.isBlank()) throw new IllegalArgumentException("Marca é obrigatória");
        if (modelo == null || modelo.isBlank()) throw new IllegalArgumentException("Modelo é obrigatório");
        if (ano < 1886) throw new IllegalArgumentException("Ano inválido");
    }
}
