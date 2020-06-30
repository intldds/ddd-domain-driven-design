package com.finance.project.persistenceLayer.repositoriesJPA;


import com.finance.project.dataModel.dataModel.AddressJpa;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AddressJpaRepository extends CrudRepository<AddressJpa, Long> {

    Optional<AddressJpa> findById(long id);
    List<AddressJpa> findAll();
}
