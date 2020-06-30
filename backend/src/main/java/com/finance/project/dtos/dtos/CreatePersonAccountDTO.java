package com.finance.project.dtos.dtos;

import java.util.Objects;

/**
 * The type Create person account dto.
 */
public class CreatePersonAccountDTO {

    private final String email;
    private final String description;     // Account description
    private final String denomination;    // Account denomination

    /**
     * Instantiates a new Create person account dto.
     *
     * @param email        the email
     * @param denomination the denomination
     * @param description  the description
     */
    public CreatePersonAccountDTO(String email, String description, String denomination){
        this.email = email;
        this.description = description;
        this.denomination = denomination;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets denomination.
     *
     * @return the denomination
     */
    public String getDenomination() {
        return denomination;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }


    // Equals and hashCode methods

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
        CreatePersonAccountDTO createPersonAccountDTO = (CreatePersonAccountDTO) o;
        return Objects.equals(email, createPersonAccountDTO.email) &&
                Objects.equals(denomination, createPersonAccountDTO.denomination) &&
                Objects.equals(description, createPersonAccountDTO.description);
    }

    /**
     * Hash code int.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        return Objects.hash(email, denomination, description);
    }
}
