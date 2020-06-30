package com.finance.project.dtos.dtos;

import java.util.Objects;

public class NewGroupAccountInfoDTO {

    private String accountDescription;
    private String accountDenomination;

    /**
     * Instantiates a new Create group account dto.
     *
     * @param accountDescription  the account description
     * @param accountDenomination the account denomination
     */
    public NewGroupAccountInfoDTO(String accountDescription, String accountDenomination){
        this.accountDescription = accountDescription;
        this.accountDenomination = accountDenomination;
    }

    public NewGroupAccountInfoDTO(){

    }

    //GET

    /**
     * Gets account denomination.
     *
     * @return the account denomination
     */
    public String getAccountDenomination() {
        return accountDenomination;
    }

    /**
     * Get account description string.
     *
     * @return the string
     */
    public String getAccountDescription(){
        return accountDescription;
    }

    //SET

    /**
     *
     * @param accountDenomination  account denomination setter
     */
    public void setAccountDenomination(String accountDenomination) {
        this.accountDenomination = accountDenomination;
    }

    /**
     *
     * @param accountDescription account description setter
     */
    public void setAccountDescription(String accountDescription) {
        this.accountDescription = accountDescription;
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
        NewGroupAccountInfoDTO that = (NewGroupAccountInfoDTO) o;
        return Objects.equals(accountDenomination, that.accountDenomination) &&
                Objects.equals(accountDescription, that.accountDescription);
    }

    /**
     * Hash code int.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        return Objects.hash(accountDenomination, accountDescription);
    }

}



