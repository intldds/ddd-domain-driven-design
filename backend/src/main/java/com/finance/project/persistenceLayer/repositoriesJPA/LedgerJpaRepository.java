package com.finance.project.persistenceLayer.repositoriesJPA;

import com.finance.project.dataModel.dataModel.LedgerJpa;
import org.springframework.data.repository.CrudRepository;
import com.finance.project.domainLayer.domainEntities.vosShared.LedgerID;

import java.util.List;
import java.util.Optional;

public interface LedgerJpaRepository extends CrudRepository<LedgerJpa, LedgerID> {

    List<LedgerJpa> findAll();

    Optional<LedgerJpa> findById(LedgerID id);

    boolean existsById(LedgerID id);

    long count();
}