package com.finance.project.dtos.dtos;

import java.util.Objects;

public class CreatePersonAccountDTO {

    private final String email;
    private final String description;     // Account description
    private final String denomination;    // Account denomination


    public CreatePersonAccountDTO(String email, String description, String denomination){
        this.email = email;
        this.description = description;
        this.denomination = denomination;
    }

    // Getters

    public String getEmail() {
        return email;
    }

    public String getDenomination() {
        return denomination;
    }

    public String getDescription() {
        return description;
    }


    // Equals and hashCode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreatePersonAccountDTO createPersonAccountDTO = (CreatePersonAccountDTO) o;
        return Objects.equals(email, createPersonAccountDTO.email) &&
                Objects.equals(denomination, createPersonAccountDTO.denomination) &&
                Objects.equals(description, createPersonAccountDTO.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, denomination, description);
    }
}
