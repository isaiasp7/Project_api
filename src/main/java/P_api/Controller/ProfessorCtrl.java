package P_api.Controller;

import P_api.DAO.Services.ProfService;
import P_api.DTO.ProfDiscDTO;
import P_api.DTO.RelacionaPDRequest;
import P_api.Model.Disciplina;
import P_api.Model.Professor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ProfCrud")

public class ProfessorCtrl {
    @Autowired
    ProfService profService;

    @GetMapping("/getAll")
    public ResponseEntity<List<ProfDiscDTO>> getAll(){
        List<ProfDiscDTO> listP =profService.getAllProfessores();
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
        System.out.println("==================================");
        System.out.println("CONTROLLER  : "+ professor.getNome());
        System.out.println("==================================");
        Professor prof = profService.newProfessor(professor);

        return ResponseEntity.ok(prof);
    }


    @PutMapping("/updateP/{id}")
    public ResponseEntity<ProfDiscDTO> updateP(@PathVariable long id,@RequestBody Professor professor) {
        Professor prof = profService.updateProfessor(id, professor);
        return ResponseEntity.ok(ProfDiscDTO.toProfessorDTO(prof));
    }

    //============= DELETE =================================

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProfessor(@PathVariable long id) {
        try {
            return ResponseEntity.ok(profService.removeProfessor(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao deletar professor: " + e.getMessage());
        }

    }
    //============= RELACIONAMENTO ========================

    @PostMapping("/AddDisciplina")
    public ResponseEntity<Disciplina> cadastrarProfessor_Disc(@RequestBody RelacionaPDRequest id) {
            Disciplina disc=profService.relacionaProf_Disc(id.getPid(), id.getDid());
            return ResponseEntity.ok(disc);
    }




}
