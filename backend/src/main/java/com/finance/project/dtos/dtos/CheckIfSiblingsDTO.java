package com.finance.project.dtos.dtos;

import java.util.Objects;

/**
 * The type Check if siblings dto.
 *
 * @author Ala Matos
 */
public class CheckIfSiblingsDTO {

    private String  email;
    private String siblingEmail;

    /**
     * Instantiates a new Check if siblings dto.
     */
    public CheckIfSiblingsDTO () {    }

    /**
     * Instantiates a new Check if siblings dto.
     *
     * @param email         the email
     * @param siblingsEmail the siblings email
     */
    public CheckIfSiblingsDTO(String email, String siblingsEmail){
        this.email = email;
        this.siblingEmail=siblingsEmail;
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
     * Gets sibling email.
     *
     * @return the sibling email
     */
    public String getSiblingEmail() {
        return siblingEmail;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Sets sibling email.
     *
     * @param siblingEmail the sibling email
     */
    public void setSiblingEmail(String siblingEmail) {
        this.siblingEmail = siblingEmail;
    }

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
        CheckIfSiblingsDTO checkIfSiblingsDTO = (CheckIfSiblingsDTO) o;
        return Objects.equals(email, checkIfSiblingsDTO.email) &&
                Objects.equals(siblingEmail, checkIfSiblingsDTO.siblingEmail);
    }

    /**
     * Hash code int.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        return Objects.hash(email, siblingEmail);
    }
}
