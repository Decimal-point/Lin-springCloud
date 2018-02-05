package com.lin.config;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.cloud.SolrZkClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;

/*@Configuration
@EnableSolrRepositories(basePackages = {"com.lin"},multicoreSupport = true)
public class SolrConfig {
    @Value("${spring.data.solr.host}")
    private String solrHost;

    @Autowired
    private SolrServer solrServer;

    @Bean
    public SolrTemplate solrTemplate(){
        return new SolrTemplate(solrServer);
    }
}*/
