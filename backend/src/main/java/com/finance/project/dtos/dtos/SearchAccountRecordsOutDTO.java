package com.finance.project.dtos.dtos;

import org.springframework.hateoas.RepresentationModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * DTO out - Info for delivering the list of transactions of a person, for a given account, within a given period.
 */
public class SearchAccountRecordsOutDTO extends RepresentationModel<SearchAccountRecordsOutDTO> {
    private List<TransactionDTOout> transactions;

    /**
     * Instantiates a new DTO (out) for delivering the list of transactions of a person, for a given account, within a given period.
     */
    public SearchAccountRecordsOutDTO() {
        this.transactions = new ArrayList<>();
    }


    /**
     * Instantiates a new DTO (out) for delivering the list of transactions of a person, for a given account, within a given period.
     *
     * @param transactions       the transactions list
     */
    public SearchAccountRecordsOutDTO(ArrayList<TransactionDTOout> transactions) {
        this.transactions = transactions;
    }


    /**
     * Gets transactions list.
     *
     * @return the transactions list
     */
    public List<TransactionDTOout> getTransactions() {
        return transactions;
    }


    /**
     * Sets transactions list.
     *
     * @param transactions the transactions list
     */
    public void setTransactions(ArrayList<TransactionDTOout> transactions) {
        this.transactions = transactions;
    }


    //Equals and HashCode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SearchAccountRecordsOutDTO that = (SearchAccountRecordsOutDTO) o;
        return transactions.equals(that.transactions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), transactions);
    }
}
