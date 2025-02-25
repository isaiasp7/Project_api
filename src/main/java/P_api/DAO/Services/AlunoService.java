package P_api.DAO.Services;




import P_api.DAO.ClassRepository.AlunosRepository;
import P_api.Model.Aluno;
import P_api.Model.Matricula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service
public class AlunoService {
    @Autowired
    private AlunosRepository repository;
    @Autowired
    private MatricService matricService;//Alguns elementos de aluno estao em matricula = id,turma, notas...

    public List<Aluno> getAlunos() {
        return repository.findAll();
    }
    //====================================

    public Aluno SearchAluno(String cpf) {
        Optional<Aluno> aluno = repository.findByCpf(cpf);
        if (aluno.isPresent()) {
            return aluno.get();
        }
        else{
            return aluno.orElse(null);
        }

    }

    public Aluno searchAlunoId(int matriculaId) {
        Matricula matricula = matricService.seachID(matriculaId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Matrícula não encontrada"));

        return matricula.getAluno(); // Retorna o aluno relacionado à matrícula
    }



    //=======================================================


    public Aluno addAluno(Aluno aluno) {
        Aluno novoAluno = new Aluno(aluno.getCpf(), aluno.getNome(), aluno.getDataNasci());
        return repository.save(novoAluno); // Retorna o aluno cadastrado
    }

    //=====================UPDATE============================

    public void saveAluno(Aluno aluno) {
        repository.save(aluno);
    }

    //======================DELETE===========================


    public boolean deleteAluno(int matriculaId) {
        if(this.searchAlunoId(matriculaId) != null) {
            repository.deleteById(matriculaId);
            return true;
        }else{
            return false;
        }

    }


}
