package P_api.DTO;

import P_api.Model.Professor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfDiscDTO {
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private DisciplinaDTO disciplina;

    public static ProfDiscDTO toProfessorDTO(Professor professor) {
        ProfDiscDTO dto = new ProfDiscDTO();
        dto.setId(professor.getId());
        dto.setNome(professor.getNome());
        dto.setEmail(professor.getEmail());
        dto.setTelefone(professor.getTelefone());

        DisciplinaDTO disciplinaDTO = new DisciplinaDTO();
        disciplinaDTO.setId(professor.getDisciplina().getId());
        disciplinaDTO.setNome(professor.getDisciplina().getNome());
        disciplinaDTO.setCargaHoraria(professor.getDisciplina().getCargaHoraria());

        dto.setDisciplina(disciplinaDTO); // Agora, a disciplina não terá o professor dentro dela

        return dto;
    }
}

