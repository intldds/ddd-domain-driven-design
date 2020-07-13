package com.finance.project.dataModel.dataAssemblers;

import com.finance.project.dataModel.dataModel.*;
import com.finance.project.domainLayer.domainEntities.vosShared.*;
import com.finance.project.domainLayer.entitiesInterfaces.OwnerID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.finance.project.domainLayer.domainEntities.aggregates.ledger.Ledger;
import com.finance.project.domainLayer.domainEntities.aggregates.ledger.Transaction;
import com.finance.project.persistenceLayer.repositoriesJPA.AccountJpaRepository;
import com.finance.project.persistenceLayer.repositoriesJPA.CategoryJpaRepository;
import com.finance.project.persistenceLayer.repositoriesJPA.TransactionJpaRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class LedgerDomainDataAssembler {

    @Autowired
    CategoryJpaRepository categoryJpaRepository;
    @Autowired
    AccountJpaRepository accountJpaRepository;
    @Autowired
    TransactionJpaRepository transactionJpaRepository;

    public LedgerJpa toData(Ledger ledger) {

        LedgerJpa ledgerJpa = new LedgerJpa(ledger.getLedgerID().getLedgerID());

        //TRANSACTIONS
        List<TransactionJpa> repoTransactions = transactionJpaRepository.findAllByLedger(ledgerJpa);
        if (ledger.getRecords().size() > repoTransactions.size()) {

            Transaction transaction = ledger.getRecords().get(ledger.getRecords().size() - 1);

            String id = "";
            CategoryJpa categoryID = null;
            AccountJpa debAccountID = null;
            AccountJpa credAccountID = null;

            if (transaction.getCategoryID().getOwnerID() instanceof PersonID) {
                PersonID personID = (PersonID) transaction.getCategoryID().getOwnerID();
                id = personID.getEmail().getEmail();
            }

            if (transaction.getCategoryID().getOwnerID() instanceof GroupID) {
                GroupID groupID = (GroupID) transaction.getCategoryID().getOwnerID();
                id = groupID.getDenomination().getDenomination();
            }

            Optional<CategoryJpa> optCategoryID = categoryJpaRepository.findById(new AbstractIdJpa(id, transaction.getCategoryID().getDenomination().getDenomination()));
            Optional<AccountJpa> optDebAccountID = accountJpaRepository.findById(new AbstractIdJpa(id, transaction.getDebitAccountID().getDenomination().getDenomination()));
            Optional<AccountJpa> optCredAccountID = accountJpaRepository.findById(new AbstractIdJpa(id, transaction.getCreditAccountID().getDenomination().getDenomination()));
            if (optCategoryID.isPresent()) {
                categoryID = optCategoryID.get();
            }
            if (optDebAccountID.isPresent()) {
                debAccountID = optDebAccountID.get();
            }
            if (optCredAccountID.isPresent()) {
                credAccountID = optCredAccountID.get();
            }

            String type = transaction.getType().getType();
            Double amount = transaction.getAmount().getAmount();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String date = transaction.getDate().getDate().format(formatter);
            String description = transaction.getDescription().getDescription();
            repoTransactions.add(new TransactionJpa(categoryID, type, description, amount, date, debAccountID, credAccountID, ledgerJpa));
        }
        ledgerJpa.setTransactions(repoTransactions);


        return ledgerJpa;
    }

    public Ledger toDomain(LedgerJpa ledgerJpa) {

        LedgerID ledgerID = new LedgerID(ledgerJpa.getId().getLedgerID());
        Ledger ledger = new Ledger(ledgerID);

        //TRANSACTIONS
        for (TransactionJpa transactionJpa : ledgerJpa.getTransactions()) {
            OwnerID ownerID;

            if (transactionJpa.getCategoryID().getId().getOwnerID().contains("@")) {
                ownerID = PersonID.createPersonID(transactionJpa.getCategoryID().getId().getOwnerID());
            } else {
                ownerID = GroupID.createGroupID(transactionJpa.getCategoryID().getId().getOwnerID());
            }

            CategoryID categoryID = CategoryID.createCategoryID(transactionJpa.getCategoryID().getId().getDenomination(), ownerID);
            AccountID debAccountID = AccountID.createAccountID(transactionJpa.getDebitAccountID().getId().getDenomination(), ownerID);
            AccountID credAccountID = AccountID.createAccountID(transactionJpa.getCreditAccountID().getId().getDenomination(), ownerID);
            String type = transactionJpa.getType();
            Double amount = transactionJpa.getAmount();
            LocalDate date = LocalDate.parse(transactionJpa.getDate());
            String description = transactionJpa.getDescription();
            ledger.createAndAddTransactionWithDate(categoryID, type, description, amount, date, debAccountID, credAccountID);
        }
        return ledger;
    }
}
