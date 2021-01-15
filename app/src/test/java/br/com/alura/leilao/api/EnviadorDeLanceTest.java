package br.com.alura.leilao.api;

import android.content.Context;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.alura.leilao.api.retrofit.client.LeilaoWebClient;
import br.com.alura.leilao.exception.UsuarioJaDeuCincoLancesException;
import br.com.alura.leilao.model.Lance;
import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.model.Usuario;
import br.com.alura.leilao.ui.dialog.AvisoDialogManager;

@RunWith(MockitoJUnitRunner.class)
public class EnviadorDeLanceTest {

    @Mock
    private LeilaoWebClient cliente;
    @Mock
    private EnviadorDeLance.LanceProcessadoListener listener;
    @Mock
    private Context context;
    @Mock
    private AvisoDialogManager manager;

    @Test
    public void deve_MostrarMensagemDeFalha_QuandoLanceForMenorQueOUltimoLance() {
        EnviadorDeLance enviador = new EnviadorDeLance(cliente, listener, context, manager);

        Leilao computador = new Leilao("Computador");
        computador.propoe(new Lance(new Usuario("Alex"), 200));

        enviador.envia(computador, new Lance(new Usuario("Fran"), 100));

        Mockito.verify(manager).mostraAvisoLanceMenorQueUltimoLance(context);
    }

    @Test
    public void deve_MostrarMensagemDeFalha_QuandoUsuarioComCincoDerNovoLance() {
        EnviadorDeLance enviador = new EnviadorDeLance(cliente, listener, context, manager);

        Leilao computador = Mockito.mock(Leilao.class);
        Mockito.doThrow(UsuarioJaDeuCincoLancesException.class)
                .when(computador).propoe(ArgumentMatchers.any(Lance.class));

        enviador.envia(computador, new Lance(new Usuario("Alex"), 200));

        Mockito.verify(manager).mostraAvisoUsuarioJaDeuCincoLances(context);
    }

}