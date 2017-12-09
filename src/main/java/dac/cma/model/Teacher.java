package dac.cma.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Teacher extends User {
    private String titulacao;
    @ManyToOne
    @JoinColumn(name="banca_id")
    private ExaminingBoard board;
    
    public Teacher() {}

    public Teacher(String titulacao, ExaminingBoard board, Long id, String name, String username, String password) {
        super(id, name, username, password);
        this.titulacao = titulacao;
        this.board = board;
    }

    public String getTitulacao() {
        return titulacao;
    }

    public void setTitulacao(String titulacao) {
        this.titulacao = titulacao;
    }
    
    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }
}
