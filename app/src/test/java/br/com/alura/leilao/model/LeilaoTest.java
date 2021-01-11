package br.com.alura.leilao.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class LeilaoTest {

    private final Leilao CONSOLE = new Leilao("Console");
    private final Usuario PAULO = new Usuario("Paulo");

    //[Nome do método][Estado do teste][Resultado esperado]
    //[deve] [resultado esperado] [estado de teste]
    @Test
    //public void getDescricao_QuandoRecebeDescricao_DevolveDescricao() {
    public void deve_DevolveDescricao_QuandoRecebeDescricao(){
        String descricao = CONSOLE.getDescricao();

        assertEquals("Console",descricao);
    }

    @Test
    public void deve_DevolveMaiorLance_QuandoRecebeApenasUmLance(){
        CONSOLE.propoe(new Lance(PAULO,200.0));
        CONSOLE.propoe(new Lance(PAULO,100.0));

        double maiorLanceDevolvido = CONSOLE.getMaiorLance();

        assertEquals(200.0,maiorLanceDevolvido,0.0001);
    }

    @Test
    public void deve_DevolveMaiorLance_QuandoRecebeMaisDeUmLanceEmOrdemCresente(){
        CONSOLE.propoe(new Lance(PAULO,100.0));
        CONSOLE.propoe(new Lance(new Usuario("Fran"),200.0));
        CONSOLE.propoe(new Lance(PAULO,300.0));

        double maiorLanceCrescente = CONSOLE.getMaiorLance();

        assertEquals(300.0,maiorLanceCrescente,0.0001);
    }

    @Test
    public void deve_DevolveMaiorLance_QuandoRecebeMaisDeUmLanceEmOrdemDecresente(){
        CONSOLE.propoe(new Lance(PAULO,300.0));
        CONSOLE.propoe(new Lance(PAULO,200.0));
        CONSOLE.propoe(new Lance(new Usuario("Fran"),100.0));

        double maiorLanceDecrescente = CONSOLE.getMaiorLance();

        assertEquals(300.0,maiorLanceDecrescente,0.0001);
    }

    @Test
    public void deve_DevolveMenorLance_QuandoRecebeApenasUmLance(){
        CONSOLE.propoe(new Lance(PAULO,100.0));

        double menorLanceDevolvido = CONSOLE.getMenorLance();

        assertEquals(100.0,menorLanceDevolvido,0.0001);
    }

    @Test
    public void deve_DevolveMenorLance_QuandoRecebeMaisDeUmLanceEmOrdemCresente(){
        CONSOLE.propoe(new Lance(PAULO,100.0));
        CONSOLE.propoe(new Lance(new Usuario("Fran"),200.0));
        CONSOLE.propoe(new Lance(PAULO,300.0));

        double menorLanceCrescente = CONSOLE.getMenorLance();

        assertEquals(100.0,menorLanceCrescente,0.0001);
    }

    @Test
    public void deve_DevolveMenorLance_QuandoRecebeMaisDeUmLanceEmOrdemDecresente(){
        CONSOLE.propoe(new Lance(new Usuario("Fran"),300.0));
        CONSOLE.propoe(new Lance(PAULO,200.0));
        CONSOLE.propoe(new Lance(PAULO,100.0));


        double menorLanceDecresente = CONSOLE.getMenorLance();

        assertEquals(100.0,menorLanceDecresente,0.0001);
    }

}