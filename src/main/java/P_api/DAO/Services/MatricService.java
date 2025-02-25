package P_api.DAO.Services;




import P_api.DAO.ClassRepository.MatriculasRepository;
import P_api.Model.Aluno;
import P_api.Model.Matricula;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MatricService {
    @Autowired
    private MatriculasRepository repository;


    //Create
    /*public void CreateAluno(Aluno aluno) {
        repository.save(aluno);
    }*/

    //READ
    public Optional<Matricula> seachID(int id) {
        return repository.findById(id);
    }

    public List<Matricula> getMatriculas() {
        return repository.findAll(); // Sem mudanças, está correto
    }
        // Buscar os dados uma única vez






    }




