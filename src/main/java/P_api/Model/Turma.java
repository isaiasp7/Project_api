package P_api.Model;

import P_api.Model.Matricula;
import Util.Utilities;
import com.fasterxml.jackson.annotation.*;
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
    private long id;

    @Column(length = 10,unique = true)
    private String nome_sala;


    private int capacidadeAtual;
    private int capacidadeMax;

    //====================================================
    //mappedBy = "turma" -> matricula vai possuir atributo indentificado de turma
    //cascade -> para não quebrar minhas perna com construct mais na frente
    //orphanRemoval -> remove matricula que não estão ligadas a ninguem
    @OneToMany(mappedBy = "turma", cascade = CascadeType.ALL, orphanRemoval = true)//relacionamento um para muitos
    @JsonProperty("Lista de matriculados na turma")
    private List<Matricula> matriculas = new ArrayList<>();

    //=====================================================
    @JsonProperty(" Lista de Disciplinas")
    @OneToMany(mappedBy = "turma")
    private List<Disciplina> disciplinaList = new ArrayList<>();

    //=====================================================


    public Turma() {
        this.id= Utilities.gerar_id("turma");
    }

    public Turma(Turma turma) {
        this.capacidadeMax = turma.getCapacidadeMax();
        this.id=Utilities.gerar_id("turma");
        this.nome_sala = turma.getNome_sala();;
        this.capacidadeAtual = 0;
    }

    public Turma(String nome,int capacidadeMax) {
        this.capacidadeMax = capacidadeMax;
        this.id=Utilities.gerar_id("turma");
        this.nome_sala = nome;
        this.capacidadeAtual = 0;
    }


/*
    public long getId() {
        return id;
    }
    public String getnome_sala() {
        return nome_sala;
    }

    public void setNomeSala(String nome) {
        this.nome_sala = nome;
    }

    public List<Matricula> getMatriculas() {
        return matriculas;
    }

    public void setMatriculas(List<Matricula> matriculas) {
        this.matriculas = matriculas;
    }

    public int getCapacidadeAtual() {
        return this.capacidadeAtual;
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


    public List<Disciplina> getDisciplinaList() {
        return disciplinaList;
    }

    public void setDisciplinaList(Disciplina disciplina) {
        this.disciplinaList.add(disciplina);
    }*/
}