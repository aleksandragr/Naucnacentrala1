package naucnaCentrala.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import naucnaCentrala.model.Magazine;
import naucnaCentrala.model.PaymentObj;

@Repository
public interface PaymentObjRepository extends JpaRepository<PaymentObj, Long>{
	
	PaymentObj findByIdEquals(Long id);

}
