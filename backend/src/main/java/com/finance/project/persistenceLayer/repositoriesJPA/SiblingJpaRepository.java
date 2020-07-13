package com.finance.project.persistenceLayer.repositoriesJPA;


import com.finance.project.dataModel.dataModel.SiblingJpa;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SiblingJpaRepository extends CrudRepository<SiblingJpa, Long> {

    SiblingJpa findById(long id);

    List<SiblingJpa> findAll();
}
