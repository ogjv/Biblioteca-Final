package br.edu.femass.model;

import javax.persistence.Entity;

@Entity
public class Aluno extends Leitor {

    private String matricula;

    public Aluno() {
        setPrazoMaximoDevolucao(prazoMaximoDevolucao = 15);
    }

    public Aluno(String nome, String endereco, String telefone, String matricula) {
        super(nome, endereco, telefone);
        this.matricula = matricula;
        this.prazoMaximoDevolucao = 15;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getMatricula() {
        return matricula;
    }

    public Integer getPrazoMaximoDevolucao() {
        return prazoMaximoDevolucao;
    }

    public String toString() {
        return (this.getNome() + " - " + this.matricula);
    }

    public boolean equals(Object obj) {
        return getMatricula().equals(this.matricula);
    }
}
