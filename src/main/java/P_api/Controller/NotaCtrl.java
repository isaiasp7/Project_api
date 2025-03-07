package P_api.Controller;

import P_api.DAO.Services.NotasService;
import P_api.DTO.NotasDTO;
import P_api.DTO.RelacionaNotas;
import P_api.Model.Matricula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/NotaCrud")
public class NotaCtrl {
    @Autowired
    private NotasService notaService;

    @GetMapping("/getNotas")
    public ResponseEntity<List<NotasDTO>> getNotas(){
        return ResponseEntity.ok(notaService.mostrarNotas());
    }

    @PostMapping("/addNotas")
    public ResponseEntity<Matricula> newNota(@RequestBody RelacionaNotas nota) {//idDisc - idMat - nota1 - nota2;
       Matricula mat= notaService.newNotas(nota.getIdMat(), nota.getIdDisc(),nota.getNota1(),nota.getNota2());
        return ResponseEntity.ok(mat);

    }


    @DeleteMapping("deletNotas/{id}")
    public ResponseEntity deleteNota(@PathVariable  long id ) {
        notaService.removerNotas(id);
        return ResponseEntity.ok("Notas deletada com sucesso");
    }
}
