package com.finance.project.domainLayer.domainEntities.vosShared;

import com.finance.project.domainLayer.entitiesInterfaces.ValueObject;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 * The type Email.
 */

@Embeddable
public class Email implements ValueObject, Serializable {

    private String email;

    //Constructor

    /**
     * Create email email.
     *
     * @param email the email
     * @return the email
     */
    public static Email createEmail(String email){
        return new Email(email);
    }

    private Email(String email) {
        if (email == null || email.equals("")) {
            throw new IllegalArgumentException("Email not created due to the fact that the email parameter hasn't a valid argument");
        }
        this.email = email;
    }

    public Email() {
    }

    //Get Email

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        Email email1 = (Email) o;
        return Objects.equals(email.toUpperCase(), email1.email.toUpperCase());
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
        return "Email{" +
                "email='" + email + '\'' +
                '}';
    }
}
