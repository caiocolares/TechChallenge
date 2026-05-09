package br.com.fiap.techchallenge.domain.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "clientes")
public class ClienteEntity {

    @Id
    private UUID id;

    @Column(nullable = false, unique = true)
    private String cpfCnpj;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String email;

    private String telefone;
    private LocalDateTime dataCadastro;

    protected ClienteEntity() {}

    public ClienteEntity(CpfCnpj cpfCnpj, String nome, String email, String telefone) {
        if (nome == null || nome.isBlank()) throw new IllegalArgumentException("Nome do cliente é obrigatório");
        if (email == null || email.isBlank()) throw new IllegalArgumentException("E-mail do cliente é obrigatório");
        this.id = UUID.randomUUID();
        this.cpfCnpj = cpfCnpj.valor();
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.dataCadastro = LocalDateTime.now();
    }

    public void atualizarContato(String email, String telefone) {
        if (email == null || email.isBlank()) throw new IllegalArgumentException("E-mail não pode ser vazio");
        this.email = email;
        this.telefone = telefone;
    }

    public UUID getId() { return id; }
    public String getCpfCnpj() { return cpfCnpj; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getTelefone() { return telefone; }
    public LocalDateTime getDataCadastro() { return dataCadastro; }
}
