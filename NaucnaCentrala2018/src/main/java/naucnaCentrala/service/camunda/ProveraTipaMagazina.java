package naucnaCentrala.service.camunda;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import naucnaCentrala.model.Magazine;
import naucnaCentrala.repository.MagazineRepository;

@Service
public class ProveraTipaMagazina implements JavaDelegate{
	
	@Autowired
	public MagazineRepository magazineRepository;

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("stigao u proveru tipa magazinaaaa\n");
		
		Long idMag = (Long) execution.getVariable("idMagazina");
		Magazine magazine = magazineRepository.findByIdEquals(idMag);
		
		if(magazine.getPaymentMethod().equals("open-access")) {
			System.out.println("magazine je open access");
			execution.setVariable("isOpenAccess", true);
		}
		else {
			System.out.println("magazine nije open access");
			execution.setVariable("isOpenAccess", false);
		}
		
	}

}
