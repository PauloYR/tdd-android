package br.com.alura.leilao.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class LeilaoTest {

    private Leilao console = new Leilao("Console");
    private Usuario paulo = new Usuario("Paulo");

    //[Nome do método][Estado do teste][Resultado esperado]
    //[deve] [resultado esperado] [estado de teste]
    @Test
    //public void getDescricao_QuandoRecebeDescricao_DevolveDescricao() {
    public void deve_DevolveDescricao_QuandoRecebeDescricao(){
        String descricao = console.getDescricao();

        assertEquals("Console",descricao);
    }

    @Test
    public void deve_DevolveMaiorLance_QuandoRecebeApenasUmLance(){
        console.propoe(new Lance(paulo,200.0));
        console.propoe(new Lance(paulo,100.0));

        double maiorLanceDevolvido = console.getMaiorLance();

        assertEquals(200.0,maiorLanceDevolvido,0.0001);
    }

    @Test
    public void deve_DevolveMaiorLance_QuandoRecebeMaisDeUmLanceEmOrdemCresente(){
        console.propoe(new Lance(paulo,100.0));
        console.propoe(new Lance(new Usuario("Fran"),200.0));
        console.propoe(new Lance(paulo,300.0));

        double maiorLanceCrescente = console.getMaiorLance();

        assertEquals(300.0,maiorLanceCrescente,0.0001);
    }

    @Test
    public void deve_DevolveMaiorLance_QuandoRecebeMaisDeUmLanceEmOrdemDecresente(){
        console.propoe(new Lance(paulo,300.0));
        console.propoe(new Lance(paulo,200.0));
        console.propoe(new Lance(new Usuario("Fran"),100.0));

        double maiorLanceDecrescente = console.getMaiorLance();

        assertEquals(300.0,maiorLanceDecrescente,0.0001);
    }

    @Test
    public void deve_DevolveMenorLance_QuandoRecebeApenasUmLance(){
        console.propoe(new Lance(paulo,100.0));

        double menorLanceDevolvido = console.getMenorLance();

        assertEquals(100.0,menorLanceDevolvido,0.0001);
    }

    @Test
    public void deve_DevolveMenorLance_QuandoRecebeMaisDeUmLanceEmOrdemCresente(){
        console.propoe(new Lance(paulo,100.0));
        console.propoe(new Lance(new Usuario("Fran"),200.0));
        console.propoe(new Lance(paulo,300.0));

        double menorLanceCrescente = console.getMenorLance();

        assertEquals(100.0,menorLanceCrescente,0.0001);
    }

    @Test
    public void deve_DevolveMenorLance_QuandoRecebeMaisDeUmLanceEmOrdemDecresente(){
        console.propoe(new Lance(new Usuario("Fran"),300.0));
        console.propoe(new Lance(paulo,200.0));
        console.propoe(new Lance(paulo,100.0));


        double menorLanceDecresente = console.getMenorLance();

        assertEquals(100.0,menorLanceDecresente,0.0001);
    }

}