package P_api.Controller;

import P_api.DAO.Services.TurmaService;
import P_api.Model.Turma;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/TurmaCrud")
public class TurmaCtrl {
    @Autowired
    private TurmaService turmaService;


    @GetMapping("/getAll")
    public ResponseEntity<List<Turma>> getAll(){
       var local= turmaService.getTurmas();
        return ResponseEntity.ok(local);
    }

    //================ CREATE ====================

    @PostMapping("createT")
    public ResponseEntity<Turma> createTurma(@RequestBody Turma turma) {
        Turma Novaturma = turmaService.createTurma(turma);
        return ResponseEntity.ok(Novaturma);
    }



    @PostMapping("/alterCapA/{id}&{cap}")
    public ResponseEntity<?> alterCapA(@PathVariable int id, @PathVariable int cap) {
        return turmaService.alterarCapacidadeA(id,cap);

    }
}
