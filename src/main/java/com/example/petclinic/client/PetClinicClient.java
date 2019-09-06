package com.example.petclinic.client;

import com.example.petclinic.client.model.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.List;

import static com.example.petclinic.client.model.PetType.DOG;
import static com.example.petclinic.client.model.Speciality.DENTISTRY;

//import static com.sun.org.apache.xerces.internal.util.PropertyState.is;

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

            String addOwnerUrl = "http://localhost:8080/owner/addOwner";
            String addPetUrl = "http://localhost:8080/owner/addPet";
            String addVetUrl = "http://localhost:8080/owner/addVet";
            String addvisitUrl = "http://localhost:8080/owner/addVisit";

            petJsonObject = new JSONObject();
            petJsonObject.put("id", 1);

            consumes.application.json();
            HttpEntity<Owner> ownerRequest = new HttpEntity<>(new Owner("bartender","123","xyz","1234567890"));
            HttpEntity<Pet> petRequest = new HttpEntity<>(new Pet("wow-cow","2019-01-19", DOG));
//            HttpEntity<Vet> vetRequest = new HttpEntity<>(new Vet("Dr. Man",Speciality.DENTISTRY));
//            HttpEntity<Visit> visitRequest = new HttpEntity<>(new Visit("2019-01-19T18:25:43.511Z","NONE"));

//            Owner owner3 = restTemplate.postForObject("http://localhost:8080/owner/addOwner", request, Owner.class);
//            assertThat(owner, notNullValue());
//            assertThat(owner.addPet(), is("smitty"));

            Owner addOwnerObject = restTemplate.postForObject(addOwnerUrl, ownerRequest, Owner.class);
//            Pet addPetObject = restTemplate.postForObject(addPetUrl, petRequest, Pet.class);
//            Vet addVetObject = restTemplate.postForObject(addVetUrl, vetRequest, Vet.class);
//            Visit addVisitObject = restTemplate.postForObject(addVisitUrl, visitRequest, Visit.class);


            List<LinkedHashMap<String, String>> object1 = restTemplate.getForObject("http://localhost:8080/owner/getAllOwners", List.class);
            List<LinkedHashMap<String, String>> object2 = restTemplate.getForObject("http://localhost:8080/pet/getAllPets", List.class);
            List<LinkedHashMap<String, String>> object3 = restTemplate.getForObject("http://localhost:8080/vet/getAllVets", List.class);
            List<LinkedHashMap<String, String>> object4 = restTemplate.getForObject("http://localhost:8080/visit/getAllVisits", List.class);

            // Need to map our list of HashMaps to a list of Owners
            ObjectMapper mapper = new ObjectMapper();
            List<Owner> owners = mapper.convertValue(object1, new TypeReference<List<Owner>>() {});
            List<Pet> pets = mapper.convertValue(object2, new TypeReference<List<Pet>>() {});
            List<Vet> vets = mapper.convertValue(object3, new TypeReference<List<Vet>>() {});
            List<Visit> visits = mapper.convertValue(object4, new TypeReference<List<Visit>>() {});

            owners.forEach(owner -> log.info(owner.toString()));
            pets.forEach(pet -> log.info(pet.toString()));
            vets.forEach(vet -> log.info(vet.toString()));
            visits.forEach(visit -> log.info(visit.toString()));

        };
    }
}