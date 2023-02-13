package com.example.day26workshop.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import static com.example.day26workshop.Constants.*;

@Configuration
public class AppConfig {
    
    // inject mongo connection string
    @Value("${mongo.url}")
    private String mongoUrl;

    // create and initialise mongotemplate
    @Bean
    public MongoTemplate createMongoTemplate() {
        // create a mongoclient
        MongoClient client = MongoClients.create(mongoUrl);
        return new MongoTemplate(client, SHOWS);
    }


}
