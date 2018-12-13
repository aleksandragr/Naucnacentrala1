package naucnaCentrala.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import naucnaCentrala.model.EditorReviewer;

@Repository
public interface EditorReviewerRepository extends JpaRepository<EditorReviewer, Long>{

}
