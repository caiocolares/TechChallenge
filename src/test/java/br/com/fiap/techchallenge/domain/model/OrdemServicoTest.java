package br.com.fiap.techchallenge.domain.model;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

class OrdemServicoTest {

    @Test
    void deveSeguirFluxoCompletoDeStatus() {
        CpfCnpj cliente = new CpfCnpj("12345678901");
        Veiculo veiculo = new Veiculo("ABC1234", "Ford", "Ka", 2020);
        OrdemServico os = new OrdemServico(cliente, veiculo);
        
        assertEquals(OrdemServico.StatusOrdemServico.RECEBIDA, os.getStatus());

        os.iniciarDiagnostico();
        assertEquals(OrdemServico.StatusOrdemServico.EM_DIAGNOSTICO, os.getStatus());

        os.adicionarServico(new ItemServico("Troca de óleo", new BigDecimal("150.00")));
        os.adicionarPeca(new ItemPeca("Filtro de óleo", new BigDecimal("50.00"), 1));
        
        os.enviarParaAprovacao();
        assertEquals(OrdemServico.StatusOrdemServico.AGUARDANDO_APROVACAO, os.getStatus());

        os.aprovar();
        assertEquals(OrdemServico.StatusOrdemServico.EM_EXECUCAO, os.getStatus());

        os.finalizarExecucao();
        assertEquals(OrdemServico.StatusOrdemServico.FINALIZADA, os.getStatus());

        os.entregarAoCliente();
        assertEquals(OrdemServico.StatusOrdemServico.ENTREGUE, os.getStatus());
    }

    @Test
    void naoDevePermitirAdicionarItensForaDoDiagnostico() {
        CpfCnpj cliente = new CpfCnpj("12345678901");
        Veiculo veiculo = new Veiculo("ABC1234", "Ford", "Ka", 2020);
        OrdemServico os = new OrdemServico(cliente, veiculo);
        
        // Em RECEBIDA não pode adicionar itens
        assertThrows(IllegalStateException.class, () -> 
            os.adicionarServico(new ItemServico("Teste", new BigDecimal("100")))
        );

        os.iniciarDiagnostico();
        os.adicionarServico(new ItemServico("Teste", new BigDecimal("100"))); // Ok

        os.enviarParaAprovacao();
        // Em AGUARDANDO_APROVACAO não pode adicionar
        assertThrows(IllegalStateException.class, () -> 
            os.adicionarServico(new ItemServico("Outro", new BigDecimal("10.00")))
        );
    }
}
