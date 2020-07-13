package com.finance.project.dtos.dtos;

import java.util.Objects;

/**
 * The type Add person to group dto.
 */
public class AddPersonToGroupDTO {

    private String email;
    private String denomination;

    public AddPersonToGroupDTO(){
    }


    public AddPersonToGroupDTO(String email, String denomination){
        this.email = email;
        this.denomination = denomination;
    }


    public String getEmail() {
        return email;
    }

    public String getDenomination() {
        return denomination;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AddPersonToGroupDTO)) return false;
        AddPersonToGroupDTO addPersonToGroupDTO = (AddPersonToGroupDTO) o;
        return Objects.equals(getEmail(), addPersonToGroupDTO.getEmail()) &&
                Objects.equals(getDenomination(), addPersonToGroupDTO.getDenomination());
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, denomination);
    }
}

