package br.com.alura.leilao.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Leilao implements Serializable {

    private final String descricao;
    private final List<Lance> lances;
    private double maiorLance = Double.NEGATIVE_INFINITY;
    private double menorLance = Double.POSITIVE_INFINITY;

    public Leilao(String descricao) {
        this.descricao = descricao;
        this.lances = new ArrayList<>();
    }

    public void propoe(Lance lance) {

        lances.add(lance);

        Collections.sort(lances);

        double valorLance = lance.getValor();
        if (valorLance > maiorLance) {
            maiorLance = valorLance;
        }
        if(valorLance < menorLance){
            menorLance = valorLance;
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
        if (size > 3){
            size = 3;
        }
        return lances.subList(0, size);
    }
}
