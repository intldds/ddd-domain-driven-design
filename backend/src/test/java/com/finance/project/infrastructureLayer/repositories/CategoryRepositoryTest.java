package com.finance.project.infrastructureLayer.repositories;

import com.finance.project.controllerLayer.integrationTests.AbstractTest;
import com.finance.project.domainLayer.domainEntities.aggregates.category.Category;
import com.finance.project.domainLayer.domainEntities.vosShared.CategoryID;
import com.finance.project.domainLayer.domainEntities.vosShared.GroupID;
import com.finance.project.domainLayer.domainEntities.vosShared.PersonID;
import com.finance.project.domainLayer.repositoriesInterfaces.ICategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


class CategoryRepositoryTest extends AbstractTest {

    @Autowired
    private ICategoryRepository categoryRepository;
    private String hulkEmail;
    private PersonID hulkID;
    private String hulkDenomination;
    private CategoryID hulkCategoryID;
    private Category hulkCategoryToSave;

    @BeforeEach
    public void init() {

//        Arrange hulk
        hulkDenomination = "Netflix";
        hulkEmail = "hulk@marvel.com";

//        Create hulk PersonID
        hulkID = PersonID.createPersonID(hulkEmail);

//        Create hulk categoryID
        hulkCategoryID = CategoryID.createCategoryID(hulkDenomination, hulkID);

//        Create hulk categoryToSave
        hulkCategoryToSave = Category.createCategory(hulkDenomination, hulkID);

    }

    @Test
    @DisplayName("Verify category repository")
    public void testCategoryRepositoryConstructor() {

//        Assert
        assertNotNull(categoryRepository);

    }


    @Test
    @DisplayName("Verify if category is saved in category repository")
    public void testSaveCategoryInCategoryRepository() {

//        Count all the categories available in the beginning of the test
        long initialCount = categoryRepository.count();

//        Save category in the categoryJpaRepository
        Category category = categoryRepository.save(hulkCategoryToSave);
        List<Category> all = categoryRepository.findAll();
        int size = all.size();

//        Verify if the category exists in the categoryJpaRepository
        boolean categoryExists = categoryRepository.existsById(hulkCategoryID);

//        Count all the categories available in the categoryJpaRepository
        long count = categoryRepository.count();

//        Expected results
        int expectedTotalNumberOfCategories = (int) initialCount + 1;

//        Assert
        assertTrue(categoryExists);
        assertEquals(expectedTotalNumberOfCategories, count);

//        Delete the category added in this test
        categoryRepository.delete(hulkCategoryID);

//        Confirm if the elimination of the category was successful
        long countAfterDelete = categoryRepository.count();

//        Assert after elimination of the category
        assertEquals(initialCount, countAfterDelete);

    }

    @Test
    @DisplayName("Verify if category is saved in category repository | Save same Category twice")
    public void testSaveSameCategoryInCategoryRepositoryTwice() {

//        Count all the categories available in the beginning of the test
        long initialCount = categoryRepository.count();

//        Save category in the categoryJpaRepository
        Category category = categoryRepository.save(hulkCategoryToSave);
        Category categorySaveTwice = categoryRepository.save(hulkCategoryToSave);

//        Verify if the category exists in the categoryJpaRepository
        List<Category> allCategoryWithSameID = categoryRepository.findAllById(hulkEmail, hulkDenomination);

//        Count all the categories available in the categoryJpaRepository
        long count = categoryRepository.count();

//        Expected results
        int expectedTotalNumberOfCategoriesWithSameID = 1;
        int expectedTotalNumberOfCategories = (int) initialCount + 1;

        //Assert
        assertEquals(expectedTotalNumberOfCategoriesWithSameID, allCategoryWithSameID.size());
        assertEquals(expectedTotalNumberOfCategories, categoryRepository.count());

//        Delete the category added in this test
        categoryRepository.delete(hulkCategoryID);
        categoryRepository.delete(hulkCategoryID);

//        Confirm if the elimination of the category was successful
        long countAfterDelete = categoryRepository.count();

//        Assert after elimination of the categories
        assertEquals(initialCount, countAfterDelete);

    }

    @Test
    @DisplayName("Verify findCategoryByCategoryID() | HappyCase")
    public void testFindCategoryByCategoryID() {

//        Count all the categories available in the beginning of the test
        long initialCount = categoryRepository.count();

//        Save category in the categoryJpaRepository
        Category expectedCategory = categoryRepository.save(hulkCategoryToSave);

//        Verify if the category exists in the categoryJpaRepository
        Optional<Category> opCategory = categoryRepository.findById(hulkEmail, hulkDenomination);
        Category categoryFromDataBase = opCategory.get();

        //Assert
        assertEquals(expectedCategory, categoryFromDataBase);

//        Delete the category added in this test
        categoryRepository.delete(hulkCategoryID);

//        Confirm if the elimination of the category was successful
        long countAfterDelete = categoryRepository.count();

//        Assert after elimination of the category
        assertEquals(initialCount, countAfterDelete);

    }


    @Test
    @DisplayName("Test For findCategoryByCategoryID() | Doesn't exists")
    public void checkFindCategoryByCategoryIDNotExists() {

//        Count all the categories available in the beginning of the test
        long initialCount = categoryRepository.count();

//        Verify if the category exists in the categoryJpaRepository
        Optional<Category> opCategory = categoryRepository.findById(hulkEmail, hulkDenomination);
        List<Category> allCategoriesWithSameId = categoryRepository.findAllById(hulkEmail, hulkDenomination);
        int numberOfCategories = allCategoriesWithSameId.size();

//        Expected result
        Optional<Category> expectedOpCategory = Optional.empty();
        int expectedNumberOfCategories = 0;

//        Assert
        assertEquals(expectedOpCategory, opCategory);
        assertEquals(expectedNumberOfCategories, numberOfCategories);

//        Delete the category added in this test
        categoryRepository.delete(hulkCategoryID);

//        Confirm if the elimination of the category was successful
        long countAfterDelete = categoryRepository.count();

//        Assert after elimination of the category
        assertEquals(initialCount, countAfterDelete);
    }


    @Test
    @DisplayName("Test For findAllCategories | Data base with categories")
    public void checkFindAllCategories() {

//        Count all the categories available in the beginning of the test
        long initialCount = categoryRepository.count();

//        Arrange wolverine Category
        String wolverineCategorydenomination = "Salary";
        String wolverineEmail = "wolverine@marvel.com";

//        Create wolverineID
        PersonID wolverineID = PersonID.createPersonID(wolverineEmail);

//        Create wolverine categoryID
        CategoryID wolverineCategoryID = CategoryID.createCategoryID(wolverineCategorydenomination, wolverineID);

//        Create wolverine categoryToSave
        Category wolverineCategoryToSave = Category.createCategory(wolverineCategorydenomination, wolverineID);

//        Save Categories in Data Base
        categoryRepository.save(hulkCategoryToSave);
        categoryRepository.save(wolverineCategoryToSave);

//        Verify if the category exists in the categoryJpaRepository
        List<Category> allCategoriesWithSameId = categoryRepository.findAll();
        int numberOfCategories = allCategoriesWithSameId.size();

//        Expected result
        long expectedNumberOfCategoriesInDB = categoryRepository.count();
        int expectedNumberOfCategories = (int) initialCount + 2;

//        Assert
        assertEquals(expectedNumberOfCategories, numberOfCategories);
        assertEquals(expectedNumberOfCategories, expectedNumberOfCategoriesInDB);

//        Delete the category added in this test
        categoryRepository.delete(hulkCategoryID);
        categoryRepository.delete(wolverineCategoryID);

//        Confirm if the elimination of the category was successful
        long countAfterDelete = categoryRepository.count();

//        Assert after elimination of the categories
        assertEquals(initialCount, countAfterDelete);
    }

    @Test
    @DisplayName("Test For findAllCategories | Empty Data base")
    public void checkFindAllCategoriesDBEmpty() {

//        Count all the categories available in the beginning of the test
        long initialCount = categoryRepository.count();

//        Verify if the category exists in the categoryJpaRepository
        List<Category> allCategoriesWithSameId = categoryRepository.findAll();
        int numberOfCategories = allCategoriesWithSameId.size();

//        Expected result
        long expectedNumberOfCategoriesInDB = categoryRepository.count();
        int expectedNumberOfCategories = (int) initialCount;

//        Assert
        assertEquals(expectedNumberOfCategories, numberOfCategories);
        assertEquals(expectedNumberOfCategories, expectedNumberOfCategoriesInDB);

    }

    @Test
    @DisplayName("Verify count() | HappyCase")
    public void testCountCategory() {

//        Count all the categories available in the beginning of the test
        long initialCount = categoryRepository.count();

//        Arrange wolverine Category
        String wolverineCategorydenomination = "Salary";
        String wolverineEmail = "wolverine@marvel.com";

//        Create wolverineID
        PersonID wolverineID = PersonID.createPersonID(wolverineEmail);

//        Create wolverine categoryID
        CategoryID wolverineCategoryID = CategoryID.createCategoryID(wolverineCategorydenomination, wolverineID);

//        Create wolverine categoryToSave
        Category wolverineCategoryToSave = Category.createCategory(wolverineCategorydenomination, wolverineID);
        Category categoryFood = Category.createCategory(wolverineCategorydenomination, wolverineID);

//        Save Categories in Data Base
        categoryRepository.save(hulkCategoryToSave);
        categoryRepository.save(wolverineCategoryToSave);

//        Verify the number of categories in the category repository
        long countOfCategoriesInRepository = categoryRepository.count();

//        Expected result
        int expectedNumberOfCategories =(int) initialCount + 2;

//        Assert
        assertEquals(expectedNumberOfCategories, countOfCategoriesInRepository);

//        Delete the category added in this test
        categoryRepository.delete(hulkCategoryID);
        categoryRepository.delete(wolverineCategoryID);

//        Confirm if the elimination of the category was successful
        long countAfterDelete = categoryRepository.count();

//        Assert after elimination of the categories
        assertEquals(initialCount, countAfterDelete);

    }

    //
    @Test
    @DisplayName("Verify exists() | Happy Case")
    public void testCategoryExistsInCategoryRepository() {

//        Count all the categories available in the beginning of the test
        long initialCount = categoryRepository.count();

//        Save Categories in Data Base
        categoryRepository.save(hulkCategoryToSave);

//        Verify if the category exists in the categoryJpaRepository
        boolean verifyIfCategoryExists = categoryRepository.existsById(hulkCategoryID);
        Optional<Category> opCategoryInJpaRepository = categoryRepository.findById(hulkEmail, hulkDenomination);
        Category categoryFromDataBase= opCategoryInJpaRepository.get();

//        Assert
        assertTrue(verifyIfCategoryExists);
        assertEquals(hulkCategoryToSave, categoryFromDataBase);

//        Delete the category added in this test
        categoryRepository.delete(hulkCategoryID);

//        Confirm if the elimination of the category was successful
        long countAfterDelete = categoryRepository.count();

//        Assert after elimination of the category
        assertEquals(initialCount, countAfterDelete);
    }

    //
    @Test
    @DisplayName("Verify exists() | Not exists")
    public void testCategoryExistsInCategoryRepositoryNotExists() {

//        Count all the categories available in the beginning of the test
        long initialCount = categoryRepository.count();

//        Arrange wolverine Category
        String wolverineCategorydenomination = "Salary";
        String wolverineEmail = "wolverine@marvel.com";

//        Create wolverineID
        PersonID wolverineID = PersonID.createPersonID(wolverineEmail);

//        Create wolverine categoryID
        CategoryID wolverineCategoryID = CategoryID.createCategoryID(wolverineCategorydenomination, wolverineID);

//        Save Categories in Data Base
        categoryRepository.save(hulkCategoryToSave);

//        Verify if the Wolverine category exists in the categoryJpaRepository
        boolean verifyIfCategoryExists = categoryRepository.existsById(wolverineCategoryID);
        Optional<Category> opCategoryInJpaRepository = categoryRepository.findById(wolverineEmail, wolverineCategorydenomination);

//        Expected result
        Optional<Category> expectedOpCategory= Optional.empty();

//        Assert
        assertFalse(verifyIfCategoryExists);
        assertEquals(expectedOpCategory, opCategoryInJpaRepository);

//        Delete the category added in this test
        categoryRepository.delete(hulkCategoryID);

//        Confirm if the elimination of the category was successful
        long countAfterDelete = categoryRepository.count();

//        Assert after elimination of the category
        assertEquals(initialCount, countAfterDelete);
    }

    @Test
    @DisplayName("Verify exists() category Group| Happy Case")
    public void testGroupCategoryExistsInCategoryRepository() {

//        Count all the categories available in the beginning of the test
        long initialCount = categoryRepository.count();

//        Arrange hulk Group
        String hulkFamilyDenomination = "Hulk Family";
        String hulkFamilyDescription = "All members from Hulk family";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String groupCreation = LocalDate.of(1980, 11, 15).format(formatter);

//        Create hulk GroupID
        GroupID hulkGroupID = GroupID.createGroupID(hulkFamilyDenomination);

//        Create hulk Group categoryID
        CategoryID categoryID = CategoryID.createCategoryID(hulkFamilyDenomination, hulkGroupID);

//        Create hulk categoryToSave
        Category expectedCategoryToSave = Category.createCategory(hulkFamilyDenomination, hulkGroupID);

//        Save Categories in Data Base
        categoryRepository.save(expectedCategoryToSave);

//        Verify if the category exists in the categoryJpaRepository
        boolean verifyIfCategoryExists = categoryRepository.existsById(categoryID);
        Optional<Category> opCategoryInJpaRepository = categoryRepository.findById(hulkFamilyDenomination, hulkFamilyDenomination);
        Category categoryFromDataBase= opCategoryInJpaRepository.get();

//        Assert
        assertTrue(verifyIfCategoryExists);
        assertEquals(expectedCategoryToSave, categoryFromDataBase);

//        Delete the category added in this test
        categoryRepository.delete(categoryID);

//        Confirm if the elimination of the category was successful
        long countAfterDelete = categoryRepository.count();

//        Assert after elimination of the category
        assertEquals(initialCount, countAfterDelete);
    }
    @Test
    @DisplayName("Verify exists() category Group| Doesn't exists")
    public void testGroupCategoryDoesntExistsInCategoryRepository() {

//        Count all the categories available in the beginning of the test
        long initialCount = categoryRepository.count();

//        Arrange another category for Hulk Group
        String anotherDenomination = "Gym";

//        Arrange hulk Group
        String hulkFamilyDenomination = "Hulk Family";
        String hulkFamilyDescription = "All members from Hulk family";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String groupCreation = LocalDate.of(1980, 11, 15).format(formatter);

//        Create hulk GroupID
        GroupID hulkGroupID = GroupID.createGroupID(hulkFamilyDenomination);

//        Create hulk Group categoryID
        CategoryID categoryID = CategoryID.createCategoryID(hulkFamilyDenomination, hulkGroupID);

//        Create hulk categoryToSave
        Category expectedCategoryToSave = Category.createCategory(hulkFamilyDenomination, hulkGroupID);

//        Create hulk Group categoryIDNotToSave
        CategoryID categoryIDNotSaved = CategoryID.createCategoryID(anotherDenomination, hulkGroupID);

//        Save Categories in Data Base
        categoryRepository.save(expectedCategoryToSave);

//        Verify if the category exists in the categoryJpaRepository
        boolean verifyIfCategoryExists = categoryRepository.existsById(categoryIDNotSaved);
        Optional<Category> opCategoryInJpaRepository = categoryRepository.findById(hulkFamilyDenomination, anotherDenomination);

//        Expected Option
        Optional<Object> expectedOpCategory = Optional.empty();

//        Assert
        assertFalse(verifyIfCategoryExists);
        assertEquals(expectedOpCategory, opCategoryInJpaRepository);

//        Delete the category added in this test
        categoryRepository.delete(categoryID);

//        Confirm if the elimination of the category was successful
        long countAfterDelete = categoryRepository.count();

//        Assert after elimination of the category
        assertEquals(initialCount, countAfterDelete);
    }

}