package dac.cma.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Project {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToOne
    @JoinColumn(name = "student_id")
    private Student student;
    @OneToOne
    @JoinColumn(name = "orientador_id")
    private Teacher orientador;
    private String description;

    public Project() {}

    public Project(Long id, String name, Student student, Teacher orientador, String description) {
        this.id = id;
        this.name = name;
        this.student = student;
        this.orientador = orientador;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Teacher getOrientador() {
        return orientador;
    }

    public void setOrientador(Teacher orientador) {
        this.orientador = orientador;
    }
}
