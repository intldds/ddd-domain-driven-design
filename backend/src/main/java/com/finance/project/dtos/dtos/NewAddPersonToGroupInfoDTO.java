package com.finance.project.dtos.dtos;

import java.util.Objects;

/**
 * The type New add person to group info dto.
 */
public class NewAddPersonToGroupInfoDTO {
    private String email;

    /**
     * Instantiates a new New add person to group info dto.
     */
    public NewAddPersonToGroupInfoDTO(){
    }

    /**
     * Instantiates a new New add person to group info dto.
     *
     * @param email        the email

     */
    public NewAddPersonToGroupInfoDTO(String email){
        this.email= email;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
//Gets
    public String getEmail() {
        return email;
    }



    /**
     * Sets email.
     *
     * @param email the email
     */
//Sets
    public void setEmail(String email) {
        this.email = email;
    }


    //equals


    /**
     * Equals boolean.
     *
     * @param o the o
     * @return the boolean
     */
    @Override

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NewAddPersonToGroupInfoDTO)) return false;
        NewAddPersonToGroupInfoDTO newAddPersonToGroupInfoDTO= (NewAddPersonToGroupInfoDTO) o;
        return Objects.equals(getEmail(), newAddPersonToGroupInfoDTO.getEmail());
    }

    /**
     * Hash code int.
     *
     * @return the int
     */
//hashcode
    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}
