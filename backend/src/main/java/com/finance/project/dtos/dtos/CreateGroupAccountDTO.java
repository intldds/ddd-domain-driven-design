package com.finance.project.dtos.dtos;

import java.util.Objects;

/**
 * The type Create group account dto.
 */
public class CreateGroupAccountDTO {

    private String personEmail;
    private String groupDenomination;
    private String accountDescription;
    private String accountDenomination;

    /**
     * Instantiates a new Create group account dto
     */
    public CreateGroupAccountDTO(){
    }

    /**
     * Instantiates a new Create group account dto.
     *
     * @param personEmail         the person email
     * @param groupDenomination   the group denomination
     * @param accountDescription  the account description
     * @param accountDenomination the account denomination
     */
    public CreateGroupAccountDTO(String personEmail, String groupDenomination, String accountDescription, String accountDenomination){
        this.personEmail = personEmail;
        this.groupDenomination = groupDenomination;
        this.accountDescription = accountDescription;
        this.accountDenomination = accountDenomination;

    }

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

    /**
     * Gets group denomination.
     *
     * @return the group denomination
     */
    public String getGroupDenomination() {
        return groupDenomination;
    }

    /**
     * Gets person email.
     *
     * @return the person email
     */
    public String getPersonEmail() {
        return personEmail;
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
        CreateGroupAccountDTO that = (CreateGroupAccountDTO) o;
        return Objects.equals(personEmail, that.personEmail) &&
                Objects.equals(groupDenomination, that.groupDenomination) &&
                Objects.equals(accountDenomination, that.accountDenomination) &&
                Objects.equals(accountDescription, that.accountDescription);
    }

    /**
     * Hash code int.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        return Objects.hash(personEmail, groupDenomination, accountDenomination, accountDescription);
    }
    
}
