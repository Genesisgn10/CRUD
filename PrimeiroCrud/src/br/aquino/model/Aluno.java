/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.aquino.model;

/**
 *
 * @author Genesis
 */
public class Aluno {
    private int id;
    private String email;
    private String nome;
    private String cpf;

    
    public Aluno(int id, String email, String nome, String cpf) {
        this.id = id;
        this.email = email;
        this.nome = nome;
        this.cpf=cpf;
    }
    
      public Aluno( String email, String nome, String cpf) {
        this.email = email;
        this.nome = nome;
        this.cpf=cpf;
    }
      
    
       
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

   
    
}
