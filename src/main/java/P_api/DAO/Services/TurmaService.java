package P_api.DAO.Services;

import P_api.DAO.ClassRepository.TurmasRepository;
import P_api.Model.Turma;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class TurmaService {
    @Autowired
    TurmasRepository turmaRepository;
    @Autowired
    private TurmasRepository turmasRepository;

    public List<Turma> getTurmas() {
        List<Turma> sala =turmasRepository.findAll();
        return sala;
    }

    public Turma createTurma(Turma turma) {

        Turma newTurma = new Turma(turma);

        return turmasRepository.save(newTurma);

    }

    public ResponseEntity<?> alterarCapacidadeA(int id, int novaCapacidade) {
        // Encontrar a sala pelo id
        Optional<Turma> salaOptional = turmasRepository.findById((long) id);

        if (salaOptional.isPresent()) {
            Turma sala = salaOptional.get();
            // Atualiza a capacidade
            sala.setCapacidadeAtual(novaCapacidade);

            // Salva as alterações no banco
            turmasRepository.save(sala);
            return ResponseEntity.ok(turmasRepository.findById((long) id));
        } else {
            throw new RuntimeException("Sala não encontrada!");
        }
    }
}
