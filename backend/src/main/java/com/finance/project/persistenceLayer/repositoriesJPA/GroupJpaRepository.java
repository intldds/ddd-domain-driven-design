package com.finance.project.persistenceLayer.repositoriesJPA;


import com.finance.project.dataModel.dataModel.GroupJpa;
import org.springframework.data.repository.CrudRepository;
import com.finance.project.domainLayer.domainEntities.vosShared.GroupID;

import java.util.List;
import java.util.Optional;

public interface GroupJpaRepository extends CrudRepository<GroupJpa, GroupID> {

	List<GroupJpa> findAll();

	Optional<GroupJpa> findById(GroupID id);

	boolean existsById(GroupID id);

	long count();
}