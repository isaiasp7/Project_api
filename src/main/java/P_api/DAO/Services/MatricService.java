package P_api.DAO.Services;


import P_api.DAO.ClassRepository.AlunosRepository;
import P_api.DAO.ClassRepository.MatriculasRepository;
import P_api.DAO.ClassRepository.TurmasRepository;
import P_api.DTO.MatriculaDTO;
import P_api.Model.Aluno;
import P_api.Model.Matricula;
import P_api.Model.Turma;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MatricService {
    @Autowired
    private MatriculasRepository matricRepository;
    @Autowired
    private TurmasRepository turmasRepository;
    @Autowired
    private AlunosRepository alunoRepository;
    @Autowired
    private TurmaService turmaService;


    //Create
    @Transactional
    public Matricula createMatricula(String aluno_cpf, long turma_id) {//Relaciona aluno com turma com uma matricula
        if (turma_id <= 0) {
            throw new IllegalArgumentException("A turma não pode ser nula.");
        }
        Turma turma = turmasRepository.findById(turma_id).orElseThrow(() -> new EntityNotFoundException("Turma não encontrada"));

        if (turma.getCapacidadeAtual() >= turma.getCapacidadeMax()) {
            throw new IllegalStateException("Turma cheia! Não é possível matricular mais alunos.");
        }
       Aluno aluno = alunoRepository.findByCpf(aluno_cpf).orElseThrow(()->new EntityNotFoundException("Aluno não encontrada"));
        // Criando a matrícula apenas se a turma for válida
        Matricula matricula = new Matricula();
        matricula.setAluno_cpf(aluno);
        matricula.setTurma(turma);
        matricRepository.save(matricula);
        // Atualizando capacidade da turma apenas se a matrícula foi criada com sucesso
        turma.setCapacidadeAtual(turma.getCapacidadeAtual() + 1);
        turmasRepository.save(turma);

        return matricula;
    }



    //======================== DELETE ===========================

    public String deletMatricula(long id){//dele apenas a matricula, aluno continua
        Matricula mat = this.seachID(id).orElseThrow(()->new EntityNotFoundException("Matricula não encontrada"));
        long idDel = mat.getId();
        Aluno aluno = mat.getAluno_cpf();
        mat.setAluno_cpf(null);
        aluno.setMatriculas(null);
        matricRepository.delete(mat);
        return  "A matricula "+ mat.getId()+" foi removida com sucesso.\n Mas o aluno "+ aluno.getNome()+ " ainda existe.";
    }

    public String deleteMatComp(long matricula){//dele apenas a matricula e aluno corresponde
        Matricula mat = this.seachID(matricula).orElseThrow(()->new EntityNotFoundException("Matricula não encontrada"));
        Aluno aluno = mat.getAluno_cpf();
        alunoRepository.delete(aluno);
        matricRepository.delete(mat);
        return  "O aluno"+  aluno.getNome()+" que corresponde a matricula:"+mat.getId()+ ", foi removido com sucesso.";
    }




    //===================  READ  ============================

    public Optional<Matricula> seachID(long id) {
        return matricRepository.findById((int)id);
    }

    /*public List<Matricula> getMatriculas() {
        return matricRepository.findAll();
    }*/
    public List<MatriculaDTO> getMatriculas() {
        List<MatriculaDTO> matriculasDTO = matricRepository.findAll()
                .stream()
                .map(matricula -> new MatriculaDTO(matricula)) // Mapeando cada matrícula para o DTO
                .collect(Collectors.toList());
        return matriculasDTO;
    }

    //====================== RELACIONAMENTOS ========================


    public Aluno relacionaMA(String alunoCpf) {//Cria uma nova Matricula para o alunoCpf
        Aluno aluno = alunoRepository.findByCpf(alunoCpf).orElseThrow(()->new EntityNotFoundException("Aluno não encontrada"));
        LocalDate hoje = LocalDate.now();
        Matricula mat = new Matricula();
        aluno.setMatriculas(mat);
        mat.setAluno_cpf(aluno);
        mat.setDataMatricula(hoje);
        alunoRepository.save(aluno);
        return aluno;

    }




    public void relacionarMT(int id, Turma dto) {

        Matricula matricula = matricRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Matrícula não encontrada"));

        turmasRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Matrícula não encontrada"));
        //matricula e turma encontrados

        dto.getMatriculas().add(matricula);

        turmasRepository.save(dto);
    }





    }




