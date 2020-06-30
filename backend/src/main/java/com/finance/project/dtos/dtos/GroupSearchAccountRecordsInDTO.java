package com.finance.project.dtos.dtos;

import java.util.Objects;

/**
 * DTO in - Info for retrieving the list of transactions of a group, by a member,
 * for a given account, within a given period.
 */
public class GroupSearchAccountRecordsInDTO {
    private final String personEmail;
    private final String groupDenomination;
    private final String accountDenomination;
    private final String startDate;
    private final String endDate;

    /**
     * Instantiates a new DTO (in) for retrieving the group's transactions, by a member,
     * for a given account, within a given period.
     *
     * @param personEmail         the person email (group member)
     * @param groupDenomination   the group denomination
     * @param accountDenomination the denomination of the account to search
     * @param startDate           the start date of the period to search
     * @param endDate             the end date of the period to search
     */
    public GroupSearchAccountRecordsInDTO(String personEmail, String groupDenomination, String accountDenomination, String startDate, String endDate) {
        this.personEmail = personEmail;
        this.groupDenomination = groupDenomination;
        this.accountDenomination = accountDenomination;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Gets person email (group member).
     *
     * @return the person email
     */
    public String getPersonEmail() {
        return personEmail;
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
        GroupSearchAccountRecordsInDTO that = (GroupSearchAccountRecordsInDTO) o;
        return personEmail.equals(that.personEmail) && groupDenomination.equals(that.groupDenomination) && accountDenomination.equals(that.accountDenomination) && startDate.equals(that.startDate) && endDate.equals(that.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personEmail, groupDenomination, accountDenomination, startDate, endDate);
    }
}
