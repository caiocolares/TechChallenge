package br.com.fiap.techchallenge.presentation.controller;

import br.com.fiap.techchallenge.domain.entity.CpfCnpj;
import br.com.fiap.techchallenge.presentation.dto.request.CadastrarClienteRequest;
import br.com.fiap.techchallenge.presentation.dto.response.ClienteResponse;
import br.com.fiap.techchallenge.application.service.ClienteService;
import br.com.fiap.techchallenge.presentation.mapper.ClienteMapper;
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
    private final ClienteMapper clienteMapper;

    public ClienteController(ClienteService clienteService, ClienteMapper clienteMapper) {
        this.clienteService = clienteService;
        this.clienteMapper = clienteMapper;
    }

    @PostMapping
    @RolesAllowed("ATENDENTE")
    public ResponseEntity<ClienteResponse> cadastrar(@RequestBody CadastrarClienteRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                clienteMapper.toResponse(clienteService.cadastrar(
                        new CpfCnpj(request.cpfCnpj()),
                        request.nome(),
                        request.email(),
                        request.telefone()
                ))
        );
    }

    @GetMapping("/{id}")
    @RolesAllowed({"ATENDENTE", "CLIENTE"})
    public ResponseEntity<ClienteResponse> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(clienteMapper.toResponse(clienteService.buscarPorId(id)));
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponse>> buscaTodos() {
        return ResponseEntity.ok(clienteMapper.toResponseList(clienteService.buscarTodos()));
    }

    @GetMapping("/cpf-cnpj/{cpfCnpj}")
    @RolesAllowed("ATENDENTE")
    public ResponseEntity<ClienteResponse> buscarPorCpfCnpj(@PathVariable String cpfCnpj) {
        return ResponseEntity.ok(clienteMapper.toResponse(clienteService.buscarPorCpfCnpj(new CpfCnpj(cpfCnpj))));
    }
}
