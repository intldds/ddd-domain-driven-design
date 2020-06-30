package com.finance.project.dtos.dtos;

import java.util.Objects;

/**
 * The type Create group transaction dto.
 *
 * @author SWitCH 2019/2020 Group 3
 * @author Joana Correia
 * @version %I%, %G% <p> CreateGroupTransactionByMemberDTO class defines the DTO responsible for: - transfer data required to create a group transaction by a member of the group
 */
public class DeleteGroupTransactionDTO {
    private final int transactionNumber;
    private final String groupDenomination;
    private final String personGroupMemberEmail;

    /**
     * Instantiates a new Create group transaction dto.
     *
     * @param groupDenomination      Name of the group where the transaction refers to
     * @param personGroupMemberEmail Email of the member of the group that wants to add the transaction
     */
    public DeleteGroupTransactionDTO(int transactionNumber, String groupDenomination, String personGroupMemberEmail) {
        this.transactionNumber = transactionNumber;
        this.groupDenomination = groupDenomination;
        this.personGroupMemberEmail = personGroupMemberEmail;
    }

    public int getTransactionNumber() {
        return transactionNumber;
    }

    /**
     * Gets group denomination.
     *
     * @return groupDenomination group denomination
     */
    public String getGroupDenomination() {
        return groupDenomination;
    }

    /**
     * Gets person group member email.
     *
     * @return personGroupMemberEmail person group member email
     */
    public String getPersonGroupMemberEmail() {
        return personGroupMemberEmail;
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
        DeleteGroupTransactionDTO that = (DeleteGroupTransactionDTO) o;
        return Objects.equals(groupDenomination, that.groupDenomination) &&
                Objects.equals(personGroupMemberEmail, that.personGroupMemberEmail) &&
                Objects.equals(transactionNumber, that.transactionNumber);
    }

    /**
     * Hash code int.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        return Objects.hash(groupDenomination, personGroupMemberEmail, transactionNumber);
    }
}
