package br.univates.luan.projeto_dicionario.entities;

import java.util.ArrayList;
import br.univates.luan.projeto_dicionario.utilities.JSONUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Dicionario {
    
    private final String nome;
    private final ArrayList<Palavra> palavras = new ArrayList<>();
    private final String enderecoDicionario;

    public Dicionario(String nome, String enderecoDicionario) {
        this.nome = nome;
        this.enderecoDicionario = enderecoDicionario;
        
        JSONArray palavrasJsonArray = JSONUtils.readExternalJson(this.enderecoDicionario);
        
        for (Object palavraJson : palavrasJsonArray) {
            JSONObject palavra = (JSONObject) palavraJson;
            String palavraNome = (String) palavra.get("palavra");
            String palavraSignificado = (String) palavra.get("significado");
            String palavraFonte = (String) palavra.get("fonte");
            this.palavras.add(new Palavra(palavraNome, palavraSignificado, palavraFonte));
        }
    }

    public String getNome() {
        return nome;
    }
    
    public String getEnderecoDicionario() {
        return this.enderecoDicionario;
    }
    
    public ArrayList<Palavra> getPalavras() {
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