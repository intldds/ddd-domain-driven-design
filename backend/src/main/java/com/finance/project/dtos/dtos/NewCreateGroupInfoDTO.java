package com.finance.project.dtos.dtos;

import java.util.Objects;

/**
 * * @author SWitCH 2019/2020 Group 3
 * * @author Elisabete do Vale
 * * @version %I%, %G%
 * * <p>
 * * NewCreateGroupInfoDTO class defines the DTO that results from Json and are responsible for:
 * * - transfer data required to create a group and become Person In Charge
 */

public class NewCreateGroupInfoDTO {

    private String email;
    private String denomination;
    private String description;

    /**
     * NewCreateGroupInfoDTO class constructor with parameter
     * @param email
     * @param denomination
     * @param description
     */

    public NewCreateGroupInfoDTO(String email, String denomination, String description) {
        this.email = email;
        this.denomination = denomination;
        this.description = description;
    }

    /**
     * NewCreateGroupInfoDTO class constructor without parameters
     */

    public NewCreateGroupInfoDTO() {
    }

    /**
     * @return personEmail
     */

    public String getEmail() {
        return email;
    }

    /**
     * @return groupDenomination
     */

    public String getDenomination() {
        return denomination;
    }

    /**
     * @return groupDescription
     */

    public String getDescription() {
        return description;
    }

    /**
     * @param email
     */

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @param denomination
     */

    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }

    /**
     * @param description
     */

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Equals boolean.
     * @param o the o
     * @return the boolean
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NewCreateGroupInfoDTO)) return false;
        NewCreateGroupInfoDTO that = (NewCreateGroupInfoDTO) o;
        return Objects.equals(getEmail(), that.getEmail()) &&
                Objects.equals(getDenomination(), that.getDenomination()) &&
                Objects.equals(getDescription(), that.getDescription());
    }

    /**
     * Hash code int.
     * @return the int
     */

    @Override
    public int hashCode() {
        return Objects.hash(getEmail(), getDenomination(), getDescription());
    }
}
