package com.finance.project.dtos.dtos;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NewGroupTransactionInfoDTOTest {

    //Test for constructor with parameters

    @Test
    @DisplayName("Test For NewGroupTransactionInfoDTO() - Constructor")
    void NewGroupCategoryInfoDTOConstructor() {

        // Arrange

        String categoryDenomination = "Salary";
        String type = "Credit";
        String description = "Salary from January";
        Double amount = 1500.00;
        String accountDebitDenomination = "Continental Mabor";
        String accountCreditDenomination = "Salaries";
        String date = "2020-06-18";

        // Act

        NewGroupTransactionInfoDTO newGroupTransactionInfoDTO = new NewGroupTransactionInfoDTO(categoryDenomination, type, description, amount, accountDebitDenomination, accountCreditDenomination, date);

        // Assert

        assertEquals(categoryDenomination, newGroupTransactionInfoDTO.getDenominationCategory());
        assertEquals(type, newGroupTransactionInfoDTO.getType());
        assertEquals(description, newGroupTransactionInfoDTO.getDescription());
        assertEquals(amount, newGroupTransactionInfoDTO.getAmount());
        assertEquals(accountDebitDenomination, newGroupTransactionInfoDTO.getDenominationAccountDeb());
        assertEquals(accountCreditDenomination, newGroupTransactionInfoDTO.getDenominationAccountCred());
        assertEquals(date, newGroupTransactionInfoDTO.getDate());
    }

    //Test for constructor without parameters

    @Test
    @DisplayName("Test For NewGroupTransactionInfoDTO() - Constructor without parameters")
    void NewGroupCategoryInfoDTOConstructorWithoutParameters() {

        // Arrange

        String categoryDenomination = "Salary";
        String type = "Credit";
        String description = "Salary from January";
        Double amount = 1500.00;
        String accountDebitDenomination = "Continental Mabor";
        String accountCreditDenomination = "Salaries";
        String date = "2020-06-18";

        // Act

        NewGroupTransactionInfoDTO newGroupTransactionInfoDTO = new NewGroupTransactionInfoDTO();
        newGroupTransactionInfoDTO.setDenominationCategory(categoryDenomination);
        newGroupTransactionInfoDTO.setType(type);
        newGroupTransactionInfoDTO.setDescription(description);
        newGroupTransactionInfoDTO.setAmount(amount);
        newGroupTransactionInfoDTO.setDenominationAccountDeb(accountDebitDenomination);
        newGroupTransactionInfoDTO.setDenominationAccountCred(accountCreditDenomination);
        newGroupTransactionInfoDTO.setDate(date);
        // Assert

        assertEquals(categoryDenomination, newGroupTransactionInfoDTO.getDenominationCategory());
        assertEquals(type, newGroupTransactionInfoDTO.getType());
        assertEquals(description, newGroupTransactionInfoDTO.getDescription());
        assertEquals(amount, newGroupTransactionInfoDTO.getAmount());
        assertEquals(accountDebitDenomination, newGroupTransactionInfoDTO.getDenominationAccountDeb());
        assertEquals(accountCreditDenomination, newGroupTransactionInfoDTO.getDenominationAccountCred());
        assertEquals(date, newGroupTransactionInfoDTO.getDate());
    }

    //Equals

    //Test for constructor with parameters

    @Test
    @DisplayName("Test For NewGroupTransactionInfoDTO() - Equals - Same Object")
    void NewGroupCategoryInfoDTOEqualsSameObject() {

        // Arrange

        String categoryDenomination = "Salary";
        String type = "Credit";
        String description = "Salary from January";
        Double amount = 1500.00;
        String accountDebitDenomination = "Continental Mabor";
        String accountCreditDenomination = "Salaries";
        String date = "2020-06-18";

        // Act

        NewGroupTransactionInfoDTO newGroupTransactionInfoDTO = new NewGroupTransactionInfoDTO(categoryDenomination, type, description, amount, accountDebitDenomination, accountCreditDenomination, date);

        // Assert

        assertTrue(newGroupTransactionInfoDTO.equals(newGroupTransactionInfoDTO));
    }

    @Test
    @DisplayName("Test For NewGroupTransactionInfoDTO() - Equals - Different Object - Same information")
    void NewGroupCategoryInfoDTOEqualsDifferentObjectSameInformation() {

        // Arrange

        String categoryDenomination = "Salary";
        String type = "Credit";
        String description = "Salary from January";
        Double amount = 1500.00;
        String accountDebitDenomination = "Continental Mabor";
        String accountCreditDenomination = "Salaries";
        String date = "2020-06-18";

        // Act

        NewGroupTransactionInfoDTO newGroupTransactionInfoDTO1 = new NewGroupTransactionInfoDTO(categoryDenomination, type, description, amount, accountDebitDenomination, accountCreditDenomination, date);
        NewGroupTransactionInfoDTO newGroupTransactionInfoDTO2 = new NewGroupTransactionInfoDTO(categoryDenomination, type, description, amount, accountDebitDenomination, accountCreditDenomination, date);

        // Assert

        assertTrue(newGroupTransactionInfoDTO1.equals(newGroupTransactionInfoDTO2));
    }

    @Test
    @DisplayName("Test For NewGroupTransactionInfoDTO() - Equals - Different Object - Null")
    void NewGroupCategoryInfoDTOEqualsDifferentObjectNull() {

        // Arrange

        String categoryDenomination = "Salary";
        String type = "Credit";
        String description = "Salary from January";
        Double amount = 1500.00;
        String accountDebitDenomination = "Continental Mabor";
        String accountCreditDenomination = "Salaries";
        String date = "2020-06-18";

        // Act

        NewGroupTransactionInfoDTO newGroupTransactionInfoDTO1 = new NewGroupTransactionInfoDTO(categoryDenomination, type, description, amount, accountDebitDenomination, accountCreditDenomination, date);
        NewGroupTransactionInfoDTO newGroupTransactionInfoDTO2 = null;

        boolean result = newGroupTransactionInfoDTO1.equals(newGroupTransactionInfoDTO2);

        // Assert

        assertFalse(newGroupTransactionInfoDTO1.equals(newGroupTransactionInfoDTO2));
    }

    @Test
    @DisplayName("Test For NewGroupTransactionInfoDTO() - Equals - Different Object - No Instance Of")
    void NewGroupCategoryInfoDTOEqualsDifferentObjectNoInstanceOf() {

        // Arrange

        String categoryDenomination = "Salary";
        String type = "Credit";
        String description = "Salary from January";
        Double amount = 1500.00;
        String accountDebitDenomination = "Continental Mabor";
        String accountCreditDenomination = "Salaries";
        String date = "2020-06-18";

        // Act

        NewGroupTransactionInfoDTO newGroupTransactionInfoDTO1 = new NewGroupTransactionInfoDTO(categoryDenomination, type, description, amount, accountDebitDenomination, accountCreditDenomination, date);


        boolean result = newGroupTransactionInfoDTO1.equals(type);

        // Assert

        assertFalse(newGroupTransactionInfoDTO1.equals(type));
    }


    @Test
    @DisplayName("Test For NewGroupTransactionInfoDTO() - Equals - Different Object - Different information")
    void NewGroupCategoryInfoDTOEqualsDifferentObjectDifferentInformation() {

        // Arrange

        String categoryDenomination = "Salary";
        String type = "Credit";
        String description = "Salary from January";
        Double amount = 1500.00;
        String accountDebitDenomination = "Continental Mabor";
        String accountCreditDenomination = "Salaries";
        String date = "2020-06-18";

        String categoryDenomination2 = "Mechanical";
        String type2 = "Credit";
        String description2 = "Car January";
        Double amount2 = 500.00;
        String accountDebitDenomination2 = "Car";
        String accountCreditDenomination2 = "Mechanical";
        String date2 = "2020-06-18";

        // Act

        NewGroupTransactionInfoDTO newGroupTransactionInfoDTO1 = new NewGroupTransactionInfoDTO(categoryDenomination, type, description, amount, accountDebitDenomination, accountCreditDenomination, date);
        NewGroupTransactionInfoDTO newGroupTransactionInfoDTO2 = new NewGroupTransactionInfoDTO(categoryDenomination2, type2, description2, amount2, accountDebitDenomination2, accountCreditDenomination2, date2);

        boolean result = newGroupTransactionInfoDTO1.equals(newGroupTransactionInfoDTO2);


        // Assert

        assertEquals(false, result);
        assertFalse(newGroupTransactionInfoDTO1.equals(newGroupTransactionInfoDTO2));
    }


    @Test
    @DisplayName("Test For NewGroupTransactionInfoDTO() - Equals - Different Object - Different category")
    void NewGroupCategoryInfoDTOEqualsDifferentObjectDifferentCategory() {

        // Arrange

        String categoryDenomination = "Salary";
        String type = "Credit";
        String description = "Salary from January";
        Double amount = 1500.00;
        String accountDebitDenomination = "Continental Mabor";
        String accountCreditDenomination = "Salaries";
        String date = "2020-06-18";

        String categoryDenomination2 = "Mechanical";


        // Act

        NewGroupTransactionInfoDTO newGroupTransactionInfoDTO1 = new NewGroupTransactionInfoDTO(categoryDenomination, type, description, amount, accountDebitDenomination, accountCreditDenomination, date);
        NewGroupTransactionInfoDTO newGroupTransactionInfoDTO2 = new NewGroupTransactionInfoDTO(categoryDenomination2, type, description, amount, accountDebitDenomination, accountCreditDenomination, date);

        boolean result = newGroupTransactionInfoDTO1.equals(newGroupTransactionInfoDTO2);


        // Assert

        assertEquals(false, result);
        assertFalse(newGroupTransactionInfoDTO1.equals(newGroupTransactionInfoDTO2));
    }

    @Test
    @DisplayName("Test For NewGroupTransactionInfoDTO() - Equals - Different Object - Different typt")
    void NewGroupCategoryInfoDTOEqualsDifferentObjectDifferentType() {

        // Arrange

        String categoryDenomination = "Salary";
        String type = "Credit";
        String description = "Salary from January";
        Double amount = 1500.00;
        String accountDebitDenomination = "Continental Mabor";
        String accountCreditDenomination = "Salaries";
        String date = "2020-06-18";

        String type2 = "Debit";

        // Act

        NewGroupTransactionInfoDTO newGroupTransactionInfoDTO1 = new NewGroupTransactionInfoDTO(categoryDenomination, type, description, amount, accountDebitDenomination, accountCreditDenomination, date);
        NewGroupTransactionInfoDTO newGroupTransactionInfoDTO2 = new NewGroupTransactionInfoDTO(categoryDenomination, type2, description, amount, accountDebitDenomination, accountCreditDenomination, date);

        boolean result = newGroupTransactionInfoDTO1.equals(newGroupTransactionInfoDTO2);


        // Assert

        assertEquals(false, result);
        assertFalse(newGroupTransactionInfoDTO1.equals(newGroupTransactionInfoDTO2));
    }

    @Test
    @DisplayName("Test For NewGroupTransactionInfoDTO() - Equals - Different Object - Different description")
    void NewGroupCategoryInfoDTOEqualsDifferentObjectDifferentDescription() {

        // Arrange

        String categoryDenomination = "Salary";
        String type = "Credit";
        String description = "Salary from January";
        Double amount = 1500.00;
        String accountDebitDenomination = "Continental Mabor";
        String accountCreditDenomination = "Salaries";
        String date = "2020-06-18";


        String description2 = "Car January";


        // Act

        NewGroupTransactionInfoDTO newGroupTransactionInfoDTO1 = new NewGroupTransactionInfoDTO(categoryDenomination, type, description, amount, accountDebitDenomination, accountCreditDenomination, date);
        NewGroupTransactionInfoDTO newGroupTransactionInfoDTO2 = new NewGroupTransactionInfoDTO(categoryDenomination, type, description2, amount, accountDebitDenomination, accountCreditDenomination, date);

        boolean result = newGroupTransactionInfoDTO1.equals(newGroupTransactionInfoDTO2);


        // Assert

        assertEquals(false, result);
        assertFalse(newGroupTransactionInfoDTO1.equals(newGroupTransactionInfoDTO2));
    }

    @Test
    @DisplayName("Test For NewGroupTransactionInfoDTO() - Equals - Different Object - Different amount")
    void NewGroupCategoryInfoDTOEqualsDifferentObjectDifferentAmount() {

        // Arrange

        String categoryDenomination = "Salary";
        String type = "Credit";
        String description = "Salary from January";
        Double amount = 1500.00;
        String accountDebitDenomination = "Continental Mabor";
        String accountCreditDenomination = "Salaries";
        String date = "2020-06-18";


        Double amount2 = 500.00;


        // Act

        NewGroupTransactionInfoDTO newGroupTransactionInfoDTO1 = new NewGroupTransactionInfoDTO(categoryDenomination, type, description, amount, accountDebitDenomination, accountCreditDenomination, date);
        NewGroupTransactionInfoDTO newGroupTransactionInfoDTO2 = new NewGroupTransactionInfoDTO(categoryDenomination, type, description, amount2, accountDebitDenomination, accountCreditDenomination, date);

        boolean result = newGroupTransactionInfoDTO1.equals(newGroupTransactionInfoDTO2);


        // Assert

        assertEquals(false, result);
        assertFalse(newGroupTransactionInfoDTO1.equals(newGroupTransactionInfoDTO2));
    }

    @Test
    @DisplayName("Test For NewGroupTransactionInfoDTO() - Equals - Different Object - Different debitAccount")
    void NewGroupCategoryInfoDTOEqualsDifferentObjectDifferentDebitAccount() {

        // Arrange

        String categoryDenomination = "Salary";
        String type = "Credit";
        String description = "Salary from January";
        Double amount = 1500.00;
        String accountDebitDenomination = "Continental Mabor";
        String accountCreditDenomination = "Salaries";
        String date = "2020-06-18";

        String accountDebitDenomination2 = "Car";


        // Act

        NewGroupTransactionInfoDTO newGroupTransactionInfoDTO1 = new NewGroupTransactionInfoDTO(categoryDenomination, type, description, amount, accountDebitDenomination, accountCreditDenomination, date);
        NewGroupTransactionInfoDTO newGroupTransactionInfoDTO2 = new NewGroupTransactionInfoDTO(categoryDenomination, type, description, amount, accountDebitDenomination2, accountCreditDenomination, date);

        boolean result = newGroupTransactionInfoDTO1.equals(newGroupTransactionInfoDTO2);


        // Assert

        assertEquals(false, result);
        assertFalse(newGroupTransactionInfoDTO1.equals(newGroupTransactionInfoDTO2));
    }

    @Test
    @DisplayName("Test For NewGroupTransactionInfoDTO() - Equals - Different Object - Different creditAccount")
    void NewGroupCategoryInfoDTOEqualsDifferentObjectDifferentCreditAccount() {

        // Arrange

        String categoryDenomination = "Salary";
        String type = "Credit";
        String description = "Salary from January";
        Double amount = 1500.00;
        String accountDebitDenomination = "Continental Mabor";
        String accountCreditDenomination = "Salaries";
        String date = "2020-06-18";

        String accountCreditDenomination2 = "Mechanical";

        // Act

        NewGroupTransactionInfoDTO newGroupTransactionInfoDTO1 = new NewGroupTransactionInfoDTO(categoryDenomination, type, description, amount, accountDebitDenomination, accountCreditDenomination, date);
        NewGroupTransactionInfoDTO newGroupTransactionInfoDTO2 = new NewGroupTransactionInfoDTO(categoryDenomination, type, description, amount, accountDebitDenomination, accountCreditDenomination2, date);

        boolean result = newGroupTransactionInfoDTO1.equals(newGroupTransactionInfoDTO2);


        // Assert

        assertEquals(false, result);
        assertFalse(newGroupTransactionInfoDTO1.equals(newGroupTransactionInfoDTO2));
    }

    //Test for hashCode

    @Test
    @DisplayName("Test For NewGroupTransactionInfoDTO() - hashCode")
    void NewGroupCategoryInfoDTOHashCode() {

        // Arrange

        String categoryDenomination = "Salary";
        String type = "Credit";
        String description = "Salary from January";
        Double amount = 1500.00;
        String accountDebitDenomination = "Continental Mabor";
        String accountCreditDenomination = "Salaries";
        String date = "2020-06-18";

        // Act

        NewGroupTransactionInfoDTO newGroupTransactionInfoDTO = new NewGroupTransactionInfoDTO(categoryDenomination, type, description, amount, accountDebitDenomination, accountCreditDenomination, date);

        int hashCode = newGroupTransactionInfoDTO.hashCode();
        int expectedHashCode = 786662463;

        // Assert

        assertEquals(expectedHashCode, hashCode);
    }

}