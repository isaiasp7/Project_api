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
//RODANDO
public class AlunoCtrl {
    @Autowired
    private AlunoService alunoS;

    //============== READ =====================

    @GetMapping("/getAll")
    public ResponseEntity<List<Aluno>> mostrarAlunos() {
        var alunos = alunoS.getAlunos();
        return ResponseEntity.ok(alunos);
    }

    @GetMapping("/searchCpf/{cpf}")
    public ResponseEntity SearchCpf(@PathVariable String cpf) {
        var lista = alunoS.searchAluno(cpf);
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/searchID/{id}")
    public ResponseEntity SearchId(@PathVariable int id) {
        return ResponseEntity.ok(alunoS.searchAlunoId(id));
    }




    //============== CREATE =====================

    @PostMapping("create")
    public ResponseEntity<Aluno> cadastrarAluno(@RequestBody Aluno aluno) {//cpf, nome, dataNasci
        aluno.setMatriculas(new Matricula());
        Aluno novoAl =alunoS.addAlunos(aluno);

        return ResponseEntity.status(HttpStatus.CREATED).body(novoAl);
    }


    //================ DELETE ========================
    @GetMapping("/delete/{id}")
    public ResponseEntity<String> deletarAluno(@PathVariable int id) {
        Aluno temp_aluno =alunoS.searchAlunoId(id);
        var Alunonome = temp_aluno.getNome();
        Boolean delBoolean = alunoS.deleteAluno(id);
        if (delBoolean) {
            return ResponseEntity.ok("Aluno "+Alunonome+" com id : "+id+" removido do sistema.");
        }
        return ResponseEntity.ok("O id inserido não existe");
    }


    //=============== UPDATE ===========================

    @PutMapping("/updateAlunosId/{id}")
    public ResponseEntity<Aluno> atualizarAluno(@PathVariable int id, @RequestBody Aluno aluno) {
        Aluno alunoAtualizado= alunoS.updateAlunosId(id,aluno);
        return ResponseEntity.ok(alunoAtualizado);
    }

    @PutMapping("/updateAlunos/{cpf}")
    public ResponseEntity atualizarAluno(@PathVariable String cpf, @RequestBody Aluno aluno) {

        Aluno alunoAtualizado =alunoS.updateAlunos(cpf,aluno);
        return ResponseEntity.ok(alunoAtualizado);
    }

    //================== RELACIONAMENTO ========================

    /*@PostMapping("/relacionaMA/{cpf}")
    public ResponseEntity<?> relacionaMA(@PathVariable String cpf, @RequestBody Matricula mat) {//dataMatricula,status,turma
        try {
            var aluno = alunoS.relacionaMA(cpf,mat);
            return ResponseEntity.ok(aluno);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao relacionar aluno com matrícula: " + e.getMessage());
        }
    }*/





}
