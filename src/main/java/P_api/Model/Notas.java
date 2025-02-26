package P_api.Model;

import P_api.Model.Disciplina;
import P_api.Model.Matricula;

import Util.Utilities;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.bind.Name;

import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name= "notas")
@Getter
@Setter
public class Notas {
    @Id
    @Column(unique = true, nullable = false)
    private long id;
    @Column(columnDefinition = "decimal(5, 2)")
    private float nota1;
    @Column(columnDefinition = "decimal(5, 2)")
    private float nota2;
    @Column(columnDefinition = "decimal(5, 2)")
    private float media;
    //===================MATRICULA=====================

    @ManyToOne
    @JoinColumn(name = "matricula_fk")

    private Matricula matricula_fk;


//             DISCIPLINA

    @ManyToOne
    @JoinColumn(name = "disciplinaN_fk")

    private Disciplina disciplinaN_fk;


    //====================================


    public Notas() {
        this.id=(int) Utilities.gerar_id("notas");
    }

    public Disciplina getDisciplina_fk() {
        return disciplinaN_fk;
    }

    public void setDisciplina_fk(Disciplina disciplina_fk) {
        this.disciplinaN_fk = disciplina_fk;
    }

    public long getId() {
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


    public float getMedia() {
        return media;
    }

    public void setMedia(float nota1,float nota2) {
        this.media=nota1+nota2/2;
    }

    public float getNota1() {
        return nota1;
    }

    public void setNota1(float nota1) {
        this.nota1 = nota1;
        if (nota1>=0 && nota2>=0) {
            this.setMedia(nota1,nota2);

        }
    }

    public float getNota2() {
        return nota2;
    }

    public void setNota2(float nota2) {

        this.nota2 = nota2;
        if (nota1>=0 && nota2>=0) {
            this.setMedia(nota1,nota2);

        }

    }
//========================================

}
