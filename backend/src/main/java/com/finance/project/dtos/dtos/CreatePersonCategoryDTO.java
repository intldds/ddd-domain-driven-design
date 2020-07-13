package com.finance.project.dtos.dtos;

import java.util.Objects;


public class CreatePersonCategoryDTO {

    private final String email;
    private final String denomination;    // Category denomination

    public CreatePersonCategoryDTO(String email, String denomination) {
        this.email = email;
        this.denomination = denomination;
    }


    // Getters

    public String getEmail() {
        return email;
    }

    public String getDenomination() {
        return denomination;
    }


    // Equals and hashCode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreatePersonCategoryDTO createPersonCategoryDTO = (CreatePersonCategoryDTO) o;
        return Objects.equals(email, createPersonCategoryDTO.email) &&
                Objects.equals(denomination, createPersonCategoryDTO.denomination);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, denomination);
    }

}
