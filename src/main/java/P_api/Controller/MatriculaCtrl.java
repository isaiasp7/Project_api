package P_api.Controller;

import P_api.DAO.Services.AlunoService;
import P_api.DAO.Services.MatricService;
import P_api.Model.Aluno;
import P_api.Model.Matricula;
import P_api.Model.Turma;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//============================================================================

@RestController
@RequestMapping("/MatriCrud")
public class MatriculaCtrl {
    @Autowired
    private MatricService matric;

    @Autowired
    private AlunoService aluno;

    //============== READ ===================

    @GetMapping
    public ResponseEntity<List<Matricula>> getAll() {
        try {
            List<Matricula> mat = matric.getMatriculas();
            if (mat.isEmpty()) {
                return ResponseEntity.noContent().build(); // Retorna 204 se a lista estiver vazia
            }
            return ResponseEntity.ok(mat); // Retorna 200 com a lista
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Retorna 500 em caso de erro
        }
    }


    @GetMapping("/getID/{id}")
    public ResponseEntity SearchId(@PathVariable int id) {
        Optional<Matricula> aluno = matric.seachID(id);
        return ResponseEntity.ok(aluno);

    }

    //================== RELACIONAMENTO =========================

    @PostMapping({"/cpf/{cpf}"})//cadastrar uma matricula a um aluno existente
    public ResponseEntity<?> create(@PathVariable String cpf,@RequestBody Matricula matricula) {
        try {
            Aluno inscrito = aluno.searchAluno(cpf);
            if (aluno == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno não encontrado com o CPF: " + cpf);
            }
            matricula.setAluno_cpf(inscrito);
            inscrito.setMatriculas(matricula);
            aluno.saveAlunos(inscrito);
            return ResponseEntity.ok(inscrito);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }



    @PutMapping("/relacoesMT")
    public ResponseEntity createRelacaoMT(@RequestParam int id, @RequestBody Turma dto) {
            matric.relacionarMT(id, dto);
            Matricula aluno = matric.seachID(id).orElseThrow(() -> new RuntimeException("Matrícula não encontrada"));
            ResponseEntity resposta = new ResponseEntity(aluno, HttpStatus.OK);
            return resposta;

    }







   // @PostMapping("/relacionamentoMT")//matricula e turma

}
