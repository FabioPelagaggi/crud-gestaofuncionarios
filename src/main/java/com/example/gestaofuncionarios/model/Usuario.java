package com.example.gestaofuncionarios.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Usuario {

    public Usuario() {
    }

    public Usuario(String id, String nome, String senha, String papel) {
        this.id = id;
        this.nome = nome;
        this.senha = senha;
        this.papel = papel;
    }

    @Id
    private String id;
    private String nome;
    private String senha;
    private String papel;

    // Getters
    public String getId() {
        return this.id;
    }

    public String getNome() {
        return this.nome;
    }

    public String getSenha() {
        return this.senha;
    }

    public String getPapel() {
        return this.papel;
    }

    // Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setPapel(String papel) {
        this.papel = papel;
    }
}
