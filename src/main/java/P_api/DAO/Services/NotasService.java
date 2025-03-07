package P_api.DAO.Services;

import P_api.DAO.ClassRepository.DisciplinasRepository;
import P_api.DAO.ClassRepository.MatriculasRepository;
import P_api.DAO.ClassRepository.NotasRepository;
import P_api.DTO.NotasDTO;
import P_api.Model.Disciplina;
import P_api.Model.Matricula;
import P_api.Model.Notas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotasService {
    @Autowired
    private MatriculasRepository matricRepository;

    @Autowired
    private NotasRepository notasRepository;
    @Autowired
    private DisciplinasRepository disciplinasRepository;

    //add notas a uma matricula 1/2 @RequestParam(required = false)
    //alterar notas pelo id da nota  1/2
    //mostrar notas de um aluno
    public List<NotasDTO> mostrarNotas(){


        List<NotasDTO> nota=notasRepository.findAll()
                .stream()
                .map(NotasDTO::new) // Converte cada Turma para TurmaDTO
                .collect(Collectors.toList());
        return nota;

    }
    /*public Notas buscarNotas(long idNota) {


    }*/

    public Matricula newNotas(long idMat,long idDisc, float nota1,float nota2){
        Matricula mat = matricRepository.findById((int)idMat).orElseThrow(null);
        Disciplina disc = disciplinasRepository.findById(idDisc).orElseThrow(null);
        if(mat != null && disc != null){

            Notas notas = new Notas(mat,disc, nota1, nota2);
            notasRepository.save(notas);
            mat.setNotas(notas);
        }
        matricRepository.save(mat);
        return mat;
    }

    public void removerNotas(long id){
        notasRepository.deleteById(id);

    }


}
