package br.univates.luan.projeto_dicionario.entities;

import java.util.ArrayList;
import br.univates.luan.projeto_dicionario.utilities.JSONUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Dicionario {

    private final String enderecoDicionario;
    private final String nome;
    private final ArrayList<Palavra> palavras;

    public Dicionario(String enderecoDicionario) {
        this.enderecoDicionario = enderecoDicionario;
        JSONObject dicionarioJson = JSONUtils.readExternalJsonObject(this.enderecoDicionario);
        this.nome = (String) dicionarioJson.get("nome");

        this.palavras = new ArrayList<>();
        JSONArray palavrasDicionario = (JSONArray) dicionarioJson.get("palavras");
        for (Object palavra : palavrasDicionario) {
            JSONObject palavraJsonObject = (JSONObject) palavra;
            String palavraNome = (String) palavraJsonObject.get("palavra");
            String palavraSignificado = (String) palavraJsonObject.get("significado");
            String palavraFonte = (String) palavraJsonObject.get("fonte");
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