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

import java.util.List;

@RestController
@RequestMapping("/alunoCrud")
@JsonIgnoreProperties(ignoreUnknown = true)
public class AlunoCtrl {
    @Autowired
    private AlunoService alunoS;

    //============== GET =====================
    @GetMapping("/getAl")
    public ResponseEntity<List<Aluno>> mostrarAlunos() {
        var alunos = alunoS.getAlunos();
        return ResponseEntity.ok(alunos);
    }

    @GetMapping("/getCpf")
    public ResponseEntity SearchCpf(@RequestParam String cpf) {
        var lista = alunoS.SearchAluno(cpf);
        return ResponseEntity.ok(lista);
    }




    //============== SET =====================

    @PostMapping
    public ResponseEntity<Aluno> cadastrarAluno(@RequestBody Object aluno) {
        GenerateObj("aluno") novoAluno = alunoS.addAluno(aluno);
        novoAluno.setMatriculas(new Matricula());
        return ResponseEntity.status(HttpStatus.CREATED).body(novoAluno);
    }



    //================ UPDATE ========================

/*    @PostMapping("/alterNome")
    public ResponseEntity alterAluno(@RequestBody Aluno aluno) {
        if (alunoS.SearchAluno(id)==null){
            Object string = "deu ruim";
            return ResponseEntity.ok(string);
        }
        else{
            Aluno aluno = alunoS.SearchAluno(id);
            aluno.setNome(nome);
            return ResponseEntity.ok(aluno);
        }

    }*/


    //================ DELETE ========================

}
