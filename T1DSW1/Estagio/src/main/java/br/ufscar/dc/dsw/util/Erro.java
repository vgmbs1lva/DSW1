package br.ufscar.dc.dsw.util;

import java.util.ArrayList;
import java.util.List;

public class Erro {

    private final List<String> erros;

    public Erro() {
        erros = new ArrayList<>();
    }

    public void add(String mensagem) {
        erros.add(mensagem);
    }

    public boolean isExisteErros() {
        return !erros.isEmpty();
    }

    public List<String> getErros() {
        return erros;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String erro : erros) {
            sb.append(erro).append("<br>");
        }
        return sb.toString();
    }
}
