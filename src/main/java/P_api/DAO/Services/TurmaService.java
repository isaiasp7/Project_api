package P_api.DAO.Services;

import P_api.DAO.ClassRepository.TurmasRepository;
import P_api.Model.Turma;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class TurmaService {

    TurmasRepository turmas;

    public Turma createTurma(Turma turma) {
        Turma newTurma = new Turma(turma);
        return turmas.save(newTurma);

    }

    public ResponseEntity<?> alterarCapacidadeA(int id, int novaCapacidade) {
        // Encontrar a sala pelo id
        Optional<Turma> salaOptional = turmas.findById((long) id);

        if (salaOptional.isPresent()) {
            Turma sala = salaOptional.get();
            // Atualiza a capacidade
            sala.setCapacidadeAtual(novaCapacidade);

            // Salva as alterações no banco
            turmas.save(sala);
            return ResponseEntity.ok(turmas.findById((long) id));
        } else {
            throw new RuntimeException("Sala não encontrada!");
        }
    }
}
