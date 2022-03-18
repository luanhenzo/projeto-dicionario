/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.univates.luan.projeto_dicionario.entities;

public class Usuario {
    
    private String nomeDeUsuario;
    private String senha;

    public Usuario(String nomeDeUsuario, String senha) {
        this.nomeDeUsuario = nomeDeUsuario;
        this.senha = senha;
    }

    public String getNomeDeUsuario() {
        return nomeDeUsuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setNomeDeUsuario(String nomeDeUsuario) {
        this.nomeDeUsuario = nomeDeUsuario;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
}
