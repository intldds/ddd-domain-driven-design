package com.finance.project.domainLayer.repositoriesInterfaces;

import org.springframework.stereotype.Repository;
import com.finance.project.domainLayer.domainEntities.aggregates.category.Category;
import com.finance.project.domainLayer.domainEntities.vosShared.CategoryID;

import java.util.List;
import java.util.Optional;


@Repository
public interface ICategoryRepository {

    Category save(Category category);

    Optional<Category> findById(String id, String denomination);

    boolean existsById(CategoryID categoryID);

    long count();

    List<Category> findAll();

    void delete(CategoryID categoryID);

    List<Category> findAllById(String id, String denomination);
}
