package dac.cma.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Student extends User {
    @Column(name="cod_disciplina")
    private String codDisciplina;
    
    public Student() {}

    public Student(Long id, String name, String username, String password) {
        super(id, name, username, password);
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

    public String getCodDisciplina() {
        return codDisciplina;
    }

    public void setCodDisciplina(String codDisciplina) {
        this.codDisciplina = codDisciplina;
    }

}