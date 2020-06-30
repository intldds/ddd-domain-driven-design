package com.finance.project.dtos.dtos;

/**
 * @Elisabete_Cavaleiro
 */

import java.util.Objects;

/**
 * The type Add person to group dto.
 */
public class AddPersonToGroupDTO {

    private String email;
    private String denomination;

    /**
     * Instantiates a new Add person to group dto.
     */
    public AddPersonToGroupDTO(){
    }

    /**
     * Instantiates a new Add person to group dto.
     *
     * @param email        the email
     * @param denomination the denomination
     */
    public AddPersonToGroupDTO(String email, String denomination){
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

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Sets denomination.
     *
     * @param denomination the denomination
     */
    public void setDenomination(String denomination) {
        this.denomination = denomination;
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
        if (!(o instanceof AddPersonToGroupDTO)) return false;
        AddPersonToGroupDTO addPersonToGroupDTO = (AddPersonToGroupDTO) o;
        return Objects.equals(getEmail(), addPersonToGroupDTO.getEmail()) &&
                Objects.equals(getDenomination(), addPersonToGroupDTO.getDenomination());
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

