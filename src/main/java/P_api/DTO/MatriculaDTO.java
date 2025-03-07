package P_api.DTO;

import P_api.Model.Aluno;
import P_api.Model.Matricula;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MatriculaDTO {
    private long id;
    private String nome;
    private AlunoDTO aluno;

    public MatriculaDTO(Matricula matricula) {
        this.id = matricula.getId();
        this.nome = matricula.getAluno_cpf().getNome();
        this.aluno = new AlunoDTO(matricula.getAluno_cpf());
    }

    public MatriculaDTO(long id,String nome, Aluno aluno) {
        this.id = id;
        this.nome = nome;
        this.aluno= new AlunoDTO(aluno);
    }

   /* public MatriculaDTO converterParaDTO(Matricula matricula) {
        return new MatriculaDTO(matricula.getId(), matricula.getAluno_cpf().getNome());
    }*/
}
