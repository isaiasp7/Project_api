package P_api.Controller;

import P_api.DAO.Services.DiscService;
import P_api.DTO.RelacionaDTRequest;
import P_api.Model.Disciplina;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/DisciCrud")
//NÃO TESTADO
public class DisciplinaCtrl {
   @Autowired
    DiscService discService;

   @GetMapping("/getAll")
   public ResponseEntity<List<Disciplina>> getAll(){
       List<Disciplina> disc = discService.getAllDisciplinas();
       return ResponseEntity.ok(disc);
   }

   @PostMapping("/create")
    public ResponseEntity<Disciplina> createDisciplina(@RequestBody Disciplina disciplina) {
       Disciplina disc = discService.createDisciplina(disciplina);
       return ResponseEntity.ok(disc);
   }

   @PostMapping("/search")
    public ResponseEntity<Disciplina> searchDisciplina(@RequestParam int id) {
            discService.getDisciplinaById(id);
            return ResponseEntity.ok(discService.getDisciplinaById(id));
   }


   @GetMapping("/delete")
    public ResponseEntity<String>  deleteDisciplina(@RequestParam int id) {
       discService.deleteDisciplinaById(id);
       return ResponseEntity.ok("Deleted Disciplina");
   }

   //=============== RELACIONAMENTOS ======================================

    @PutMapping("/relacionaDT")
    public ResponseEntity relacioDT(@RequestBody RelacionaDTRequest lista) {//Racionana turma a Disciplina
       Disciplina relacionamento =discService.relacionaDT(lista.getIdDisciplina(), lista.getIdTurma());
        return ResponseEntity.ok(relacionamento);
   }
}
