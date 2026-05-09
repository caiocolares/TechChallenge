package br.com.fiap.techchallenge.presentation.controller;

import br.com.fiap.techchallenge.domain.entity.PecaOficinaEntity;
import br.com.fiap.techchallenge.presentation.dto.request.AtualizarEstoqueRequest;
import br.com.fiap.techchallenge.presentation.dto.request.CadastrarPecaOficinaRequest;
import br.com.fiap.techchallenge.presentation.dto.response.PecaOficinaResponse;
import br.com.fiap.techchallenge.application.service.PecaOficinaService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/pecas")
public class PecaOficinaController {

    private final PecaOficinaService pecaOficinaService;

    public PecaOficinaController(PecaOficinaService pecaOficinaService) {
        this.pecaOficinaService = pecaOficinaService;
    }

    @PostMapping
    @RolesAllowed("MECANICO")
    public ResponseEntity<PecaOficinaResponse> cadastrar(@RequestBody CadastrarPecaOficinaRequest request) {
        PecaOficinaEntity peca = pecaOficinaService.cadastrar(
                request.nome(),
                request.descricao(),
                request.codigoReferencia(),
                request.precoUnitario(),
                request.quantidadeEstoque()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(PecaOficinaResponse.de(peca));
    }

    @GetMapping
    @RolesAllowed({"MECANICO", "ATENDENTE"})
    public ResponseEntity<List<PecaOficinaResponse>> listar() {
        return ResponseEntity.ok(pecaOficinaService.listarTodas().stream()
                .map(PecaOficinaResponse::de)
                .toList());
    }

    @GetMapping("/{id}")
    @RolesAllowed({"MECANICO", "ATENDENTE"})
    public ResponseEntity<PecaOficinaResponse> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(PecaOficinaResponse.de(pecaOficinaService.buscarPorId(id)));
    }

    @PostMapping("/{id}/entrada-estoque")
    @RolesAllowed("MECANICO")
    public ResponseEntity<Void> entradaEstoque(@PathVariable UUID id, @RequestBody AtualizarEstoqueRequest request) {
        pecaOficinaService.entradaEstoque(id, request.quantidade());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/baixar-estoque")
    @RolesAllowed("MECANICO")
    public ResponseEntity<Void> baixarEstoque(@PathVariable UUID id, @RequestBody AtualizarEstoqueRequest request) {
        pecaOficinaService.baixarEstoque(id, request.quantidade());
        return ResponseEntity.ok().build();
    }
}
