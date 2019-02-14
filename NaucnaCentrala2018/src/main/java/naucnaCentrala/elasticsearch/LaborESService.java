package naucnaCentrala.elasticsearch;

import java.util.ArrayList;
import java.util.List;



import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import naucnaCentrala.dto.LaborESDTO;
import naucnaCentrala.model.Magazine;
import naucnaCentrala.model.ScientificArea;
import naucnaCentrala.model.User;
import naucnaCentrala.repository.MagazineRepository;
import naucnaCentrala.repository.ScientificAreaRepository;
import naucnaCentrala.repository.UserRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;


import static org.elasticsearch.index.query.QueryBuilders.*;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.apache.lucene.search.join.ScoreMode;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;





@Service
public class LaborESService {
	
	@Autowired
	private LaborElasticSearchRepository laborElasticSearchRepository;
	
	@Autowired
	private MagazineRepository magazineRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ScientificAreaRepository scientificAreaRepository;
	
	@Autowired
	private ElasticsearchTemplate elasticsearchTemplate;
	
	
	public String addLaborEs(LaborESDTO labor) {
		
		
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username="";

		if (principal instanceof UserDetails) {
			username = ((UserDetails)principal).getUsername();
		} else {
			username = principal.toString();
		}
		
		User user = userRepository.findByUsername(username);
		
		if(user==null) {
			return null;
		}
		
		Magazine m = magazineRepository.findByIdEquals(labor.getMagazineid());
		
		if(m==null) {
			return null;
		}
		
		ScientificArea s = scientificAreaRepository.findByIdEquals(labor.getScientificareaid());
		
		if(s==null) {
			return null;
		}
		
		List<String> terms = labor.getKeyterms();
		
		if(terms==null) {
			return null;
		}
		
		LaborElasticSearch l = new LaborElasticSearch();
		
		List<KeyTerm> list = new ArrayList<>();
		for(int i=0;i<labor.getKeyterms().size();i++) {
			KeyTerm t = new KeyTerm();
			t.setKeyterm(labor.getKeyterms().get(i));
			list.add(t);
		}	
		
		l.setKeyTerms(list);
		//l.setId("1");
		l.setMagazinename(m.getName());
		l.setLaborname(labor.getLaborname());
		l.setAuthor(user.getName()+" "+user.getSurname());
		l.setScientificarea(s.getName());
		
		if(!labor.getPdfname().equals("")) {
			System.out.println("NAZIV PDF: " + labor.getPdfname());
			String st = labor.getPdfname().split("\\\\")[2];
			
			System.out.println(st+ "file name");
			String fp = System.getProperty("user.dir");
			 String fpp=new String(fp+"\\Magazini\\");
			 String originalFileName = st;
			  byte[] input_file;
			try {
				input_file = Files.readAllBytes(Paths.get(fpp+originalFileName));
				 byte[] encodedBytes = Base64.getEncoder().encode(input_file);
				 l.setPdf(encodedBytes);
			}
			catch(Exception e){
				
			}

		}
		
		
		
		
		
		
		laborElasticSearchRepository.save(l);
		
		
		return "uspesno";
	}
	
	public List<LaborElasticSearch> searchLaborName(String name){
		
		List<LaborElasticSearch> list = laborElasticSearchRepository.findByLaborname(name);
		
		if(list==null) {
			return null;
		}
		
		
		return list;
	}
	
	
	public List<LaborElasticSearch> searchMagazinename(String name){
		
		List<LaborElasticSearch> list = laborElasticSearchRepository.findByMagazinenameEquals(name);
		
		if(list==null) {
			return null;
		}
		
		
		return list;
	}
	
	public List<LaborElasticSearch> searchScientificArea(String name){
		
		List<LaborElasticSearch> list = laborElasticSearchRepository.findByScientificareaEquals(name);
		
		if(list==null) {
			return null;
		}
		
		
		return list;
	}
	
	public List<LaborElasticSearch> searchAuthor(String name){
		
		List<LaborElasticSearch> list = null;
		
		if(name.contains(" ")) {
			String[] words = name.split("\\s+");
			System.out.println("prvi deo: "+words[0]);
			System.out.println("drugi deo: "+words[1]);
			list = laborElasticSearchRepository.findByAuthorContaining(words[0],words[1]);		
		}
		else {
			System.out.println(name);
			list = laborElasticSearchRepository.findByAuthorContaining(name);
			
			
		}

		if(list==null) {
			return null;
		}
		
		
		return list;
	}
	
	
	public List<LaborElasticSearch> searchKeyTerms(String name){
		
		String[] words = name.split("\\s+");
		
		
		if(words.length == 2) {
			List<LaborElasticSearch> labsa = laborElasticSearchRepository.findByKeyTermsKeytermAndKeyTermsKeyterm(words[0], words[1]);
			System.out.println("AAAAAAAAA " + labsa.size());
			return labsa;
		}
		 if(words.length == 1) {
			List<LaborElasticSearch> labsa = laborElasticSearchRepository.findByKeyTermsKeyterm(words[0]);
			System.out.println("AAAAAAAAA " + labsa.size());
			return labsa;
		}
		else if(words.length == 3) {
			List<LaborElasticSearch> labsa = laborElasticSearchRepository.findByKeyTermsKeytermAndKeyTermsKeytermAndKeyTermsKeyterm(words[0], words[1], words[2]);
			System.out.println("AAAAAAAAA " + labsa.size());
			return labsa;
		}
		
		return null;
	}
	
	public List<LaborElasticSearch> searchAll(SearchDTO searchdto){
		System.out.println(searchdto.getAndor());
		
		final QueryBuilder builder;
		if(searchdto.getAndor().equals("or")) {
			builder = boolQuery()
					.should(matchQuery("laborname", searchdto.getLaborname()))
					.should(matchQuery("scientificarea", searchdto.getScientificarea()))
					.should(matchQuery("author", searchdto.getAuthor()))
					.should(matchQuery("magazinename", searchdto.getMagazinename()))
					//.should(matchQuery("keyterms", searchdto.getAuthor()))
					;
		}
		else {
			builder = boolQuery()
					.must(matchQuery("laborname", searchdto.getLaborname()))
					.must(matchQuery("scientificarea", searchdto.getScientificarea()))
					.must(matchQuery("author", searchdto.getAuthor()))
					.must(matchQuery("magazinename", searchdto.getMagazinename()))
					//.should(matchQuery("keyterms", searchdto.getAuthor()))
					;
		}
		System.out.println("DDDDDDDd " + builder.toString());
		//final QueryBuilder builder =  nestedQuery("labor", boolQuery().must(termQuery("laborname", "RadA")).must(termQuery("scientificarea", "Saobracaj")), ScoreMode.None);

		
		final SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(builder).build();
		final List<LaborElasticSearch> labor = elasticsearchTemplate.queryForList(searchQuery, LaborElasticSearch.class);
		System.out.println("RESSSSSSSSS " + labor.size());
		return labor;
	}

	
	
	

}
