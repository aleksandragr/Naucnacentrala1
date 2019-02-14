package naucnaCentrala.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import naucnaCentrala.model.EditorReviewer;
import naucnaCentrala.model.User;

@Repository
public interface EditorReviewerRepository extends JpaRepository<EditorReviewer, Long>{
	
	EditorReviewer findByUsername(String username);


}
