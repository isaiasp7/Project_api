package Model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name="disciplina")
public class Disciplina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private int Carga_horaria;

    //=======================PROFESSOR=========================

    @OneToMany(mappedBy = "disciplina_fk") //disciplina é nome do atributo na class= professor que recebera o valores
    private List<Professor_responsavel> professor = new ArrayList<>();

    //                     NOTAS

    @OneToMany(mappedBy = "disciplinaN_fk")
    private List<Notas> notas = new ArrayList<>();



    public int getCarga_horaria() {
        return Carga_horaria;
    }

    public void setCarga_horaria(int carga_horaria) {
        Carga_horaria = carga_horaria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Professor_responsavel> getProfessor() {
        return professor;
    }

    public void setProfessor(List<Professor_responsavel> professor) {
        this.professor = professor;
    }
}
