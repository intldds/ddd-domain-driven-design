package com.finance.project.dtos.dtos;

import java.util.Objects;

/**
 * The type Person iddto.
 */
public class PersonIDDTO {

    private String email;

    /**
     * Instantiates a new Person iddto.
     *
     * @param personID the person id
     */
    public PersonIDDTO(String personID) {
        this.email = personID;
    }

    /**
     * Gets denomination.
     *
     * @return the denomination
     */
    public String getEmail() { return email; }

    /**
     * Equals boolean.
     *
     * @param o the o
     * @return the boolean
     */


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersonIDDTO)) return false;
        PersonIDDTO that = (PersonIDDTO) o;
        return email.equals(that.email);
    }

    /**
     * Hash code int.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}
