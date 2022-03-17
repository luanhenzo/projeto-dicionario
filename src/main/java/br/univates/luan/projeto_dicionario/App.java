package br.univates.luan.projeto_dicionario;

import java.util.Scanner;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

import br.univates.luan.projeto_dicionario.entities.Dicionario;
import br.univates.luan.projeto_dicionario.entities.Palavra;
import br.univates.luan.projeto_dicionario.entities.Usuario;
import br.univates.luan.projeto_dicionario.utilities.JSONUtils;

public class App {

    private static final JSONArray LISTA_DE_USUARIOS = JSONUtils.readExternalJson("C:\\Users\\areia\\Documents\\Coisas do Luan\\Desenvolvimento\\Estudos\\projeto-dicionario\\src\\main\\java\\br\\univates\\luan\\projeto_dicionario\\entities\\usuarios.json");
    
    private static boolean validaExistenciaUsuario(Usuario usuarioTentandoEntrar) {
        boolean usuarioEncontrado = false;
        if (usuarioTentandoEntrar != null && LISTA_DE_USUARIOS != null) {
            for(int i = 0; i < LISTA_DE_USUARIOS.size(); i++) {
                JSONObject usuarioNoJson = (JSONObject) LISTA_DE_USUARIOS.get(i);
                if (usuarioTentandoEntrar.getNomeDeUsuario().equals((String) usuarioNoJson.get("nome")) &&
                    usuarioTentandoEntrar.getSenha().equals((String) usuarioNoJson.get("senha"))) {
                        usuarioEncontrado = true;
                        break;
                } 
            }
        }
        return usuarioEncontrado;
    }
    
    private static boolean iniciaApp() {
        boolean appDesligado = false;
        
        Dicionario dicionarioLuan = new Dicionario("O Grande Dicionário do Luan", "C:\\Users\\areia\\Documents\\Coisas do Luan\\Desenvolvimento\\Estudos\\projeto-dicionario\\src\\main\\java\\br\\univates\\luan\\projeto_dicionario\\entities\\dicionario.json");
        
        for (Palavra palavra : dicionarioLuan.getPalavras()) {
            System.out.println(palavra.getPalavra() + ": " + palavra.getSignificado());
            System.out.println("Fonte: " + palavra.getFonte() + "\n");
        }
        
        return appDesligado;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        String nomeDeUsuario;
        String senhaDeAcesso;
        Usuario usuario = null;
        
        while(!validaExistenciaUsuario(usuario)) {
            System.out.print("Insiria o nome de usuário: ");
            nomeDeUsuario = scanner.nextLine();
            System.out.print("Insira a senha: ");
            senhaDeAcesso = scanner.nextLine();

            usuario = new Usuario(nomeDeUsuario, senhaDeAcesso);

            if (validaExistenciaUsuario(usuario)) {
                System.out.println("Logado como: " + usuario.getNomeDeUsuario() + "\n");
                iniciaApp();
            } else {
                System.out.println("Usuário ou senha inválido!\n");
            }
        }
    }
}
