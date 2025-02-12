package Model;

import jakarta.persistence.*;
import org.springframework.boot.context.properties.bind.Name;

import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name= "notas")
public class Notas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int id;
    //===================MATRICULA=====================

    @ManyToOne
    @JoinColumn(name = "matricula_fk")
    private Matricula matricula_fk;

    //             DISCIPLINA

    @ManyToOne
    @JoinColumn(name = "disciplinaN_fk")
    private Disciplina disciplinaN_fk;

    public Disciplina getDisciplina_fk() {
        return disciplinaN_fk;
    }

    public void setDisciplina_fk(Disciplina disciplina_fk) {
        this.disciplinaN_fk = disciplina_fk;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Matricula getMatricula_fk() {
        return matricula_fk;
    }

    public void setMatricula_fk(Matricula matricula_fk) {
        this.matricula_fk = matricula_fk;
    }
//========================================

}
