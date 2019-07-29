# PetClinic Phase 4 REST Client

#### Overview
This application is used with one of the activities in the PetClinic Phase 4 project.  It uses a Spring RestTemplate to query REST endpoints.  It can be customized to test any endpoint.

#### How to Use
* To use this REST client, modify the following lines in the `PetClinicClient` class as needed:

```text
List<LinkedHashMap<String, String>> objects = restTemplate.getForObject("http://localhost:8080/owner/getAllOwners", List.class);

List<Owner> owners = mapper.convertValue(objects, new TypeReference<List<Owner>>() { });
```

* The `getForObject` method on the first line can be changed to point to different endpoints.
* The `convertValue` method on the second line can be modified to accept different model types (Owner, Pet, Visit, Vet).  Be sure to change the model reference in both places on that line.

#### Notes
* Dependencies have already been added for you in the pom.xml file for this phase.  You shouldn't have to modify or add anything to your build file (pom.xml), but feel free to as needed.

