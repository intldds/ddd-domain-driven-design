package com.finance.project.persistenceLayer.repositoriesJPA;

import com.finance.project.dataModel.dataModel.AbstractIdJpa;
import com.finance.project.dataModel.dataModel.CategoryJpa;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryJpaRepository extends CrudRepository<CategoryJpa, AbstractIdJpa> {

	List<CategoryJpa> findAll();

	Optional<CategoryJpa> findById(AbstractIdJpa id);

	boolean existsById(AbstractIdJpa id);

	long count();

	void delete(CategoryJpa categoryJpa);

	List<CategoryJpa> findAllById(AbstractIdJpa id);
}
