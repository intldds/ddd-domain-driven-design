package com.finance.project.dtos.dtos;

import org.springframework.hateoas.RepresentationModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class PersonSearchAccountRecordsOutDTO extends RepresentationModel<PersonSearchAccountRecordsOutDTO> {
    private List<TransactionDTOout> transactions;

    public PersonSearchAccountRecordsOutDTO() {
        this.transactions = new ArrayList<>();
    }

    public PersonSearchAccountRecordsOutDTO(ArrayList<TransactionDTOout> transactions) {
        this.transactions = transactions;
    }


    // Getters & setters

    public List<TransactionDTOout> getTransactions() {
        return transactions;
    }

    public void setTransactions(ArrayList<TransactionDTOout> transactions) {
        this.transactions = transactions;
    }


    // Equals and hashCode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PersonSearchAccountRecordsOutDTO that = (PersonSearchAccountRecordsOutDTO) o;
        return transactions.equals(that.transactions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), transactions);
    }
}
