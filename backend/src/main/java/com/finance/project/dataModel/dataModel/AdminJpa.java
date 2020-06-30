package com.finance.project.dataModel.dataModel;

import com.finance.project.domainLayer.domainEntities.vosShared.PersonID;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "admins")
public class AdminJpa {


    @Embeddable
    static class GroupPersonIdJpa implements Serializable {

        @ManyToOne
        @JoinColumn(name = "group_id")
        private GroupJpa groupJpa;

        @Column(nullable = false, updatable = false)
        private PersonID personID;

        // CONSTRUCTOR

        public GroupPersonIdJpa(GroupJpa groupJpa, PersonID personID) {
            this.groupJpa=groupJpa;
            this.personID=personID;
        }

        public GroupPersonIdJpa() {
        }

        // GETTERS AND SETTERS

        public GroupJpa getGroupJpa() {
            return groupJpa;
        }

        public void setGroupJpa(GroupJpa groupJpa) {
            this.groupJpa = groupJpa;
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
                    "groupID=" + groupJpa.getId().toString() +
                    ", personID=" + personID.toString() +
                    '}';
        }

        // EQUALS AND HASHCODE


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            GroupPersonIdJpa that = (GroupPersonIdJpa) o;
            return Objects.equals(groupJpa.getId(), that.groupJpa.getId()) &&
                    Objects.equals(personID, that.personID);
        }

        @Override
        public int hashCode() {
            return Objects.hash(groupJpa.getId(), personID);
        }
    }

    @EmbeddedId
    private GroupPersonIdJpa id;

    // CONSTRUCTOR

    public AdminJpa() {
    }

    public AdminJpa(GroupJpa groupJpa, PersonID personID) {
        this.id = new GroupPersonIdJpa(groupJpa, personID);
    }

    // GETTERS AND SETTERS

    public GroupPersonIdJpa getId() {
        return id;
    }

    public void setId(GroupPersonIdJpa id) {
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
        AdminJpa adminJpa = (AdminJpa) o;
        return Objects.equals(id, adminJpa.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
