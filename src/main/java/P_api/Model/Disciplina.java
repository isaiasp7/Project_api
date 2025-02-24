package P_api.Model;

import Util.utilities;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name="disciplina")
@Getter
@Setter
public class Disciplina {
    @Id
    private int id;
    @Column(columnDefinition = "varchar(50)",nullable = false)
    private String nome;
    private int Carga_horaria;

    //=======================PROFESSOR=========================

    @OneToMany(mappedBy = "disciplina_fk")//disciplina é nome do atributo na class= professor que recebera o valores
    private List<Professor_responsavel> professor = new ArrayList<>();

    //                     NOTAS


    @OneToMany(mappedBy = "disciplinaN_fk")
    private List<Notas> notas = new ArrayList<>();
//==============================================================


    public Disciplina() {
        this.id=(int)utilities.gerar_id("disciplina");
    }


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


   /* public List<Professor_responsavel> getProfessor() {

        return professor;
    }

    public void setProfessor(List<Professor_responsavel> professor) {
        this.professor = professor;
    }*/
}
