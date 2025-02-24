package P_api.DAO.Services;




import P_api.DAO.ClassRepository.AlunosRepository;
import P_api.Model.Aluno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;
import java.util.Optional;
@Service
public class AlunoService {
    @Autowired
    private AlunosRepository repository;

    public List<Aluno> getAlunos() {
        return repository.findAll();
    }

    public Aluno SearchAluno(@RequestHeader("User-Agent") String cpf) {
        Optional<Aluno> aluno = repository.findByCpf(cpf);
        if (aluno.isPresent()) {
            return aluno.get();
        }
        else{
            return aluno.orElse(null);
        }




    }


    public Aluno addAluno(Aluno aluno) {

        return repository.save(aluno); // Retorna o aluno cadastrado
    }



}
