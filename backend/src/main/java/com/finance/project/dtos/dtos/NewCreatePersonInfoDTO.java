package com.finance.project.dtos.dtos;

import java.util.Objects;

/**
 * * @author SWitCH 2019/2020 Group 3
 * * @author Miguel Pereira

 */

public class NewCreatePersonInfoDTO {

    private String email;
    private String name;
    private String birthdate;
    private String birthplace;


    /**
     * NewCreatePersonInfoDTO class constructor with parameter
     * @param email
     * @param name
     * @param birthdate
     * @param birthplace
     */

    public NewCreatePersonInfoDTO(String email, String name, String  birthdate, String birthplace) {
        this.email = email;
        this.name = name;
        this.birthdate = birthdate;
        this.birthplace = birthplace;
    }

    /**
     * NewCreatePersonInfoDTO class constructor without parameters
     */

    public NewCreatePersonInfoDTO() {
    }

    /**
     * @return personEmail
     */

    public String getEmail() {
        return email;
    }

    /**
     * @return personName
     */

    public String getName() {
        return name;
    }

    /**
     * @return personBirthdate
     */

    public String getBirthdate() { return birthdate; }

    /**
     * @return personBirthplace
     */

    public String getBirthplace() {
        return birthplace;
    }



    /**
     * @param email
     */

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @param name
     */

    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param birthdate
     */

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }


    /**
     * @param birthplace
     */

    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }

    /**
     * Equals boolean.
     * @param o the o
     * @return the boolean
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewCreatePersonInfoDTO that = (NewCreatePersonInfoDTO) o;
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
