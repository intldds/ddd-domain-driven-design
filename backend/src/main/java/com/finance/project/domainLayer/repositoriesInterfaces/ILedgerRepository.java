package com.finance.project.domainLayer.repositoriesInterfaces;

import org.springframework.stereotype.Repository;
import com.finance.project.dtos.dtos.DeleteGroupTransactionDTO;
import com.finance.project.dtos.dtos.DeletePersonTransactionDTO;
import com.finance.project.dtos.dtos.UpdateGroupTransactionDTO;
import com.finance.project.dtos.dtos.UpdatePersonTransactionDTO;
import com.finance.project.domainLayer.domainEntities.aggregates.ledger.Ledger;
import com.finance.project.domainLayer.domainEntities.vosShared.LedgerID;

import java.util.Optional;


@Repository
public interface ILedgerRepository {

    Ledger save(Ledger ledger);

    Optional<Ledger> findById(LedgerID id);

    boolean addAndSaveTransaction(Ledger ledger);

    boolean updatePersonTransaction(Ledger ledger, UpdatePersonTransactionDTO updatePersonTransactionDTO);

    boolean updateTransaction(Ledger ledger, UpdateGroupTransactionDTO updateGroupTransactionDTO);

    boolean deletePersonTransaction(Ledger ledger, DeletePersonTransactionDTO deletePersonTransactionDTO0);

    boolean deleteTransaction(Ledger ledger, DeleteGroupTransactionDTO deleteGroupTransactionDTO);

    boolean exists(LedgerID ledgerID);

    long count();
}
