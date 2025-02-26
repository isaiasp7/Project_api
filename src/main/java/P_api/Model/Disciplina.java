package P_api.Model;

import Util.Utilities;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name="disciplina")
@Getter
@Setter
//Deu ruim
public class Disciplina {
    @Id
    private long id;
    @Column(columnDefinition = "varchar(50)",nullable = false)
    private String nome;

    @JsonProperty("Carga_Horária")
    private int cargaHoraria;

    //=======================PROFESSOR=========================

    @OneToMany(mappedBy = "disciplina_fk")//disciplina é nome do atributo na class= professor que recebera o valores
    private List<Professor> professor = new ArrayList<>();

    //====================== NOTAS ============================


    @OneToMany(mappedBy = "disciplinaN_fk")
    private List<Notas> notas = new ArrayList<>();

    //====================== TURMA ============================
    @ManyToOne
    @JoinColumn(name = "turma_fk")
    private Turma turma;


//==============================================================


    public Disciplina() {
        this.id= Utilities.gerar_id("disciplina");
    }

    public Disciplina(Disciplina disciplina) {
        this.nome = disciplina.getNome();
        this.cargaHoraria = disciplina.getCargaHoraria();
        this.id= Utilities.gerar_id("disciplina");
    }

    public Disciplina(String nome, int cargaHoraria) {
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
        this.id=Utilities.gerar_id("disciplina");
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getId() {
        return this.id;
    }

    public List<Professor> getProfessor() {
        return professor;
    }

    public List<Object> getTurma() {
        List<Object>  lista = new ArrayList<>();
        lista.add(turma.getId());
        lista.add(turma.getnome_sala());
        return lista;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public int getCargaHoraria() {
        return this.cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria= cargaHoraria;
    }
}
