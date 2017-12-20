package dac.cma.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Student extends User {
    @Column(name="cod_disciplina")
    private String codDisciplina = "tcc1";
    @OneToMany(mappedBy="student")
    private List<Project> projects;

    public Student() {}

    public Student(Long id, String name, String username, String password, List<Project> projects, String codDisciplina) {
        super(id, name, username, password);
        this.projects = projects;
        this.codDisciplina = codDisciplina;
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

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

}