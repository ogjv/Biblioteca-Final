package br.edu.femass.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Exemplar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //private String codigoEx;
    private LocalDate dataAquisicao;

    @ManyToOne(cascade = CascadeType.ALL)
    private Livro livro; 

    public Exemplar(Livro livro) {
        this.livro = livro;
        this.dataAquisicao = LocalDate.now();
    }

    public Exemplar() {
        this.dataAquisicao = LocalDate.now();
    }
    public long getId() {
        return id;
    }


    public void setId(long id) {
        this.id = id;
    }


    public void setDataAquisicao(LocalDate dataAquisicao) {
        this.dataAquisicao = dataAquisicao;
    }


    public void setLivro(Livro livro) {
        this.livro = livro;
    }


    // public String getCodigoEx() {
    //     return codigoEx;
    // }

    public LocalDate getDataAquisicao() {
        return dataAquisicao;
    }

    public Livro getLivro() {
        return livro;
    }

    @Override
    public String toString() {
        return ("Exemplar do livro " + this.getLivro() + "-" + this.dataAquisicao);
    }


}