package com.finance.project.persistenceLayer.repositoriesJPA;


import com.finance.project.dataModel.dataModel.AdminJpa;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AdminJpaRepository extends CrudRepository<AdminJpa, Long> {

    AdminJpa findById(long id);

    List<AdminJpa> findAll();
}
