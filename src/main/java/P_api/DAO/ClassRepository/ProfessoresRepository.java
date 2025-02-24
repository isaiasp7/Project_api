package P_api.DAO.ClassRepository;



import P_api.Model.Professor_responsavel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessoresRepository extends JpaRepository<Professor_responsavel, Long> {
}
