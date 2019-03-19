package naucnaCentrala.service.camunda;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Service
public class ProveraClanarine implements JavaDelegate{

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("dosao u proveru clanarine");
		execution.setVariable("isPlacena", true);
		System.out.println(execution.getVariable("isPlacena"));
	}

}
