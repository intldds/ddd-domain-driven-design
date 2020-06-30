package com.finance.project.dataModel.dataModel;

import com.finance.project.domainLayer.domainEntities.vosShared.PersonID;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "siblings")
public class SiblingJpa {


    @Embeddable
    static class PersonPersonIdJpa implements Serializable {

        @ManyToOne
        @JoinColumn(name = "my_person_id")
        private PersonJpa personJpa;

        @Column(nullable = false, updatable = false)
        private PersonID personID;

        // CONSTRUCTOR

        public PersonPersonIdJpa(PersonJpa personJpa, PersonID personID) {
            this.personJpa=personJpa;
            this.personID=personID;
        }

        public PersonPersonIdJpa() {
        }

        // GETTERS AND SETTERS

        public PersonJpa getPersonJpa() {
            return personJpa;
        }

        public void setPersonJpa(PersonJpa personJpa) {
            this.personJpa = personJpa;
        }

        public PersonID getPersonID() {
            return personID;
        }

        public void setPersonID(PersonID personID) {
            this.personID = personID;
        }


        // TO STRING


        @Override
        public String toString() {
            return "GroupPersonIdJpa{" +
                    "personID=" + personJpa.getId().toString() +
                    ", personID=" + personID.toString() +
                    '}';
        }

        // EQUALS AND HASHCODE


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            PersonPersonIdJpa that = (PersonPersonIdJpa) o;
            return Objects.equals(personJpa.getId(), that.personJpa.getId()) &&
                    Objects.equals(personID, that.personID);
        }

        @Override
        public int hashCode() {
            return Objects.hash(personJpa.getId(), personID);
        }
    }

    @EmbeddedId
    private PersonPersonIdJpa id;

    // CONSTRUCTOR

    public SiblingJpa() {
    }

    public SiblingJpa(PersonJpa personJpa, PersonID personID) {
        this.id = new PersonPersonIdJpa(personJpa, personID);
    }

    // GETTERS AND SETTERS

    public PersonPersonIdJpa getId() {
        return id;
    }

    public void setId(PersonPersonIdJpa id) {
        this.id = id;
    }

    public PersonID getPersonID(){
        return id.personID;
    }


    // TO STRING

    @Override
    public String toString() {
        return "AdminJpa{" +
                "id=" + id.toString() +
                '}';
    }


    // EQUALS AND HASHCODE


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SiblingJpa adminJpa = (SiblingJpa) o;
        return Objects.equals(id, adminJpa.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
