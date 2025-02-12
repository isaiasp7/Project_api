package Model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import Model.StatusEnums.StatusEnum.Status;  // Importando a enum Status
import org.antlr.v4.runtime.misc.NotNull;

@Table(name="matricula")
@Entity
public class Matricula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id", unique = true, nullable = false)
    private long id;
    private Date dataMatricula;
    @Enumerated(EnumType.STRING)//traduz o valor de status para enum
    @NotNull
    @Column(nullable = false)
    private Status status;

    //=============== ALUNO =================

    @OneToOne   //representa uma relação de um para um. como é o caso de uma matricula para cada aluno
    @JoinColumn(name = "aluno_cpf")//junção de colunas && uma nova tabela chamada ALUNO_CPF sera gerada possuindo apenas a FK
    private Aluno aluno_cpf;

    //                 TURMAS

    @ManyToOne   //representa uma relação de um para um. como é o caso de uma matricula para cada aluno
    @JoinColumn(name = "turma_id")//junção de colunas && uma nova tabela chamada turma_id sera gerada possuindo apenas a FK
    private Turma turma;


    //                 NOTAS

    @OneToMany(mappedBy = "matricula_fk")
    private List<Notas> notas = new ArrayList<>();

    //=====================================

    //construtor
    public Matricula() {
        this.id =  ThreadLocalRandom.current().nextInt(10000, 99999);//gerar entre intrevalos. essa vai gerar um id de length =5
    }



    //metodos
    public Date getDataMatricula() {
        return dataMatricula;
    }

    public void setDataMatricula(Date dataMatricula) {
        this.dataMatricula = dataMatricula;
    }

    public Aluno getAluno() {
        return aluno_cpf;
    }

    public long getId() {
        return id;
    }

    public void setStatus(Status status) {
        // Usa Enum.valueOf() para verificar se o status é válido
        try {
            this.status = Status.valueOf(status.name());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Status inválido!");
        }
    }

    public Status getStatus() {
        return status;
    }





}
