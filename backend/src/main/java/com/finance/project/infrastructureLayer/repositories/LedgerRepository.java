package com.finance.project.infrastructureLayer.repositories;

import com.finance.project.dataModel.dataAssemblers.LedgerDomainDataAssembler;
import com.finance.project.dataModel.dataModel.*;
import com.finance.project.persistenceLayer.repositoriesJPA.AccountJpaRepository;
import com.finance.project.persistenceLayer.repositoriesJPA.CategoryJpaRepository;
import com.finance.project.persistenceLayer.repositoriesJPA.LedgerJpaRepository;
import com.finance.project.domainLayer.repositoriesInterfaces.ILedgerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.finance.project.dtos.dtos.DeleteGroupTransactionDTO;
import com.finance.project.dtos.dtos.DeletePersonTransactionDTO;
import com.finance.project.dtos.dtos.UpdateGroupTransactionDTO;
import com.finance.project.dtos.dtos.UpdatePersonTransactionDTO;
import com.finance.project.domainLayer.domainEntities.aggregates.ledger.Ledger;
import com.finance.project.domainLayer.domainEntities.vosShared.LedgerID;


import java.util.List;
import java.util.Optional;


@Repository
public class LedgerRepository implements ILedgerRepository {

    @Autowired
    LedgerDomainDataAssembler ledgerAssembler;
    @Autowired
    LedgerJpaRepository ledgerJpaRepository;
    @Autowired
    CategoryJpaRepository categoryJpaRepository;
    @Autowired
    AccountJpaRepository accountJpaRepository;


    public LedgerRepository() {
    }


    public Ledger save(Ledger ledger) {
        LedgerJpa ledgerJpa = ledgerAssembler.toData(ledger);

        LedgerJpa savedLedgerJpa = ledgerJpaRepository.save(ledgerJpa);

        return ledgerAssembler.toDomain(savedLedgerJpa);
    }

    @Transactional
    public Optional<Ledger> findById(LedgerID id) {
        Optional<LedgerJpa> opLedgerJpa = ledgerJpaRepository.findById(id);

        if (opLedgerJpa.isPresent()) {
            LedgerJpa ledgerJpa = opLedgerJpa.get();

            Ledger ledger = ledgerAssembler.toDomain(ledgerJpa);
            return Optional.of(ledger);
        } else
            return Optional.empty();
    }

    public boolean addAndSaveTransaction(Ledger ledger) {
        LedgerJpa ledgerJpa = ledgerAssembler.toData(ledger);

        List<LedgerJpa> ledgers = ledgerJpaRepository.findAll();

        ledgerJpaRepository.save(ledgerJpa);

        List<LedgerJpa> ledgersB = ledgerJpaRepository.findAll();

        return ledgers.equals(ledgersB);

    }

    public boolean updatePersonTransaction(Ledger ledger, UpdatePersonTransactionDTO updatePersonTransactionDTO) {
        LedgerJpa ledgerJpa = ledgerAssembler.toData(ledger);

        List<TransactionJpa> transactionJpas = ledgerJpa.getTransactions();

        long transactionNumber = transactionJpas.get(updatePersonTransactionDTO.getTransactionNumber()-1).getId();
        String transactionDate = transactionJpas.get(updatePersonTransactionDTO.getTransactionNumber()-1).getDate();

        Optional<CategoryJpa> categoryJpa = categoryJpaRepository.findById(new AbstractIdJpa(updatePersonTransactionDTO.getEmail(), updatePersonTransactionDTO.getDenominationCategory()));
        Optional<AccountJpa> debAccountJpa = accountJpaRepository.findById(new AbstractIdJpa(updatePersonTransactionDTO.getEmail(), updatePersonTransactionDTO.getDenominationAccountDeb()));
        Optional<AccountJpa> credAccountJpa = accountJpaRepository.findById(new AbstractIdJpa(updatePersonTransactionDTO.getEmail(), updatePersonTransactionDTO.getDenominationAccountCred()));

        TransactionJpa newTransactionJpa = new TransactionJpa(transactionNumber, categoryJpa.get(), updatePersonTransactionDTO.getType(), updatePersonTransactionDTO.getDescription(), updatePersonTransactionDTO.getAmount(), transactionDate, debAccountJpa.get(), credAccountJpa.get(), ledgerJpa);

        transactionJpas.set(updatePersonTransactionDTO.getTransactionNumber()-1, newTransactionJpa);

        ledgerJpaRepository.save(ledgerJpa);

        return true;

    }

    public boolean updateTransaction(Ledger ledger, UpdateGroupTransactionDTO updateGroupTransactionDTO) {
        LedgerJpa ledgerJpa = ledgerAssembler.toData(ledger);

        List<TransactionJpa> transactionJpas = ledgerJpa.getTransactions();

        long transactionNumber = transactionJpas.get(updateGroupTransactionDTO.getTransactionNumber() - 1).getId();
        String transactionDate = transactionJpas.get(updateGroupTransactionDTO.getTransactionNumber() - 1).getDate();

        Optional<CategoryJpa> categoryJpa = categoryJpaRepository.findById(new AbstractIdJpa(updateGroupTransactionDTO.getGroupDenomination(), updateGroupTransactionDTO.getCategoryDenomination()));
        Optional<AccountJpa> credAccountJpa = accountJpaRepository.findById(new AbstractIdJpa(updateGroupTransactionDTO.getGroupDenomination(), updateGroupTransactionDTO.getAccountToCreditName()));
        Optional<AccountJpa> debAccountJpa = accountJpaRepository.findById(new AbstractIdJpa(updateGroupTransactionDTO.getGroupDenomination(), updateGroupTransactionDTO.getAccountToDebitName()));

        TransactionJpa newTransactionJpa = new TransactionJpa(transactionNumber, categoryJpa.get(), updateGroupTransactionDTO.getTransactionType(), updateGroupTransactionDTO.getTransactionDescription(), updateGroupTransactionDTO.getTransactionAmount(), transactionDate, debAccountJpa.get(), credAccountJpa.get(), ledgerJpa);

        transactionJpas.set(updateGroupTransactionDTO.getTransactionNumber() - 1, newTransactionJpa);

        ledgerJpaRepository.save(ledgerJpa);

        return true;
    }

    public boolean deletePersonTransaction(Ledger ledger, DeletePersonTransactionDTO deletePersonTransactionDTO) {
        LedgerJpa ledgerJpa = ledgerAssembler.toData(ledger);

        List<TransactionJpa> transactionJpas = ledgerJpa.getTransactions();

        transactionJpas.remove(deletePersonTransactionDTO.getTransactionNumber()-1);

        ledgerJpaRepository.save(ledgerJpa);

        return true;
    }

    public boolean deleteTransaction(Ledger ledger, DeleteGroupTransactionDTO deleteGroupTransactionDTO) {
        LedgerJpa ledgerJpa = ledgerAssembler.toData(ledger);

        List<TransactionJpa> transactionJpas = ledgerJpa.getTransactions();

        transactionJpas.remove(deleteGroupTransactionDTO.getTransactionNumber() - 1);

        ledgerJpaRepository.save(ledgerJpa);

        return true;
    }

    public boolean exists(LedgerID ledgerID) {
        return ledgerJpaRepository.existsById(ledgerID);
    }

    public long count() {
        return ledgerJpaRepository.count();
    }
}
