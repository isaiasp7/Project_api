package P_api.Controller;

import P_api.DAO.Services.DiscService;
import P_api.Model.Disciplina;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/DisciCrud")
public class DisciplinaCtrl {
   @Autowired
    DiscService discService;

   @GetMapping("/getAll")
   public ResponseEntity<List<Disciplina>> getAll(){
       var disc = discService.getAllDisciplinas();
       return ResponseEntity.ok(disc);
   }

   @PostMapping("/create")
    public ResponseEntity<Disciplina> createDisciplina(@RequestBody Disciplina disciplina) {
       var disc = discService.createDisciplina(disciplina);
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
}
