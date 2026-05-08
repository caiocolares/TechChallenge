package br.com.fiap.techchallenge.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Cliente {
    private final UUID id;
    private final CpfCnpj cpfCnpj;
    private String nome;
    private String email;
    private String telefone;
    private final LocalDateTime dataCadastro;

    public Cliente(CpfCnpj cpfCnpj, String nome, String email, String telefone) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome do cliente é obrigatório");
        }
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("E-mail do cliente é obrigatório");
        }
        this.id = UUID.randomUUID();
        this.cpfCnpj = cpfCnpj;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.dataCadastro = LocalDateTime.now();
    }

    private Cliente(UUID id, CpfCnpj cpfCnpj, String nome, String email, String telefone, LocalDateTime dataCadastro) {
        this.id = id;
        this.cpfCnpj = cpfCnpj;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.dataCadastro = dataCadastro;
    }

    public static Cliente reconstituir(UUID id, CpfCnpj cpfCnpj, String nome, String email, String telefone, LocalDateTime dataCadastro) {
        return new Cliente(id, cpfCnpj, nome, email, telefone, dataCadastro);
    }

    public void atualizarContato(String email, String telefone) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("E-mail não pode ser vazio");
        }
        this.email = email;
        this.telefone = telefone;
    }

    public UUID getId() { return id; }
    public CpfCnpj getCpfCnpj() { return cpfCnpj; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getTelefone() { return telefone; }
    public LocalDateTime getDataCadastro() { return dataCadastro; }
}