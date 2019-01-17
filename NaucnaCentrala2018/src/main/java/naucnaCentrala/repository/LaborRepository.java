package naucnaCentrala.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import naucnaCentrala.model.Labor;

@Repository
public interface LaborRepository extends JpaRepository<Labor, Long>{

}
