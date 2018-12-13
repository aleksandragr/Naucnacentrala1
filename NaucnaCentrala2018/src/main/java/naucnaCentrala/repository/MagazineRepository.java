package naucnaCentrala.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import naucnaCentrala.model.Magazine;

@Repository
public interface MagazineRepository extends JpaRepository<Magazine, Long>{

}
