package com.finance.project.dtos.dtos;

import java.time.LocalDate;
import java.util.Objects;


public class CreatePersonDTO {

    private String email;
    private String name;
    private LocalDate birthdate;
    private String birthplace;


    public CreatePersonDTO(String email, String name, LocalDate birthdate, String birthplace) {
        this.email = email;
        this.name = name;
        this.birthdate = birthdate;
        this.birthplace = birthplace;
    }

    // Getters

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public String getBirthplace() {
        return birthplace;
    }


    // Equals & hashCode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreatePersonDTO that = (CreatePersonDTO) o;
        return Objects.equals(email, that.email) &&
                Objects.equals(name, that.name) &&
                Objects.equals(birthdate, that.birthdate) &&
                Objects.equals(birthplace, that.birthplace);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, name, birthdate, birthplace);
    }
}
