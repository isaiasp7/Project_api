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
    private AlunosRepository alunoRepository;
    @Autowired
    private MatricService matricService;//Alguns elementos de aluno estao em matricula = id,turma, notas...

    public List<Aluno> getAlunos() {
        return alunoRepository.findAll();
    }
    //====================================

    public Aluno searchAluno(String cpf) {
        Optional<Aluno> aluno = alunoRepository.findByCpf(cpf);
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


    public Aluno addAlunos(Aluno aluno) {
        Aluno novoAluno = new Aluno(aluno.getCpf(), aluno.getNome(), aluno.getDataNasci());
        return alunoRepository.save(novoAluno); // Retorna o aluno cadastrado
    }

    //=====================UPDATE============================

    public void saveAlunos(Aluno aluno) {
        alunoRepository.save(aluno);
    }


    public Aluno updateAlunosId(int id, Aluno alunoAtualizado) {
        Aluno alunoExistente = this.searchAlunoId(id);

        try {

            // Não faz sentido aluno atualizar campos de cadastro
            if (alunoAtualizado.getEmail() != null) {
                alunoExistente.setEmail(alunoAtualizado.getEmail());
            }

            return alunoRepository.save(alunoExistente);
        } catch (Exception e) {
            throw new RuntimeException("Aluno não encontrado");
        }


    }


    public Aluno updateAlunos(String cpf, Aluno alunoAtualizado) {
        Aluno alunoExistente = this.searchAluno(cpf);

        try {

            // Não faz sentido aluno atualizar campos de cadastro
            if (alunoAtualizado.getEmail() != null) {
                alunoExistente.setEmail(alunoAtualizado.getEmail());
            }
            if(alunoAtualizado.getQuant_faltas()!=null) {
                alunoExistente.setQuant_faltas(alunoAtualizado.getQuant_faltas());
            }
            alunoRepository.save(alunoExistente);
            return alunoExistente;
        } catch (Exception e) {
             throw new RuntimeException("Aluno não encontrado");
        }


    }

    //======================DELETE===========================


    public boolean deleteAluno(int matriculaId) {
        if(this.searchAlunoId(matriculaId) != null) {
            alunoRepository.deleteById(matriculaId);
            return true;
        }else{
            return false;
        }

    }


    /*
    public Aluno relacionaMA(String alunocpf,Matricula mat) {//MAtricula e aluno
        Aluno aluno =  this.searchAluno(alunocpf);
        aluno.setMatriculas(mat);
        this.saveAlunos(aluno);
        alunoRepository.save(aluno);
        return aluno;

    }*/


}
