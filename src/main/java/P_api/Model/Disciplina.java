package P_api.Model;

import Util.Utilities;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="disciplina")
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@JsonIgnoreProperties(ignoreUnknown = true)//ignora campos nulos, usado para update

public class Disciplina {
    @Id
    private long id;
    @Column(columnDefinition = "varchar(50)",nullable = false)
    @JsonMerge
    private String nome;
    @Column(nullable = false)
    private Integer cargaHoraria;

    //=======================PROFESSOR=========================

    @OneToOne(mappedBy = "disciplina_fk")//disciplina_id é nome do atributo na class= professor que recebera o valores

    private Professor professor;

    //====================== NOTAS ============================


    @OneToMany(mappedBy = "disciplinaN_fk")
    private List<Notas> notas = new ArrayList<>();

    //====================== TURMA ============================
    @ManyToOne
    @JoinColumn(name = "turma_fk")
    //@JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Turma turma;


//==============================================================
public Disciplina(){}

    /*public Disciplina() {
        this.id= Utilities.gerar_id("disciplina");
    }*/

    /*public Disciplina(Disciplina disciplina) {
        this.nome = disciplina.getNome();
        this.carga_horaria = disciplina.getCarga_horaria();
        this.id= Utilities.gerar_id("disciplina");
    }*/

    public Disciplina(String nome, int carga_h) {
        this.nome = nome;
        this.setCargaHoraria(carga_h);
        this.id=Utilities.gerar_id("disciplina");
    }

    public Disciplina(Disciplina disciplina) {

    this.nome = disciplina.nome;
    this.cargaHoraria = disciplina.cargaHoraria;
    this.id = Utilities.gerar_id("disciplina");
    this.professor = disciplina.professor;
    this.turma = disciplina.turma;
    this.notas = disciplina.notas;
    }




   public List<Object> getTurma() {
            if (turma != null) {
                List<Object> lista = new ArrayList<>();
                lista.add(turma.getId());
                lista.add(turma.getNome_sala());
                return  lista;
            }else{
                return null;
            }

    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }
    public List<Object> getProfessor() {
        if (professor != null) {
            List<Object> lista = new ArrayList<>();
            lista.add(professor.getId());
            lista.add(professor.getNome());
            return lista;
        }
        return null;
    }

    public void setCargaHoraria(Integer cargaHoraria) {
        if(cargaHoraria!=null){
            this.cargaHoraria = cargaHoraria;
        }

    }
}
