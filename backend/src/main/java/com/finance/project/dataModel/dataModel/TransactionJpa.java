package com.finance.project.dataModel.dataModel;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "transactions")
public class TransactionJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String type;
    private String description;
    private double amount;
    private String date;

    @OneToOne
    private CategoryJpa categoryID;

    @OneToOne
    private AccountJpa debitAccountID;

    @OneToOne
    private AccountJpa creditAccountID;

    @ManyToOne(fetch = FetchType.EAGER)
    private LedgerJpa ledger;

    // CONSTRUCTOR

    public TransactionJpa(CategoryJpa categoryID, String type, String description, Double amount, String date, AccountJpa debitAccountID, AccountJpa creditAccountID, LedgerJpa ledgerJpa) {
        this.categoryID = categoryID;
        this.type = type;
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.debitAccountID = debitAccountID;
        this.creditAccountID = creditAccountID;

        this.ledger = ledgerJpa;
    }

    public TransactionJpa(long id, CategoryJpa categoryID, String type, String description, Double amount, String date, AccountJpa debitAccountID, AccountJpa creditAccountID, LedgerJpa ledgerJpa) {
        this.id = id;
        this.categoryID = categoryID;
        this.type = type;
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.debitAccountID = debitAccountID;
        this.creditAccountID = creditAccountID;

        this.ledger = ledgerJpa;
    }

    public TransactionJpa() {
    }


    // GETTERS AND SETTERS

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public CategoryJpa getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(CategoryJpa categoryID) {
        this.categoryID = categoryID;
    }

    public AccountJpa getDebitAccountID() {
        return debitAccountID;
    }

    public void setDebitAccountID(AccountJpa debitAccountID) {
        this.debitAccountID = debitAccountID;
    }

    public AccountJpa getCreditAccountID() {
        return creditAccountID;
    }

    public void setCreditAccountID(AccountJpa creditAccountID) {
        this.creditAccountID = creditAccountID;
    }

    public LedgerJpa getLedger() {
        return ledger;
    }

    public void setLedger(LedgerJpa ledger) {
        this.ledger = ledger;
    }

    // TO STRING

    @Override
    public String toString() {
        return "TransactionJpa{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", date='" + date + '\'' +
                ", categoryID=" + categoryID +
                ", debitAccountID=" + debitAccountID +
                ", creditAccountID=" + creditAccountID +
                ", ledger=" + ledger +
                '}';
    }


    // EQUALS AND HASHCODE


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionJpa that = (TransactionJpa) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
