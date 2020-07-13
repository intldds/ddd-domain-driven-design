package com.finance.project.dtos.dtos;

import org.springframework.hateoas.RepresentationModel;

import java.util.List;
import java.util.Objects;

public class TransactionsDTO extends RepresentationModel<TransactionsDTO> {

    private List<TransactionDTOout> transactions;

    public TransactionsDTO(List<TransactionDTOout> transactions) {
        this.transactions = transactions;
    }

    public TransactionsDTO() {
    }

    public List<TransactionDTOout> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionDTOout> transactions) {
        this.transactions = transactions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionsDTO that = (TransactionsDTO) o;
        return Objects.equals(getTransactions(), that.getTransactions());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getTransactions());
    }
}
