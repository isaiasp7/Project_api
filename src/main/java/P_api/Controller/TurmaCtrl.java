package P_api.Controller;

import P_api.DAO.Services.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/TurmaCrud")
public class TurmaCtrl {
    @Autowired
    private TurmaService turma;

    @PostMapping("/alterCapA/{id}&{cap}")
    public ResponseEntity<?> alterCapA(@PathVariable int id, @PathVariable int cap) {
        return turma.alterarCapacidadeA(id,cap);

    }
}
