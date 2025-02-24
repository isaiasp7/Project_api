package P_api.Controller;

import P_api.DAO.Services.MatricService;
import P_api.Model.Matricula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/MatriCrud")
public class MatriculaCtrl {
    @Autowired
    private MatricService matric;

    //==============GET===================

    @GetMapping
    public ResponseEntity<List<Matricula>> getAll() {
        try {
            List<Matricula> mat = matric.getMatriculas();
            if (mat.isEmpty()) {
                return ResponseEntity.noContent().build(); // Retorna 204 se a lista estiver vazia
            }
            return ResponseEntity.ok(mat); // Retorna 200 com a lista
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Retorna 500 em caso de erro
        }
    }
    /*@PostMapping
    public ResponseEntity create(@RequestBody Matricula matricula) {
        Matricula mat = new Matricula(matricula);
        return ResponseEntity.ok(mat);
    }*/
}
