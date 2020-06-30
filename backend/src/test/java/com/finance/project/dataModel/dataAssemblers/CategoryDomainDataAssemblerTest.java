package com.finance.project.dataModel.dataAssemblers;

import com.finance.project.dataModel.dataModel.CategoryJpa;
import com.finance.project.domainLayer.domainEntities.aggregates.category.Category;
import com.finance.project.domainLayer.domainEntities.vosShared.PersonID;
import com.finance.project.domainLayer.entitiesInterfaces.OwnerID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryDomainDataAssemblerTest {

    @Test
    @DisplayName("CategoryDomainDataAssembler - Test create CategoryJpa to data")

    public void categoryDomainDataAssembler_toData () {

        //Arrange

        String id = "alexandre@gmail.com";
        OwnerID ownerID = PersonID.createPersonID("alexandre@gmail.com");
        String categoryDenomination = "Salary";

        Category category = Category.createCategory(categoryDenomination, ownerID);

        //Act

        CategoryDomainDataAssembler categoryDomainDataAssembler = new CategoryDomainDataAssembler();
        CategoryJpa categoryJpa = categoryDomainDataAssembler.toData(category);

        //Assert

        assertEquals(id, categoryJpa.getId().getOwnerID());
        assertEquals(categoryDenomination, categoryJpa.getId().getDenomination());
    }

    @Test
    @DisplayName("CategoryDomainDataAssembler - Test create CategoryJpa to domain")

    public void categoryDomainDataAssembler_toDomain () {

        //Arrange

        String id = "alexandre@gmail.com";
        OwnerID ownerID = PersonID.createPersonID("alexandre@gmail.com");
        String categoryDenomination = "Salary";

        Category category = Category.createCategory(categoryDenomination, ownerID);

        //Act

        CategoryDomainDataAssembler categoryDomainDataAssembler = new CategoryDomainDataAssembler();
        CategoryJpa categoryJpa = categoryDomainDataAssembler.toData(category);
        Category newCategory = categoryDomainDataAssembler.toDomain(categoryJpa);

        //Assert

        Assertions.assertEquals(ownerID, newCategory.getCategoryID().getOwnerID());
        assertEquals(categoryDenomination, newCategory.getCategoryID().getDenomination().getDenomination());
    }

}