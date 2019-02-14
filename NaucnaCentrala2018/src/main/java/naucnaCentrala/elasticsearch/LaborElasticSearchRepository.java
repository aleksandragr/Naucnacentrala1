package naucnaCentrala.elasticsearch;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface LaborElasticSearchRepository extends ElasticsearchRepository<LaborElasticSearch, String> {
	
	
	List<LaborElasticSearch> findByLaborname(String laborname);
	
	List<LaborElasticSearch> findByMagazinenameEquals(String magazinename);
	
	List<LaborElasticSearch> findByScientificareaEquals(String magazinename);
	
	List<LaborElasticSearch> findByAuthorContaining(String name, String surname);
	
	List<LaborElasticSearch> findByAuthorContaining(String name);
	
	List<LaborElasticSearch> findByKeyTermsKeyterm(String name);
	
	List<LaborElasticSearch> findByKeyTermsKeytermAndKeyTermsKeyterm(String name,String name1);
	
	List<LaborElasticSearch> findByKeyTermsKeytermAndKeyTermsKeytermAndKeyTermsKeyterm(String name,String name1,String s2);
	
	
}
