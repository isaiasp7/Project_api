package P_api.Controller;

import P_api.DAO.Services.AlunoService;
import P_api.Factory.GenerateObj;
import P_api.Model.Aluno;
import P_api.Model.Matricula;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import P_api.Factory.GenerateObj;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;


import static P_api.Factory.GenerateObj.createEntity;
import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("/alunoCrud")
public class AlunoCtrl {
    @Autowired
    private AlunoService alunoS;

    //============== GET =====================
    @GetMapping("/getAll")
    public ResponseEntity<List<Aluno>> mostrarAlunos() {
        var alunos = alunoS.getAlunos();
        return ResponseEntity.ok(alunos);
    }

    @GetMapping("/getCpf")
    public ResponseEntity SearchCpf(@RequestParam String cpf) {
        var lista = alunoS.SearchAluno(cpf);
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/getID/{id}")
    public ResponseEntity SearchId(@PathVariable int id) {
        return ResponseEntity.ok(alunoS.searchAlunoId(id));
    }




    //============== SET =====================

    @PostMapping("create")
    public ResponseEntity<Aluno> cadastrarAluno(@RequestBody Aluno aluno) {
        aluno.setMatriculas(new Matricula());
        alunoS.addAluno(aluno);

        return ResponseEntity.status(HttpStatus.CREATED).body(aluno);
    }



    //================ UPDATE ========================
    //



    //================ DELETE ========================
    @GetMapping("/delete")
    public ResponseEntity<String> deletarAluno(@RequestParam int id) {
        Aluno temp_aluno =alunoS.searchAlunoId(id);
        Aluno delA = new Aluno(temp_aluno);
        Boolean delBoolean = alunoS.deleteAluno(id);
        if (delBoolean) {
            return ResponseEntity.ok("Aluno "+delA.getNome()+" com id : "+id+" removido do sistema.");
        }
        return ResponseEntity.ok("O id inserido não existe");
    }
}
