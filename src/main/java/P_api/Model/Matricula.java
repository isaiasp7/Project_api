package P_api.Model;

import P_api.Model.Aluno;
import P_api.Model.StatusEnums.StatusEnum;
import Util.Utilities;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

 // Importando a enum Status
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

@Table(name="matricula")
@Entity
@Getter
@Setter

public class Matricula {
    @Id
    @Column(name ="id", unique = true, nullable = false)
    private long id;

    @Column(columnDefinition = "date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dataMatricula;

    @Enumerated(EnumType.STRING)//traduz o valor de status para enum
    @NotNull
    @Column(nullable = false)
    private StatusEnum.Status status;

    //=============== ALUNO =================

    @OneToOne   //representa uma relação de um para um. como é o caso de uma matricula para cada aluno
    @JoinColumn(name = "aluno_cpf")//junção de colunas && uma nova tabela chamada ALUNO_CPF sera gerada possuindo apenas a FK
    @JsonIgnore
    private Aluno aluno_cpf;

    //                 TURMAS

    @ManyToOne
    @JoinColumn(name = "turma_id")//junção de colunas && uma nova tabela chamada turma_id sera gerada possuindo apenas a FK
    @JsonIgnore
    private Turma turma;


    //                 NOTAS

    @OneToMany(mappedBy = "matricula_fk")

    private List<Notas> notas = new ArrayList<>();

    //=====================================

    //construtor
    public Matricula() {

        this.id= (int) Utilities.gerar_id("matricula");
    }

    public Matricula(Date dataMatricula, StatusEnum.Status status, Turma turma) {

        this.dataMatricula = dataMatricula;
        this.id= (int) Utilities.gerar_id("matricula");
        if(this.getAluno().getEmail()==null){
            this.getAluno().setEmail(Utilities.gerar_email(this.getAluno().getNome()));
        }

        this.status = status;
        this.turma = turma;
        this.turma.setCapacidadeAtual(this.turma.getCapacidadeAtual()+1);
    }
//=============================================================


    public Date getDataMatricula() {
        return dataMatricula;
    }

    public void setDataMatricula(Date dataMatricula) {
        this.dataMatricula = dataMatricula;
    }


    public long getId() {
        return id;
    }



    public void setStatus(StatusEnum.Status status) {
        // Usa Enum.valueOf() para verificar se o status é válido
        try {
            this.status = StatusEnum.Status.valueOf(status.name());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Status inválido!");
        }
    }

    public StatusEnum.Status getStatus() {
        return status;
    }

    public Aluno getAluno() {
        return this.aluno_cpf;
    }


}
