package P_api.Model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "aluno")
@Getter
@Setter

public class Aluno {
    @Id
    @NotNull//Validação em nível de aplicação: Garante que o campo não seja null antes de a entidade ser persistida no banco de dados.
    @Column(name="cpf",length = 11,unique = true,nullable = false)//Define restrições no banco de dados: Se nullable = false, cria a coluna no banco como NOT NULL.
    private String cpf;

    @Column(name="nome",columnDefinition = "varchar(50)", nullable = false)
    private String nome;

    @Column(columnDefinition = "date", name="dataNasc",length = 10,nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dataNasci;

    @Column(name="quantFalt",length = 3, nullable = false)
    private int Quant_faltas;

    //==========================================
    @OneToOne(mappedBy = "aluno_cpf", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)//relacionamento um para muitos

    private Matricula matriculas;


    /*======================================*/
    public Aluno(String cpf, String nome, Date dataNasci) {
        this.cpf = cpf;
        this.nome = nome;
        this.dataNasci = dataNasci;


    }
    public Aluno() {

    }


    public Date getDataNasci() {
        return dataNasci;
    }

    public void setDataNasci(Date dataNasci) {
        this.dataNasci = dataNasci;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuant_faltas() {
        return Quant_faltas;
    }

    public void setQuant_faltas(int quant_faltas) {
        Quant_faltas = quant_faltas;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @JsonGetter("matriculas")
    public Object getMatriculasJson() {
        return (matriculas != null) ? matriculas : "Aluno não cadastrado em uma turma";
    }

   /* public int getMatriculas() {
        return matriculas.getId();
    }

    public void setMatriculas(int matriculas) {
        this.matriculas.setId(matriculas);
    }*/
}
