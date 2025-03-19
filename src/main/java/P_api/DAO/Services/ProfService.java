package P_api.DAO.Services;

import P_api.DAO.ClassRepository.DisciplinasRepository;
import P_api.DAO.ClassRepository.ProfessoresRepository;
import P_api.DTO.ProfDiscDTO;
import P_api.Factory.ClassFactory;
import P_api.Model.Disciplina;
import P_api.Model.Professor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static Util.Utilities.reflexao;

@Service
public class ProfService {
    @Autowired
    ProfessoresRepository profRepository;
    @Autowired
    private DisciplinasRepository discRepository;

    DiscService discService;

    public Professor newProfessor(Professor professor) {
        Professor newP = new Professor(professor);

        if (profRepository.existsById(newP.getId())) {
            //notificar que ja existia
            return profRepository.findById(professor.getId()).get();
        } else {

            profRepository.save(newP);//saveAndFlush funciona como o save, mas age de maneira imediata
        }
        return newP;
    }
    public List<ProfDiscDTO> getAllProfessores() {
        return profRepository.findAll() // Busca todos os professores do repositório
                .stream() // Converte a lista em um fluxo
                .map(ClassFactory::toProfDiscDTO) // Mapeia cada Professor para um ProfDiscDTO
                .collect(Collectors.toList()); // Coleta os resultados em uma lista de ProfDiscDTO
    }
    /*
    //CORRIGIR O RETORNO DISCIPLINA APARECE DUAS VEZES
    public List<Professor> getAllProfessores() {
        return profRepository.findAll();
    }*/

    public Professor getProfessorById(long id) {
        Optional<Professor> prof = profRepository.findById((long) id);
        if (prof.isPresent()) {
            return prof.get();

        } else {
            return null;
        }
    }

    public Disciplina relacionaProf_Disc(long Pid, long Did) {
        var profId = profRepository.findById( Pid);
        if (profId.isPresent()) {
            Professor prof = this.getProfessorById(Pid);
            Disciplina disc = discService.getDisciplinaById(Did);
            prof.setDisciplina(disc);
            return disc;

        } else {
            return null;

        }
    }

    public String removeProfessor(long id) {
        profRepository.deleteById(id);
        return "Professor removido com sucesso!";

    }
    //CORRIGIR O RETORNO
    public Professor updateProfessor(long id, Professor alterProfessor) {
        Professor prof = profRepository.findById(id).get();
        try {
            for (Map.Entry<String, Object> camposAlter : reflexao(alterProfessor).entrySet()) {
                String key = camposAlter.getKey();
                Object val = camposAlter.getValue();
                Field field = Professor.class.getDeclaredField(key);//pega o atributo
                field.setAccessible(true);//isso é usado para dizer que mesmo o atributo sendo priva
                if (key.equals("id")) {
                    continue;
                } else if (key.equals("discplina_fk")) {
                    prof.setDisciplina_fk((Disciplina) val);
                }
                field.set(prof, val);

            }
            profRepository.save(prof);


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println(prof);


        return this.getProfessorById(id);
    }
}
