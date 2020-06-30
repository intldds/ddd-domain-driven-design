package com.finance.project.dtos.dtos;

import java.util.Objects;

/**
 * @author Ala Matos
 */

public class PrepareInfoToCheckIfSiblingsDTO {

    private String email;
    private String siblingEmail;

    public PrepareInfoToCheckIfSiblingsDTO() {
    }

    public PrepareInfoToCheckIfSiblingsDTO(String email, String siblingsEmail) {
        this.email = email;
        this.siblingEmail = siblingsEmail;
    }


    public String getEmail() {
        return email;
    }

    public String getSiblingEmail() {
        return siblingEmail;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSiblingEmail(String siblingEmail) {
        this.siblingEmail = siblingEmail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PrepareInfoToCheckIfSiblingsDTO PrepareInfoToCheckIfSiblingsDTO = (PrepareInfoToCheckIfSiblingsDTO) o;
        return Objects.equals(email, PrepareInfoToCheckIfSiblingsDTO.email) &&
                Objects.equals(siblingEmail, PrepareInfoToCheckIfSiblingsDTO.siblingEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, siblingEmail);
    }
}