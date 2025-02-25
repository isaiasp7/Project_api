package P_api.DAO.Services;

import P_api.DAO.ClassRepository.DisciplinasRepository;
import P_api.Model.Disciplina;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiscService {
    @Autowired
    DisciplinasRepository discModel;


    public List<Disciplina> getAllDisciplinas(){
        return discModel.findAll();
    }

    public Disciplina createDisciplina(Disciplina disciplina) {
        Disciplina novaD = new Disciplina(disciplina);
        System.out.println("id disc service "+ novaD.getId());
        discModel.saveAndFlush(novaD);
        return novaD;
    }

    public Disciplina getDisciplinaById(int id) {
        Optional<Disciplina> disc = discModel.findById((long)id);
        if(disc.isPresent()) {
            return disc.get();
        }
        else {
            return null;
        }

    }

    public void deleteDisciplinaById(int id) {
        Optional<Disciplina> disc = discModel.findById((long)id);
        if(disc.isPresent()) {
            discModel.delete(disc.get());
        }
        else {
            System.out.println("id disc service "+ id);
        }
    }


}
