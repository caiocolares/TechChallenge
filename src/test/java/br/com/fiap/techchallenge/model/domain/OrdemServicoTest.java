package br.com.fiap.techchallenge.model.domain;

import br.com.fiap.techchallenge.domain.entity.*;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

class OrdemServicoTest {

    @Test
    void deveSeguirFluxoCompletoDeStatus() {
        CpfCnpj cliente = new CpfCnpj("12345678901");
        VeiculoEmbeddable veiculo = new VeiculoEmbeddable("ABC1234", "Ford", "Ka", 2020);
        OrdemServicoEntity os = new OrdemServicoEntity(cliente, veiculo);
        
        assertEquals(OrdemServicoEntity.StatusOrdemServico.RECEBIDA, os.getStatus());

        os.iniciarDiagnostico();
        assertEquals(OrdemServicoEntity.StatusOrdemServico.EM_DIAGNOSTICO, os.getStatus());

        os.adicionarServico(new ItemServicoEmbeddable("Troca de óleo", new BigDecimal("150.00")));
        os.adicionarPeca(new ItemPecaEmbeddable("Filtro de óleo", new BigDecimal("50.00"), 1));
        
        os.enviarParaAprovacao();
        assertEquals(OrdemServicoEntity.StatusOrdemServico.AGUARDANDO_APROVACAO, os.getStatus());

        os.aprovar();
        assertEquals(OrdemServicoEntity.StatusOrdemServico.EM_EXECUCAO, os.getStatus());

        os.finalizarExecucao();
        assertEquals(OrdemServicoEntity.StatusOrdemServico.FINALIZADA, os.getStatus());

        os.entregarAoCliente();
        assertEquals(OrdemServicoEntity.StatusOrdemServico.ENTREGUE, os.getStatus());
    }

    @Test
    void naoDevePermitirAdicionarItensForaDoDiagnostico() {
        CpfCnpj cliente = new CpfCnpj("12345678901");
        VeiculoEmbeddable veiculo = new VeiculoEmbeddable("ABC1234", "Ford", "Ka", 2020);
        OrdemServicoEntity os = new OrdemServicoEntity(cliente, veiculo);
        
        // Em RECEBIDA não pode adicionar itens
        assertThrows(IllegalStateException.class, () -> 
            os.adicionarServico(new ItemServicoEmbeddable("Teste", new BigDecimal("100")))
        );

        os.iniciarDiagnostico();
        os.adicionarServico(new ItemServicoEmbeddable("Teste", new BigDecimal("100"))); // Ok

        os.enviarParaAprovacao();
        // Em AGUARDANDO_APROVACAO não pode adicionar
//        assertThrows(IllegalStateException.class, () ->
//            os.adicionarServico(new ItemServicoEmbeddable("Outro", new BigDecimal("10.00")))
//        );
    }
}
