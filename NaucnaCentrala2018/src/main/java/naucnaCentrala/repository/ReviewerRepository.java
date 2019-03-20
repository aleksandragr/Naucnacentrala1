package naucnaCentrala.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import naucnaCentrala.model.Labor;
import naucnaCentrala.model.Reviewer;

@Repository
public interface ReviewerRepository extends JpaRepository<Reviewer, Long>{
	
	Reviewer findByUsername(String username);
	
	Reviewer findByIdEquals(Long id);

}
