package dac.cma.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
public class Project {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @OneToOne
    @JoinColumn(name = "student_id")
    private Student student;
    @OneToOne
    @JoinColumn(name = "orientador_id")
    private Teacher orientador;
    private String description;
    @Column(nullable = false)
    private int status; //0 = nao ativo, 1 = ativo, 2 = fim do semestre, 3 = defesa, 4 = finalizado
    private float nota;
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(iso = ISO.DATE)
    private Date dt_defense;

    public Project() {}

    public Project(Long id, String name, Student student, Teacher orientador, String description, int status, float nota) {
        this.id = id;
        this.name = name;
        this.student = student;
        this.orientador = orientador;
        this.description = description;
        this.status = status;
        this.nota = nota;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public float getNota() {
        return nota;
    }

    public void setNota(float nota) {
        this.nota = nota;
    }

    public Date getDt_defense() {
        return dt_defense;
    }

    public void setDt_defense(Date dt_defense) {
        this.dt_defense = dt_defense;
    }
}
