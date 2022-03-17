package br.univates.luan.projeto_dicionario;

import java.util.Scanner;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

import br.univates.luan.projeto_dicionario.entities.Dicionario;
import br.univates.luan.projeto_dicionario.entities.Usuario;
import br.univates.luan.projeto_dicionario.utilities.JSONUtils;

public class App {

    private static boolean validaExistenciaUsuario(Usuario usuarioTentandoEntrar, JSONArray listaDeUsuarios) {
        boolean usuarioEncontrado = false;
        if (usuarioTentandoEntrar != null && listaDeUsuarios != null) {
            for(int i = 0; i < listaDeUsuarios.size(); i++) {
                JSONObject usuarioNoJson = (JSONObject) listaDeUsuarios.get(i);
                if (usuarioTentandoEntrar.getNomeDeUsuario().equals((String) usuarioNoJson.get("nome")) &&
                    usuarioTentandoEntrar.getSenha().equals((String) usuarioNoJson.get("senha"))) {
                        usuarioEncontrado = true;
                        break;
                } 
            }
        }
        return usuarioEncontrado;
    }
    
    public static void main(String[] args) {
        JSONArray jsonDeUsuarios = JSONUtils.readExternalJson("C:\\Users\\areia\\Documents\\Coisas do Luan\\Desenvolvimento\\Estudos\\projeto-dicionario\\src\\main\\java\\br\\univates\\luan\\projeto_dicionario\\entities\\usuarios.json");
        Scanner scanner = new Scanner(System.in);
        
        String nomeDeUsuario;
        String senhaDeAcesso;
        Usuario usuario = null;
        
        while(!validaExistenciaUsuario(usuario, jsonDeUsuarios)) {
            System.out.print("Insiria o nome de usuário: ");
            nomeDeUsuario = scanner.nextLine();
            System.out.print("Insira a senha: ");
            senhaDeAcesso = scanner.nextLine();

            usuario = new Usuario(nomeDeUsuario, senhaDeAcesso);

            if (validaExistenciaUsuario(usuario, jsonDeUsuarios)) {
                System.out.println("Logado como: " + usuario.getNomeDeUsuario() + "\n");
            } else {
                System.out.println("Usuário ou senha inválido!\n");
            }
        }
    }
}
