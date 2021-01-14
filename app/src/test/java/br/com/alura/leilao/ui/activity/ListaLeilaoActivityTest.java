package br.com.alura.leilao.ui.activity;

import org.junit.Test;

public class ListaLeilaoActivityTest {
    @Test
    public void deve_AtualizarListaDeLeiloes_QuandoBuscarLeiloesDaApi(){
        ListaLeilaoActivity activity = new ListaLeilaoActivity();
        activity.onResume();
        int quantidadeLeiloesDevolvida = activity.getAdapter().getItemCount();


    }

}