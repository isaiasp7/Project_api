package P_api.DTO;

import P_api.Model.Turma;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class LitlleTurmaDTO {
    private Long id;
    private String nome;

    public LitlleTurmaDTO(Turma turma) {
        this.id = turma.getId();
        this.nome = turma.getNome();

    }
}
