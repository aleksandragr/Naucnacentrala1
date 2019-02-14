package naucnaCentrala.elasticsearch;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.elasticsearch.client.Client;
import org.springframework.context.annotation.Bean;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.TransportAddress;
import java.net.InetAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

@Configuration
@EnableElasticsearchRepositories(basePackages="naucnaCentrala.elasticSearch")
public class EsConfig {

	
	@Value("${elasticsearch.host}")
    private String EsHost;

    @Value("${elasticsearch.port}")
    private int EsPort;
/*
    @Value("${elasticsearch.clustername}")
    private String EsClusterName;
  */  
    private PreBuiltTransportClient preBuiltTransportClient;

    @Bean
    public Client client() throws Exception {

    	Settings settings = Settings.builder()
    	        .put("cluster.name", "elasticsearch").build();
    	preBuiltTransportClient = new PreBuiltTransportClient(settings);
		TransportClient client = preBuiltTransportClient
    	        .addTransportAddress(new TransportAddress(InetAddress.getByName(EsHost), EsPort));
    	        

    	return client;
    }
    
    @Bean
    public ElasticsearchOperations elasticsearchTemplate() throws Exception {
        return new ElasticsearchTemplate(client());
    }
    
    
}