package br.com.alura.leilao.ui;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.alura.leilao.api.retrofit.client.LeilaoWebClient;
import br.com.alura.leilao.api.retrofit.client.RespostaListener;
import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.ui.recyclerview.adapter.ListaLeilaoAdapter;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AtualizardorDeLeiloesTest {

    @Mock
    private ListaLeilaoAdapter adapter;

    @Mock
    private LeilaoWebClient client;

    @Mock
    private AtualizardorDeLeiloes.ErroCarregaLeiloesListener listener;

    @Test
    public void deve_AtualizarListaDeLeiloes_QuandoBuscarLeiloesDaApi() {
        AtualizardorDeLeiloes atualizardor = new AtualizardorDeLeiloes();
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                RespostaListener<List<Leilao>> argument = invocation.getArgument(0);
                argument.sucesso(new ArrayList<>(Arrays.asList(
                        new Leilao("Computador"),
                        new Leilao("Console"),
                        new Leilao("Monaliza")
                )));
                return null;
            }
        }).when(client).todos(any(RespostaListener.class));

        atualizardor.buscaLeiloes(adapter, client,listener);

        verify(client).todos(any(RespostaListener.class));

        verify(adapter).atualiza(new ArrayList<>(Arrays.asList(
                new Leilao("Computador"),
                new Leilao("Console"),
                new Leilao("Monaliza")
        )));
    }

    @Test
    public void deve_ApresentarMensagemDeFalha_QuandoFalharABuscaDeLeiloes() {
        AtualizardorDeLeiloes atualizardor = new AtualizardorDeLeiloes();

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                RespostaListener<List<Leilao>> argument = invocation.getArgument(0);
                argument.falha(anyString());
                return null;
            }
        }).when(client).todos(any(RespostaListener.class));

        atualizardor.buscaLeiloes(adapter, client, listener);

        verify(listener).erroAoCarregar(anyString());

    }

}