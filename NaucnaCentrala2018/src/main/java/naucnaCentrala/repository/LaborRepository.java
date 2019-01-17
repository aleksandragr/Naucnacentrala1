package naucnaCentrala.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import naucnaCentrala.model.Work;

@Repository
public interface WorkRepository extends JpaRepository<Work, Long>{

}
