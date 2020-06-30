package com.finance.project.dtos.dtos;

import java.time.LocalDate;
import java.util.Objects;

/**
 * The type Group transactions within period dt oin.
 */
public class GroupTransactionsWithinPeriodDTOin {
    private final String personEmail;
    private final String groupDenomination;
    private final LocalDate startDate;
    private final LocalDate endDate;

    /**
     * Instantiates a new Group transactions within period dt oin.
     *
     * @param personEmail       the person email
     * @param groupDenomination the group denomination
     * @param startDate         the start date
     * @param endDate           the end date
     */
    public GroupTransactionsWithinPeriodDTOin(String personEmail, String groupDenomination, LocalDate startDate, LocalDate endDate) {
        this.personEmail = personEmail;
        this.groupDenomination = groupDenomination;
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
     * Gets group denomination.
     *
     * @return the group denomination
     */
    public String getGroupDenomination() {
        return groupDenomination;
    }

    /**
     * Gets start date.
     *
     * @return the start date
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Gets end date.
     *
     * @return the end date
     */
    public LocalDate getEndDate() {
        return endDate;
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
        GroupTransactionsWithinPeriodDTOin that = (GroupTransactionsWithinPeriodDTOin) o;
        return personEmail.equals(that.personEmail) &&
                groupDenomination.equals(that.groupDenomination) &&
                startDate.equals(that.startDate) &&
                endDate.equals(that.endDate);
    }

    /**
     * Hash code int.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        return Objects.hash(personEmail, groupDenomination, startDate, endDate);
    }
}
