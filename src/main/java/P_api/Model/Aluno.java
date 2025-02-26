package P_api.Model;

import Util.Utilities;
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

    @Column(name="quantFalt",length = 3)
    private Integer Quant_faltas=0;

    @Column(name="email")
    private String email;

    //==========================================
    @OneToOne(mappedBy = "aluno_cpf", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)//relacionamento um para muitos

    private Matricula matriculas;


    /*======================================*/
    public Aluno(String cpf, String nome, Date dataNasci) {

        this.cpf = cpf;
        this.nome = nome;
        this.dataNasci = dataNasci;
        this.email = Utilities.gerar_email(this.nome);



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

    public Integer getQuant_faltas() {
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



    public Object getMatriculas() {
        if (matriculas != null) {
            return matriculas.getId();
        }else{
            return "Aluno ainda não associado a uma turma";
        }

    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
