package com.finance.project.dtos.dtos;

import java.util.Objects;

public class  PersonSearchAccountRecordsInDTO {
    private final String personEmail;
    private final String accountDenomination;
    private final String startDate;
    private final String endDate;


    public PersonSearchAccountRecordsInDTO(String personEmail, String accountDenomination, String startDate, String endDate) {
        this.personEmail = personEmail;
        this.accountDenomination = accountDenomination;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // Getters

    public String getPersonEmail() {
        return personEmail;
    }

    public String getAccountDenomination() {
        return accountDenomination;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }


    // Equals and hashCode

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
