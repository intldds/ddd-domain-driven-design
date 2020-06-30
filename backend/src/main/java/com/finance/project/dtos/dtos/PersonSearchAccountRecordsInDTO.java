package com.finance.project.dtos.dtos;

import java.util.Objects;

/**
 * DTO in - Info for retrieving the list of transactions of a person, for a given account, within a given period.
 */
public class  PersonSearchAccountRecordsInDTO {
    private final String personEmail;
    private final String accountDenomination;
    private final String startDate;
    private final String endDate;

    /**
     * Instantiates a new DTO (in) for retrieving a person's transactions within a given period.
     *
     * @param personEmail         the person email
     * @param accountDenomination the denomination of the account to search
     * @param startDate           the start date of the period to search
     * @param endDate             the end date of the period to search
     */
    public PersonSearchAccountRecordsInDTO(String personEmail, String accountDenomination, String startDate, String endDate) {
        this.personEmail = personEmail;
        this.accountDenomination = accountDenomination;
        this.startDate = startDate;
        this.endDate = endDate;
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
     * Gets account denomination.
     *
     * @return the account denomination
     */
    public String getAccountDenomination() {
        return accountDenomination;
    }

    /**
     * Gets start date.
     *
     * @return the start date
     */
    public String getStartDate() {
        return startDate;
    }


    /**
     * Gets end date.
     *
     * @return the end date
     */
    public String getEndDate() {
        return endDate;
    }


    //Equals and HashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonSearchAccountRecordsInDTO that = (PersonSearchAccountRecordsInDTO) o;
        return personEmail.equals(that.personEmail) &&
                accountDenomination.equals(that.accountDenomination) &&
                startDate.equals(that.startDate) &&
                endDate.equals(that.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personEmail, accountDenomination, startDate, endDate);
    }
}
