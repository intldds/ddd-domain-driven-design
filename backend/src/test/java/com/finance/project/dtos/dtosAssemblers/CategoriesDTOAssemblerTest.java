package com.finance.project.dtos.dtosAssemblers;

import com.finance.project.domainLayer.domainEntities.vosShared.CategoryID;
import com.finance.project.domainLayer.domainEntities.vosShared.PersonID;
import com.finance.project.domainLayer.entitiesInterfaces.OwnerID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.finance.project.dtos.dtos.CategoriesDTO;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CategoriesDTOAssemblerTest {

    @Test
    @DisplayName("CategoriesDTOAssembler - Test create categoriesDTO from domain objects")
    void categoriesDTOAssembler_createDTOFromDomainObject() {

        //Arrange
        String emailMaria = "maria@gmail.com";

        OwnerID ownerID = PersonID.createPersonID(emailMaria);

        String categoriesDenomination = "Food";

        CategoryID categoryID = CategoryID.createCategoryID(categoriesDenomination, ownerID);

        List<CategoryID> categoryIDS = new ArrayList<>();
        categoryIDS.add(categoryID);

        //Act
        CategoriesDTOAssembler categoriesDTOAssembler = new CategoriesDTOAssembler();
        CategoriesDTO categoriesDTO = categoriesDTOAssembler.createDTOFromDomainObject(categoryIDS);

        //Expected
        List<String> categories = new ArrayList<>();
        categories.add(categoriesDenomination);
        CategoriesDTO categoriesDTOExpected = new CategoriesDTO(categories);

        //Assert
        assertEquals(categoriesDTOExpected, categoriesDTO);
    }

}