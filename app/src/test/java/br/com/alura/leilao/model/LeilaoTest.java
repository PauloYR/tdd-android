package br.com.alura.leilao.model;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;

import br.com.alura.leilao.exception.LanceMenorQueUltimoLanceException;
import br.com.alura.leilao.exception.LanceSeguidoDoMesmoUsuarioException;
import br.com.alura.leilao.exception.UsuarioJaDeuCincoLancesException;

import static org.hamcrest.CoreMatchers.both;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.*;

public class LeilaoTest {

    public static final double DELTA = 0.0001;
    private final Leilao CONSOLE = new Leilao("Console");
    private final Usuario PAULO = new Usuario("Paulo");

    //@Rule
    //public ExpectedException exception = ExpectedException.none();

    //[Nome do método][Estado do teste][Resultado esperado]
    //[deve] [resultado esperado] [estado de teste]
    @Test
    //public void getDescricao_QuandoRecebeDescricao_DevolveDescricao() {
    public void deve_DevolveDescricao_QuandoRecebeDescricao() {
        String descricao = CONSOLE.getDescricao();

        //assertEquals("Console", descricao);
        assertThat(descricao, is(equalTo("Console")));
    }

    @Test
    public void deve_DevolveMaiorLance_QuandoRecebeApenasUmLance() {
        CONSOLE.propoe(new Lance(PAULO, 200.0));

        double maiorLanceDevolvido = CONSOLE.getMaiorLance();

        //assertEquals(200.0, maiorLanceDevolvido, DELTA);
        assertThat(maiorLanceDevolvido, closeTo(200.0, DELTA));
    }

    @Test
    public void deve_DevolveMaiorLance_QuandoRecebeMaisDeUmLanceEmOrdemCresente() {
        CONSOLE.propoe(new Lance(PAULO, 100.0));
        CONSOLE.propoe(new Lance(new Usuario("Fran"), 200.0));
        CONSOLE.propoe(new Lance(PAULO, 300.0));

        double maiorLanceCrescente = CONSOLE.getMaiorLance();

        assertEquals(300.0, maiorLanceCrescente, DELTA);
    }

    @Test
    public void deve_DevolveMenorLance_QuandoRecebeApenasUmLance() {
        CONSOLE.propoe(new Lance(PAULO, 100.0));

        double menorLanceDevolvido = CONSOLE.getMenorLance();

        assertEquals(100.0, menorLanceDevolvido, DELTA);
    }

    @Test
    public void deve_DevolveMenorLance_QuandoRecebeMaisDeUmLanceEmOrdemCresente() {
        CONSOLE.propoe(new Lance(PAULO, 100.0));
        CONSOLE.propoe(new Lance(new Usuario("Fran"), 200.0));
        CONSOLE.propoe(new Lance(PAULO, 300.0));

        double menorLanceCrescente = CONSOLE.getMenorLance();

        assertEquals(100.0, menorLanceCrescente, DELTA);
    }

    @Test
    public void deve_DevolveTresMaioresLances_QuandoRecebeExatosTresLances() {
        CONSOLE.propoe(new Lance(PAULO, 300.0));
        CONSOLE.propoe(new Lance(new Usuario("Fran"), 400.0));
        CONSOLE.propoe(new Lance(new Usuario("Alex"), 500.0));


        List<Lance> tresMaioresLances = CONSOLE.tresMaioresLances();

//        assertEquals(500.0, tresMaioresLances.get(0).getValor(), DELTA);
//        assertThat(tresMaioresLances,hasItem(new Lance(new Usuario("Alex"), 500.0)));

//        assertEquals(400.0, tresMaioresLances.get(1).getValor(), DELTA);
//        assertEquals(300.0, tresMaioresLances.get(2).getValor(), DELTA);
//        assertThat(tresMaioresLances, contains(
//                new Lance(new Usuario("Alex"), 500.0),
//                new Lance(new Usuario("Fran"), 400.0),
//                new Lance(PAULO, 300.0)
//        ));


        //assertEquals(3, tresMaioresLances.size());

//        assertThat(tresMaioresLances, hasSize(equalTo(3)));

        assertThat(tresMaioresLances,
                both(Matchers.<Lance>hasSize(equalTo(3)))
                        .and(contains(
                                new Lance(new Usuario("Alex"), 500.0),
                                new Lance(new Usuario("Fran"), 400.0),
                                new Lance(PAULO, 300.0))));
    }

    @Test
    public void deve_DevolverValorZeroParaMaiorLance_QuandoNaoTiverLances() {
        double maiorLance = CONSOLE.getMaiorLance();
        assertEquals(0.0, maiorLance, DELTA);
    }

    @Test
    public void deve_DevolverValorZeroParaMenorLance_QuandoNaoTiverLances() {
        double menorLance = CONSOLE.getMenorLance();
        assertEquals(0.0, menorLance, DELTA);
    }

    @Test(expected = LanceMenorQueUltimoLanceException.class)
    public void deve_LancarException_QuandoForMenorQueOMaiorLance() {
        CONSOLE.propoe(new Lance(PAULO, 500));

        CONSOLE.propoe(new Lance(new Usuario("Fran"), 400));
    }

    @Test(expected = LanceSeguidoDoMesmoUsuarioException.class)
    public void deve_LancarException_QuandoForOMesmoDoUltimoLance() {

        //exception.expectMessage("lance for menor que o ultimo lance");
        CONSOLE.propoe(new Lance(PAULO, 500));

        CONSOLE.propoe(new Lance(new Usuario("Paulo"), 600));
    }

    @Test(expected = UsuarioJaDeuCincoLancesException.class)
    public void deve_LancarException_QuandoUsuarioDerCincoLances() {

        final Usuario FRAN = new Usuario("Fran");

        CONSOLE.propoe(new Lance(PAULO, 100.0));
        CONSOLE.propoe(new Lance(FRAN, 200.0));
        CONSOLE.propoe(new Lance(PAULO, 300.0));
        CONSOLE.propoe(new Lance(FRAN, 400.0));
        CONSOLE.propoe(new Lance(PAULO, 500.0));
        CONSOLE.propoe(new Lance(FRAN, 600.0));
        CONSOLE.propoe(new Lance(PAULO, 700.0));
        CONSOLE.propoe(new Lance(FRAN, 800.0));
        CONSOLE.propoe(new Lance(PAULO, 900.0));
        CONSOLE.propoe(new Lance(FRAN, 1000.0));
        CONSOLE.propoe(new Lance(PAULO, 1100.0));


    }
}