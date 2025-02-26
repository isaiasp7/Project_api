package P_api.DAO.Services;




import P_api.DAO.ClassRepository.AlunosRepository;
import P_api.DAO.ClassRepository.MatriculasRepository;
import P_api.DAO.ClassRepository.TurmasRepository;
import P_api.Model.Aluno;
import P_api.Model.Matricula;

import P_api.Model.Turma;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MatricService {
    @Autowired
    private MatriculasRepository repository;
    @Autowired
    private TurmasRepository turmasRepository;



    //Create



    //===================  READ  ============================

    public Optional<Matricula> seachID(int id) {
        return repository.findById(id);
    }

    //======================== UPDATE ==========================



    public List<Matricula> getMatriculas() {
        return repository.findAll();
    }



    public void relacionarMT(int id, Turma dto) {

        Matricula matricula = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Matrícula não encontrada"));

        turmasRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Matrícula não encontrada"));
        //matricula e turma encontrados

        dto.getMatriculas().add(matricula);

        turmasRepository.save(dto);
    }





    }




