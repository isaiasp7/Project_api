package P_api.Model;


import P_api.Model.Disciplina;
import Util.utilities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="profesor_respons")
@Getter
@Setter
public class Professor_responsavel{

    @Id
    @Column(unique = true, nullable = false)
    private int id;
    @Column(columnDefinition = "varchar(50)",unique = true, nullable = false)
    private String nome;
    @Column(columnDefinition = "varchar(50)",unique = true)
    private String email;
    private String telefone;

    //====================DISCIPLINA=======================
    @ManyToOne
    @JoinColumn(name = "disciplina_id")//cria uma tabela chamada disciplina_id em professor

    private Disciplina disciplina_fk;

    public Professor_responsavel() {
        this.id=(int)utilities.gerar_id("professor");
        this.email = utilities.gerar_email(this.nome);
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
