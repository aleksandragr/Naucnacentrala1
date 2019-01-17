package naucnaCentrala.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import naucnaCentrala.model.MembershipFee;

@Repository
public interface MembershipFeeRepository extends JpaRepository<MembershipFee, Long>{

}
