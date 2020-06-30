package com.finance.project.dtos.dtos;

import java.util.Objects;

public class NewPersonAccountInfoDTO {

    private String description;     // account description
    private String denomination;    // account denomination

    public NewPersonAccountInfoDTO(String description, String denomination) {
        this.description = description;
        this.denomination = denomination;
    }

    public NewPersonAccountInfoDTO() {
    }


    // Getters
    public String getDenomination() {
        return denomination;
    }

    public String getDescription() {
        return description;
    }

    // Setters
    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewPersonAccountInfoDTO that = (NewPersonAccountInfoDTO) o;
        return Objects.equals(denomination, that.denomination) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(denomination, description);
    }
}