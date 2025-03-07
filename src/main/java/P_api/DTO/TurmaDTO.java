package P_api.DTO;

import P_api.Model.Turma;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class TurmaDTO {

        private Long id;
        private String nome;
        private List<MatriculaDTO> matriculas;
        private int capacidadeAtual;
        private int capacidadeMax;
        private List<DisciplinaDTO> disciplinaList;

    public TurmaDTO() {

    }

    public TurmaDTO(Turma turma) {
            this.id = turma.getId();
            this.nome = turma.getNome();
            this.matriculas = turma.getMatriculasDTO(); // Chama o método que retorna os DTOs
            this.capacidadeAtual = turma.getCapacidadeAtual();
            this.capacidadeMax = turma.getCapacidadeMax();
            this.disciplinaList = turma.getDisciplinaDTO();
    }

        // Getters e Setters


}
