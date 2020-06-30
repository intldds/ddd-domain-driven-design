package com.finance.project.controllerLayer.unitTests;

import com.finance.project.applicationLayer.applicationServices.groupServices.CreateGroupTransactionService;
import com.finance.project.controllerLayer.controllersREST.groupControllers.CreateGroupTransactionControllerREST;
import com.finance.project.controllerLayer.integrationTests.AbstractTest;
import com.finance.project.domainLayer.exceptions.InvalidArgumentsBusinessException;
import com.finance.project.domainLayer.exceptions.NotFoundArgumentsBusinessException;
import com.finance.project.dtos.dtos.CreateGroupTransactionDTO;
import com.finance.project.dtos.dtos.NewGroupTransactionInfoDTO;
import com.finance.project.dtos.dtosAssemblers.CreateGroupTransactionDTOAssembler;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

//@SpringBootTest
//@ActiveProfiles("test")
//@ExtendWith(SpringExtension.class)
public class CreateGroupTransactionControllerRESTTest extends AbstractTest {

    @Mock
    private CreateGroupTransactionService service;
    @Autowired
    private CreateGroupTransactionControllerREST controller;

    //SUCCESS

//    @Test
//    public void whenGroupTransactionIsCreated_thenRetrievedMsgIsSuccess() {
//        //Arrange
//
//        String personEmail = "manuel@gmail.com";
//        String groupDenomination = "Fontes Family";
//        String groupDescription = "All members from Fontes family";
//
//
//        String denominationCategory = "IRS";
//        String type = "debit";
//        String transactionDescription = "May IRS";
//        double amount = 150.0;
//        String denominationAccountDeb = "Bank Account";
//        String denominationAccountCred = "State";
//
//
//        //Expected result
//        Denomination denomination = Denomination.createDenomination(groupDenomination);
//        Description description = Description.createDescription(groupDescription);
//        DateOfCreation dateOfCreation = DateOfCreation.createDateOfCreation(LocalDate.now());
//        GroupDTO isTransactionCreatedExpected = GroupDTOAssembler.createDTOFromDomainObject(denomination, description, dateOfCreation);
//
//        NewGroupTransactionInfoDTO newGroupTransactionInfoDTO = new NewGroupTransactionInfoDTO(denominationCategory, type, transactionDescription, amount, denominationAccountDeb, denominationAccountCred);
//
//        CreateGroupTransactionDTO createGroupTransactionDTO = CreateGroupTransactionDTOAssembler.createDTOFromPrimitiveTypes(groupDenomination, personEmail, newGroupTransactionInfoDTO.getDenominationCategory(), newGroupTransactionInfoDTO.getDenominationAccountDeb(), newGroupTransactionInfoDTO.getDenominationAccountCred(), newGroupTransactionInfoDTO.getAmount(), newGroupTransactionInfoDTO.getType(), newGroupTransactionInfoDTO.getDescription());
//
//        //Expected Response Entity
//        ResponseEntity<Object> expectedResponse = new ResponseEntity<>(isTransactionCreatedExpected, HttpStatus.CREATED);
//
//        // Mock the behaviour of the service's createCategoryAsPersonInCharge method,
//        // so it does not depend on other parts (e.g. DB)
//        Mockito.when(service.createGroupTransaction(createGroupTransactionDTO)).thenReturn(isTransactionCreatedExpected);
//
//        //Act
//
//        ResponseEntity<Object> isCategoryCreated = controller.createGroupTransaction(newGroupTransactionInfoDTO, personEmail, groupDenomination);
//
//        //Assert
//        assertEquals(expectedResponse, isCategoryCreated);
//    }

    //GROUP_DOES_NOT_EXIST

    @Test
    public void whenGroupTransactionIsNotCreated_thenRetrievedMsgGroupDoesNotExist() {
        //Arrange

        String personEmail = "manuel@gmail.com";
        String groupDenomination = "Vale Family";
        String groupDescription = "All members from Fontes family";


        String denominationCategory = "IRS";
        String type = "debit";
        String transactionDescription = "May IRS";
        double amount = 150.0;
        String denominationAccountDeb = "Bank Account";
        String denominationAccountCred = "State";
        String date = "2020-06-18";

        NewGroupTransactionInfoDTO newGroupTransactionInfoDTO = new NewGroupTransactionInfoDTO(denominationCategory, type, transactionDescription, amount, denominationAccountDeb, denominationAccountCred, date);

        CreateGroupTransactionDTO createGroupTransactionDTO = CreateGroupTransactionDTOAssembler.createDTOFromPrimitiveTypes(groupDenomination, personEmail, newGroupTransactionInfoDTO.getDenominationCategory(), newGroupTransactionInfoDTO.getDenominationAccountDeb(), newGroupTransactionInfoDTO.getDenominationAccountCred(), newGroupTransactionInfoDTO.getAmount(), newGroupTransactionInfoDTO.getType(), newGroupTransactionInfoDTO.getDescription(), newGroupTransactionInfoDTO.getDate());

        // Mock the behaviour of the service's createCategoryAsPersonInCharge method,
        // so it does not depend on other parts (e.g. DB)
        Mockito.when(service.createGroupTransaction(createGroupTransactionDTO)).thenThrow(new NotFoundArgumentsBusinessException(CreateGroupTransactionService.GROUP_DOES_NOT_EXIST));

        //Act

        Throwable thrown = assertThrows(NotFoundArgumentsBusinessException.class, () -> controller.createGroupTransaction(newGroupTransactionInfoDTO, personEmail, groupDenomination));

        //Assert
        assertEquals(thrown.getMessage(), CreateGroupTransactionService.GROUP_DOES_NOT_EXIST);
    }

    //PERSON_NOT_MEMBER

    /*
    @Test
    public void whenGroupTransactionIsNotCreated_thenRetrievedMsgPersonNotMember() {
        //Arrange

        String personEmail = "francis@gmail.com";
        String groupDenomination = "Fontes Family";
        String groupDescription = "All members from Fontes family";


        String denominationCategory = "IRS";
        String type = "debit";
        String transactionDescription = "May IRS";
        double amount = 150.0;
        String denominationAccountDeb = "Bank Account";
        String denominationAccountCred = "State";
        String date = "2020-06-18";

        NewGroupTransactionInfoDTO newGroupTransactionInfoDTO = new NewGroupTransactionInfoDTO(denominationCategory, type, transactionDescription, amount, denominationAccountDeb, denominationAccountCred, date);

        CreateGroupTransactionDTO createGroupTransactionDTO = CreateGroupTransactionDTOAssembler.createDTOFromPrimitiveTypes(groupDenomination, personEmail, newGroupTransactionInfoDTO.getDenominationCategory(), newGroupTransactionInfoDTO.getDenominationAccountDeb(), newGroupTransactionInfoDTO.getDenominationAccountCred(), newGroupTransactionInfoDTO.getAmount(), newGroupTransactionInfoDTO.getType(), newGroupTransactionInfoDTO.getDescription(), newGroupTransactionInfoDTO.getDate());

        // Mock the behaviour of the service's createCategoryAsPersonInCharge method,
        // so it does not depend on other parts (e.g. DB)
        Mockito.when(service.createGroupTransaction(createGroupTransactionDTO)).thenThrow(new NotFoundArgumentsBusinessException(CreateGroupTransactionService.PERSON_NOT_MEMBER));

        //Act

        Throwable thrown = assertThrows(InvalidArgumentsBusinessException.class, () -> controller.createGroupTransaction(newGroupTransactionInfoDTO, personEmail, groupDenomination));

        //Assert
        assertEquals(thrown.getMessage(), CreateGroupTransactionService.PERSON_NOT_MEMBER);
    }

     */

    //NEED_TO_CREATE_CATEGORY

//    @Test
//    public void whenGroupTransactionIsNotCreated_thenRetrievedMsgNeedToCreateCategory() {
//        //Arrange
//
//        String personEmail = "manuel@gmail.com";
//        String groupDenomination = "Fontes Family";
//        String groupDescription = "All members from Fontes family";
//
//
//        String denominationCategory = "Pets";
//        String type = "debit";
//        String transactionDescription = "May IRS";
//        double amount = 150.0;
//        String denominationAccountDeb = "Bank Account";
//        String denominationAccountCred = "State";
//
//        NewGroupTransactionInfoDTO newGroupTransactionInfoDTO = new NewGroupTransactionInfoDTO(denominationCategory, type, transactionDescription, amount, denominationAccountDeb, denominationAccountCred);
//
//        CreateGroupTransactionDTO createGroupTransactionDTO = CreateGroupTransactionDTOAssembler.createDTOFromPrimitiveTypes(groupDenomination, personEmail, newGroupTransactionInfoDTO.getDenominationCategory(), newGroupTransactionInfoDTO.getDenominationAccountDeb(), newGroupTransactionInfoDTO.getDenominationAccountCred(), newGroupTransactionInfoDTO.getAmount(), newGroupTransactionInfoDTO.getType(), newGroupTransactionInfoDTO.getDescription());
//
//        // Mock the behaviour of the service's createCategoryAsPersonInCharge method,
//        // so it does not depend on other parts (e.g. DB)
//        Mockito.when(service.createGroupTransaction(createGroupTransactionDTO)).thenThrow(new NotFoundArgumentsBusinessException(US008_1CreateGroupTransactionService.NEED_TO_CREATE_CATEGORY));
//
//        //Act
//
//        Throwable thrown = assertThrows(NotFoundArgumentsBusinessException.class, () -> controller.createGroupTransaction(newGroupTransactionInfoDTO, personEmail, groupDenomination));
//
//        //Assert
//        assertEquals(thrown.getMessage(), US008_1CreateGroupTransactionService.NEED_TO_CREATE_CATEGORY);
//    }
//
//    //NEED_TO_CREATE_ACCOUNT_TO_DEBIT
//
//    @Test
//    public void whenGroupTransactionIsNotCreated_thenRetrievedMsgNeedToCreateAccountToDebit() {
//        //Arrange
//
//        String personEmail = "manuel@gmail.com";
//        String groupDenomination = "Fontes Family";
//        String groupDescription = "All members from Fontes family";
//
//
//        String denominationCategory = "IRS";
//        String type = "debit";
//        String transactionDescription = "May IRS";
//        double amount = 150.0;
//        String denominationAccountDeb = "Vet";
//        String denominationAccountCred = "State";
//
//        NewGroupTransactionInfoDTO newGroupTransactionInfoDTO = new NewGroupTransactionInfoDTO(denominationCategory, type, transactionDescription, amount, denominationAccountDeb, denominationAccountCred);
//
//        CreateGroupTransactionDTO createGroupTransactionDTO = CreateGroupTransactionDTOAssembler.createDTOFromPrimitiveTypes(groupDenomination, personEmail, newGroupTransactionInfoDTO.getDenominationCategory(), newGroupTransactionInfoDTO.getDenominationAccountDeb(), newGroupTransactionInfoDTO.getDenominationAccountCred(), newGroupTransactionInfoDTO.getAmount(), newGroupTransactionInfoDTO.getType(), newGroupTransactionInfoDTO.getDescription());
//
//        // Mock the behaviour of the service's createCategoryAsPersonInCharge method,
//        // so it does not depend on other parts (e.g. DB)
//        Mockito.when(service.createGroupTransaction(createGroupTransactionDTO)).thenThrow(new NotFoundArgumentsBusinessException(US008_1CreateGroupTransactionService.NEED_TO_CREATE_ACCOUNT_TO_DEBIT));
//
//        //Act
//
//        Throwable thrown = assertThrows(NotFoundArgumentsBusinessException.class, () -> controller.createGroupTransaction(newGroupTransactionInfoDTO, personEmail, groupDenomination));
//
//        //Assert
//        assertEquals(thrown.getMessage(), US008_1CreateGroupTransactionService.NEED_TO_CREATE_ACCOUNT_TO_DEBIT);
//    }

    //NEED_TO_CREATE_ACCOUNT_TO_CREDIT

//    @Test
//    public void whenGroupTransactionIsNotCreated_thenRetrievedMsgNeedToCreateAccountToCredit() {
//        //Arrange
//
//        String personEmail = "manuel@gmail.com";
//        String groupDenomination = "Fontes Family";
//        String groupDescription = "All members from Fontes family";
//
//
//        String denominationCategory = "IRS";
//        String type = "debit";
//        String transactionDescription = "May IRS";
//        double amount = 150.0;
//        String denominationAccountDeb = "Bank Account";
//        String denominationAccountCred = "Vet";
//
//        NewGroupTransactionInfoDTO newGroupTransactionInfoDTO = new NewGroupTransactionInfoDTO(denominationCategory, type, transactionDescription, amount, denominationAccountDeb, denominationAccountCred);
//
//        CreateGroupTransactionDTO createGroupTransactionDTO = CreateGroupTransactionDTOAssembler.createDTOFromPrimitiveTypes(groupDenomination, personEmail, newGroupTransactionInfoDTO.getDenominationCategory(), newGroupTransactionInfoDTO.getDenominationAccountDeb(), newGroupTransactionInfoDTO.getDenominationAccountCred(), newGroupTransactionInfoDTO.getAmount(), newGroupTransactionInfoDTO.getType(), newGroupTransactionInfoDTO.getDescription());
//
//        // Mock the behaviour of the service's createCategoryAsPersonInCharge method,
//        // so it does not depend on other parts (e.g. DB)
//        Mockito.when(service.createGroupTransaction(createGroupTransactionDTO)).thenThrow(new NotFoundArgumentsBusinessException(US008_1CreateGroupTransactionService.NEED_TO_CREATE_ACCOUNT_TO_CREDIT));
//
//        //Act
//
//        Throwable thrown = assertThrows(NotFoundArgumentsBusinessException.class, () -> controller.createGroupTransaction(newGroupTransactionInfoDTO, personEmail, groupDenomination));
//
//        //Assert
//        assertEquals(thrown.getMessage(), US008_1CreateGroupTransactionService.NEED_TO_CREATE_ACCOUNT_TO_CREDIT);
//    }
}
