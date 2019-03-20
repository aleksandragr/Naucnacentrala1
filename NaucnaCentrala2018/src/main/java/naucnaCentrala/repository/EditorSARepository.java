package naucnaCentrala.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import naucnaCentrala.model.EditorReviewer;
import naucnaCentrala.model.EditorSA;

@Repository
public interface EditorSARepository extends JpaRepository<EditorSA, Long>{
	
	EditorSA findByUsername(String username);

}
