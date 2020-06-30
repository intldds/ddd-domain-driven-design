package com.finance.project.dtos.dtos;

import java.util.Objects;

/**
 * The type Create group transaction dto.
 *
 * @author SWitCH 2019/2020 Group 3
 * @author Joana Correia
 * @version %I%, %G% <p> CreateGroupTransactionByMemberDTO class defines the DTO responsible for: - transfer data required to create a group transaction by a member of the group
 */
public class CreateGroupTransactionDTO {
    private final String groupDenomination;
    private final String personGroupMemberEmail;
    private final String categoryDenomination;
    private final String accountToDebitName;
    private final String accountToCreditName;
    private final double transactionAmount;
    private final String transactionType;
    private final String transactionDescription;
    private final String date;

    /**
     * Instantiates a new Create group transaction dto.
     *
     * @param groupDenomination      Name of the group where the transaction refers to
     * @param personGroupMemberEmail Email of the member of the group that wants to add the transaction
     * @param categoryDenomination   Category that classifies the transaction
     * @param accountToDebitName     Denomination of the account where amount will be debited (subtracted)
     * @param accountToCreditName    Denomination of the account where amount will be credited (added)
     * @param transactionAmount      Amount of money transferred from account to debit and account to credit
     * @param transactionType        Type of transaction (credit, between accountToDebit to accountToCredit; debit between accountToCredit to accountToDebit)
     * @param transactionDescription Description related with the scope of the transaction
     */
    public CreateGroupTransactionDTO(String groupDenomination, String personGroupMemberEmail, String categoryDenomination, String accountToDebitName, String accountToCreditName, double transactionAmount, String transactionType, String transactionDescription, String date) {
        this.groupDenomination = groupDenomination;
        this.personGroupMemberEmail = personGroupMemberEmail;
        this.categoryDenomination = categoryDenomination;
        this.accountToDebitName = accountToDebitName;
        this.accountToCreditName = accountToCreditName;
        this.transactionAmount = transactionAmount;
        this.transactionType = transactionType;
        this.transactionDescription = transactionDescription;
        this.date = date;
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
     * Gets category denomination.
     *
     * @return categoryDenomination category denomination
     */
    public String getCategoryDenomination() {
        return categoryDenomination;
    }

    /**
     * Gets account to debit name.
     *
     * @return accountToDebitName account to debit name
     */
    public String getAccountToDebitName() {
        return accountToDebitName;
    }

    /**
     * Gets account to credit name.
     *
     * @return accountToCreditName account to credit name
     */
    public String getAccountToCreditName() {
        return accountToCreditName;
    }

    /**
     * Gets transaction amount.
     *
     * @return transactionAmount transaction amount
     */
    public double getTransactionAmount() {
        return transactionAmount;
    }

    /**
     * Gets transaction type.
     *
     * @return transactionType transaction type
     */
    public String getTransactionType() {
        return transactionType;
    }

    /**
     * Gets transaction description.
     *
     * @return transactionDescription transaction description
     */
    public String getTransactionDescription() {
        return transactionDescription;
    }

    public String getDate() {
        return date;
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
        CreateGroupTransactionDTO that = (CreateGroupTransactionDTO) o;
        return Double.compare(that.transactionAmount, transactionAmount) == 0 &&
                Objects.equals(groupDenomination, that.groupDenomination) &&
                Objects.equals(personGroupMemberEmail, that.personGroupMemberEmail) &&
                Objects.equals(categoryDenomination, that.categoryDenomination) &&
                Objects.equals(accountToDebitName, that.accountToDebitName) &&
                Objects.equals(accountToCreditName, that.accountToCreditName) &&
                Objects.equals(transactionType, that.transactionType) &&
                Objects.equals(transactionDescription, that.transactionDescription) &&
                Objects.equals(date, that.date);
    }

    /**
     * Hash code int.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        return Objects.hash(groupDenomination, personGroupMemberEmail, categoryDenomination, accountToDebitName, accountToCreditName, transactionAmount, transactionType, transactionDescription, date);
    }
}
