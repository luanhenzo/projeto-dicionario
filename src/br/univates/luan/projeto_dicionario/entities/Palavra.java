package br.univates.luan.projeto_dicionario.entities;

import br.univates.luan.projeto_dicionario.utilities.JSONUtils;

public class Palavra {
    
    private String palavra;
    private String significado;
    private String fonte;

    public Palavra(String palavra, String significado, String fonte) {
        this.palavra = palavra;
        this.significado = significado;
        this.fonte = fonte;
    }

    public String getPalavra() {
        return palavra;
    }

    public String getSignificado() {
        return significado;
    }

    public String getFonte() {
        return fonte;
    }

    public void setPalavra(String palavra) {
        this.palavra = palavra;
    }

    public void setSignificado(String significado) {
        this.significado = significado;
    }

    public void setFonte(String fonte) {
        this.fonte = fonte;
    }
}
