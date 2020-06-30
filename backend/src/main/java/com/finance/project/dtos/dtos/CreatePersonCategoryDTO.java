package com.finance.project.dtos.dtos;

import java.util.Objects;

/**
 * The type Create person category dto.
 */
public class CreatePersonCategoryDTO {

    private final String email;
    private final String denomination;    // Category denomination

    /**
     * Instantiates a new Create person category dto.
     *
     * @param email        the email
     * @param denomination the denomination
     */
    public CreatePersonCategoryDTO(String email, String denomination){
        this.email = email;
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
        CreatePersonCategoryDTO createPersonCategoryDTO = (CreatePersonCategoryDTO) o;
        return Objects.equals(email, createPersonCategoryDTO.email) &&
                Objects.equals(denomination, createPersonCategoryDTO.denomination);
    }

    /**
     * Hash code int.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        return Objects.hash(email, denomination);
    }
    
}
