package com.finance.project.persistenceLayer.repositoriesJPA;

import com.finance.project.dataModel.dataModel.LedgerJpa;
import com.finance.project.dataModel.dataModel.TransactionJpa;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;


public interface TransactionJpaRepository extends CrudRepository<TransactionJpa, Long> {

    List<TransactionJpa> findAll();

    Optional<TransactionJpa> findById(Long id);

    List<TransactionJpa> findAllByLedger(LedgerJpa id);
}