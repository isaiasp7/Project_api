package P_api.Controller;

import P_api.DAO.Services.ProfService;
import P_api.Model.Disciplina;
import P_api.Model.Professor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ProfCrud")

public class ProfessorCtrl {
    @Autowired
    ProfService profService;

    //============= CREATE =================================

    @PostMapping("/create")
    public ResponseEntity<Professor> createProfessor(@RequestBody Professor professor) {//nome,disciplina_fk,telefone
        Professor prof = profService.newProfessor(professor);
        return ResponseEntity.ok(prof);
    }

    @PostMapping("/AddDisciplina")
    public ResponseEntity<Disciplina> cadastrarProfessor_Disc(int Pid, int Did) {
            Disciplina disc=profService.relacionaProf_Disc(Pid, Did);
            return ResponseEntity.ok(disc);
    }


}
