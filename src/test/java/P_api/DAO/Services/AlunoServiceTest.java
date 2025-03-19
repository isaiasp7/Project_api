package P_api.DAO.Services;


import P_api.DAO.ClassRepository.AlunosRepository;
import P_api.DAO.ClassRepository.MatriculasRepository;
import P_api.Model.Aluno;
import P_api.Model.Matricula;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.server.ResponseStatusException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AlunoServiceTest {

    @Mock
    private AlunosRepository alunoRepository;

    @Mock
    private MatriculasRepository matricRepository;

    @Mock
    private MatricService matricService;//Alguns elementos de aluno estao em matricula = id,turma, notas...



    @InjectMocks
    private AlunoService alunoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    /*private Aluno createAluno(Aluno aluno1) {
        // Apenas mocka o comportamento do repository
        when(alunoRepository.save(any(Aluno.class))).thenReturn(aluno1);
        return aluno1;
    }*/

   /* @Test
    void getAlunos() {
    }*/



    @Test
    @DisplayName("Cenario onde o aluno como o cpf correspondente existe")
    void searchAlunoCase1() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date dataNascimento = sdf.parse("26/05/2005");
        Aluno aluno = new Aluno("46532918764", "Mauricio", dataNascimento);

        // Simula o comportamento do repositório ao buscar o aluno
        when(alunoRepository.findByCpf(anyString())).thenReturn(Optional.of(aluno));

        // Chama o método do serviço
        Optional<Aluno> result = Optional.ofNullable(alunoService.searchAluno(aluno.getCpf()));

        // Verifica se o resultado é o esperado
        assertThat(result.isPresent()).isTrue();
        assertThat(result.get().getNome()).isEqualTo("Mauricio");
    }

    @Test
    @DisplayName("Cenario onde o aluno como o cpf correspondente não existe")
    void searchAlunoCase2() {

        when(alunoRepository.findByCpf(anyString())).thenReturn(Optional.empty());

        Aluno result = alunoService.searchAluno("46532918764");
        assertThat(result).isNull();

    }

    @Test
    @DisplayName("Cenario onde existem varios alunos e eles são retornados")
    void getAlunosCase1() throws ParseException {
        List<Aluno> lista = new ArrayList<>();
        Random sorte = new Random();
        for (int i = 0; i < 5; i++) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date dataNascimento = sdf.parse("26/05/2005");
            Aluno aluno = new Aluno(String.valueOf(sorte.nextLong(10000000000L)),"Mauricio", dataNascimento);
            lista.add(aluno);

        }
        // Simula o comportamento do repositório ao buscar o aluno
        when(alunoRepository.findAll()).thenReturn(lista);
        List<Aluno> result = alunoService.getAlunos();
        assertThat(result.size()).isEqualTo(5);
        for (int i = 0; i < 5; i++) {
            assertThat(result.get(i).getNome()).isNotEmpty();
            assertThat(result.get(i).getCpf()).isNotEmpty();
        }

    }
    @Test
    @DisplayName("Cenario onde não existem alunos no banco")
    void getAlunosCase2() {
        when(alunoRepository.findAll()).thenReturn(Collections.emptyList());
        List<Aluno> result = alunoService.getAlunos();
        assertThat(result.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("Cenario onde um aluno com o id correspondente é retornado")
    void searchAlunoIdCase1() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date dataNascimento = sdf.parse("26/05/2005");
        Aluno aluno = new Aluno("46532918764", "Mauricio", dataNascimento);
        Matricula mat = new Matricula();
        mat.setId(94630520L);
        mat.setAluno_cpf(aluno);

        // Simula o comportamento do repositório ao buscar o aluno
        when(matricService.seachID(94630520)).thenReturn(Optional.of(mat));

        Aluno result = alunoService.searchAlunoId(94630520);
        assertThat(result).isNotNull();
        assertThat(result.getCpf()).isEqualTo(aluno.getCpf());

    }

    @Test
    @DisplayName("Cenario onde o aluno com determinado id não existe")
    void searchAlunoIdCase2(){
        long id = 94630520L;
        when(matricService.seachID(id)).thenReturn(Optional.empty());
        Optional<Matricula> result = matricService.seachID(id);
        assertThrows(ResponseStatusException.class, () -> {
            alunoService.searchAlunoId((int) id);
        });


    }

    @Test
    @DisplayName("Recebe valores corretos e persiste no banco")
    void addAlunosCase1() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date dataNascimento = sdf.parse("26/05/2005");
        Aluno aluno = new Aluno("46532918764", "Mauricio", dataNascimento);

        when(alunoRepository.save(aluno)).thenReturn(aluno);
         alunoService.saveAlunos(aluno);
        // Verifica se o metodo save foi chamado no repositório com o objeto aluno
        verify(alunoRepository).save(aluno);

    }
    @Test
    @DisplayName("Não recebe aluno")
    void addAlunosCase2(){

        when(alunoRepository.save(null)).thenReturn(null);
        Aluno saveAluno = alunoRepository.save(null);
        assertThat(saveAluno).isNull();

    }



    @Test
    @DisplayName("Basicamente usa o save do jpa")
    void saveAlunos() {
    }


    @Test
    @DisplayName("Deve atualizar o email de um aluno com ID válido")//chat
    //Revisar
    void updateAlunosId_Success() {
        int matriculaId = 1;
        String novoEmail = "novoemail@email.com";

        // Criando um aluno e uma matrícula fictícia
        Aluno alunoExistente = new Aluno("46532918764", "Mauricio", null);
        Matricula matricula = new Matricula();
        matricula.setId(matriculaId);
        matricula.setAluno_cpf(alunoExistente);

        // Corrigindo o mock para matricService
        when(matricService.seachID(matriculaId)).thenReturn(Optional.of(matricula));

        // Mockando o repositório de matrícula
        when(matricRepository.findById(matriculaId)).thenReturn(Optional.of(matricula));

        // Mockando o repositório de aluno
        when(alunoRepository.save(alunoExistente)).thenReturn(alunoExistente);

        // Chamando o metodo de atualização
        Aluno alunoAtualizado = alunoService.updateAlunosId(matriculaId, novoEmail);

        // Verificando se o email foi atualizado corretamente
        assertNotNull(alunoAtualizado);
        assertEquals(novoEmail, alunoAtualizado.getEmail());

        // Verificando se os mocks foram chamados corretamente
        verify(matricService, times(1)).seachID(matriculaId);
        verify(alunoRepository, times(1)).save(alunoExistente);
    }

    @Test
    @DisplayName("Deve lançar exceção quando a matrícula não for encontrada")
    void updateAlunosId_MatriculaNaoEncontrada() {
        int matriculaId = 1;
        String novoEmail = "novoemail@email.com";

        // Simulando que a matrícula não existe
        when(matricRepository.findById(matriculaId)).thenReturn(Optional.empty());

        // Verificando se a exceção correta é lançada
        assertThrows(ResponseStatusException.class, () -> {
            alunoService.updateAlunosId(matriculaId, novoEmail);
        });

        // Garantindo que o método save() nunca foi chamado
        verify(alunoRepository, never()).save(any());
    }

    @Test
    @DisplayName("Deve lançar exceção quando o email não for informado")
    void updateAlunosId_EmailNulo() {
        int matriculaId = 1;

        // Criando um aluno e uma matrícula fictícia
        Aluno alunoExistente = new Aluno("46532918764", "Mauricio", null);
        Matricula matricula = new Matricula();
        matricula.setId(matriculaId);
        matricula.setAluno_cpf(alunoExistente);

        // Simulando a busca da matrícula no repository
        when(matricRepository.findById(matriculaId)).thenReturn(Optional.of(matricula));

        // Verificando se a exceção correta é lançada
        assertThrows(RuntimeException.class, () -> {
            alunoService.updateAlunosId(matriculaId, null);
        });

        // Garantindo que o método save() nunca foi chamado
        verify(alunoRepository, never()).save(any());
    }

//========================================================

/*
    @Test
    void updateAlunos() {
    }

    @Test
    void deleteAluno() {
    }*/
}