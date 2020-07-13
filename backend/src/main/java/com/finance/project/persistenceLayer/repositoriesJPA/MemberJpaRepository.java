package com.finance.project.persistenceLayer.repositoriesJPA;


import com.finance.project.dataModel.dataModel.MemberJpa;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MemberJpaRepository extends CrudRepository<MemberJpa, Long> {

    MemberJpa findById(long id);

    List<MemberJpa> findAll();
}
