package br.univates.luan.projeto_dicionario.entities;

import java.util.List;

public class Dicionario {
    
    private final String nome;
    private List<Palavra> palavras;
    private String enderecoDicionario;

    public Dicionario(String nome, String enderecoDicionario) {
        this.nome = nome;
        this.enderecoDicionario = enderecoDicionario;
    }

    public String getNome() {
        return nome;
    }

    public List<Palavra> getAllPalavras() {
        return palavras;
    }
    
    public Palavra getPalavra(int index) {
        return palavras.get(index);
    }
    
    public void addPalavra(Palavra palavra) {
        this.palavras.add(palavra);
    }
    
    public void removePalavra(Palavra palavra) {
        this.palavras.remove(palavra);
    }
    
}
