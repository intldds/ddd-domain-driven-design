package com.finance.project.dtos.dtos;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TransactionsDTOTest {

    @Test
    @DisplayName("TransactionsDTO - Test Constructor with Parameters")
    void transactionsDTO_ConstructorWithParametersTest() {

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
        double amountTransaction1House = 40.00;

        TransactionDTOout transactionDTOout = new TransactionDTOout(denominationExpenses, typeTransaction1House, descriptionTransaction1House, amountTransaction1House, dateTransaction1House.toString(), houseWalletAccountDenomination, houseEdpAccountDenomination);

        List<TransactionDTOout> transactions = new ArrayList<>();
        transactions.add(transactionDTOout);

        //Act
        TransactionsDTO transactionsDTO = new TransactionsDTO(transactions);

        //Assert
        assertEquals(transactions, transactionsDTO.getTransactions());
    }

    @Test
    @DisplayName("TransactionsDTO - Test Constructor without Parameters")
    void transactionsDTO_ConstructorWithoutParametersTest() {

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
        double amountTransaction1House = 40.00;

        TransactionDTOout transactionDTOout = new TransactionDTOout(denominationExpenses, typeTransaction1House, descriptionTransaction1House, amountTransaction1House, dateTransaction1House.toString(), houseWalletAccountDenomination, houseEdpAccountDenomination);

        List<TransactionDTOout> transactions = new ArrayList<>();
        transactions.add(transactionDTOout);

        //Act
        TransactionsDTO transactionsDTO = new TransactionsDTO();
        transactionsDTO.setTransactions(transactions);

        //Assert
        assertEquals(transactions, transactionsDTO.getTransactions());
    }

    @Test
    @DisplayName("TransactionsDTO - Test Equals || Same Object")
    void transactionsDTO_EqualsTest_SameObject() {

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
        double amountTransaction1House = 40.00;

        TransactionDTOout transactionDTOout = new TransactionDTOout(denominationExpenses, typeTransaction1House, descriptionTransaction1House, amountTransaction1House, dateTransaction1House.toString(), houseWalletAccountDenomination, houseEdpAccountDenomination);

        List<TransactionDTOout> transactions = new ArrayList<>();
        transactions.add(transactionDTOout);

        //Act
        TransactionsDTO transactionsDTO1 = new TransactionsDTO(transactions);
        TransactionsDTO transactionsDTO2 = new TransactionsDTO(transactions);

        //Assert
        assertTrue(transactionsDTO1.equals(transactionsDTO2));
        assertTrue(transactionsDTO1.equals(transactionsDTO1)); //the object is equal to itself
    }

    @Test
    @DisplayName("TransactionsDTO - Test Equals || Different Object")
    void transactionsDTO_EqualsTest_DifferentObject() {

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
        double amountTransaction1House = 40.00;

        TransactionDTOout transactionDTOout = new TransactionDTOout(denominationExpenses, typeTransaction1House, descriptionTransaction1House, amountTransaction1House, dateTransaction1House.toString(), houseWalletAccountDenomination, houseEdpAccountDenomination);

        List<TransactionDTOout> transactions1 = new ArrayList<>();
        transactions1.add(transactionDTOout);

        List<TransactionDTOout> transactions2 = new ArrayList<>();

        String bugKiller = "Bug Killer";

        //Act
        TransactionsDTO transactionsDTO1 = new TransactionsDTO(transactions1);
        TransactionsDTO transactionsDTO2 = new TransactionsDTO(transactions2);
        TransactionsDTO transactionsDTO3 = null;

        //Assert
        assertFalse(transactionsDTO1.equals(transactionsDTO2));
        assertFalse(transactionsDTO1.equals(bugKiller)); //not same instance
        assertFalse(transactions1.equals(transactionsDTO3)); //object is null
    }

    @Test
    @DisplayName("TransactionsDTO - Test Equals || Different Object - Different Information")
    void transactionsDTO_EqualsTest_DifferentObjectDifferentInformation() {

        //Arrange

        //Transaction1

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
        double amountTransaction1House = 40.00;

        //Transaction2

        //Category Electricity Expenses (Group House)
        String denominationExpenses2 = "Internet Expenses";

        //Account House Wallet (Group House)
        String houseWalletAccountDenomination2 = "House Funds";

        //Account EDP (Group House)
        String houseVodafoneAccountDenomination = "Vodafone";

        //Transaction 1 on Group House
        String typeTransaction2House = "Debit";
        String descriptionTransaction2House = "Vodafone bill from January/2020";
        LocalDate dateTransaction2House = LocalDate.of(2020, 02, 02);
        double amountTransaction2House = 60.00;

        TransactionDTOout transactionDTOout = new TransactionDTOout(denominationExpenses, typeTransaction1House, descriptionTransaction1House, amountTransaction1House, dateTransaction1House.toString(), houseWalletAccountDenomination, houseEdpAccountDenomination);
        TransactionDTOout transactionDTOout2 = new TransactionDTOout(denominationExpenses2, typeTransaction2House, descriptionTransaction2House, amountTransaction2House, dateTransaction2House.toString(), houseWalletAccountDenomination2, houseVodafoneAccountDenomination);

        List<TransactionDTOout> transactions1 = new ArrayList<>();
        transactions1.add(transactionDTOout);

        List<TransactionDTOout> transactions2 = new ArrayList<>();
        transactions2.add(transactionDTOout2);

        //Act
        TransactionsDTO transactionsDTO1 = new TransactionsDTO(transactions1);
        TransactionsDTO transactionsDTO2 = new TransactionsDTO(transactions2);

        //Assert
        assertFalse(transactionsDTO1.equals(transactionsDTO2));
        assertFalse(transactionsDTO1.equals(null));
    }

    @Test
    @DisplayName("TransactionsDTO - Test Hash Code")
    void transactionsDTO_HashCodeTest() {

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
        double amountTransaction1House = 40.00;

        TransactionDTOout transactionDTOout = new TransactionDTOout(denominationExpenses, typeTransaction1House, descriptionTransaction1House, amountTransaction1House, dateTransaction1House.toString(), houseWalletAccountDenomination, houseEdpAccountDenomination);

        List<TransactionDTOout> transactions = new ArrayList<>();
        transactions.add(transactionDTOout);

        //Act
        TransactionsDTO transactionsDTO = new TransactionsDTO(transactions);

        int transactionsDTOHashcode = transactionsDTO.hashCode();
        int expectedHashCode = -1524583737;

        //Assert
        assertEquals(expectedHashCode, transactionsDTOHashcode);
    }

}