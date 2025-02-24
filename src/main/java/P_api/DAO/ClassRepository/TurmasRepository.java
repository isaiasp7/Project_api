package P_api.DAO.ClassRepository;



import P_api.Model.Turma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TurmasRepository extends JpaRepository<Turma, Long> {
}
