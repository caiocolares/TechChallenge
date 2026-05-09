package br.com.fiap.techchallenge.presentation.controller;

import br.com.fiap.techchallenge.domain.entity.ClienteEntity;
import br.com.fiap.techchallenge.domain.entity.CpfCnpj;
import br.com.fiap.techchallenge.presentation.dto.CadastrarClienteRequest;
import br.com.fiap.techchallenge.presentation.dto.ClienteResponse;
import br.com.fiap.techchallenge.application.service.ClienteService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    @RolesAllowed("ATENDENTE")
    public ResponseEntity<ClienteResponse> cadastrar(@RequestBody CadastrarClienteRequest request) {
        ClienteEntity cliente = clienteService.cadastrar(
                new CpfCnpj(request.cpfCnpj()),
                request.nome(),
                request.email(),
                request.telefone()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(ClienteResponse.de(cliente));
    }

    @GetMapping("/{id}")
    @RolesAllowed({"ATENDENTE", "CLIENTE"})
    public ResponseEntity<ClienteResponse> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(ClienteResponse.de(clienteService.buscarPorId(id)));
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponse>> buscaTodos() {
        return ResponseEntity.ok(clienteService.buscarTodos().stream()
                .map(ClienteResponse::de)
                .toList());
    }

    @GetMapping("/cpf-cnpj/{cpfCnpj}")
    @RolesAllowed("ATENDENTE")
    public ResponseEntity<ClienteResponse> buscarPorCpfCnpj(@PathVariable String cpfCnpj) {
        return ResponseEntity.ok(ClienteResponse.de(clienteService.buscarPorCpfCnpj(new CpfCnpj(cpfCnpj))));
    }
}
