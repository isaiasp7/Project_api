package P_api.DAO.Services;

import P_api.DAO.ClassRepository.DisciplinasRepository;
import P_api.DAO.ClassRepository.TurmasRepository;
import P_api.Model.Disciplina;
import P_api.Model.Turma;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiscService {
    @Autowired
    DisciplinasRepository discRepository;
    @Autowired
    private TurmasRepository turmasRepository;


    public List<Disciplina> getAllDisciplinas(){
        return discRepository.findAll();
    }

    public Disciplina createDisciplina(Disciplina disciplina) {
        Disciplina novaD = new Disciplina(disciplina);
        discRepository.save(novaD);
        return novaD;
    }

    public Disciplina getDisciplinaById(long id) {
        Optional<Disciplina> disc = discRepository.findById(id);
        if(disc.isPresent()) {
            return disc.get();
        }else{
            return null;
        }

    }

    public void deleteDisciplinaById(int id) {
        Optional<Disciplina> disc = discRepository.findById((long)id);
        if(disc.isPresent()) {
            discRepository.delete(disc.get());
        }
        else {
            System.out.println("id disc service "+ id);
        }
    }


    public Disciplina relacionaDT(long idDisciplina,long idTurma){
        Disciplina disc =this.getDisciplinaById(idDisciplina);
        Turma turma  = turmasRepository.getReferenceById(idTurma);
        if(disc!=null && turma!=null) {
            disc.setTurma(turma);
             discRepository.save(disc);
            return disc;
        }
        return null;
    }/*
   public Disciplina relacionaDT(long idDisciplina, long idTurma) {
       Disciplina disc = this.getDisciplinaById(idDisciplina);
       Turma turma = turmasRepository.findById(idTurma).orElseThrow(() -> new RuntimeException("Turma não encontrada"));

       if (disc != null) {
           disc.setTurma(turma); // Relaciona a disciplina com a turma
           discRepository.save(disc); // Salva a relação no banco
           return disc;
       }

       throw new RuntimeException("Disciplina não encontrada");
   }*/


}
