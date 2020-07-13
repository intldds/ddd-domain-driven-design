package com.finance.project.infrastructureLayer.repositories;

import com.finance.project.dataModel.dataAssemblers.CategoryDomainDataAssembler;
import com.finance.project.dataModel.dataModel.AbstractIdJpa;
import com.finance.project.dataModel.dataModel.CategoryJpa;
import com.finance.project.persistenceLayer.repositoriesJPA.CategoryJpaRepository;
import com.finance.project.domainLayer.repositoriesInterfaces.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.finance.project.domainLayer.domainEntities.aggregates.category.Category;
import com.finance.project.domainLayer.domainEntities.vosShared.CategoryID;
import com.finance.project.domainLayer.domainEntities.vosShared.GroupID;
import com.finance.project.domainLayer.domainEntities.vosShared.PersonID;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Repository
public class CategoryRepository implements ICategoryRepository {

    @Autowired
    CategoryJpaRepository categoryJpaRepository;
    @Autowired
    CategoryDomainDataAssembler categoryAssembler;

    // Constructor

    public CategoryRepository() {
    }


    public Category save(Category category) {
        CategoryJpa categoryJpa = categoryAssembler.toData(category);

        CategoryJpa savedCategoryJpa = categoryJpaRepository.save(categoryJpa);

        return categoryAssembler.toDomain(savedCategoryJpa);
    }

    public Optional<Category> findById(String id, String denomination) {

        AbstractIdJpa abstractIdJpa = new AbstractIdJpa(id, denomination);

        Optional<CategoryJpa> opCategoryJpa = categoryJpaRepository.findById(abstractIdJpa);

        if(opCategoryJpa.isPresent()) {
            CategoryJpa categoryJpa = opCategoryJpa.get();

            Category category = categoryAssembler.toDomain(categoryJpa);
            return Optional.of(category);
        }
        else
            return Optional.empty();
    }

    public boolean existsById(CategoryID categoryID){

        String id = "";

        if (categoryID.getOwnerID() instanceof PersonID) {
            PersonID personID = (PersonID) categoryID.getOwnerID();
            id = personID.getEmail().getEmail();
        }

        if (categoryID.getOwnerID() instanceof GroupID) {
            GroupID groupID = (GroupID) categoryID.getOwnerID();
            id = groupID.getDenomination().getDenomination();
        }

        AbstractIdJpa abstractIdJpa = new AbstractIdJpa(id, categoryID.getDenomination().getDenomination());

        return categoryJpaRepository.existsById(abstractIdJpa);
    }

    public long count() {
        return categoryJpaRepository.count();
    }

    public List<Category> findAll() {

        List<Category> newCategoryList = new ArrayList<>();

        List<CategoryJpa> allCategoriesInDataBase = categoryJpaRepository.findAll();

//        Go through all CategoryJPA in allCategoriesInDataBase, and pass them toDomain
        for (CategoryJpa categoryJpa : allCategoriesInDataBase) {
            Category categoryFromDataBase = categoryAssembler.toDomain(categoryJpa);
            newCategoryList.add(categoryFromDataBase);
        }
        return newCategoryList;

    }
    public void delete(CategoryID categoryID){

//        Arrange information to create Category
        String owner = categoryID.getOwnerID().toString();
        String denomination = categoryID.getDenomination().getDenomination();
        Category first = Category.createCategory(denomination, categoryID.getOwnerID());

//        Pass category to categoryJpa
        CategoryJpa categoryJpa = categoryAssembler.toData(first);

//        Delete CategoryJpa from CategoryJpaRepository
        categoryJpaRepository.delete(categoryJpa);

    }

    public List<Category> findAllById(String id, String denomination) {

//        Arrange information to create Category
        Category categoryWanted = Category.createCategory(denomination, PersonID.createPersonID(id));

//        Arrange information to find Category by ID
        AbstractIdJpa abstractIdJpa = new AbstractIdJpa(id, denomination);
        List<CategoryJpa> allCategoriesSameIDInDataBase;
        List<Category> newCategoryList = new ArrayList<>();

//        Go through all CategoryJPA in allCategoriesSameIDInDataBase, and pass them toDomain
        allCategoriesSameIDInDataBase = categoryJpaRepository.findAllById(abstractIdJpa);
        for (CategoryJpa categoryIDToAnalyse : allCategoriesSameIDInDataBase) {
            Category categoryToAnalyze = categoryAssembler.toDomain(categoryIDToAnalyse);

//        If the categoryToAnalyze is equals to the categoryWanted, will be added to the list
            if (categoryWanted.equals(categoryToAnalyze)) {
                newCategoryList.add(categoryToAnalyze);
            }
        }
        return newCategoryList;

    }


}