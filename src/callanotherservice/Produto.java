/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package callanotherservice;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author richard
 */
@Entity
public class Produto implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private String nome;
    private int preco;
    @ManyToOne
    @JoinColumn(name="id_loja", nullable=false)
    private Loja loja;

    public Produto() {
    }

    public Produto(Long id, String nome, int preco, Loja loja) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.loja = loja;
    }

    public Produto(String nome, int preco, Loja loja) {
        this.nome = nome;
        this.preco = preco;
        this.loja = loja;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPreco() {
        return preco;
    }

    public void setPreco(int preco) {
        this.preco = preco;
    }

    public Loja getLoja() {
        return loja;
    }

    public void setLoja(Loja loja) {
        this.loja = loja;
    }
}
