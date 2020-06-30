package com.finance.project.dtos.dtos;

import org.springframework.hateoas.RepresentationModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The type Group transactions within period dt oout.
 */
public class GroupTransactionsWithinPeriodDTOout extends RepresentationModel<GroupTransactionsWithinPeriodDTOout> {
    private List<TransactionDTOout> transactionsList;

    /**
     * Instantiates a new Group transactions within period dt oout.
     */
    public GroupTransactionsWithinPeriodDTOout() {
        this.transactionsList = new ArrayList<>();
    }

    /**
     * Instantiates a new Group transactions within period dt oout.
     *
     * @param transactionsList       the transactions list
     */
    public GroupTransactionsWithinPeriodDTOout(ArrayList<TransactionDTOout> transactionsList) {
        this.transactionsList = transactionsList;
    }

    /**
     * Gets transactions list.
     *
     * @return the transactions list
     */
    public List<TransactionDTOout> getTransactionsList() {
        return transactionsList;
    }

    /**
     * Sets transactions list.
     *
     * @param transactionsList the transactions list
     */
    public void setTransactionsList(ArrayList<TransactionDTOout> transactionsList) {
        this.transactionsList = transactionsList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupTransactionsWithinPeriodDTOout that = (GroupTransactionsWithinPeriodDTOout) o;
        return transactionsList.equals(that.transactionsList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionsList);
    }
}
