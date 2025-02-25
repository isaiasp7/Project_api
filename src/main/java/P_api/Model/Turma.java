package P_api.Model;

import P_api.Model.Matricula;
import Util.utilities;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Entity
@Table(name = "turma")
@Getter
@Setter
public class Turma {
    @Id
    @Column(name = "id", unique = true, nullable = false)
    private int id;
    @Column(columnDefinition = "varchar(30)", nullable = false)
    private int serie;
    @Column(length = 10,unique = true)
    private String nome_sala;
    @Column(name = "capacidadeAtual")
    private int capacidadeAtual;


    @Column(name = "capacidadeMax")
    private int capacidadeMax;

    //====================================================
    //mappedBy = "turma" -> matricula vai possuir atributo indentificado de turma
    //cascade -> para não quebrar minhas perna com construct mais na frente
    //orphanRemoval -> remove matricula que não estão ligadas a ninguem
    @OneToMany(mappedBy = "turma", cascade = CascadeType.ALL, orphanRemoval = true)//relacionamento um para muitos

    private List<Matricula> matriculas = new ArrayList<>();

    //=====================================================


    public Turma() {
        this.id=(int)utilities.gerar_id("turma");
    }

    public Turma(Turma turma) {
        this.capacidadeMax = turma.getCapacidadeMax();
        this.id=(int)utilities.gerar_id("turma");
        this.nome_sala = turma.getNomeSala();;
        this.serie = turma.getSerie();
        this.capacidadeAtual = 0;
    }

    public Turma(String nome_sala,int serie,int capacidadeMax) {
        this.capacidadeMax = capacidadeMax;
        this.id=(int)utilities.gerar_id("turma");
        this.nome_sala = nome_sala;
        this.serie = serie;
        this.capacidadeAtual = 0;
    }

    public long getId() {
        return id;
    }

    public int getSerie() {
        return serie;
    }

    public void setSerie(int serie) {
        this.serie = serie;
    }

    public String getNomeSala() {
        return nome_sala;
    }

    public void setNomeSala(String nomeSala) {
        this.nome_sala = nomeSala;
    }

    public List<Matricula> getMatriculas() {
        return matriculas;
    }

    public void setMatriculas(List<Matricula> matriculas) {
        this.matriculas = matriculas;
    }

    public int getCapacidadeAtual() {
        return this.capacidadeMax;
    }

    public void setCapacidadeAtual(int capacidadeAtual) {
        this.capacidadeAtual = capacidadeAtual;
    }

    public int getCapacidadeMax() {
        return capacidadeMax;
    }

    public void setCapacidadeMax(int capacidadeMax) {
        this.capacidadeMax = capacidadeMax;
    }


}