package naucnaCentrala.elasticsearch;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import naucnaCentrala.dto.LaborESDTO;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/laborES")
public class LaborESController {
	
	@Autowired
	private LaborESService laborESService;
	
	@PreAuthorize("hasRole('USER') or hasRole('AUTHOR')")
	@PostMapping("/addLaborr")
	public String addLabor(@RequestBody LaborESDTO labor) {
		
		String res = laborESService.addLaborEs(labor);
		
		if(res==null) {
			return null;
		}
		
		return res;
		
	}
	
	@PreAuthorize("hasRole('USER') or hasRole('AUTHOR')")
	@GetMapping("/searchLaborname/{name}")
	public List<LaborElasticSearch> searchLaborname(@PathVariable String name){
		
		List<LaborElasticSearch> list = laborESService.searchLaborName(name);
		
		if(list==null) {
			return null;
		}
		
		return list;
	}

	@PreAuthorize("hasRole('USER') or hasRole('AUTHOR')")
	@GetMapping("/searchMagazinename/{name}")
	public List<LaborElasticSearch> searchMagazinename(@PathVariable String name){
		
		List<LaborElasticSearch> list = laborESService.searchMagazinename(name);
		
		if(list==null) {
			return null;
		}
		
		return list;
	}
	
	@PreAuthorize("hasRole('USER') or hasRole('AUTHOR')")
	@GetMapping("/searchSciarea/{name}")
	public List<LaborElasticSearch> searchSciArea(@PathVariable String name){
		
		List<LaborElasticSearch> list = laborESService.searchScientificArea(name);
		
		if(list==null) {
			return null;
		}
		
		return list;
	}
	
	@PreAuthorize("hasRole('USER') or hasRole('AUTHOR')")
	@GetMapping("/searchAuthor/{name}")
	public List<LaborElasticSearch> searchAuthor(@PathVariable String name){
		
		List<LaborElasticSearch> list = laborESService.searchAuthor(name);
		
		if(list==null) {
			return null;
		}
		
		return list;
	}
	
	@PreAuthorize("hasRole('USER') or hasRole('AUTHOR')")
	@GetMapping("/searchTerms/{name}")
	public List<LaborElasticSearch> searchTerms(@PathVariable String name){
		
		List<LaborElasticSearch> list = laborESService.searchKeyTerms(name);
		
		if(list==null) {
			return null;
		}
		
		return list;
	}
	
	@PreAuthorize("hasRole('USER') or hasRole('AUTHOR')")
	@PostMapping("/searchAll")
	public List<LaborElasticSearch> searchAll(@RequestBody SearchDTO dto){
		
		List<LaborElasticSearch> list = laborESService.searchAll(dto);
		
		if(list==null) {
			return null;
		}
		
		return list;
	}
	
}
