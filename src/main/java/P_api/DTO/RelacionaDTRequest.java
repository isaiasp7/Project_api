package P_api.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RelacionaDTRequest {//Usado em Disciplina controller para receber um object com dois campos que são {idDisciplina e idTurma}

        private long idDisciplina;
        private long idTurma;


}
