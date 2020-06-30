package com.finance.project.dtos.dtos;

import org.springframework.hateoas.RepresentationModel;

import java.util.Objects;

/**
 * @author Ala Matos
 */

public class PersonEmailDTO extends RepresentationModel<PersonEmailDTO> {

    private String email;

    public PersonEmailDTO(String email) {
        this.email = email;

    }

    /**
     * Gets email
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }


    /**
     * Equals boolean used to compare objects of the class PersonEmailDTO
     *
     * @param o the object o
     * @return the boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonEmailDTO that = (PersonEmailDTO) o;
        return Objects.equals(email, that.email);
    }

    /**
     * Hash code int as outcome
     *
     * @return an int
     */

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}
