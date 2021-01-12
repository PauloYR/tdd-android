package br.com.alura.leilao.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.alura.leilao.R;
import br.com.alura.leilao.model.Lance;
import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.model.Usuario;
import br.com.alura.leilao.ui.recyclerview.adapter.ListaLeilaoAdapter;

public class ListaLeilaoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_leilao);
        ListaLeilaoAdapter adapter = new ListaLeilaoAdapter(this, leiloesDeExemplo());
        RecyclerView recyclerView = findViewById(R.id.lista_leilao_recyclerview);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new ListaLeilaoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Leilao leilao) {
                Intent vaiParaLancesLeilao = new Intent(ListaLeilaoActivity.this, LancesLeilaoActivity.class);
                vaiParaLancesLeilao.putExtra("leilao", leilao);
                startActivity(vaiParaLancesLeilao);
            }
        });
    }

    private List<Leilao> leiloesDeExemplo() {
        final Leilao console = new Leilao("Console");

        final Usuario paulo = new Usuario("Paulo");
        final Usuario fran = new Usuario("Fran");

        console.propoe(
                new Lance(paulo, 200.0));
        console.propoe(
                new Lance(fran, 100.0));

        final Leilao monaliza = new Leilao("Monaliza");

        monaliza.propoe(new Lance(fran,200.0));
        monaliza.propoe(new Lance(paulo,400.0));
        monaliza.propoe(new Lance(fran,800.0));
        monaliza.propoe(new Lance(paulo,1000.0));

        return new ArrayList<>(Arrays.asList(
                console,
                monaliza
        ));
    }

}
