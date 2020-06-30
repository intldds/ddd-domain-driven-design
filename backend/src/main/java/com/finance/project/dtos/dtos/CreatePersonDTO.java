package com.finance.project.dtos.dtos;

import java.time.LocalDate;
import java.util.Objects;

/**
 * The type Create group dto.
 */
public class CreatePersonDTO {

    private String email;
    private String name;
    private LocalDate birthdate;
    private String birthplace;

    /**
     * Instantiates a new Create group dto.
     *
     * @param email         the email
     * @param name          the name
     * @param birthdate     the birthdate
     * @param birthplace    the birthplace
     */

    public CreatePersonDTO(String email, String name, LocalDate birthdate, String birthplace) {
        this.email = email;
        this.name = name;
        this.birthdate = birthdate;
        this.birthplace = birthplace;
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
     * Gets name
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets birthdate
     *
     * @return the birthdate
     */
    public LocalDate getBirthdate() {
        return birthdate;
    }


    /**
     * Gets birthplace.
     *
     * @return the birthplace
     */
    public String getBirthplace() {
        return birthplace;
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
