package br.univates.luan.projeto_dicionario.entities;

import br.univates.luan.projeto_dicionario.utilities.JSONUtils;

public class Palavra {
    
    private String palavra;
    private String significado;
    private String fonte;
    private Dicionario dicionarioFonte;

    public Palavra(String palavra, String significado, String fonte) {
        this.palavra = palavra;
        this.significado = significado;
        this.fonte = fonte;
        this.dicionarioFonte = null;
    }

    public String getPalavra() {
        return palavra;
    }

    public void setPalavra(String palavra) {
        this.palavra = palavra;
    }

    public String getSignificado() {
        return significado;
    }

    public void setSignificado(String significado) {
        this.significado = significado;
    }

    public String getFonte() {
        return fonte;
    }

    public void setFonte(String fonte) {
        this.fonte = fonte;
    }

    public Dicionario getDicionarioFonte() {
        return dicionarioFonte;
    }

    public void setDicionarioFonte(Dicionario dicionarioFonte) {
        this.dicionarioFonte = dicionarioFonte;
    }
}
