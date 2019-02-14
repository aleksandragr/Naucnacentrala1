package naucnaCentrala.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import naucnaCentrala.model.Magazine;
import naucnaCentrala.model.ScientificArea;

@Repository
public interface ScientificAreaRepository extends JpaRepository<ScientificArea, Long>{
	
	ScientificArea findByIdEquals(Long id);

}
