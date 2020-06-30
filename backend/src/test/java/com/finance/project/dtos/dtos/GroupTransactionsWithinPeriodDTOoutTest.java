package com.finance.project.dtos.dtos;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GroupTransactionsWithinPeriodDTOoutTest {

    @Test
    @DisplayName("Test GroupTransactionsWithinPeriodDTOout constructor")
    void testConstructor() {
        //Arrange
        //Category Electricity Expenses (Group House)
        String denominationExpenses = "Electricity Expenses";

        //Account House Wallet (Group House)
        String houseWalletAccountDenomination = "House Funds";

        //Account EDP (Group House)
        String houseEdpAccountDenomination = "EDP";

        //Transaction 1 on Group House
        String typeTransaction1House = "Debit";
        String descriptionTransaction1House = "EDP bill from January/2020";
        LocalDate dateTransaction1House = LocalDate.of(2020, 02, 01);
        String dateTransaction1HouseString = "2020-02-01";
        double amountTransaction1House = 40.00;

        //Transaction 2 on Group House
        String typeTransaction1House2 = "Debit";
        String descriptionTransaction1House2 = "EDP bill from February/2020";
        LocalDate dateTransaction1House2 = LocalDate.of(2020, 03, 01);
        String dateTransaction1HouseString2 = "2020-03-01";
        double amountTransaction1House2 = 45.00;

        //DTO inputs
        TransactionDTOout transactionDTOout1 = new TransactionDTOout(denominationExpenses, typeTransaction1House, descriptionTransaction1House, amountTransaction1House, dateTransaction1HouseString, houseWalletAccountDenomination, houseEdpAccountDenomination);
        TransactionDTOout transactionDTOout2 = new TransactionDTOout(denominationExpenses, typeTransaction1House2, descriptionTransaction1House2, amountTransaction1House2, dateTransaction1HouseString2, houseWalletAccountDenomination, houseEdpAccountDenomination);
        ArrayList<TransactionDTOout> expectedListTransactionOut = new ArrayList<>();
        expectedListTransactionOut.add(transactionDTOout1);
        expectedListTransactionOut.add(transactionDTOout2);


        //Act
        GroupTransactionsWithinPeriodDTOout groupTransactionsWithinPeriodDTOout = new GroupTransactionsWithinPeriodDTOout(expectedListTransactionOut);


        //Assert
        assertEquals(expectedListTransactionOut, groupTransactionsWithinPeriodDTOout.getTransactionsList());
    }

    @Test
    @DisplayName("Test empty constructor of GroupTransactionsWithinPeriodDTOout")
    void testEmptyConstructor() {
        //Arrange
        ArrayList<TransactionDTOout> expectedListTransactionOut = new ArrayList<>();


        //Act
        GroupTransactionsWithinPeriodDTOout groupTransactionsWithinPeriodDTOout = new GroupTransactionsWithinPeriodDTOout();
        groupTransactionsWithinPeriodDTOout.setTransactionsList(expectedListTransactionOut);


        //Assert
        assertEquals(expectedListTransactionOut, groupTransactionsWithinPeriodDTOout.getTransactionsList());
    }

    @Test
    @DisplayName("Test GroupTransactionsWithinPeriodDTOout getters - getTransactionsList()")
    void testGetTransactionsList() {
        //Arrange
        //Category Electricity Expenses (Group House)
        String denominationExpenses = "Electricity Expenses";

        //Account House Wallet (Group House)
        String houseWalletAccountDenomination = "House Funds";

        //Account EDP (Group House)
        String houseEdpAccountDenomination = "EDP";

        //Transaction 1 on Group House
        String typeTransaction1House = "Debit";
        String descriptionTransaction1House = "EDP bill from January/2020";
        LocalDate dateTransaction1House = LocalDate.of(2020, 02, 01);
        String dateTransaction1HouseString = "2020-02-01";
        double amountTransaction1House = 40.00;

        //Transaction 2 on Group House
        String typeTransaction1House2 = "Debit";
        String descriptionTransaction1House2 = "EDP bill from February/2020";
        LocalDate dateTransaction1House2 = LocalDate.of(2020, 03, 01);
        String dateTransaction1HouseString2 = "2020-03-01";
        double amountTransaction1House2 = 45.00;

        //DTO inputs
        TransactionDTOout transactionDTOout1 = new TransactionDTOout(denominationExpenses, typeTransaction1House, descriptionTransaction1House, amountTransaction1House, dateTransaction1HouseString, houseWalletAccountDenomination, houseEdpAccountDenomination);
        TransactionDTOout transactionDTOout2 = new TransactionDTOout(denominationExpenses, typeTransaction1House2, descriptionTransaction1House2, amountTransaction1House2, dateTransaction1HouseString2, houseWalletAccountDenomination, houseEdpAccountDenomination);
        ArrayList<TransactionDTOout> expectedListTransactionOut = new ArrayList<>();
        expectedListTransactionOut.add(transactionDTOout1);
        expectedListTransactionOut.add(transactionDTOout2);


        //Act
        GroupTransactionsWithinPeriodDTOout groupTransactionsWithinPeriodDTOout = new GroupTransactionsWithinPeriodDTOout(expectedListTransactionOut);


        //Assert
        assertEquals(expectedListTransactionOut, groupTransactionsWithinPeriodDTOout.getTransactionsList());
    }

    @Test
    @DisplayName("Test for equals() and hasCode() - Happy path")
    void testEqualsAndHashCode_HappyPath() {
        //Arrange
        //Group House 1
        //Category Electricity Expenses (Group House 1)
        String denominationExpenses1 = "Electricity Expenses";

        //Account House Wallet (Group House 1)
        String houseWalletAccountDenomination1 = "House Funds";

        //Account EDP (Group House 1)
        String houseEdpAccountDenomination1 = "EDP";

        //Transaction 1 on Group House 1
        String typeTransaction1House1 = "Debit";
        String descriptionTransaction1House1 = "EDP bill from January/2020";
        LocalDate dateTransaction1House1 = LocalDate.of(2020, 02, 01);
        String dateTransaction1HouseString1 = "2020-02-01";
        double amountTransaction1House1 = 40.00;

        //Transaction 2 on Group House 1
        String typeTransaction1House21 = "Debit";
        String descriptionTransaction1House21 = "EDP bill from February/2020";
        LocalDate dateTransaction1House21 = LocalDate.of(2020, 03, 01);
        String dateTransaction1HouseString21 = "2020-03-01";
        double amountTransaction1House21 = 45.00;

        //DTO inputs 1
        TransactionDTOout transactionDTOout11 = new TransactionDTOout(denominationExpenses1, typeTransaction1House1, descriptionTransaction1House1, amountTransaction1House1, dateTransaction1HouseString1, houseWalletAccountDenomination1, houseEdpAccountDenomination1);
        TransactionDTOout transactionDTOout21 = new TransactionDTOout(denominationExpenses1, typeTransaction1House21, descriptionTransaction1House21, amountTransaction1House21, dateTransaction1HouseString21, houseWalletAccountDenomination1, houseEdpAccountDenomination1);
        ArrayList<TransactionDTOout> expectedListTransactionOut1 = new ArrayList<>();
        expectedListTransactionOut1.add(transactionDTOout11);
        expectedListTransactionOut1.add(transactionDTOout21);

        boolean expectedNoTransactionsToReport1 = false;
        String expectedMsg1 = "Success";

        //DTO 1
        GroupTransactionsWithinPeriodDTOout groupTransactionsWithinPeriodDTOout1 = new GroupTransactionsWithinPeriodDTOout(expectedListTransactionOut1);


        // Group House 2
        //Category Electricity Expenses (Group House 2)
        String denominationExpenses2 = "Electricity Expenses";

        //Account House Wallet (Group House 2)
        String houseWalletAccountDenomination2 = "House Funds";

        //Account EDP (Group House 2)
        String houseEdpAccountDenomination2 = "EDP";

        //Transaction 1 on Group House 2
        String typeTransaction1House2 = "Debit";
        String descriptionTransaction1House2 = "EDP bill from January/2020";
        LocalDate dateTransaction1House2 = LocalDate.of(2020, 02, 01);
        String dateTransaction1HouseString2 = "2020-02-01";
        double amountTransaction1House2 = 40.00;

        //Transaction 2 on Group House 2
        String typeTransaction1House22 = "Debit";
        String descriptionTransaction1House22 = "EDP bill from February/2020";
        LocalDate dateTransaction1House22 = LocalDate.of(2020, 03, 01);
        String dateTransaction1HouseString22 = "2020-03-01";
        double amountTransaction1House22 = 45.00;

        //DTO inputs 2
        TransactionDTOout transactionDTOout12 = new TransactionDTOout(denominationExpenses2, typeTransaction1House2, descriptionTransaction1House2, amountTransaction1House2, dateTransaction1HouseString2, houseWalletAccountDenomination2, houseEdpAccountDenomination2);
        TransactionDTOout transactionDTOout22 = new TransactionDTOout(denominationExpenses2, typeTransaction1House22, descriptionTransaction1House22, amountTransaction1House22, dateTransaction1HouseString22, houseWalletAccountDenomination2, houseEdpAccountDenomination2);
        ArrayList<TransactionDTOout> expectedListTransactionOut2 = new ArrayList<>();
        expectedListTransactionOut2.add(transactionDTOout12);
        expectedListTransactionOut2.add(transactionDTOout22);

        //DTO 2
        GroupTransactionsWithinPeriodDTOout groupTransactionsWithinPeriodDTOout2 = new GroupTransactionsWithinPeriodDTOout(expectedListTransactionOut2);


        //Act
        boolean resultEquals = groupTransactionsWithinPeriodDTOout1.equals(groupTransactionsWithinPeriodDTOout2);
        boolean resultHashCode = (groupTransactionsWithinPeriodDTOout1.hashCode() == groupTransactionsWithinPeriodDTOout2.hashCode());


        //Assert
        assertEquals(true, resultEquals);
        assertEquals(true, resultHashCode);
    }


    @Test
    @DisplayName("Test for equals() and hasCode() - Same Object")
    void testEqualsAndHashCode_SameObject() {
        //Arrange
        //Group House 1
        //Category Electricity Expenses (Group House 1)
        String denominationExpenses1 = "Electricity Expenses";

        //Account House Wallet (Group House 1)
        String houseWalletAccountDenomination1 = "House Funds";

        //Account EDP (Group House 1)
        String houseEdpAccountDenomination1 = "EDP";

        //Transaction 1 on Group House 1
        String typeTransaction1House1 = "Debit";
        String descriptionTransaction1House1 = "EDP bill from January/2020";
        LocalDate dateTransaction1House1 = LocalDate.of(2020, 02, 01);
        String dateTransaction1HouseString1 = "2020-02-01";
        double amountTransaction1House1 = 40.00;

        //Transaction 2 on Group House 1
        String typeTransaction1House21 = "Debit";
        String descriptionTransaction1House21 = "EDP bill from February/2020";
        LocalDate dateTransaction1House21 = LocalDate.of(2020, 03, 01);
        String dateTransaction1HouseString21 = "2020-03-01";
        double amountTransaction1House21 = 45.00;

        //DTO inputs 1
        TransactionDTOout transactionDTOout11 = new TransactionDTOout(denominationExpenses1, typeTransaction1House1, descriptionTransaction1House1, amountTransaction1House1, dateTransaction1HouseString1, houseWalletAccountDenomination1, houseEdpAccountDenomination1);
        TransactionDTOout transactionDTOout21 = new TransactionDTOout(denominationExpenses1, typeTransaction1House21, descriptionTransaction1House21, amountTransaction1House21, dateTransaction1HouseString21, houseWalletAccountDenomination1, houseEdpAccountDenomination1);
        ArrayList<TransactionDTOout> expectedListTransactionOut1 = new ArrayList<>();
        expectedListTransactionOut1.add(transactionDTOout11);
        expectedListTransactionOut1.add(transactionDTOout21);

        //DTO 1
        GroupTransactionsWithinPeriodDTOout groupTransactionsWithinPeriodDTOout1 = new GroupTransactionsWithinPeriodDTOout(expectedListTransactionOut1);


        //Act
        boolean resultEquals = groupTransactionsWithinPeriodDTOout1.equals(groupTransactionsWithinPeriodDTOout1);
        boolean resultHashCode = (groupTransactionsWithinPeriodDTOout1.hashCode() == groupTransactionsWithinPeriodDTOout1.hashCode());


        //Assert
        assertEquals(true, resultEquals);
        assertEquals(true, resultHashCode);
    }

    @Test
    @DisplayName("Test for equals() - Fail (null object)")
    void testEquals_FailNullObject() {
        //Arrange
        //Group House 1
        //Category Electricity Expenses (Group House 1)
        String denominationExpenses1 = "Electricity Expenses";

        //Account House Wallet (Group House 1)
        String houseWalletAccountDenomination1 = "House Funds";

        //Account EDP (Group House 1)
        String houseEdpAccountDenomination1 = "EDP";

        //Transaction 1 on Group House 1
        String typeTransaction1House1 = "Debit";
        String descriptionTransaction1House1 = "EDP bill from January/2020";
        LocalDate dateTransaction1House1 = LocalDate.of(2020, 02, 01);
        String dateTransaction1HouseString1 = "2020-02-01";
        double amountTransaction1House1 = 40.00;

        //Transaction 2 on Group House 1
        String typeTransaction1House21 = "Debit";
        String descriptionTransaction1House21 = "EDP bill from February/2020";
        LocalDate dateTransaction1House21 = LocalDate.of(2020, 03, 01);
        String dateTransaction1HouseString21 = "2020-03-01";
        double amountTransaction1House21 = 45.00;

        //DTO inputs 1
        TransactionDTOout transactionDTOout11 = new TransactionDTOout(denominationExpenses1, typeTransaction1House1, descriptionTransaction1House1, amountTransaction1House1, dateTransaction1HouseString1, houseWalletAccountDenomination1, houseEdpAccountDenomination1);
        TransactionDTOout transactionDTOout21 = new TransactionDTOout(denominationExpenses1, typeTransaction1House21, descriptionTransaction1House21, amountTransaction1House21, dateTransaction1HouseString21, houseWalletAccountDenomination1, houseEdpAccountDenomination1);
        ArrayList<TransactionDTOout> expectedListTransactionOut1 = new ArrayList<>();
        expectedListTransactionOut1.add(transactionDTOout11);
        expectedListTransactionOut1.add(transactionDTOout21);

        //DTO 1
        GroupTransactionsWithinPeriodDTOout groupTransactionsWithinPeriodDTOout1 = new GroupTransactionsWithinPeriodDTOout(expectedListTransactionOut1);


        // Group House 2
        //DTO 2
        GroupTransactionsWithinPeriodDTOout groupTransactionsWithinPeriodDTOout2 = null;


        //Act
        boolean resultEquals = groupTransactionsWithinPeriodDTOout1.equals(groupTransactionsWithinPeriodDTOout2);


        //Assert
        assertEquals(false, resultEquals);
    }

    @Test
    @DisplayName("Test for equals() and hasCode() - Fail (different class)")
    void testEqualsAndHashCode_FailDiffClass() {
        //Arrange
        //Group House 1
        //Category Electricity Expenses (Group House 1)
        String denominationExpenses1 = "Electricity Expenses";

        //Account House Wallet (Group House 1)
        String houseWalletAccountDenomination1 = "House Funds";

        //Account EDP (Group House 1)
        String houseEdpAccountDenomination1 = "EDP";

        //Transaction 1 on Group House 1
        String typeTransaction1House1 = "Debit";
        String descriptionTransaction1House1 = "EDP bill from January/2020";
        LocalDate dateTransaction1House1 = LocalDate.of(2020, 02, 01);
        String dateTransaction1HouseString1 = "2020-02-01";
        double amountTransaction1House1 = 40.00;

        //Transaction 2 on Group House 1
        String typeTransaction1House21 = "Debit";
        String descriptionTransaction1House21 = "EDP bill from February/2020";
        LocalDate dateTransaction1House21 = LocalDate.of(2020, 03, 01);
        String dateTransaction1HouseString21 = "2020-03-01";
        double amountTransaction1House21 = 45.00;

        //DTO inputs 1
        TransactionDTOout transactionDTOout11 = new TransactionDTOout(denominationExpenses1, typeTransaction1House1, descriptionTransaction1House1, amountTransaction1House1, dateTransaction1HouseString1, houseWalletAccountDenomination1, houseEdpAccountDenomination1);
        TransactionDTOout transactionDTOout21 = new TransactionDTOout(denominationExpenses1, typeTransaction1House21, descriptionTransaction1House21, amountTransaction1House21, dateTransaction1HouseString21, houseWalletAccountDenomination1, houseEdpAccountDenomination1);
        ArrayList<TransactionDTOout> expectedListTransactionOut1 = new ArrayList<>();
        expectedListTransactionOut1.add(transactionDTOout11);
        expectedListTransactionOut1.add(transactionDTOout21);

        //DTO 1
        GroupTransactionsWithinPeriodDTOout groupTransactionsWithinPeriodDTOout1 = new GroupTransactionsWithinPeriodDTOout(expectedListTransactionOut1);

        String object = "Object from class String";


        //Act
        boolean resultEquals = groupTransactionsWithinPeriodDTOout1.equals(object);
        boolean resultHashCode = (groupTransactionsWithinPeriodDTOout1.hashCode() == object.hashCode());


        //Assert
        assertEquals(false, resultEquals);
        assertEquals(false, resultHashCode);
    }

    @Test
    @DisplayName("Test for equals() and hasCode() - Fail (different DTO list)")
    void testEqualsAndHashCode_FailDiffDTOList() {
        //Arrange
        //Group House 1
        //Category Electricity Expenses (Group House 1)
        String denominationExpenses1 = "Electricity Expenses";

        //Account House Wallet (Group House 1)
        String houseWalletAccountDenomination1 = "House Funds";

        //Account EDP (Group House 1)
        String houseEdpAccountDenomination1 = "EDP";

        //Transaction 1 on Group House 1
        String typeTransaction1House1 = "Debit";
        String descriptionTransaction1House1 = "EDP bill from January/2020";
        LocalDate dateTransaction1House1 = LocalDate.of(2020, 02, 01);
        String dateTransaction1HouseString1 = "2020-02-01";
        double amountTransaction1House1 = 40.00;

        //Transaction 2 on Group House 1
        String typeTransaction1House21 = "Debit";
        String descriptionTransaction1House21 = "EDP bill from February/2020";
        LocalDate dateTransaction1House21 = LocalDate.of(2020, 03, 01);
        String dateTransaction1HouseString21 = "2020-03-01";
        double amountTransaction1House21 = 45.00;

        //DTO inputs 1
        TransactionDTOout transactionDTOout11 = new TransactionDTOout(denominationExpenses1, typeTransaction1House1, descriptionTransaction1House1, amountTransaction1House1, dateTransaction1HouseString1, houseWalletAccountDenomination1, houseEdpAccountDenomination1);
        TransactionDTOout transactionDTOout21 = new TransactionDTOout(denominationExpenses1, typeTransaction1House21, descriptionTransaction1House21, amountTransaction1House21, dateTransaction1HouseString21, houseWalletAccountDenomination1, houseEdpAccountDenomination1);
        ArrayList<TransactionDTOout> expectedListTransactionOut1 = new ArrayList<>();
        expectedListTransactionOut1.add(transactionDTOout11);

        //DTO 1
        GroupTransactionsWithinPeriodDTOout groupTransactionsWithinPeriodDTOout1 = new GroupTransactionsWithinPeriodDTOout(expectedListTransactionOut1);


        // Group House 2
        //Category Electricity Expenses (Group House 2)
        String denominationExpenses2 = "Electricity Expenses";

        //Account House Wallet (Group House 2)
        String houseWalletAccountDenomination2 = "House Funds";

        //Account EDP (Group House 2)
        String houseEdpAccountDenomination2 = "EDP";

        //Transaction 1 on Group House 2
        String typeTransaction1House2 = "Debit";
        String descriptionTransaction1House2 = "EDP bill from January/2020";
        LocalDate dateTransaction1House2 = LocalDate.of(2020, 02, 01);
        String dateTransaction1HouseString2 = "2020-02-01";
        double amountTransaction1House2 = 40.00;

        //Transaction 2 on Group House 2
        String typeTransaction1House22 = "Debit";
        String descriptionTransaction1House22 = "EDP bill from February/2020";
        LocalDate dateTransaction1House22 = LocalDate.of(2020, 03, 01);
        String dateTransaction1HouseString22 = "2020-03-01";
        double amountTransaction1House22 = 45.00;

        //DTO inputs 2
        TransactionDTOout transactionDTOout12 = new TransactionDTOout(denominationExpenses2, typeTransaction1House2, descriptionTransaction1House2, amountTransaction1House2, dateTransaction1HouseString2, houseWalletAccountDenomination2, houseEdpAccountDenomination2);
        TransactionDTOout transactionDTOout22 = new TransactionDTOout(denominationExpenses2, typeTransaction1House22, descriptionTransaction1House22, amountTransaction1House22, dateTransaction1HouseString22, houseWalletAccountDenomination2, houseEdpAccountDenomination2);
        ArrayList<TransactionDTOout> expectedListTransactionOut2 = new ArrayList<>();
        expectedListTransactionOut2.add(transactionDTOout12);
        expectedListTransactionOut2.add(transactionDTOout22);

        //DTO 2
        GroupTransactionsWithinPeriodDTOout groupTransactionsWithinPeriodDTOout2 = new GroupTransactionsWithinPeriodDTOout(expectedListTransactionOut2);


        //Act
        boolean resultEquals = groupTransactionsWithinPeriodDTOout1.equals(groupTransactionsWithinPeriodDTOout2);
        boolean resultHashCode = (groupTransactionsWithinPeriodDTOout1.hashCode() == groupTransactionsWithinPeriodDTOout2.hashCode());


        //Assert
        assertEquals(false, resultEquals);
        assertEquals(false, resultHashCode);
    }
}