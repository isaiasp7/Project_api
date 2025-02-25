package P_api.Factory;

import P_api.Model.*;

public class GenerateObj {

    //RECEBE UMA SCRING, CRIA A INSTANCIA E

    //TBM PODE RECEBER MAIS DE UM PARAMETRO COMO NOME E O QUE DESEJA FAZER

        public static Object createEntity(String entity ) {
            return switch (entity) {
                case "aluno" -> new Aluno();
                case "disciplina" -> new Disciplina();
                case "matricula" -> new Matricula();
                case "notas" -> new Notas();
                case "professor" -> new Professor();
                case "turma" -> new Turma();
                default -> throw new IllegalArgumentException("Entidade desconhecida: " + entity);
            };
        }


}
