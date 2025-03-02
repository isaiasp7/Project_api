package P_api.DTO;

public class RelacionaDTRequest {//Usado em Disciplina controller para receber um object com dois campos que são {idDisciplina e idTurma}

        private long idDisciplina;
        private long idTurma;

        // Getters e Setters
        public long getIdDisciplina() {
            return idDisciplina;
        }

        public void setIdDisciplina(long idDisciplina) {
            this.idDisciplina = idDisciplina;
        }

        public long getIdTurma() {
            return idTurma;
        }

        public void setIdTurma(long idTurma) {
            this.idTurma = idTurma;
        }

}
