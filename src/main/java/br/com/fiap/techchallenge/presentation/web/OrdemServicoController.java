package br.com.fiap.techchallenge.presentation.web;

import br.com.fiap.techchallenge.application.port.in.AdicionarItemUseCase;
import br.com.fiap.techchallenge.application.port.in.AtualizarStatusOrdemServicoUseCase;
import br.com.fiap.techchallenge.application.port.in.ConsultarOrdemServicoUseCase;
import br.com.fiap.techchallenge.application.port.in.CriarOrdemServicoUseCase;
import br.com.fiap.techchallenge.domain.model.CpfCnpj;
import br.com.fiap.techchallenge.domain.model.ItemPeca;
import br.com.fiap.techchallenge.domain.model.ItemServico;
import br.com.fiap.techchallenge.domain.model.OrdemServico;
import br.com.fiap.techchallenge.domain.model.Veiculo;
import br.com.fiap.techchallenge.presentation.web.dto.AdicionarPecaRequest;
import br.com.fiap.techchallenge.presentation.web.dto.AdicionarServicoRequest;
import br.com.fiap.techchallenge.presentation.web.dto.CriarOrdemServicoRequest;
import br.com.fiap.techchallenge.presentation.web.dto.OrdemServicoResponse;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/ordens-servico")
public class OrdemServicoController {

    private final CriarOrdemServicoUseCase criarUseCase;
    private final ConsultarOrdemServicoUseCase consultarUseCase;
    private final AtualizarStatusOrdemServicoUseCase atualizarStatusUseCase;
    private final AdicionarItemUseCase adicionarItemUseCase;

    public OrdemServicoController(
            CriarOrdemServicoUseCase criarUseCase,
            ConsultarOrdemServicoUseCase consultarUseCase,
            AtualizarStatusOrdemServicoUseCase atualizarStatusUseCase,
            AdicionarItemUseCase adicionarItemUseCase) {
        this.criarUseCase = criarUseCase;
        this.consultarUseCase = consultarUseCase;
        this.atualizarStatusUseCase = atualizarStatusUseCase;
        this.adicionarItemUseCase = adicionarItemUseCase;
    }

    @PostMapping
    @RolesAllowed("ATENDENTE")
    public ResponseEntity<OrdemServicoResponse> criar(@RequestBody CriarOrdemServicoRequest request) {
        OrdemServico os = criarUseCase.criarOS(
                new CpfCnpj(request.cpfCnpj()),
                new Veiculo(request.placa(), request.marca(), request.modelo(), request.ano())
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(OrdemServicoResponse.de(os));
    }


    @GetMapping("/{id}/status")
    @RolesAllowed("CLIENTE")
    public ResponseEntity<OrdemServicoResponse> consultarStatus(@PathVariable UUID id) {
        OrdemServico os = consultarUseCase.consultarStatus(id);
        return ResponseEntity.ok(OrdemServicoResponse.de(os));
    }

    @PostMapping("/{id}/servicos")
    @RolesAllowed("MECANICO")
    public ResponseEntity<Void> adicionarServico(@PathVariable UUID id,
                                                  @RequestBody AdicionarServicoRequest request) {
        adicionarItemUseCase.adicionarServico(id, new ItemServico(request.descricao(), request.precoUnitario()));
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/pecas")
    @RolesAllowed("MECANICO")
    public ResponseEntity<Void> adicionarPeca(@PathVariable UUID id,
                                               @RequestBody AdicionarPecaRequest request) {
        adicionarItemUseCase.adicionarPeca(id,
                new ItemPeca(request.nome(), request.precoUnitario(), request.quantidade()));
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/iniciar-diagnostico")
    @RolesAllowed("MECANICO")
    public ResponseEntity<Void> iniciarDiagnostico(@PathVariable UUID id) {
        atualizarStatusUseCase.iniciarDiagnostico(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/enviar-orcamento")
    @RolesAllowed("MECANICO")
    public ResponseEntity<Void> enviarOrcamento(@PathVariable UUID id) {
        atualizarStatusUseCase.enviarOrcamento(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/aprovar")
    @RolesAllowed("CLIENTE")
    public ResponseEntity<Void> aprovar(@PathVariable UUID id) {
        atualizarStatusUseCase.aprovarOS(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/finalizar")
    @RolesAllowed("MECANICO")
    public ResponseEntity<Void> finalizar(@PathVariable UUID id) {
        atualizarStatusUseCase.finalizarExecucao(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/entregar")
    @RolesAllowed("ATENDENTE")
    public ResponseEntity<Void> entregar(@PathVariable UUID id) {
        atualizarStatusUseCase.entregarOS(id);
        return ResponseEntity.ok().build();
    }
}