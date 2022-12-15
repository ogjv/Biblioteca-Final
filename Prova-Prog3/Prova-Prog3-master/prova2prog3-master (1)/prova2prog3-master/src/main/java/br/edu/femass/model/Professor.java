package br.edu.femass.model;
import javax.persistence.Entity;


@Entity
public class Professor extends Leitor {

    private String disciplina;

    public Professor() {
        setPrazoMaximoDevolucao(prazoMaximoDevolucao = 30);
    }

    public Professor(String nome, String endereco, String telefone, String disciplina) {
        super(nome, endereco, telefone);
        this.disciplina = disciplina;
        this.prazoMaximoDevolucao = 30;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public Integer getPrazoMaximoDevolucao() {
        return prazoMaximoDevolucao;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public String toString() {
        return (this.getNome() + " - " + this.getDisciplina());
    }

    public boolean equals(Object obj) {
        return getNome().equals(this.nome);
    }

}