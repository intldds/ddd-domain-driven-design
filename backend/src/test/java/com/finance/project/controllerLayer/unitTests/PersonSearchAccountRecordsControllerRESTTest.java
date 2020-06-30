package com.finance.project.controllerLayer.unitTests;

import com.finance.project.applicationLayer.applicationServices.personServices.PersonSearchAccountRecordsService;
import com.finance.project.controllerLayer.controllersREST.personControllers.PersonSearchAccountRecordsControllerREST;
import com.finance.project.controllerLayer.integrationTests.AbstractTest;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PersonSearchAccountRecordsControllerRESTTest extends AbstractTest {

    @Mock
    private PersonSearchAccountRecordsService us010Service;

    @Autowired
    private PersonSearchAccountRecordsControllerREST us010RestController;
//
//    @Test
//    @DisplayName("Test for controller method getPersonAccountTransactionsWithinPeriod() - Success")
//    void getAccountTransactionsWithinPeriod_success() throws Exception {
//        //ARRANGE
//        //Person to search
//        String personEmail = "elsa@gmail.com";
//        PersonID personID = PersonID.createPersonID(personEmail);
//
//        //Account to search - Wallet
//        String accountDenomination = "Wallet";
//
//        //Dates to search
//        String startDate = "2020-01-10";
//        String endDate = "2020-02-10";
//
//        //Other Accounts used on transactions
//        String bankAccountDenomination = "Bank Account";
//        String supermarketAccountDenomination = "Supermarket";
//
//        //Transaction 1 - Wallet - credit
//        String drawMoneyDenomination = "Draw Money";
//        CategoryID drawMoneyCatID = CategoryID.createCategoryID(drawMoneyDenomination, personID);
//        String typeTransaction1 = "credit";
//        String descriptionTransaction1 = "January draw money";
//        double amountTransaction1 = 100.0;
//        LocalDate dateTransaction1 = LocalDate.of(2020, 01, 25);
//        AccountID debAccountTransaction1 = AccountID.createAccountID(bankAccountDenomination, personID);
//        AccountID credAccountTransaction1 = AccountID.createAccountID(accountDenomination, personID);
//        Transaction transaction1 = Transaction.createTransaction(drawMoneyCatID, typeTransaction1, descriptionTransaction1, amountTransaction1, dateTransaction1, debAccountTransaction1, credAccountTransaction1);
//
//        //Transaction 2 - Wallet - debit
//        String foodDenomination = "Food";
//        CategoryID foodCatID = CategoryID.createCategoryID(foodDenomination, personID);
//        String typeTransaction2 = "debit";
//        String descriptionTransaction2 = "January food";
//        double amountTransaction2 = 50.0;
//        LocalDate dateTransaction2 = LocalDate.of(2020, 01, 29);
//        AccountID debAccountTransaction2 = AccountID.createAccountID(accountDenomination, personID);
//        AccountID credAccountTransaction2 = AccountID.createAccountID(supermarketAccountDenomination, personID);
//        Transaction transaction2 = Transaction.createTransaction(foodCatID, typeTransaction2, descriptionTransaction2, amountTransaction2, dateTransaction2, debAccountTransaction2, credAccountTransaction2);
//
//        //Expected DTO result
//        ArrayList<Transaction> expectedTransactions = new ArrayList<>();
//        expectedTransactions.add(transaction1);
//        expectedTransactions.add(transaction2);
//
//        SearchAccountRecordsOutDTO expectedDTOout = SearchAccountRecordsOutDTOAssembler.accountTransactionsOutDTO(expectedTransactions);
//
//        //Arrange DTO in
//        PersonSearchAccountRecordsInDTO dtoIn = PersonSearchAccountRecordsInDTOAssembler.personAccountTransactionsInDTO(personEmail, accountDenomination, startDate, endDate);
//
//        //Mock the behaviour of the service method getPersonAccountTransactionsWithinPeriod,
//        //so it does not depend on other parts (e.g. DB)
//        Mockito.when(us010Service.getPersonAccountTransactionsWithinPeriod(dtoIn)).thenReturn(expectedDTOout);
//
//        ResponseEntity<Object> expectedResponseEntity = new ResponseEntity<>(expectedDTOout, HttpStatus.OK);
//
//
//        //ACT
//        ResponseEntity<Object> result = us010RestController.searchPersonRecords(accountDenomination, startDate, endDate, personEmail);
//
//
//        //ASSERT
//        assertEquals(expectedResponseEntity, result);
//    }


}