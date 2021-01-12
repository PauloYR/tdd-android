package br.com.alura.leilao.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.alura.leilao.exception.LanceMenorQueUltimoLanceException;
import br.com.alura.leilao.exception.LanceSeguidoDoMesmoUsuarioException;
import br.com.alura.leilao.exception.UsuarioJaDeuCincoLancesException;

public class Leilao implements Serializable {

    private final String descricao;
    private final List<Lance> lances;
    private double maiorLance = 0.0;
    private double menorLance = 0.0;

    public Leilao(String descricao) {
        this.descricao = descricao;
        this.lances = new ArrayList<>();
    }

    public void propoe(Lance lance) {
        validaLance(lance);

        lances.add(lance);

        double valorLance = lance.getValor();
        if (defineMaiorEMenorLanceParaOPrimeiroLance(valorLance)) return;

        Collections.sort(lances);

        calcularMaiorLance(valorLance);
    }

    private boolean defineMaiorEMenorLanceParaOPrimeiroLance(double valorLance) {
        if (lances.size() == 1) {
            menorLance = maiorLance = valorLance;
            return true;
        }
        return false;
    }

    private void validaLance(Lance lance) {
        if (lanceForMenorQueOUltimoLance(lance))
            throw new LanceMenorQueUltimoLanceException("lance for menor que o ultimo lance");
        if (temLances()) {
            Usuario usuarioNovo = lance.getUsuario();
            if (usuarioForOMesmoDoUltimoLance(usuarioNovo))
                throw new LanceSeguidoDoMesmoUsuarioException();
            if (usuarioDeuCincoLances(usuarioNovo)) throw new UsuarioJaDeuCincoLancesException();
        }
    }

    private boolean temLances() {
        return !lances.isEmpty();
    }

    private boolean usuarioDeuCincoLances(Usuario usuarioNovo) {
        int lancesDoUsuario = 0;
        for (Lance l : lances) {
            Usuario usuarioExistente = l.getUsuario();
            if (usuarioExistente.equals(usuarioNovo)) {
                lancesDoUsuario++;
                if (lancesDoUsuario == 5)
                    return true;
            }
        }
        return false;
    }

    private boolean usuarioForOMesmoDoUltimoLance(Usuario usuarioNovo) {
        Usuario ultimoUsuario = lances.get(0).getUsuario();
        if (usuarioNovo.equals(ultimoUsuario)) {
            return true;
        }
        return false;
    }

    private boolean lanceForMenorQueOUltimoLance(Lance lance) {
        if (maiorLance > lance.getValor()) {
            return true;
        }
        return false;
    }

    private void calcularMaiorLance(double valorLance) {
        if (valorLance > maiorLance) {
            maiorLance = valorLance;
        }
    }


    public double getMaiorLance() {
        return maiorLance;
    }

    public double getMenorLance() {
        return menorLance;
    }

    public void setMaiorLance(double maiorLance) {
        this.maiorLance = maiorLance;
    }

    public String getDescricao() {
        return descricao;
    }

    public List<Lance> tresMaioresLances() {
        int size = lances.size();
        if (size > 3) {
            size = 3;
        }
        return lances.subList(0, size);
    }

    public int quantidaDeLances() {
        return lances.size();
    }
}
