package P_api.DAO.Services;

import P_api.DAO.ClassRepository.ProfessoresRepository;
import P_api.Model.Disciplina;
import P_api.Model.Professor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfService {
    @Autowired
    ProfessoresRepository profRepository;

    DiscService discService;

    public Professor newProfessor(Professor professor) {
        Professor newP = new Professor(professor);
        if (profRepository.existsById(newP.getId())) {
            //notificar que ja existia
            return profRepository.findById(professor.getId()).get();
        }
        else{

            profRepository.saveAndFlush(newP);//saveAndFlush funciona como o save, mas age de maneira imediata
        }
        return newP;
    }

    public List<Professor> getAllProfessores() {
        return profRepository.findAll();
    }

    public Professor getProfessorById(long id) {
        Optional<Professor> prof = profRepository.findById((long)id);
        if (prof.isPresent()) {
            return prof.get();

        }else{
            return null;
        }
    }

    public Disciplina relacionaProf_Disc(int Pid, int Did){
        var profId = profRepository.findById((long)Pid);
        if (profId.isPresent()) {
            Professor prof =this.getProfessorById(Pid);
            Disciplina disc = discService.getDisciplinaById(Did);
            prof.setDisciplina(disc);
            return disc;

        } else {
            return null;

        }
    }
}
