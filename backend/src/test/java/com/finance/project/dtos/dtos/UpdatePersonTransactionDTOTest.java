package com.finance.project.dtos.dtos;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UpdatePersonTransactionDTOTest {

    //Constructor

    @Test
    @DisplayName("UpdatePersonTransactionDTO_Constructor - Constructor")
    void updatePersonTransactionDTO_Constructor() {

        //Arrange

        int transactionNumber = 3;
        String email = "francisco@gmail.com";
        String denominationCategory = "Gasoline";
        String type = "debit";
        String description = "lead-free 95";
        double amount = 150.00;
        String denominationAccountDeb = "Bank Account";
        String denominationAccountCred = "Galp";

        //Act

        UpdatePersonTransactionDTO updatePersonTransactionDTO = new UpdatePersonTransactionDTO(transactionNumber, email, denominationCategory, type, description, amount, denominationAccountDeb, denominationAccountCred);

        //Assert

        assertEquals(transactionNumber, updatePersonTransactionDTO.getTransactionNumber());
        assertEquals(email, updatePersonTransactionDTO.getEmail());
        assertEquals(denominationCategory, updatePersonTransactionDTO.getDenominationCategory());
        assertEquals(type, updatePersonTransactionDTO.getType());
        assertEquals(description, updatePersonTransactionDTO.getDescription());
        assertEquals(amount, updatePersonTransactionDTO.getAmount());
        assertEquals(denominationAccountDeb, updatePersonTransactionDTO.getDenominationAccountDeb());
        assertEquals(denominationAccountCred, updatePersonTransactionDTO.getDenominationAccountCred());

    }

    //Equals

    @Test
    @DisplayName("UpdatePersonTransactionDTO_Equals - Same object")
    void updatePersonTransactionDTO_EqualsSameObject() {

        //Arrange

        int transactionNumber = 3;
        String email = "francisco@gmail.com";
        String denominationCategory = "Gasoline";
        String type = "debit";
        String description = "lead-free 95";
        double amount = 150.00;
        String denominationAccountDeb = "Bank Account";
        String denominationAccountCred = "Galp";

        //Act

        UpdatePersonTransactionDTO updatePersonTransactionDTO = new UpdatePersonTransactionDTO(transactionNumber, email, denominationCategory, type, description, amount, denominationAccountDeb, denominationAccountCred);

        //Assert

        assertTrue(updatePersonTransactionDTO.equals(updatePersonTransactionDTO));

    }

    @Test
    @DisplayName("UpdateGroupTransactionDTO_Equals - Different object - Same information")
    void updatePersonTransactionDTO_EqualsDifferentObjectSameInformation() {

        //Arrange

        int transactionNumber = 3;
        String email = "francisco@gmail.com";
        String denominationCategory = "Gasoline";
        String type = "debit";
        String description = "lead-free 95";
        double amount = 150.00;
        String denominationAccountDeb = "Bank Account";
        String denominationAccountCred = "Galp";

        //Act

        UpdatePersonTransactionDTO updatePersonTransactionDTO1 = new UpdatePersonTransactionDTO(transactionNumber, email, denominationCategory, type, description, amount, denominationAccountDeb, denominationAccountCred);
        UpdatePersonTransactionDTO updatePersonTransactionDTO2 = new UpdatePersonTransactionDTO(transactionNumber, email, denominationCategory, type, description, amount, denominationAccountDeb, denominationAccountCred);

        //Assert

        assertTrue(updatePersonTransactionDTO1.equals(updatePersonTransactionDTO2));

    }

    @Test
    @DisplayName("UpdatePersonTransactionDTO_Equals - Different object")
    void updatePersonTransactionDTO_EqualsDifferentObject() {

        //Arrange

        //Transaction1
        int transactionNumber1 = 3;
        String email1 = "francisco@gmail.com";
        String denominationCategory1 = "Gasoline";
        String type1 = "debit";
        String description1 = "lead-free 95";
        double amount1 = 150.00;
        String denominationAccountDeb1 = "Bank Account";
        String denominationAccountCred1 = "Galp";

        //Transaction2
        int transactionNumber2 = 4;
        String email2 = "paulo@gmail.com";
        String denominationCategory2 = "Food";
        String type2 = "credit";
        String description2 = "Rotten Snacks";
        double amount2 = 10.00;
        String denominationAccountDeb2 = "Kiosk";
        String denominationAccountCred2 = "Wallet";

        //Act

        UpdatePersonTransactionDTO updatePersonTransactionDTO1 = new UpdatePersonTransactionDTO(transactionNumber1, email1, denominationCategory1, type1, description1, amount1, denominationAccountDeb1, denominationAccountCred1);
        UpdatePersonTransactionDTO updatePersonTransactionDTO2 = new UpdatePersonTransactionDTO(transactionNumber2, email2, denominationCategory2, type2, description2, amount2, denominationAccountDeb2, denominationAccountCred2);

        boolean result = updatePersonTransactionDTO1.equals(updatePersonTransactionDTO2);

        //Assert

        assertEquals(false, result);
        assertFalse(updatePersonTransactionDTO1.equals(updatePersonTransactionDTO2));
        assertFalse(updatePersonTransactionDTO1.equals(amount1)); // Not instance of

    }

    @Test
    @DisplayName("UpdatePersonTransactionDTO_Equals - Different transactionNumber")
    void updatePersonTransactionDTO_EqualsDifferentTransactionNumber() {

        //Arrange

        //Transaction1
        int transactionNumber1 = 3;
        String email1 = "francisco@gmail.com";
        String denominationCategory1 = "Gasoline";
        String type1 = "debit";
        String description1 = "lead-free 95";
        double amount1 = 150.00;
        String denominationAccountDeb1 = "Bank Account";
        String denominationAccountCred1 = "Galp";

        //Transaction2
        int transactionNumber2 = 4;

        //Act

        UpdatePersonTransactionDTO updatePersonTransactionDTO1 = new UpdatePersonTransactionDTO(transactionNumber1, email1, denominationCategory1, type1, description1, amount1, denominationAccountDeb1, denominationAccountCred1);
        UpdatePersonTransactionDTO updatePersonTransactionDTO2 = new UpdatePersonTransactionDTO(transactionNumber2, email1, denominationCategory1, type1, description1, amount1, denominationAccountDeb1, denominationAccountCred1);

        boolean result = updatePersonTransactionDTO1.equals(updatePersonTransactionDTO2);

        //Assert

        assertEquals(false, result);
        assertFalse(updatePersonTransactionDTO1.equals(updatePersonTransactionDTO2));

    }

    @Test
    @DisplayName("UpdatePersonTransactionDTO_Equals - Different email")
    void updatePersonTransactionDTO_EqualsDifferentEmail() {

        //Arrange

        //Transaction1
        int transactionNumber1 = 3;
        String email1 = "francisco@gmail.com";
        String denominationCategory1 = "Gasoline";
        String type1 = "debit";
        String description1 = "lead-free 95";
        double amount1 = 150.00;
        String denominationAccountDeb1 = "Bank Account";
        String denominationAccountCred1 = "Galp";

        //Transaction2
        String email2 = "paulo@gmail.com";

        //Act

        UpdatePersonTransactionDTO updatePersonTransactionDTO1 = new UpdatePersonTransactionDTO(transactionNumber1, email1, denominationCategory1, type1, description1, amount1, denominationAccountDeb1, denominationAccountCred1);
        UpdatePersonTransactionDTO updatePersonTransactionDTO2 = new UpdatePersonTransactionDTO(transactionNumber1, email2, denominationCategory1, type1, description1, amount1, denominationAccountDeb1, denominationAccountCred1);

        boolean result = updatePersonTransactionDTO1.equals(updatePersonTransactionDTO2);

        //Assert

        assertEquals(false, result);
        assertFalse(updatePersonTransactionDTO1.equals(updatePersonTransactionDTO2));

    }

    @Test
    @DisplayName("UpdatePersonTransactionDTO_Equals - Different denominationCategory")
    void updatePersonTransactionDTO_EqualsDifferentDenominationCategory() {

        //Arrange

        //Transaction1
        int transactionNumber1 = 3;
        String email1 = "francisco@gmail.com";
        String denominationCategory1 = "Gasoline";
        String type1 = "debit";
        String description1 = "lead-free 95";
        double amount1 = 150.00;
        String denominationAccountDeb1 = "Bank Account";
        String denominationAccountCred1 = "Galp";

        //Transaction2
        String denominationCategory2 = "Food";

        //Act

        UpdatePersonTransactionDTO updatePersonTransactionDTO1 = new UpdatePersonTransactionDTO(transactionNumber1, email1, denominationCategory1, type1, description1, amount1, denominationAccountDeb1, denominationAccountCred1);
        UpdatePersonTransactionDTO updatePersonTransactionDTO2 = new UpdatePersonTransactionDTO(transactionNumber1, email1, denominationCategory2, type1, description1, amount1, denominationAccountDeb1, denominationAccountCred1);

        boolean result = updatePersonTransactionDTO1.equals(updatePersonTransactionDTO2);

        //Assert

        assertEquals(false, result);
        assertFalse(updatePersonTransactionDTO1.equals(updatePersonTransactionDTO2));

    }

    @Test
    @DisplayName("UpdatePersonTransactionDTO_Equals - Different type")
    void updatePersonTransactionDTO_EqualsDifferentType() {

        //Arrange

        //Transaction1
        int transactionNumber1 = 3;
        String email1 = "francisco@gmail.com";
        String denominationCategory1 = "Gasoline";
        String type1 = "debit";
        String description1 = "lead-free 95";
        double amount1 = 150.00;
        String denominationAccountDeb1 = "Bank Account";
        String denominationAccountCred1 = "Galp";

        //Transaction2
        String type2 = "credit";

        //Act

        UpdatePersonTransactionDTO updatePersonTransactionDTO1 = new UpdatePersonTransactionDTO(transactionNumber1, email1, denominationCategory1, type1, description1, amount1, denominationAccountDeb1, denominationAccountCred1);
        UpdatePersonTransactionDTO updatePersonTransactionDTO2 = new UpdatePersonTransactionDTO(transactionNumber1, email1, denominationCategory1, type2, description1, amount1, denominationAccountDeb1, denominationAccountCred1);

        boolean result = updatePersonTransactionDTO1.equals(updatePersonTransactionDTO2);

        //Assert

        assertEquals(false, result);
        assertFalse(updatePersonTransactionDTO1.equals(updatePersonTransactionDTO2));

    }

    @Test
    @DisplayName("UpdatePersonTransactionDTO_Equals - Different type")
    void updatePersonTransactionDTO_EqualsDifferentDescription() {

        //Arrange

        //Transaction1
        int transactionNumber1 = 3;
        String email1 = "francisco@gmail.com";
        String denominationCategory1 = "Gasoline";
        String type1 = "debit";
        String description1 = "lead-free 95";
        double amount1 = 150.00;
        String denominationAccountDeb1 = "Bank Account";
        String denominationAccountCred1 = "Galp";

        //Transaction2
        String description2 = "Rotten Snacks";

        //Act

        UpdatePersonTransactionDTO updatePersonTransactionDTO1 = new UpdatePersonTransactionDTO(transactionNumber1, email1, denominationCategory1, type1, description1, amount1, denominationAccountDeb1, denominationAccountCred1);
        UpdatePersonTransactionDTO updatePersonTransactionDTO2 = new UpdatePersonTransactionDTO(transactionNumber1, email1, denominationCategory1, type1, description2, amount1, denominationAccountDeb1, denominationAccountCred1);

        boolean result = updatePersonTransactionDTO1.equals(updatePersonTransactionDTO2);

        //Assert

        assertEquals(false, result);
        assertFalse(updatePersonTransactionDTO1.equals(updatePersonTransactionDTO2));

    }

    @Test
    @DisplayName("UpdatePersonTransactionDTO_Equals - Different amount")
    void updatePersonTransactionDTO_EqualsDifferentAmount() {

        //Arrange

        //Transaction1
        int transactionNumber1 = 3;
        String email1 = "francisco@gmail.com";
        String denominationCategory1 = "Gasoline";
        String type1 = "debit";
        String description1 = "lead-free 95";
        double amount1 = 150.00;
        String denominationAccountDeb1 = "Bank Account";
        String denominationAccountCred1 = "Galp";

        //Transaction2
        double amount2 = 10.00;

        //Act

        UpdatePersonTransactionDTO updatePersonTransactionDTO1 = new UpdatePersonTransactionDTO(transactionNumber1, email1, denominationCategory1, type1, description1, amount1, denominationAccountDeb1, denominationAccountCred1);
        UpdatePersonTransactionDTO updatePersonTransactionDTO2 = new UpdatePersonTransactionDTO(transactionNumber1, email1, denominationCategory1, type1, description1, amount2, denominationAccountDeb1, denominationAccountCred1);

        boolean result = updatePersonTransactionDTO1.equals(updatePersonTransactionDTO2);

        //Assert

        assertEquals(false, result);
        assertFalse(updatePersonTransactionDTO1.equals(updatePersonTransactionDTO2));

    }

    @Test
    @DisplayName("UpdatePersonTransactionDTO_Equals - Different denominationAccountDeb")
    void updatePersonTransactionDTO_EqualsDifferentDenominationAccountDeb() {

        //Arrange

        //Transaction1
        int transactionNumber1 = 3;
        String email1 = "francisco@gmail.com";
        String denominationCategory1 = "Gasoline";
        String type1 = "debit";
        String description1 = "lead-free 95";
        double amount1 = 150.00;
        String denominationAccountDeb1 = "Bank Account";
        String denominationAccountCred1 = "Galp";

        //Transaction2
        String denominationAccountDeb2 = "Kiosk";

        //Act

        UpdatePersonTransactionDTO updatePersonTransactionDTO1 = new UpdatePersonTransactionDTO(transactionNumber1, email1, denominationCategory1, type1, description1, amount1, denominationAccountDeb1, denominationAccountCred1);
        UpdatePersonTransactionDTO updatePersonTransactionDTO2 = new UpdatePersonTransactionDTO(transactionNumber1, email1, denominationCategory1, type1, description1, amount1, denominationAccountDeb2, denominationAccountCred1);

        boolean result = updatePersonTransactionDTO1.equals(updatePersonTransactionDTO2);

        //Assert

        assertEquals(false, result);
        assertFalse(updatePersonTransactionDTO1.equals(updatePersonTransactionDTO2));

    }

    @Test
    @DisplayName("UpdatePersonTransactionDTO_Equals - Different denominationAccountCred")
    void updatePersonTransactionDTO_EqualsDifferentDenominationAccountCred() {

        //Arrange

        //Transaction1
        int transactionNumber1 = 3;
        String email1 = "francisco@gmail.com";
        String denominationCategory1 = "Gasoline";
        String type1 = "debit";
        String description1 = "lead-free 95";
        double amount1 = 150.00;
        String denominationAccountDeb1 = "Bank Account";
        String denominationAccountCred1 = "Galp";

        //Transaction2
        String denominationAccountCred2 = "Wallet";

        //Act

        UpdatePersonTransactionDTO updatePersonTransactionDTO1 = new UpdatePersonTransactionDTO(transactionNumber1, email1, denominationCategory1, type1, description1, amount1, denominationAccountDeb1, denominationAccountCred1);
        UpdatePersonTransactionDTO updatePersonTransactionDTO2 = new UpdatePersonTransactionDTO(transactionNumber1, email1, denominationCategory1, type1, description1, amount1, denominationAccountDeb1, denominationAccountCred2);

        boolean result = updatePersonTransactionDTO1.equals(updatePersonTransactionDTO2);

        //Assert

        assertEquals(false, result);
        assertFalse(updatePersonTransactionDTO1.equals(updatePersonTransactionDTO2));

    }

    @Test
    @DisplayName("UpdatePersonTransactionDTO_Equals - Different object: Null")
    void updatePersonTransactionDTO_EqualsDifferentObjectNull() {

        //Arrange

        //Transaction1
        int transactionNumber = 3;
        String email = "francisco@gmail.com";
        String denominationCategory = "Gasoline";
        String type = "debit";
        String description = "lead-free 95";
        double amount = 150.00;
        String denominationAccountDeb = "Bank Account";
        String denominationAccountCred = "Galp";

        //Act

        UpdatePersonTransactionDTO updatePersonTransactionDTO1 = new UpdatePersonTransactionDTO(transactionNumber, email, denominationCategory, type, description, amount, denominationAccountDeb, denominationAccountCred);
        UpdatePersonTransactionDTO updatePersonTransactionDTO2 = null;

        boolean result = updatePersonTransactionDTO1.equals(updatePersonTransactionDTO2);

        //Assert

        assertEquals(false, result);
        assertFalse(updatePersonTransactionDTO1.equals(updatePersonTransactionDTO2));

    }

    //HashCode

    @Test
    @DisplayName("UpdatePersonTransactionDTO_HashCode")
    void updatePersonTransactionDTO_HashCode() {

        //Arrange

        //Transaction1
        int transactionNumber = 3;
        String email = "francisco@gmail.com";
        String denominationCategory = "Gasoline";
        String type = "debit";
        String description = "lead-free 95";
        double amount = 150.00;
        String denominationAccountDeb = "Bank Account";
        String denominationAccountCred = "Galp";

        //Act

        UpdatePersonTransactionDTO updatePersonTransactionDTO = new UpdatePersonTransactionDTO(transactionNumber, email, denominationCategory, type, description, amount, denominationAccountDeb, denominationAccountCred);

        int hashCode = updatePersonTransactionDTO.hashCode();
        int expectedHashCode = 1666272091;

        //Assert

        assertEquals(expectedHashCode, hashCode);

    }

}