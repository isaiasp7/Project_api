package P_api.Controller;

import P_api.DAO.Services.ProfService;
import P_api.DTO.ProfDiscDTO;
import P_api.Model.Disciplina;
import P_api.Model.Professor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ProfCrud")

public class ProfessorCtrl {
    @Autowired
    ProfService profService;

    @GetMapping("/getAll")
    public ResponseEntity<List<Professor>> getAll(){
        List<Professor> listP =profService.getAllProfessores();
        return ResponseEntity.ok(listP);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<ProfDiscDTO> getById(@PathVariable int id){
        Professor p = profService.getProfessorById(id);
        return ResponseEntity.ok(ProfDiscDTO.toProfessorDTO(p));//retorna o dto para que ao ler disciplinas de prof não retorne para prof novamente
    }


    //============= CREATE =================================

    @PostMapping("/create")
    public ResponseEntity<Professor> createProfessor(@RequestBody Professor professor) {//nome,telefone
        Professor prof = profService.newProfessor(professor);
        return ResponseEntity.ok(prof);
    }

    @PostMapping("/AddDisciplina")
    public ResponseEntity<Disciplina> cadastrarProfessor_Disc(int Pid, int Did) {
            Disciplina disc=profService.relacionaProf_Disc(Pid, Did);
            return ResponseEntity.ok(disc);
    }




}
