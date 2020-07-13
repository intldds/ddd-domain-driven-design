package com.finance.project.dtos.dtos;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PersonSearchAccountRecordsOutDTOTest {

    @Test
    @DisplayName("Test SearchAccountRecordsOutDTO constructor and getter")
    void testConstructorAndGetter() {
        // ARRANGE
        // Category Electricity Expenses
        String denominationExpenses = "Electricity Expenses";

        // Account Wallet
        String walletAccountDenomination = "House Funds";

        // Account EDP
        String edpAccountDenomination = "EDP";

        // Transaction 1
        String typeTransaction1 = "Debit";
        String descriptionTransaction1 = "EDP bill from January/2020";
        String dateTransaction1String = "2020-02-01";
        double amountTransaction1 = 40.00;

        // Transaction 2
        String typeTransaction2 = "Credit";
        String descriptionTransaction2 = "EDP bill from February/2020 - settlement - overcharge";
        String dateTransaction2String = "2020-03-01";
        double amountTransaction2 = 15.00;

        // DTO inputs
        TransactionDTOout transactionDTOout1 = new TransactionDTOout(denominationExpenses, typeTransaction1, descriptionTransaction1, amountTransaction1, dateTransaction1String, walletAccountDenomination, edpAccountDenomination);
        TransactionDTOout transactionDTOout2 = new TransactionDTOout(denominationExpenses, typeTransaction2, descriptionTransaction2, amountTransaction2, dateTransaction2String, edpAccountDenomination, walletAccountDenomination);

        // Expected delivering list of transactions
        ArrayList<TransactionDTOout> expectedListTransactionOut = new ArrayList<>();
        expectedListTransactionOut.add(transactionDTOout1);
        expectedListTransactionOut.add(transactionDTOout2);


        // ACT
        PersonSearchAccountRecordsOutDTO personSearchAccountRecordsOutDTO = new PersonSearchAccountRecordsOutDTO(expectedListTransactionOut);


        // ASSERT
        assertEquals(expectedListTransactionOut, personSearchAccountRecordsOutDTO.getTransactions());
    }

    @Test
    @DisplayName("Test empty constructor of SearchAccountRecordsOutDTO")
    void testEmptyConstructor() {
        // Arrange
        ArrayList<TransactionDTOout> expectedListTransactionOut = new ArrayList<>();

        // Act
        PersonSearchAccountRecordsOutDTO personSearchAccountRecordsOutDTO = new PersonSearchAccountRecordsOutDTO();
        personSearchAccountRecordsOutDTO.setTransactions(expectedListTransactionOut);

        //Assert
        assertEquals(expectedListTransactionOut, personSearchAccountRecordsOutDTO.getTransactions());
    }

    @Test
    @DisplayName("Test for equals() and hasCode() - Happy path")
    void testEqualsAndHashCode_HappyPath() {
        // ARRANGE
        // Object 1
        // Category Electricity Expenses - 1
        String denominationExpenses1 = "Electricity Expenses";

        // Account Wallet - 1
        String walletAccountDenomination1 = "House Funds";

        // Account EDP - 1
        String edpAccountDenomination1 = "EDP";

        // Transaction 1 - 1
        String typeTransaction11 = "Debit";
        String descriptionTransaction11 = "EDP bill from January/2020";
        String dateTransaction11String = "2020-02-01";
        double amountTransaction11 = 40.00;

        // Transaction 2 - 1
        String typeTransaction21 = "Credit";
        String descriptionTransaction21 = "EDP bill from February/2020 - settlement - overcharge";
        String dateTransaction21String = "2020-03-01";
        double amountTransaction21 = 15.00;

        // DTO inputs - 1
        TransactionDTOout transactionDTOout11 = new TransactionDTOout(denominationExpenses1, typeTransaction11, descriptionTransaction11, amountTransaction11, dateTransaction11String, walletAccountDenomination1, edpAccountDenomination1);
        TransactionDTOout transactionDTOout21 = new TransactionDTOout(denominationExpenses1, typeTransaction21, descriptionTransaction21, amountTransaction21, dateTransaction21String, edpAccountDenomination1, walletAccountDenomination1);

        // DTO out1 - Expected delivering list of transactions
        ArrayList<TransactionDTOout> expectedListTransactionOut1 = new ArrayList<>();
        expectedListTransactionOut1.add(transactionDTOout11);
        expectedListTransactionOut1.add(transactionDTOout21);
        PersonSearchAccountRecordsOutDTO personSearchAccountRecordsOutDTO1 = new PersonSearchAccountRecordsOutDTO(expectedListTransactionOut1);


        // Object 2
        // Category Electricity Expenses - 2
        String denominationExpenses2 = "Electricity Expenses";

        // Account Wallet - 2
        String walletAccountDenomination2 = "House Funds";

        // Account EDP - 2
        String edpAccountDenomination2 = "EDP";

        // Transaction 1 - 2
        String typeTransaction12 = "Debit";
        String descriptionTransaction12 = "EDP bill from January/2020";
        String dateTransaction12String = "2020-02-01";
        double amountTransaction12 = 40.00;

        // Transaction 2 - 2
        String typeTransaction22 = "Credit";
        String descriptionTransaction22 = "EDP bill from February/2020 - settlement - overcharge";
        String dateTransaction22String = "2020-03-01";
        double amountTransaction22 = 15.00;

        // DTO inputs - 2
        TransactionDTOout transactionDTOout12 = new TransactionDTOout(denominationExpenses2, typeTransaction12, descriptionTransaction12, amountTransaction12, dateTransaction12String, walletAccountDenomination2, edpAccountDenomination2);
        TransactionDTOout transactionDTOout22 = new TransactionDTOout(denominationExpenses2, typeTransaction22, descriptionTransaction22, amountTransaction22, dateTransaction22String, edpAccountDenomination2, walletAccountDenomination2);

        // DTO out2 - Expected delivering list of transactions
        ArrayList<TransactionDTOout> expectedListTransactionOut2 = new ArrayList<>();
        expectedListTransactionOut2.add(transactionDTOout12);
        expectedListTransactionOut2.add(transactionDTOout22);
        PersonSearchAccountRecordsOutDTO personSearchAccountRecordsOutDTO2 = new PersonSearchAccountRecordsOutDTO(expectedListTransactionOut2);


        // ACT
        boolean resultEquals = personSearchAccountRecordsOutDTO1.equals(personSearchAccountRecordsOutDTO2);
        boolean resultHashCode = (personSearchAccountRecordsOutDTO1.hashCode() == personSearchAccountRecordsOutDTO2.hashCode());


        //ASSERT
        assertEquals(true, resultEquals);
        assertEquals(true, resultHashCode);
    }

    @Test
    @DisplayName("Test for equals() and hasCode() - Same Object")
    void testEqualsAndHashCode_SameObject() {
        // ARRANGE
        // Object 1
        // Category Electricity Expenses - 1
        String denominationExpenses1 = "Electricity Expenses";

        // Account Wallet - 1
        String walletAccountDenomination1 = "House Funds";

        // Account EDP - 1
        String edpAccountDenomination1 = "EDP";

        // Transaction 1 - 1
        String typeTransaction11 = "Debit";
        String descriptionTransaction11 = "EDP bill from January/2020";
        String dateTransaction11String = "2020-02-01";
        double amountTransaction11 = 40.00;

        // Transaction 2 - 1
        String typeTransaction21 = "Credit";
        String descriptionTransaction21 = "EDP bill from February/2020 - settlement - overcharge";
        String dateTransaction21String = "2020-03-01";
        double amountTransaction21 = 15.00;

        // DTO inputs - 1
        TransactionDTOout transactionDTOout11 = new TransactionDTOout(denominationExpenses1, typeTransaction11, descriptionTransaction11, amountTransaction11, dateTransaction11String, walletAccountDenomination1, edpAccountDenomination1);
        TransactionDTOout transactionDTOout21 = new TransactionDTOout(denominationExpenses1, typeTransaction21, descriptionTransaction21, amountTransaction21, dateTransaction21String, edpAccountDenomination1, walletAccountDenomination1);

        // DTO out1 - Expected delivering list of transactions
        ArrayList<TransactionDTOout> expectedListTransactionOut1 = new ArrayList<>();
        expectedListTransactionOut1.add(transactionDTOout11);
        expectedListTransactionOut1.add(transactionDTOout21);
        PersonSearchAccountRecordsOutDTO personSearchAccountRecordsOutDTO1 = new PersonSearchAccountRecordsOutDTO(expectedListTransactionOut1);


        // ACT
        boolean resultEquals = personSearchAccountRecordsOutDTO1.equals(personSearchAccountRecordsOutDTO1);
        boolean resultHashCode = (personSearchAccountRecordsOutDTO1.hashCode() == personSearchAccountRecordsOutDTO1.hashCode());


        // ASSERT
        assertEquals(true, resultEquals);
        assertEquals(true, resultHashCode);
    }

    @Test
    @DisplayName("Test for equals() - Fail (null object)")
    void testEquals_FailNullObject() {
        // ARRANGE
        // Object1
        // Category Electricity Expenses - 1
        String denominationExpenses1 = "Electricity Expenses";

        // Account Wallet - 1
        String walletAccountDenomination1 = "House Funds";

        // Account EDP - 1
        String edpAccountDenomination1 = "EDP";

        // Transaction 1 - 1
        String typeTransaction11 = "Debit";
        String descriptionTransaction11 = "EDP bill from January/2020";
        String dateTransaction11String = "2020-02-01";
        double amountTransaction11 = 40.00;

        // Transaction 2 - 1
        String typeTransaction21 = "Credit";
        String descriptionTransaction21 = "EDP bill from February/2020 - settlement - overcharge";
        String dateTransaction21String = "2020-03-01";
        double amountTransaction21 = 15.00;

        // DTO inputs - 1
        TransactionDTOout transactionDTOout11 = new TransactionDTOout(denominationExpenses1, typeTransaction11, descriptionTransaction11, amountTransaction11, dateTransaction11String, walletAccountDenomination1, edpAccountDenomination1);
        TransactionDTOout transactionDTOout21 = new TransactionDTOout(denominationExpenses1, typeTransaction21, descriptionTransaction21, amountTransaction21, dateTransaction21String, edpAccountDenomination1, walletAccountDenomination1);

        // DTO out1 - Expected delivering list of transactions
        ArrayList<TransactionDTOout> expectedListTransactionOut1 = new ArrayList<>();
        expectedListTransactionOut1.add(transactionDTOout11);
        expectedListTransactionOut1.add(transactionDTOout21);
        PersonSearchAccountRecordsOutDTO personSearchAccountRecordsOutDTO1 = new PersonSearchAccountRecordsOutDTO(expectedListTransactionOut1);


        // Object 2
        // DTO out2 - null
        PersonSearchAccountRecordsOutDTO personSearchAccountRecordsOutDTO2 = null;

        // ACT
        boolean resultEquals = personSearchAccountRecordsOutDTO1.equals(personSearchAccountRecordsOutDTO2);


        // ASSERT
        assertEquals(false, resultEquals);
    }

    @Test
    @DisplayName("Test for equals() and hasCode() - Fail (different class)")
    void testEqualsAndHashCode_FailDiffClass() {
        // ARRANGE
        // Object1
        // Category Electricity Expenses - 1
        String denominationExpenses1 = "Electricity Expenses";

        // Account Wallet - 1
        String walletAccountDenomination1 = "House Funds";

        // Account EDP - 1
        String edpAccountDenomination1 = "EDP";

        // Transaction 1 - 1
        String typeTransaction11 = "Debit";
        String descriptionTransaction11 = "EDP bill from January/2020";
        String dateTransaction11String = "2020-02-01";
        double amountTransaction11 = 40.00;

        // Transaction 2 - 1
        String typeTransaction21 = "Credit";
        String descriptionTransaction21 = "EDP bill from February/2020 - settlement - overcharge";
        String dateTransaction21String = "2020-03-01";
        double amountTransaction21 = 15.00;

        // DTO inputs - 1
        TransactionDTOout transactionDTOout11 = new TransactionDTOout(denominationExpenses1, typeTransaction11, descriptionTransaction11, amountTransaction11, dateTransaction11String, walletAccountDenomination1, edpAccountDenomination1);
        TransactionDTOout transactionDTOout21 = new TransactionDTOout(denominationExpenses1, typeTransaction21, descriptionTransaction21, amountTransaction21, dateTransaction21String, edpAccountDenomination1, walletAccountDenomination1);

        // DTO out1 - Expected delivering list of transactions
        ArrayList<TransactionDTOout> expectedListTransactionOut1 = new ArrayList<>();
        expectedListTransactionOut1.add(transactionDTOout11);
        expectedListTransactionOut1.add(transactionDTOout21);
        PersonSearchAccountRecordsOutDTO personSearchAccountRecordsOutDTO1 = new PersonSearchAccountRecordsOutDTO(expectedListTransactionOut1);


        // Object 2 - Different class
        String object = "Object from class String";


        // ACT
        boolean resultEquals = personSearchAccountRecordsOutDTO1.equals(object);
        boolean resultHashCode = (personSearchAccountRecordsOutDTO1.hashCode() == object.hashCode());


        // ASSERT
        assertEquals(false, resultEquals);
        assertEquals(false, resultHashCode);
    }

    @Test
    @DisplayName("Test for equals() and hasCode() - Fail (different DTO list)")
    void testEqualsAndHashCode_FailDiffDTOList() {
        // ARRANGE
        // Object 1
        // Category Electricity Expenses - 1
        String denominationExpenses1 = "Electricity Expenses";

        // Account Wallet - 1
        String walletAccountDenomination1 = "House Funds";

        // Account EDP - 1
        String edpAccountDenomination1 = "EDP";

        // Transaction 1 - 1
        String typeTransaction11 = "Debit";
        String descriptionTransaction11 = "EDP bill from January/2020";
        String dateTransaction11String = "2020-02-01";
        double amountTransaction11 = 40.00;

        // Transaction 2 - 1
        String typeTransaction21 = "Credit";
        String descriptionTransaction21 = "EDP bill from February/2020 - settlement - overcharge";
        String dateTransaction21String = "2020-03-01";
        double amountTransaction21 = 15.00;

        // DTO inputs - 1
        TransactionDTOout transactionDTOout11 = new TransactionDTOout(denominationExpenses1, typeTransaction11, descriptionTransaction11, amountTransaction11, dateTransaction11String, walletAccountDenomination1, edpAccountDenomination1);
        TransactionDTOout transactionDTOout21 = new TransactionDTOout(denominationExpenses1, typeTransaction21, descriptionTransaction21, amountTransaction21, dateTransaction21String, edpAccountDenomination1, walletAccountDenomination1);

        // DTO out1 - Expected delivering list of transactions
        ArrayList<TransactionDTOout> expectedListTransactionOut1 = new ArrayList<>();
        expectedListTransactionOut1.add(transactionDTOout11);
        expectedListTransactionOut1.add(transactionDTOout21);
        PersonSearchAccountRecordsOutDTO personSearchAccountRecordsOutDTO1 = new PersonSearchAccountRecordsOutDTO(expectedListTransactionOut1);


        // Object 2
        // Category Electricity Expenses - 2
        String denominationExpenses2 = "Electricity Expenses";

        // Account Wallet - 2
        String walletAccountDenomination2 = "House Funds";

        // Account EDP - 2
        String edpAccountDenomination2 = "EDP";

        // Transaction 1 - 2
        String typeTransaction12 = "Debit";
        String descriptionTransaction12 = "EDP bill from January/2020";
        String dateTransaction12String = "2020-02-01";
        double amountTransaction12 = 40.00;

        // Transaction 2 - 2
        String typeTransaction22 = "Credit";
        String descriptionTransaction22 = "EDP bill from February/2020 - settlement - overcharge";
        String dateTransaction22String = "2020-03-01";
        double amountTransaction22 = 15.00;

        // DTO inputs - 2
        TransactionDTOout transactionDTOout12 = new TransactionDTOout(denominationExpenses2, typeTransaction12, descriptionTransaction12, amountTransaction12, dateTransaction12String, walletAccountDenomination2, edpAccountDenomination2);
        TransactionDTOout transactionDTOout22 = new TransactionDTOout(denominationExpenses2, typeTransaction22, descriptionTransaction22, amountTransaction22, dateTransaction22String, edpAccountDenomination2, walletAccountDenomination2);

        // DTO out2 - Expected delivering list of transactions
        ArrayList<TransactionDTOout> expectedListTransactionOut2 = new ArrayList<>();
        expectedListTransactionOut1.add(transactionDTOout12);
        PersonSearchAccountRecordsOutDTO personSearchAccountRecordsOutDTO2 = new PersonSearchAccountRecordsOutDTO(expectedListTransactionOut2);


        //ACT
        boolean resultEquals = personSearchAccountRecordsOutDTO1.equals(personSearchAccountRecordsOutDTO2);
        boolean resultHashCode = (personSearchAccountRecordsOutDTO1.hashCode() == personSearchAccountRecordsOutDTO2.hashCode());


        //ASSERT
        assertEquals(false, resultEquals);
        assertEquals(false, resultHashCode);
    }
}