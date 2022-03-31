package br.univates.luan.projeto_dicionario.entities;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
        for (Object p : palavrasDicionario) {
            JSONObject palavraJsonObject = (JSONObject) p;
            String palavraNome = (String) palavraJsonObject.get("palavra");
            String palavraSignificado = (String) palavraJsonObject.get("significado");
            String palavraFonte = (String) palavraJsonObject.get("fonte");
            Palavra palavra = new Palavra(palavraNome, palavraSignificado, palavraFonte);
            palavra.setDicionarioFonte(this);
            this.palavras.add(palavra);
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

    public Palavra getPalavra(String palavra) {
        Palavra palavraSelecionada = null;
        for (Palavra p : palavras) {
            if (p.getPalavra().equalsIgnoreCase(palavra)) {
                palavraSelecionada = p;
                break;
            }
        }
        return palavraSelecionada;
    }
    
    public boolean addPalavra(Palavra palavra) {
        boolean palavraAdicionadaComSucesso =  false;
        JSONObject dicioJo = JSONUtils.readExternalJsonObject(getEnderecoDicionario());
        JSONArray dicioPalavras = (JSONArray) dicioJo.get("palavras");

        boolean palavraExiste = false;
        for (Object p : dicioPalavras) {
            JSONObject pJo = (JSONObject) p;
            if (pJo.get("palavra").equals(palavra.getPalavra())) {
                palavraExiste = true;
                break;
            }
        }

        if (!palavraExiste) {
            JSONObject palavraJo = new JSONObject();

            palavraJo.put("palavra", palavra.getPalavra());
            palavraJo.put("significado", palavra.getSignificado());
            palavraJo.put("fonte", palavra.getFonte());

            dicioPalavras.add(palavraJo);

            dicioJo.put("palavras", dicioPalavras);
            try {
                Files.write(Paths.get(getEnderecoDicionario()), dicioJo.toJSONString().getBytes());
                this.palavras.add(palavra);
                palavra.setDicionarioFonte(this);
                palavraAdicionadaComSucesso = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return palavraAdicionadaComSucesso;
    }
    
    public boolean removePalavra(Palavra palavra) {
        JSONObject dicioJo = JSONUtils.readExternalJsonObject(getEnderecoDicionario());
        JSONArray dicioPalavras = (JSONArray) dicioJo.get("palavras");

        boolean palavraExiste = false;
        int index = 0;
        for (Palavra p : getPalavras()) {
            if (p.getPalavra().equals(palavra.getPalavra())) {
                palavraExiste = true;
                break;
            }
            index++;
        }

        if (palavraExiste) {
            dicioPalavras.remove(index);
            dicioJo.put("palavras", dicioPalavras);
            try {
                Files.write(Paths.get(getEnderecoDicionario()), dicioJo.toJSONString().getBytes());
                this.palavras.remove(palavra);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return palavraExiste;
    }
    
}