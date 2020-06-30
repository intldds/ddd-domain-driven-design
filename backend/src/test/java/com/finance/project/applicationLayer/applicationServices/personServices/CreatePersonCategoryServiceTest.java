package com.finance.project.applicationLayer.applicationServices.personServices;

/*
    US005 Como utilizador, quero adicionar uma categoria à minha lista de categorias,
    para depois a poder atribuir a um movimento.
*/

import com.finance.project.controllerLayer.integrationTests.AbstractTest;
import com.finance.project.domainLayer.domainEntities.aggregates.person.Person;
import com.finance.project.domainLayer.domainEntities.vosShared.CategoryID;
import com.finance.project.domainLayer.domainEntities.vosShared.PersonID;
import com.finance.project.domainLayer.exceptions.InvalidArgumentsBusinessException;
import com.finance.project.domainLayer.exceptions.NotFoundArgumentsBusinessException;
import com.finance.project.domainLayer.repositoriesInterfaces.ICategoryRepository;
import com.finance.project.domainLayer.repositoriesInterfaces.IPersonRepository;
import com.finance.project.dtos.dtosAssemblers.CreatePersonCategoryDTOAssembler;
import com.finance.project.dtos.dtosAssemblers.PersonDTOAssembler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import com.finance.project.dtos.dtos.CreatePersonCategoryDTO;
import com.finance.project.dtos.dtos.PersonDTO;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CreatePersonCategoryServiceTest extends AbstractTest {

    @Mock
    private IPersonRepository personRepository;
    @Mock
    private ICategoryRepository categoryRepository;

    private CreatePersonCategoryService createPersonCategoryService;

    private Person person;

    private PersonID personID;

    @BeforeEach
    public void init() {

        // Ilda
        String ildaEmail = "ilda@gmail.com";
        String ildaName = "Ilda";
        LocalDate ildaBirthdate = LocalDate.of(1999, 2, 20);
        String ildaBirthplace = "Porto";

        this.person = Person.createPerson(ildaEmail, ildaName, ildaBirthdate, ildaBirthplace);
        this.personID = PersonID.createPersonID(ildaEmail);

        // Categories:
        // Salary / Draw Money / IRS / Food / Water Bill / Netflix

        // Salary
        String salaryDenomination = "Salary";
        CategoryID salaryID = CategoryID.createCategoryID(salaryDenomination, personID);
        person.addCategory(salaryID);

        // Draw Money
        String drawMoneyDenomination = "Draw Money";
        CategoryID drawMoneyID = CategoryID.createCategoryID(drawMoneyDenomination, personID);
        person.addCategory(drawMoneyID);

        // IRS
        String irsDenomination = "IRS";
        CategoryID irsID = CategoryID.createCategoryID(irsDenomination, personID);
        person.addCategory(irsID);

        // Food
        String foodDenomination = "Food";
        CategoryID foodID = CategoryID.createCategoryID(foodDenomination, personID);
        person.addCategory(foodID);

        // Water Bill
        String waterBillDenomination = "Water Bill";
        CategoryID waterBillID = CategoryID.createCategoryID(waterBillDenomination, personID);
        person.addCategory(waterBillID);

        // Netflix
        String netflixDenomination = "Netflix";
        CategoryID netflixID = CategoryID.createCategoryID(netflixDenomination, personID);
        person.addCategory(netflixID);
    }

    // Tests

    @Test
    @DisplayName("Test for createCategory() | Success")
    void createCategory_Success() {

        //Arrange
        String email = "ilda@gmail.com";
        String denomination = "Electricity";

        //To Search
        CategoryID categoryID = CategoryID.createCategoryID(denomination, personID);

        //Returning an Optional<Person> Person
        Mockito.when(personRepository.findById(personID)).thenReturn(Optional.of(person));

        //Returning False
        Mockito.when(categoryRepository.existsById(categoryID)).thenReturn(false);

        //DTO
        CreatePersonCategoryDTO createPersonCategoryDTO = CreatePersonCategoryDTOAssembler.createDTOFromPrimitiveTypes(email, denomination);

        //Expected PersonDTO
        PersonDTO expectedPersonDTO = PersonDTOAssembler.createDTOFromDomainObject(
                person.getPersonID().getEmail(), person.getName(),
                person.getBirthdate(), person.getBirthplace(),
                person.getFather(), person.getMother());

        //Service
        createPersonCategoryService = new CreatePersonCategoryService(
                personRepository, categoryRepository);

        //Act
        PersonDTO result = createPersonCategoryService.createCategory(createPersonCategoryDTO);

        //Assert
        assertEquals(expectedPersonDTO, result);
        assertEquals(true, person.checkIfPersonHasCategory(categoryID));
    }

    @Test
    @DisplayName("Test For createCategory() | Fail | Category Already Exists")
    void createCategory_Fail_CategoryAlreadyExists() {

        //Arrange
        String email = "ilda@gmail.com";
        String denomination = "Salary";

        //To Search
        CategoryID categoryID = CategoryID.createCategoryID(denomination, personID);

        //Returning an Optional<Person> Person
        Mockito.when(personRepository.findById(personID)).thenReturn(Optional.of(person));

        //Returning False
        Mockito.when(categoryRepository.existsById(categoryID)).thenReturn(true);

        //DTO
        CreatePersonCategoryDTO createPersonCategoryDTO = CreatePersonCategoryDTOAssembler.createDTOFromPrimitiveTypes(email, denomination);

        //Service
        createPersonCategoryService = new CreatePersonCategoryService(
                personRepository, categoryRepository);

        //Act
        Throwable thrown = assertThrows(InvalidArgumentsBusinessException.class, () ->
                createPersonCategoryService.createCategory(createPersonCategoryDTO));

        //Assert
        assertEquals(thrown.getMessage(), CreatePersonCategoryService.CATEGORY_ALREADY_EXIST);
    }

    @Test
    @DisplayName("Test For createCategory() | Fail | Person Does Not Exist")
    void createCategory_Fail_PersonDoesNotExist() {

        //Arrange
        String email = "lebron@gmail.com";
        String denomination = "Electricity";

        //Returning an Optional<Person> Person
        Mockito.when(personRepository.findById(personID)).thenReturn(Optional.of(person));

        //DTO
        CreatePersonCategoryDTO createPersonCategoryDTO = CreatePersonCategoryDTOAssembler.createDTOFromPrimitiveTypes(email, denomination);

        //Service
        createPersonCategoryService = new CreatePersonCategoryService(
                personRepository, categoryRepository);

        //Act
        Throwable thrown = assertThrows(NotFoundArgumentsBusinessException.class, () ->
                createPersonCategoryService.createCategory(createPersonCategoryDTO));

        //Assert
        assertEquals(thrown.getMessage(), CreatePersonCategoryService.PERSON_DOES_NOT_EXIST);
    }
}