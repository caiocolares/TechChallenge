package br.com.fiap.techchallenge.domain.entity;

import jakarta.persistence.Embeddable;

@Embeddable
public class VeiculoEmbeddable {
    private String placa;
    private String marca;
    private String modelo;
    private int ano;

    protected VeiculoEmbeddable() {}

    public VeiculoEmbeddable(String placa, String marca, String modelo, int ano) {
        if (placa == null || placa.isBlank()) throw new IllegalArgumentException("Placa é obrigatória");
        if (marca == null || marca.isBlank()) throw new IllegalArgumentException("Marca é obrigatória");
        if (modelo == null || modelo.isBlank()) throw new IllegalArgumentException("Modelo é obrigatório");
        if (ano < 1886) throw new IllegalArgumentException("Ano inválido");
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
    }

    public String getPlaca() { return placa; }
    public String getMarca() { return marca; }
    public String getModelo() { return modelo; }
    public int getAno() { return ano; }
}
