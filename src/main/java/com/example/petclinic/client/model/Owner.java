package com.example.petclinic.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Owner {

    private Long id;
    private String name;
    private String address;
    private String city;
    private String phoneNumber;

    private List<Pet> pets = new ArrayList<>();

    protected Owner() {

    }

    public Owner(String name, String address, String city, String phoneNumber) {

        this.name = name;
        this.address = address;
        this.city = city;
        this.phoneNumber = phoneNumber;

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // Update the relationship between Pet and Owner when adding a Pet
    public void addPet(Pet pet) {

        addPet(pet, true);
    }

    public void addPet(Pet pet, Boolean updateRelationship) {

        pets.add(pet);
        if (updateRelationship) {
            pet.addOwner(this, false);
        }
    }

    // Update the relationship between Pet and Owner when removing a Pet
    public void removePet(Pet pet) {

        removePet(pet, true);
    }

    public void removePet(Pet pet, Boolean updateRelationship) {

        pets.remove(pet);
        if (updateRelationship) {
            pet.removeOwner(this, false);
        }
    }

    public List<Pet> getPets() {
        return this.pets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Owner owner = (Owner) o;
        return id == owner.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {

        final StringBuilder sb = new StringBuilder("Owner {");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", address='").append(address).append('\'');
        sb.append(", city='").append(city).append('\'');
        sb.append(", phoneNumber='").append(phoneNumber).append('\'');
        sb.append('}');

        return sb.toString();
    }

    // Builder pattern using static builder
    public static OwnerBuilder builder() {
        return new OwnerBuilder();
    }

    public static final class OwnerBuilder {

        private Owner owner;

        private OwnerBuilder() {
            owner = new Owner();
        }

        public OwnerBuilder withId(Long id) {
            owner.setId(id);
            return this;
        }

        public OwnerBuilder withName(String name) {
            owner.setName(name);
            return this;
        }

        public OwnerBuilder withAddress(String address) {
            owner.setAddress(address);
            return this;
        }

        public OwnerBuilder withCity(String city) {
            owner.setCity(city);
            return this;
        }

        public OwnerBuilder withPhoneNumber(String phoneNumber) {
            owner.setPhoneNumber(phoneNumber);
            return this;
        }

        public OwnerBuilder withPet(Pet pet) {
            owner.addPet(pet);
            return this;
        }

        public Owner build() {
            return owner;
        }
    }
}
