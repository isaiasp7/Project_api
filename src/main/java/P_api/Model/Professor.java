package P_api.Model;


import P_api.Model.Disciplina;
import Util.Utilities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="profesor")
@Getter
@Setter
public class Professor {

    @Id
    @Column(unique = true, nullable = false)
    private long id;
    @Column(columnDefinition = "varchar(50)",unique = true, nullable = false)
    private String nome;
    @Column(columnDefinition = "varchar(50)",unique = true)
    private String email;
    private String telefone;

    //====================DISCIPLINA=======================
    @ManyToOne
    @JoinColumn(name = "disciplina_id")//cria uma tabela chamada disciplina_id em professor

    private Disciplina disciplina_fk;

    public Professor() {

    }
    public Professor(Professor professor) {
        this.email = Utilities.gerar_email(this.nome);
        this.disciplina_fk = professor.getDisciplina();
        this.id= Utilities.gerar_id("professor");
        this.nome = professor.getNome();
        this.telefone = professor.getTelefone();
    }

    public Professor(String nome, Disciplina disciplina_fk, String telefone) {
        this.email = Utilities.gerar_email(this.nome);
        this.disciplina_fk = disciplina_fk;
        this.id=(int)Utilities.gerar_id("professor");
        this.nome = nome;
        this.telefone = telefone;
    }


    public long getId() {
        return id;
    }


    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }


    public Disciplina getDisciplina() {
        return disciplina_fk;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina_fk = disciplina;
    }
}
