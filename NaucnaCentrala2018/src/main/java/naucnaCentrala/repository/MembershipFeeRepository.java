package naucnaCentrala.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import naucnaCentrala.model.MembershipFee;

@Repository
public interface MembershipFeeRepository extends JpaRepository<MembershipFee, Long>{
	
	
	MembershipFee findByMagazine_idEqualsAndUser_idEquals(Long idm, Long idu);
	
	List<MembershipFee> findByUser_idEquals(Long id);

}
