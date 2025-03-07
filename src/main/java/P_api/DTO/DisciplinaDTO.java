package P_api.DTO;

import P_api.Model.Disciplina;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DisciplinaDTO {
    private Long id;
    private String nome;

    public DisciplinaDTO() {

    }
    public DisciplinaDTO(Disciplina disciplina) {
        this.id = disciplina.getId();
        this.nome = disciplina.getNome();
    }

    public DisciplinaDTO(long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

}

