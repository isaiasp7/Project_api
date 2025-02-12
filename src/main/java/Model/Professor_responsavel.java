package Model;


import jakarta.persistence.*;
import Model.metodos_globais.*;

@Entity
@Table(name="profesor_respons")
public class Professor_responsavel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int id;
    @Column(columnDefinition = "var", unique = true, nullable = false)
    private String nome;
    private String email;
    private String telefone;

    //====================DISCIPLINA=======================
    @ManyToOne
    @JoinColumn(name = "disciplina_id")//cria uma tabela chamada disciplina_id em professor
    private Disciplina disciplina_fk;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setEmail() {
         this.email = Mg.gerar_email(this.nome);

    }

    public Disciplina getDisciplina() {
        return disciplina_fk;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina_fk = disciplina;
    }
}
