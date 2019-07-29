package com.example.petclinic.client;

import com.example.petclinic.client.model.Owner;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.List;

@SpringBootApplication
public class PetClinicClient {

    private static final Logger log = LoggerFactory.getLogger(PetClinicClient.class);

    public static void main(String args[]) {
        SpringApplication.run(PetClinicClient.class);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
        return args -> {

            List<LinkedHashMap<String, String>> objects = restTemplate.getForObject("http://localhost:8080/owner/getAllOwners", List.class);

            // Need to map our list of HashMaps to a list of Owners
            ObjectMapper mapper = new ObjectMapper();
            List<Owner> owners = mapper.convertValue(objects, new TypeReference<List<Owner>>() {
            });

            owners.forEach(owner -> log.info(owner.toString()));

        };
    }
}