package br.com.fiap.techchallenge.presentation.web;

import br.com.fiap.techchallenge.application.port.in.CadastrarClienteUseCase;
import br.com.fiap.techchallenge.application.port.in.ConsultarClienteUseCase;
import br.com.fiap.techchallenge.domain.model.Cliente;
import br.com.fiap.techchallenge.domain.model.CpfCnpj;
import br.com.fiap.techchallenge.presentation.web.dto.CadastrarClienteRequest;
import br.com.fiap.techchallenge.presentation.web.dto.ClienteResponse;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final CadastrarClienteUseCase cadastrarUseCase;
    private final ConsultarClienteUseCase consultarUseCase;

    public ClienteController(CadastrarClienteUseCase cadastrarUseCase, ConsultarClienteUseCase consultarUseCase) {
        this.cadastrarUseCase = cadastrarUseCase;
        this.consultarUseCase = consultarUseCase;
    }

    @PostMapping
    @RolesAllowed("ATENDENTE")
    public ResponseEntity<ClienteResponse> cadastrar(@RequestBody CadastrarClienteRequest request) {
        Cliente cliente = cadastrarUseCase.cadastrar(
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
        Cliente cliente = consultarUseCase.buscarPorId(id);
        return ResponseEntity.ok(ClienteResponse.de(cliente));
    }

    @GetMapping("/cpf-cnpj/{cpfCnpj}")
    @RolesAllowed("ATENDENTE")
    public ResponseEntity<ClienteResponse> buscarPorCpfCnpj(@PathVariable String cpfCnpj) {
        Cliente cliente = consultarUseCase.buscarPorCpfCnpj(new CpfCnpj(cpfCnpj));
        return ResponseEntity.ok(ClienteResponse.de(cliente));
    }
}