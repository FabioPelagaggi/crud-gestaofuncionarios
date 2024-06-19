package com.example.gestaofuncionarios.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Departamento {

    public Departamento() {
    }

    public Departamento(long id, String nome, String local) {
        this.id = id;
        this.nome = nome;
        this.local = local;
    }

    public Departamento(String nome, String local) {
        this.nome = nome;
        this.local = local;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String local;

    @OneToMany(mappedBy = "departamento")
    private List<Funcionario> funcionarios;

    // Getters
    public Long getId() {
        return this.id;
    }

    public String getNome() {
        return this.nome;
    }

    public String getLocal() {
        return this.local;
    }

    public List<Funcionario> getFuncionarios() {
        return this.funcionarios;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

}
