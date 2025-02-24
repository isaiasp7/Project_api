package P_api.Factory;

import P_api.Model.*;
import org.springframework.web.bind.annotation.RequestBody;

public class GenerateObj {
    //RECEBE UMA SCRING, CRIA A INSTANCIA E

    //TBM PODE RECEBER MAIS DE UM PARAMETRO COMO NOME E O QUE DESEJA FAZER

        public static Object createEntity(@RequestBody ) {
            return switch (entity) {
                case Aluno -> new Aluno();
                case "disciplina" -> new Disciplina();
                case "matricula" -> new Matricula();
                case "notas" -> new Notas();
                case "professor" -> new Professor_responsavel();
                case "turma" -> new Turma();
                default -> throw new IllegalArgumentException("Entidade desconhecida: " + entityType);
            };
        }


}
