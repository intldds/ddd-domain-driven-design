package com.finance.project.applicationLayer.applicationServices.otherServices;


import com.finance.project.controllerLayer.integrationTests.AbstractTest;
import com.finance.project.domainLayer.domainEntities.aggregates.person.Person;
import com.finance.project.domainLayer.domainEntities.vosShared.PersonID;
import com.finance.project.domainLayer.exceptions.InvalidArgumentsBusinessException;
import com.finance.project.domainLayer.exceptions.NotFoundArgumentsBusinessException;
import com.finance.project.domainLayer.repositoriesInterfaces.IPersonRepository;
import com.finance.project.dtos.dtosAssemblers.BooleanDTOAssembler;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import com.finance.project.dtos.dtos.BooleanDTO;
import com.finance.project.dtos.dtos.CheckIfSiblingsDTO;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class CheckSiblingsServiceTest extends AbstractTest {

    @Mock
    private IPersonRepository personRepository;
    private CheckSiblingsService checkSiblingsService;
    private CheckIfSiblingsDTO checkIfSiblingsDTO;

    @Test
    @DisplayName("Test Controller_US01 - Paulo | Helder | siblings")
    void controller_US01() {

        checkSiblingsService = new CheckSiblingsService(personRepository);

        //        Arrange person Paulo
        String pauloEmail = "paulo@gmail.com";
        String pauloName = "Paulo Fontes";
        LocalDate pauloBirthdateLD = LocalDate.of(1993, 3, 15);
        String pauloBirthplace = "Vila Nova de Gaia";
        Person paulo = Person.createPerson(pauloEmail, pauloName, pauloBirthdateLD, pauloBirthplace);

        //        Arrange person Helder
        String helderEmail = "helder@gmail.com";
        String helderName = "Helder Fontes";
        LocalDate helderBirthdateLD = LocalDate.of(1983, 1, 30);
        String helderBirthplace = "Vila Nova de Gaia";
        Person helder = Person.createPerson(helderEmail, helderName, helderBirthdateLD, helderBirthplace);

        //        Arrange people ID
        PersonID pauloID = PersonID.createPersonID(pauloEmail);
        PersonID helderID = PersonID.createPersonID(helderEmail);

        //        Add Helder to Paulo list of siblings
        paulo.addSibling(helderID);

        //        Act
        //          Mock the behaviour of personRepository
        //        Returning an Optional<Person> paulo
        Mockito.when(personRepository.findById(pauloID))
                .thenReturn(Optional.of(paulo));

        //        Returning an Optional<Person> helder
        Mockito.when(personRepository.findById(helderID))
                .thenReturn(Optional.of(helder));

        //        Expected BooleanDTO
        BooleanDTO expectedBooleanDTO = BooleanDTOAssembler.createDTOFromPrimitiveTypes(true, CheckSiblingsService.SUCCESS);

        //        Act DTO
        checkIfSiblingsDTO = new CheckIfSiblingsDTO(pauloEmail, helderEmail);

        //       Expected message
        String message = "Siblings";

        //        Assert
        BooleanDTO verifyIfSibling = checkSiblingsService.checkIfSiblings(checkIfSiblingsDTO);

        //        Assert response
        assertEquals(expectedBooleanDTO, verifyIfSibling);

        //        Assert the message
        assertEquals(message, verifyIfSibling.getMsg());
    }


    @Test
    @DisplayName("Test Controller_US01 - Paulo | Helder | Not siblings")
    void controller_US01NotSiblings() throws InvalidArgumentsBusinessException {

        checkSiblingsService = new CheckSiblingsService(personRepository);

//        Arrange person Paulo
        String pauloEmail = "paulo@gmail.com";
        String pauloName = "Paulo Fontes";
        LocalDate pauloBirthdateLD = LocalDate.of(1993, 3, 15);
        String pauloBirthplace = "Vila Nova de Gaia";
        Person paulo = Person.createPerson(pauloEmail, pauloName, pauloBirthdateLD, pauloBirthplace);

//        Arrange person Helder
        String helderEmail = "helder@gmail.com";
        String helderName = "Helder Fontes";
        LocalDate helderBirthdateLD = LocalDate.of(1983, 1, 30);
        String helderBirthplace = "Vila Nova de Gaia";
        Person helder = Person.createPerson(helderEmail, helderName, helderBirthdateLD, helderBirthplace);

//        Arrange people ID
        PersonID pauloID = PersonID.createPersonID(pauloEmail);
        PersonID helderID = PersonID.createPersonID(helderEmail);

//        Act
//          Mock the behaviour of personRepository
//        Returning an Optional<Person> paulo
        Mockito.when(personRepository.findById(pauloID))
                .thenReturn(Optional.of(paulo));

//        Returning an Optional<Person> helder
        Mockito.when(personRepository.findById(helderID))
                .thenReturn(Optional.of(helder));

//        Act DTO
        checkIfSiblingsDTO = new CheckIfSiblingsDTO(pauloEmail, helderEmail);

//        Expected message
        String expectedMessage = "Not Siblings";

        //Act expected object
        Throwable thrown = assertThrows(InvalidArgumentsBusinessException.class, () -> checkSiblingsService.checkIfSiblings(checkIfSiblingsDTO));

//        Assert
        assertEquals(expectedMessage, thrown.getMessage());

    }


    @Test
    @DisplayName("Test - bataguas | bataguas is null")
    void controller_US01NotSiblingsNullPerson() throws InvalidArgumentsBusinessException {

        checkSiblingsService = new CheckSiblingsService(personRepository);

//        Arrange person Paulo
        String pauloEmail = "paulo@gmail.com";
        String pauloName = "Paulo Fontes";
        LocalDate pauloBirthdateLD = LocalDate.of(1993, 3, 15);
        String pauloBirthplace = "Vila Nova de Gaia";
        Person paulo = Person.createPerson(pauloEmail, pauloName, pauloBirthdateLD, pauloBirthplace);

//        Arrange bataguas Email
        String bataguasEmail = "bataguas@gmail.com";

//        Arrange people ID
        PersonID pauloID = PersonID.createPersonID(pauloEmail);
        PersonID bataguasID = PersonID.createPersonID(bataguasEmail);

//        Act
//          Mock the behaviour of personRepository
//        Returning an Optional<Person> paulo
        Mockito.when(personRepository.findById(pauloID))
                .thenReturn(Optional.of(paulo));

//        Returning an Optional<Person> bataguas
        Mockito.when(personRepository.findById(bataguasID))
                .thenThrow(new NotFoundArgumentsBusinessException("Second Person does not exist"));

//        Expected message
        String expectedMessage = "Second Person does not exist";


        //Act expected object
        Throwable thrown = assertThrows(NotFoundArgumentsBusinessException.class, () -> personRepository.findById(bataguasID));

//        Assert
        assertEquals(expectedMessage, thrown.getMessage());

    }


    @Test
    @DisplayName("Test Controller_US01 - Wolverine | bataguas is null")
    void controller_US01NotSiblingsNullSibling() throws InvalidArgumentsBusinessException {

        //      Instantiating an us001CheckIfSiblingsService with personRepository as parameter
        checkSiblingsService = new CheckSiblingsService(personRepository);

//        Arrange person Paulo
        String pauloEmail = "paulo@gmail.com";
        String pauloName = "Paulo Fontes";
        LocalDate pauloBirthdateLD = LocalDate.of(1993, 3, 15);
        String pauloBirthplace = "Vila Nova de Gaia";
        Person paulo = Person.createPerson(pauloEmail, pauloName, pauloBirthdateLD, pauloBirthplace);

//        Arrange bataguas Email
        String bataguasEmail = "bataguas@gmail.com";

//        Arrange people ID
        PersonID pauloID = PersonID.createPersonID(pauloEmail);
        PersonID bataguasID = PersonID.createPersonID(bataguasEmail);

//        Act
//          Mock the behaviour of personRepository
//        Returning an Optional<Person> paulo
        Mockito.when(personRepository.findById(bataguasID))
                .thenThrow(new NotFoundArgumentsBusinessException("First Person does not exist"));

//        Returning an Optional<Person> Paulo
        Mockito.when(personRepository.findById(pauloID))
                .thenReturn(Optional.of(paulo));

//        Expected message
        String expectedMessage = "First Person does not exist";

        //Act expected object
        Throwable thrown = assertThrows(NotFoundArgumentsBusinessException.class, () -> personRepository.findById(bataguasID));

//        Assert
        assertEquals(expectedMessage, thrown.getMessage());

    }


}


