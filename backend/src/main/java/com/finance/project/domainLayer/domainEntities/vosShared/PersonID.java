package com.finance.project.domainLayer.domainEntities.vosShared;

import com.finance.project.domainLayer.entitiesInterfaces.OwnerID;
import com.finance.project.domainLayer.entitiesInterfaces.ValueObject;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.io.Serializable;
import java.util.Objects;

/**
 * The type Person id.
 */

@Embeddable
public class PersonID implements OwnerID, ValueObject, Serializable {

    @Embedded
    private Email email;

    //Constructor

    /**
     * Create person id person id.
     *
     * @param email the email
     * @return the person id
     */
    public static PersonID createPersonID(String email){
        return new PersonID(email);
    }

    private PersonID(String email) {
        if (email == null) {
            throw new IllegalArgumentException("PersonID not created due to the fact that the email parameter hasn't a valid argument");
        }
        this.email = Email.createEmail(email);
    }

    public PersonID() {
    }

    //Get email

    /**
     * Gets email.
     *
     * @return the email
     */
    public Email getEmail() {
        return email;
    }

    //Equals

    /**
     * Equals boolean.
     *
     * @param o the o
     * @return the boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonID personID = (PersonID) o;
        return Objects.equals(email, personID.email);
    }

    //HashCode

    /**
     * Hash code int.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    @Override
    public String toString() {
        return "PersonID{" +
                "email=" + email.toString() +
                '}';
    }
}
