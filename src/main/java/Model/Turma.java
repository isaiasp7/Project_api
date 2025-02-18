package Model;

import Util.utilities;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Entity
@Table(name = "turma")
public class Turma {
    @Id
    @Column(name = "id", unique = true, nullable = false)
    private long id;
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
        this.id=utilities.gerar_id("turma");
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

    public String getCapacidadeAtual() {
        return "A capacidade atual da sala é de : " + capacidadeAtual+"\nSendo lotação total de :"+capacidadeMax;
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