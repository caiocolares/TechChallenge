package br.com.fiap.techchallenge.presentation.controller;

import br.com.fiap.techchallenge.domain.entity.*;
import br.com.fiap.techchallenge.presentation.dto.AdicionarPecaRequest;
import br.com.fiap.techchallenge.presentation.dto.AdicionarServicoRequest;
import br.com.fiap.techchallenge.presentation.dto.CriarOrdemServicoRequest;
import br.com.fiap.techchallenge.presentation.dto.OrdemServicoResponse;
import br.com.fiap.techchallenge.application.service.OrdemServicoService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/ordens-servico")
public class OrdemServicoController {

    private final OrdemServicoService ordemServicoService;

    public OrdemServicoController(OrdemServicoService ordemServicoService) {
        this.ordemServicoService = ordemServicoService;
    }

    @PostMapping
    @RolesAllowed("ATENDENTE")
    public ResponseEntity<OrdemServicoResponse> criar(@RequestBody CriarOrdemServicoRequest request) {
        OrdemServicoEntity os = ordemServicoService.criarOS(
                new CpfCnpj(request.cpfCnpj()),
                new VeiculoEmbeddable(request.placa(), request.marca(), request.modelo(), request.ano())
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(OrdemServicoResponse.de(os));
    }

    @GetMapping("/{id}/status")
    @RolesAllowed("CLIENTE")
    public ResponseEntity<OrdemServicoResponse> consultarStatus(@PathVariable UUID id) {
        return ResponseEntity.ok(OrdemServicoResponse.de(ordemServicoService.consultarStatus(id)));
    }

    @PostMapping("/{id}/servicos")
    @RolesAllowed("MECANICO")
    public ResponseEntity<Void> adicionarServico(@PathVariable UUID id,
                                                  @RequestBody AdicionarServicoRequest request) {
        ordemServicoService.adicionarServico(id, new ItemServicoEmbeddable(request.descricao(), request.precoUnitario()));
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/pecas")
    @RolesAllowed("MECANICO")
    public ResponseEntity<Void> adicionarPeca(@PathVariable UUID id,
                                               @RequestBody AdicionarPecaRequest request) {
        ordemServicoService.adicionarPeca(id, new ItemPecaEmbeddable(request.nome(), request.precoUnitario(), request.quantidade()));
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/iniciar-diagnostico")
    @RolesAllowed("MECANICO")
    public ResponseEntity<Void> iniciarDiagnostico(@PathVariable UUID id) {
        ordemServicoService.iniciarDiagnostico(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/enviar-orcamento")
    @RolesAllowed("MECANICO")
    public ResponseEntity<Void> enviarOrcamento(@PathVariable UUID id) {
        ordemServicoService.enviarOrcamento(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/aprovar")
    @RolesAllowed("CLIENTE")
    public ResponseEntity<Void> aprovar(@PathVariable UUID id) {
        ordemServicoService.aprovarOS(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/finalizar")
    @RolesAllowed("MECANICO")
    public ResponseEntity<Void> finalizar(@PathVariable UUID id) {
        ordemServicoService.finalizarExecucao(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/entregar")
    @RolesAllowed("ATENDENTE")
    public ResponseEntity<Void> entregar(@PathVariable UUID id) {
        ordemServicoService.entregarOS(id);
        return ResponseEntity.ok().build();
    }
}
