package com.finance.project.dtos.dtos;

import org.springframework.hateoas.RepresentationModel;

import java.util.Objects;


public class PersonDTO extends RepresentationModel<PersonDTO> {

    private String email;
    private String ledger;
    private String name;
    private String birthdate;
    private String birthplace;
    private String father;
    private String mother;

    public PersonDTO(String email, String name, String birthdate, String birthplace, String father, String mother) {
        this.email = email;
        this.ledger = "";
        this.name = name;
        this.birthdate = birthdate;
        this.birthplace = birthplace;
        this.father = father;
        this.mother = mother;
    }

    public PersonDTO(String email, String name, String birthdate, String birthplace) {
        this.email = email;
        this.ledger = "";
        this.name = name;
        this.birthdate = birthdate;
        this.birthplace = birthplace;
    }

    public PersonDTO(String email, String ledger, String name, String birthdate, String birthplace, String father, String mother) {
        this.email = email;
        this.ledger = ledger;
        this.name = name;
        this.birthdate = birthdate;
        this.birthplace = birthplace;
        this.father = father;
        this.mother = mother;
    }

    public PersonDTO(String email, String ledger, String name, String birthdate, String birthplace) {
        this.email = email;
        this.ledger = ledger;
        this.name = name;
        this.birthdate = birthdate;
        this.birthplace = birthplace;
    }

    public PersonDTO() {

    }

    // Getters & Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLedger() { return ledger; }

    public void setLedger(String ledgerID) { this.ledger = ledgerID; }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getBirthplace() {
        return birthplace;
    }

    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }

    public String getFather() {
        return father;
    }

    public void setFather(String father) {
        this.father = father;
    }

    public String getMother() {
        return mother;
    }

    public void setMother(String mother) {
        this.mother = mother;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonDTO personDTO = (PersonDTO) o;
        return Objects.equals(email, personDTO.email) &&
                Objects.equals(ledger, personDTO.ledger) &&
                Objects.equals(name, personDTO.name) &&
                Objects.equals(birthdate, personDTO.birthdate) &&
                Objects.equals(birthplace, personDTO.birthplace) &&
                Objects.equals(father, personDTO.father) &&
                Objects.equals(mother, personDTO.mother);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), email, ledger, name, birthdate, birthplace, father, mother);
    }
}