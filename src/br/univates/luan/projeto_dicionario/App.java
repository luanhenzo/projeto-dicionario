package br.univates.luan.projeto_dicionario;

import java.util.Scanner;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

import br.univates.luan.projeto_dicionario.entities.Dicionario;
import br.univates.luan.projeto_dicionario.entities.Palavra;
import br.univates.luan.projeto_dicionario.entities.Usuario;
import br.univates.luan.projeto_dicionario.utilities.JSONUtils;
import br.univates.luan.projeto_dicionario.views.LoginView;

public class App {
    
    public static void main(String[] args) {
        new LoginView().setVisible(true);
    }
}
