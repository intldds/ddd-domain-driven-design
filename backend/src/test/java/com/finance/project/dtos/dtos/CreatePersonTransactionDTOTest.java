package com.finance.project.dtos.dtos;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class CreatePersonTransactionDTOTest {

    @Test
    @DisplayName("Test For US08_DTO()")
    void US08_DTO() {

        // Arrange

        String email = "paulo@gmail.com";
        String denominationCategory = "HairStylist";
        String type = "debit";
        String description = "Pente0";
        double amount = 150.0;
        String denominationAccountDeb = "DebitAccountJon";
        String denominationAccountCred = "CredditAccountJon";
        String date = "2020-03-05";

        // Act

        CreatePersonTransactionDTO createPersonTransactionDTO = new CreatePersonTransactionDTO(email, denominationCategory, type, description, amount, denominationAccountDeb, denominationAccountCred, date);

        // Assert

        assertEquals(email, createPersonTransactionDTO.getEmail());
        assertEquals(denominationCategory, createPersonTransactionDTO.getDenominationCategory());
        assertEquals(type, createPersonTransactionDTO.getType());
        assertEquals(description, createPersonTransactionDTO.getDescription());
        assertEquals(amount, createPersonTransactionDTO.getAmount());
        assertEquals(denominationAccountDeb, createPersonTransactionDTO.getDenominationAccountDeb());
        assertEquals(denominationAccountCred, createPersonTransactionDTO.getDenominationAccountCred());
        assertEquals(date, createPersonTransactionDTO.getDate());
    }

    @Test
    @DisplayName("Test For getEmail()")
    void getEmail() {

        // Arrange

        String email = "paulo@gmail.com";
        String denominationCategory = "HairStylist";
        String type = "debit";
        String description = "Pente0";
        double amount = 150.0;
        String denominationAccountDeb = "DebitAccountJon";
        String denominationAccountCred = "CredditAccountJon";
        String date = "2020-03-05";

        // Act

        CreatePersonTransactionDTO createPersonTransactionDTO = new CreatePersonTransactionDTO(email, denominationCategory, type, description, amount, denominationAccountDeb, denominationAccountCred, date);

        // Assert

        assertEquals(email, createPersonTransactionDTO.getEmail());
    }

    @Test
    @DisplayName("Test For getDenominationCategory()")
    void getDenominationCategory() {

        // Arrange

        String email = "paulo@gmail.com";
        String denominationCategory = "HairStylist";
        String type = "debit";
        String description = "Pente0";
        double amount = 150.0;
        String denominationAccountDeb = "DebitAccountJon";
        String denominationAccountCred = "CredditAccountJon";
        String date = "2020-03-05";

        // Act

        CreatePersonTransactionDTO createPersonTransactionDTO = new CreatePersonTransactionDTO(email, denominationCategory, type, description, amount, denominationAccountDeb, denominationAccountCred, date);

        // Assert

        assertEquals(denominationCategory, createPersonTransactionDTO.getDenominationCategory());
    }

    @Test
    @DisplayName("Test For getType()")
    void getType() {

        // Arrange

        String email = "paulo@gmail.com";
        String denominationCategory = "HairStylist";
        String type = "debit";
        String description = "Pente0";
        double amount = 150.0;
        String denominationAccountDeb = "DebitAccountJon";
        String denominationAccountCred = "CredditAccountJon";
        String date = "2020-03-05";

        // Act

        CreatePersonTransactionDTO createPersonTransactionDTO = new CreatePersonTransactionDTO(email, denominationCategory, type, description, amount, denominationAccountDeb, denominationAccountCred, date);

        // Assert

        assertEquals(type, createPersonTransactionDTO.getType());
    }

    @Test
    @DisplayName("Test For getDescription()")
    void getDescription() {

        // Arrange

        String email = "paulo@gmail.com";
        String denominationCategory = "HairStylist";
        String type = "debit";
        String description = "Pente0";
        double amount = 150.0;
        String denominationAccountDeb = "DebitAccountJon";
        String denominationAccountCred = "CredditAccountJon";
        String date = "2020-03-05";

        // Act

        CreatePersonTransactionDTO createPersonTransactionDTO = new CreatePersonTransactionDTO(email, denominationCategory, type, description, amount, denominationAccountDeb, denominationAccountCred, date);

        // Assert

        assertEquals(description, createPersonTransactionDTO.getDescription());
    }

    @Test
    @DisplayName("Test For getAmount()")
    void getAmount() {

        // Arrange

        String email = "paulo@gmail.com";
        String denominationCategory = "HairStylist";
        String type = "debit";
        String description = "Pente0";
        double amount = 150.0;
        String denominationAccountDeb = "DebitAccountJon";
        String denominationAccountCred = "CredditAccountJon";
        String date = "2020-03-05";

        // Act

        CreatePersonTransactionDTO createPersonTransactionDTO = new CreatePersonTransactionDTO(email, denominationCategory, type, description, amount, denominationAccountDeb, denominationAccountCred, date);

        // Assert

        assertEquals(amount, createPersonTransactionDTO.getAmount());
    }

    @Test
    @DisplayName("Test For getDenominationAccountDeb()")
    void getDenominationAccountDeb() {

        // Arrange

        String email = "paulo@gmail.com";
        String denominationCategory = "HairStylist";
        String type = "debit";
        String description = "Pente0";
        double amount = 150.0;
        String denominationAccountDeb = "DebitAccountJon";
        String denominationAccountCred = "CredditAccountJon";
        String date = "2020-03-05";

        // Act

        CreatePersonTransactionDTO createPersonTransactionDTO = new CreatePersonTransactionDTO(email, denominationCategory, type, description, amount, denominationAccountDeb, denominationAccountCred, date);

        // Assert

        assertEquals(denominationAccountDeb, createPersonTransactionDTO.getDenominationAccountDeb());
    }

    @Test
    @DisplayName("Test For getDenominationAccountCred()")
    void getDenominationAccountCred() {

        // Arrange

        String email = "paulo@gmail.com";
        String denominationCategory = "HairStylist";
        String type = "debit";
        String description = "Pente0";
        double amount = 150.0;
        String denominationAccountDeb = "DebitAccountJon";
        String denominationAccountCred = "CredditAccountJon";
        String date = "2020-03-05";

        // Act

        CreatePersonTransactionDTO createPersonTransactionDTO = new CreatePersonTransactionDTO(email, denominationCategory, type, description, amount, denominationAccountDeb, denominationAccountCred, date);

        // Assert

        assertEquals(denominationAccountCred, createPersonTransactionDTO.getDenominationAccountCred());
    }

    @Test
    @DisplayName("Test For getDate()")
    void getDate() {

        // Arrange

        String email = "paulo@gmail.com";
        String denominationCategory = "HairStylist";
        String type = "debit";
        String description = "Pente0";
        double amount = 150.0;
        String denominationAccountDeb = "DebitAccountJon";
        String denominationAccountCred = "CredditAccountJon";
        String date = "2020-03-05";

        // Act

        CreatePersonTransactionDTO createPersonTransactionDTO = new CreatePersonTransactionDTO(email, denominationCategory, type, description, amount, denominationAccountDeb, denominationAccountCred, date);

        // Assert

        assertEquals(date, createPersonTransactionDTO.getDate());

    }

    //Equals

    @Test
    @DisplayName("Test For equals() | Success")
    void equalsSuccess() {

        // Arrange

        //First DTO

        String firstEmail = "paulo@gmail.com";
        String firstDenominationCategory = "HairStylist";
        String firstType = "debit";
        String firstDescription = "Pente0";
        double firstAmount = 150.0;
        String firstDenominationAccountDeb = "DebitAccountJon";
        String firstDenominationAccountCred = "CreditAccountJon";
        String firstDate = "2020-03-05";

        CreatePersonTransactionDTO first_CreatePersonTransactionDTO = new CreatePersonTransactionDTO(firstEmail, firstDenominationCategory, firstType, firstDescription, firstAmount, firstDenominationAccountDeb, firstDenominationAccountCred, firstDate);

        //Second DTO

        String secondEmail = "paulo@gmail.com";
        String secondDenominationCategory = "HairStylist";
        String secondType = "debit";
        String secondDescription = "Pente0";
        double secondAmount = 150.0;
        String secondDenominationAccountDeb = "DebitAccountJon";
        String secondDenominationAccountCred = "CreditAccountJon";
        String secondDate = "2020-03-05";

        CreatePersonTransactionDTO second_CreatePersonTransactionDTO = new CreatePersonTransactionDTO(secondEmail, secondDenominationCategory, secondType, secondDescription, secondAmount, secondDenominationAccountDeb, secondDenominationAccountCred, secondDate);

        // Act

        boolean result = first_CreatePersonTransactionDTO.equals(second_CreatePersonTransactionDTO);

        // Assert

        assertEquals(true, result);
    }

    @Test
    @DisplayName("Test For equals() | Fail | Null")
    void equalsFailNull() {

        // Arrange

        String email = "paulo@gmail.com";
        String denominationCategory = "HairStylist";
        String type = "debit";
        String description = "Pente0";
        double amount = 150.0;
        String denominationAccountDeb = "DebitAccountJon";
        String denominationAccountCred = "CreditAccountJon";
        String date = "2020-03-05";

        CreatePersonTransactionDTO first_CreatePersonTransactionDTO = new CreatePersonTransactionDTO(email, denominationCategory, type, description, amount, denominationAccountDeb, denominationAccountCred, date);

        // Act

        boolean result = first_CreatePersonTransactionDTO.equals(null);

        // Assert

        assertEquals(false, result);
    }

    @Test
    @DisplayName("Test For equals() | Same object | Success")
    void equalsSuccess_SameObject() {

        // Arrange

        String email = "paulo@gmail.com";
        String denominationCategory = "HairStylist";
        String type = "debit";
        String description = "Pente0";
        double amount = 150.0;
        String denominationAccountDeb = "DebitAccountJon";
        String denominationAccountCred = "CreditAccountJon";
        String date = "2020-03-05";

        CreatePersonTransactionDTO createPersonTransactionDTO = new CreatePersonTransactionDTO(email, denominationCategory, type, description, amount, denominationAccountDeb, denominationAccountCred, date);

        // Act

        boolean result = createPersonTransactionDTO.equals(createPersonTransactionDTO);

        // Assert

        assertEquals(true, result);
    }

    @Test
    @DisplayName("Test For equals() | Object from different Class| Fail")
    void equalsFailObjectFromDifferentClass() {

        // Arrange

        //First DTO

        String firstEmail = "paulo@gmail.com";
        String firstDenominationCategory = "HairStylist";
        String firstType = "debit";
        String firstDescription = "Pente0";
        double firstAmount = 150.0;
        String firstDenominationAccountDeb = "DebitAccountJon";
        String firstDenominationAccountCred = "CreditAccountJon";
        String date = "2020-03-05";

        CreatePersonTransactionDTO first_CreatePersonTransactionDTO = new CreatePersonTransactionDTO(firstEmail, firstDenominationCategory, firstType, firstDescription, firstAmount, firstDenominationAccountDeb, firstDenominationAccountCred, date);

        String string = "Bug killer";

        // Act

        boolean result = first_CreatePersonTransactionDTO.equals(string);

        // Assert

        assertEquals(false, result);
    }

    @Test
    @DisplayName("Test For equals() | Different email | Fail")
    void equalsFailDifferentEmail() {

        // Arrange

        //First DTO

        String firstEmail = "paulo@gmail.com";
        String firstDenominationCategory = "HairStylist";
        String firstType = "debit";
        String firstDescription = "Pente0";
        double firstAmount = 150.0;
        String firstDenominationAccountDeb = "DebitAccountJon";
        String firstDenominationAccountCred = "CreditAccountJon";
        String firstDate = "2020-03-05";

        CreatePersonTransactionDTO first_CreatePersonTransactionDTO = new CreatePersonTransactionDTO(firstEmail, firstDenominationCategory, firstType, firstDescription, firstAmount, firstDenominationAccountDeb, firstDenominationAccountCred, firstDate);

        //Second DTO

        String secondEmail = "vegeta@gmail.com";
        String secondDenominationCategory = "HairStylist";
        String secondType = "debit";
        String secondDescription = "Pente0";
        double secondAmount = 150.0;
        String secondDenominationAccountDeb = "DebitAccountJon";
        String secondDenominationAccountCred = "CreditAccountJon";
        String secondDate = "2020-03-05";

        CreatePersonTransactionDTO second_CreatePersonTransactionDTO = new CreatePersonTransactionDTO(secondEmail, secondDenominationCategory, secondType, secondDescription, secondAmount, secondDenominationAccountDeb, secondDenominationAccountCred, secondDate);

        // Act

        boolean result = first_CreatePersonTransactionDTO.equals(second_CreatePersonTransactionDTO);

        // Assert

        assertEquals(false, result);
    }

    @Test
    @DisplayName("Test For equals() | Different Category denomination | Fail")
    void equalsFailDifferentCategoryDenomination() {

        // Arrange

        //First DTO

        String firstEmail = "paulo@gmail.com";
        String firstDenominationCategory = "HairStylist";
        String firstType = "debit";
        String firstDescription = "Pente0";
        double firstAmount = 150.0;
        String firstDenominationAccountDeb = "DebitAccountJon";
        String firstDenominationAccountCred = "CreditAccountJon";
        String firstDate = "2020-03-05";

        CreatePersonTransactionDTO first_CreatePersonTransactionDTO = new CreatePersonTransactionDTO(firstEmail, firstDenominationCategory, firstType, firstDescription, firstAmount, firstDenominationAccountDeb, firstDenominationAccountCred, firstDate);

        //Second DTO

        String secondEmail = "paulo@gmail.com";
        String secondDenominationCategory = "Food";
        String secondType = "debit";
        String secondDescription = "Pente0";
        double secondAmount = 150.0;
        String secondDenominationAccountDeb = "DebitAccountJon";
        String secondDenominationAccountCred = "CreditAccountJon";
        String secondDate = "2020-03-05";

        CreatePersonTransactionDTO second_CreatePersonTransactionDTO = new CreatePersonTransactionDTO(secondEmail, secondDenominationCategory, secondType, secondDescription, secondAmount, secondDenominationAccountDeb, secondDenominationAccountCred, secondDate);

        // Act

        boolean result = first_CreatePersonTransactionDTO.equals(second_CreatePersonTransactionDTO);

        // Assert

        assertEquals(false, result);
    }

    @Test
    @DisplayName("Test For equals() | Different Type | Fail")
    void equalsFailDifferentType() {

        // Arrange

        //First DTO

        String firstEmail = "paulo@gmail.com";
        String firstDenominationCategory = "HairStylist";
        String firstType = "debit";
        String firstDescription = "Pente0";
        double firstAmount = 150.0;
        String firstDenominationAccountDeb = "DebitAccountJon";
        String firstDenominationAccountCred = "CreditAccountJon";
        String firstDate = "2020-03-05";

        CreatePersonTransactionDTO first_CreatePersonTransactionDTO = new CreatePersonTransactionDTO(firstEmail, firstDenominationCategory, firstType, firstDescription, firstAmount, firstDenominationAccountDeb, firstDenominationAccountCred, firstDate);

        //Second DTO

        String secondEmail = "paulo@gmail.com";
        String secondDenominationCategory = "HairStylist";
        String secondType = "credit";
        String secondDescription = "Pente0";
        double secondAmount = 150.0;
        String secondDenominationAccountDeb = "DebitAccountJon";
        String secondDenominationAccountCred = "CreditAccountJon";
        String secondDate = "2020-03-05";

        CreatePersonTransactionDTO second_CreatePersonTransactionDTO = new CreatePersonTransactionDTO(secondEmail, secondDenominationCategory, secondType, secondDescription, secondAmount, secondDenominationAccountDeb, secondDenominationAccountCred, secondDate);

        // Act

        boolean result = first_CreatePersonTransactionDTO.equals(second_CreatePersonTransactionDTO);

        // Assert

        assertEquals(false, result);
    }

    @Test
    @DisplayName("Test For equals() | Different Description | Fail")
    void equalsFailDifferentDescription() {

        // Arrange

        //First DTO

        String firstEmail = "paulo@gmail.com";
        String firstDenominationCategory = "HairStylist";
        String firstType = "debit";
        String firstDescription = "Pente0";
        double firstAmount = 150.0;
        String firstDenominationAccountDeb = "DebitAccountJon";
        String firstDenominationAccountCred = "CreditAccountJon";
        String firstDate = "2020-03-05";

        CreatePersonTransactionDTO first_CreatePersonTransactionDTO = new CreatePersonTransactionDTO(firstEmail, firstDenominationCategory, firstType, firstDescription, firstAmount, firstDenominationAccountDeb, firstDenominationAccountCred, firstDate);

        //Second DTO

        String secondEmail = "paulo@gmail.com";
        String secondDenominationCategory = "HairStylist";
        String secondType = "debit";
        String secondDescription = "Pente5";
        double secondAmount = 150.0;
        String secondDenominationAccountDeb = "DebitAccountJon";
        String secondDenominationAccountCred = "CreditAccountJon";
        String secondDate = "2020-03-05";

        CreatePersonTransactionDTO second_CreatePersonTransactionDTO = new CreatePersonTransactionDTO(secondEmail, secondDenominationCategory, secondType, secondDescription, secondAmount, secondDenominationAccountDeb, secondDenominationAccountCred, secondDate);

        // Act

        boolean result = first_CreatePersonTransactionDTO.equals(second_CreatePersonTransactionDTO);

        // Assert

        assertEquals(false, result);
    }

    @Test
    @DisplayName("Test For equals() | Different Amount | Fail")
    void equalsFailDifferentAmount() {

        // Arrange

        //First DTO

        String firstEmail = "paulo@gmail.com";
        String firstDenominationCategory = "HairStylist";
        String firstType = "debit";
        String firstDescription = "Pente0";
        double firstAmount = 150.0;
        String firstDenominationAccountDeb = "DebitAccountJon";
        String firstDenominationAccountCred = "CreditAccountJon";
        String firstDate = "2020-03-05";

        CreatePersonTransactionDTO first_CreatePersonTransactionDTO = new CreatePersonTransactionDTO(firstEmail, firstDenominationCategory, firstType, firstDescription, firstAmount, firstDenominationAccountDeb, firstDenominationAccountCred, firstDate);

        //Second DTO

        String secondEmail = "paulo@gmail.com";
        String secondDenominationCategory = "HairStylist";
        String secondType = "debit";
        String secondDescription = "Pente0";
        double secondAmount = 120.0;
        String secondDenominationAccountDeb = "DebitAccountJon";
        String secondDenominationAccountCred = "CreditAccountJon";
        String secondDate = "2020-03-05";

        CreatePersonTransactionDTO second_CreatePersonTransactionDTO = new CreatePersonTransactionDTO(secondEmail, secondDenominationCategory, secondType, secondDescription, secondAmount, secondDenominationAccountDeb, secondDenominationAccountCred, secondDate);

        // Act

        boolean result = first_CreatePersonTransactionDTO.equals(second_CreatePersonTransactionDTO);

        // Assert

        assertEquals(false, result);
    }

    @Test
    @DisplayName("Test For equals() | Different debit account denomination | Fail")
    void equalsFailDifferentAccountDebDenomination() {

        // Arrange

        //First DTO

        String firstEmail = "paulo@gmail.com";
        String firstDenominationCategory = "HairStylist";
        String firstType = "debit";
        String firstDescription = "Pente0";
        double firstAmount = 150.0;
        String firstDenominationAccountDeb = "DebitAccountJon";
        String firstDenominationAccountCred = "CreditAccountJon";
        String firstDate = "2020-03-05";

        CreatePersonTransactionDTO first_CreatePersonTransactionDTO = new CreatePersonTransactionDTO(firstEmail, firstDenominationCategory, firstType, firstDescription, firstAmount, firstDenominationAccountDeb, firstDenominationAccountCred, firstDate);

        //Second DTO

        String secondEmail = "paulo@gmail.com";
        String secondDenominationCategory = "HairStylist";
        String secondType = "debit";
        String secondDescription = "Pente0";
        double secondAmount = 150.0;
        String secondDenominationAccountDeb = "DebitAccountPaulo";
        String secondDenominationAccountCred = "CreditAccountJon";
        String secondDate = "2020-03-05";

        CreatePersonTransactionDTO second_CreatePersonTransactionDTO = new CreatePersonTransactionDTO(secondEmail, secondDenominationCategory, secondType, secondDescription, secondAmount, secondDenominationAccountDeb, secondDenominationAccountCred, secondDate);

        // Act

        boolean result = first_CreatePersonTransactionDTO.equals(second_CreatePersonTransactionDTO);

        // Assert

        assertEquals(false, result);
    }

    @Test
    @DisplayName("Test For equals() | Different credit account denomination | Fail")
    void equalsFailDifferentAccountCredDenomination() {

        // Arrange

        //First DTO

        String firstEmail = "paulo@gmail.com";
        String firstDenominationCategory = "HairStylist";
        String firstType = "debit";
        String firstDescription = "Pente0";
        double firstAmount = 150.0;
        String firstDenominationAccountDeb = "DebitAccountJon";
        String firstDenominationAccountCred = "CreditAccountJon";
        String firstDate = "2020-03-05";

        CreatePersonTransactionDTO first_CreatePersonTransactionDTO = new CreatePersonTransactionDTO(firstEmail, firstDenominationCategory, firstType, firstDescription, firstAmount, firstDenominationAccountDeb, firstDenominationAccountCred, firstDate);

        //Second DTO

        String secondEmail = "paulo@gmail.com";
        String secondDenominationCategory = "HairStylist";
        String secondType = "debit";
        String secondDescription = "Pente0";
        double secondAmount = 150.0;
        String secondDenominationAccountDeb = "DebitAccountJon";
        String secondDenominationAccountCred = "CreditAccountPaulo";
        String secondDate = "2020-03-05";

        CreatePersonTransactionDTO second_CreatePersonTransactionDTO = new CreatePersonTransactionDTO(secondEmail, secondDenominationCategory, secondType, secondDescription, secondAmount, secondDenominationAccountDeb, secondDenominationAccountCred, secondDate);

        // Act

        boolean result = first_CreatePersonTransactionDTO.equals(second_CreatePersonTransactionDTO);

        // Assert

        assertEquals(false, result);
    }

    @Test
    @DisplayName("Test For equals() | Different date | Fail")
    void equalsFailDifferentDate() {

        // Arrange

        //First DTO

        String firstEmail = "paulo@gmail.com";
        String firstDenominationCategory = "HairStylist";
        String firstType = "debit";
        String firstDescription = "Pente0";
        double firstAmount = 150.0;
        String firstDenominationAccountDeb = "DebitAccountJon";
        String firstDenominationAccountCred = "CreditAccountJon";
        String firstDate = "2020-03-05";

        CreatePersonTransactionDTO first_CreatePersonTransactionDTO = new CreatePersonTransactionDTO(firstEmail, firstDenominationCategory, firstType, firstDescription, firstAmount, firstDenominationAccountDeb, firstDenominationAccountCred, firstDate);

        //Second DTO

        String secondEmail = "paulo@gmail.com";
        String secondDenominationCategory = "HairStylist";
        String secondType = "debit";
        String secondDescription = "Pente0";
        double secondAmount = 150.0;
        String secondDenominationAccountDeb = "DebitAccountJon";
        String secondDenominationAccountCred = "CreditAccountJon";
        String secondDate = "2020-03-07";

        CreatePersonTransactionDTO second_CreatePersonTransactionDTO = new CreatePersonTransactionDTO(secondEmail, secondDenominationCategory, secondType, secondDescription, secondAmount, secondDenominationAccountDeb, secondDenominationAccountCred, secondDate);

        // Act

        boolean result = first_CreatePersonTransactionDTO.equals(second_CreatePersonTransactionDTO);

        // Assert

        assertEquals(false, result);
    }

    @Test
    @DisplayName("Test For hashCode | Success")
    void hashCodeSuccess() {

        // Arrange

        String email = "paulo@gmail.com";
        String denominationCategory = "HairStylist";
        String type = "debit";
        String description = "Pente0";
        double amount = 150.0;
        String denominationAccountDeb = "DebitAccountJon";
        String denominationAccountCred = "CreditAccountJon";
        String date = "2020-03-05";

        //DTO

        CreatePersonTransactionDTO createPersonTransactionDTO = new CreatePersonTransactionDTO(email, denominationCategory, type, description, amount, denominationAccountDeb, denominationAccountCred, date);


        // Act

        int runnersHash = createPersonTransactionDTO.hashCode();
        int expectedHash = -622149752;

        // Assert

        assertEquals(expectedHash, runnersHash);
    }

    @Test
    @DisplayName("Test For hashcode() | Different")
    void hashcodeDifferent() {

        // Arrange

        //First DTO
        String firstEmail = "paulo@gmail.com";
        String firstDenominationCategory = "HairStylist";
        String firstType = "debit";
        String firstDescription = "Pente0";
        double firstAmount = 150.0;
        String firstDenominationAccountDeb = "DebitAccountJon";
        String firstDenominationAccountCred = "CreditAccountJon";
        String firstDate = "2020-03-05";

        CreatePersonTransactionDTO firstCreatePersonTransactionDTO = new CreatePersonTransactionDTO(firstEmail, firstDenominationCategory, firstType, firstDescription, firstAmount, firstDenominationAccountDeb, firstDenominationAccountCred, firstDate);

        //Second DTO
        String secondEmail = "paulo@gmail.com";
        String secondDenominationCategory = "Food";
        String secondType = "debit";
        String secondDescription = "Pente0";
        double secondAmount = 150.0;
        String secondDenominationAccountDeb = "DebitAccountJon";
        String secondDenominationAccountCred = "CreditAccountJon";
        String secondDate = "2020-03-05";

        CreatePersonTransactionDTO secondCreatePersonTransactionDTO = new CreatePersonTransactionDTO(secondEmail, secondDenominationCategory, secondType, secondDescription, secondAmount, secondDenominationAccountDeb, secondDenominationAccountCred, secondDate);

        // Act
        int firstHashcode = firstCreatePersonTransactionDTO.hashCode();
        int secondHashcode = secondCreatePersonTransactionDTO.hashCode();

        // Assert
        assertNotEquals(firstHashcode, secondHashcode);

    }

}