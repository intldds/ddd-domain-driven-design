package com.finance.project.dtos.dtos;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class NewPersonTransactionInfoDTOTest {

    @Test
    @DisplayName("Test For NewPersonTransactionInfoDTO()")
    void NewPersonTransactionInfoDTO() {

        // Arrange
        String denominationCategory = "HairStylist";
        String type = "debit";
        String description = "Pente0";
        double amount = 150.0;
        String denominationAccountDeb = "DebitAccountJon";
        String denominationAccountCred = "CreditAccountJon";
        String date = "2020-03-05";

        // Act
        NewPersonTransactionInfoDTO newPersonTransactionInfoDTO = new NewPersonTransactionInfoDTO(denominationCategory, type, description, amount, denominationAccountDeb, denominationAccountCred, date);

        // Assert
        assertEquals(denominationCategory, newPersonTransactionInfoDTO.getDenominationCategory());
        assertEquals(type, newPersonTransactionInfoDTO.getType());
        assertEquals(description, newPersonTransactionInfoDTO.getDescription());
        assertEquals(amount, newPersonTransactionInfoDTO.getAmount());
        assertEquals(denominationAccountDeb, newPersonTransactionInfoDTO.getDenominationAccountDeb());
        assertEquals(denominationAccountCred, newPersonTransactionInfoDTO.getDenominationAccountCred());
    }

    //Test for constructor without parameters

    @Test
    @DisplayName("Test For NewPersonTransactionInfoDTO() - Empty Constructor")
    void NewPersonTransactionInfoDTO_EmptyConstructor() {

        // Arrange

        // Act
        NewPersonTransactionInfoDTO newPersonTransactionInfoDTO = new NewPersonTransactionInfoDTO();

        // Assert
        assertEquals(null, newPersonTransactionInfoDTO.getDenominationCategory());
        assertEquals(null, newPersonTransactionInfoDTO.getType());
        assertEquals(null, newPersonTransactionInfoDTO.getDescription());
        assertEquals(0.0, newPersonTransactionInfoDTO.getAmount());
        assertEquals(null, newPersonTransactionInfoDTO.getDenominationAccountDeb());
        assertEquals(null, newPersonTransactionInfoDTO.getDenominationAccountCred());
    }

    //Test get's

    @Test
    @DisplayName("Test For getDenominationCategory()")
    void getDenominationCategory() {

        // Arrange
        String denominationCategory = "HairStylist";
        String type = "debit";
        String description = "Pente0";
        double amount = 150.0;
        String denominationAccountDeb = "DebitAccountJon";
        String denominationAccountCred = "CreditAccountJon";
        String date = "2020-03-05";

        // Act
        NewPersonTransactionInfoDTO newPersonTransactionInfoDTO = new NewPersonTransactionInfoDTO(denominationCategory, type, description, amount, denominationAccountDeb, denominationAccountCred, date);

        // Assert
        assertEquals(denominationCategory, newPersonTransactionInfoDTO.getDenominationCategory());

    }

    @Test
    @DisplayName("Test For getType()")
    void getType() {

        // Arrange
        String denominationCategory = "HairStylist";
        String type = "debit";
        String description = "Pente0";
        double amount = 150.0;
        String denominationAccountDeb = "DebitAccountJon";
        String denominationAccountCred = "CreditAccountJon";
        String date = "2020-03-05";

        // Act
        NewPersonTransactionInfoDTO newPersonTransactionInfoDTO = new NewPersonTransactionInfoDTO(denominationCategory, type, description, amount, denominationAccountDeb, denominationAccountCred, date);

        // Assert
        assertEquals(type, newPersonTransactionInfoDTO.getType());

    }

    @Test
    @DisplayName("Test For getDescription()")
    void getDescription() {

        // Arrange
        String denominationCategory = "HairStylist";
        String type = "debit";
        String description = "Pente0";
        double amount = 150.0;
        String denominationAccountDeb = "DebitAccountJon";
        String denominationAccountCred = "CreditAccountJon";
        String date = "2020-03-05";

        // Act
        NewPersonTransactionInfoDTO newPersonTransactionInfoDTO = new NewPersonTransactionInfoDTO(denominationCategory, type, description, amount, denominationAccountDeb, denominationAccountCred, date);

        // Assert
        assertEquals(description, newPersonTransactionInfoDTO.getDescription());

    }

    @Test
    @DisplayName("Test For getAmount()")
    void getAmount() {

        // Arrange
        String denominationCategory = "HairStylist";
        String type = "debit";
        String description = "Pente0";
        double amount = 150.0;
        String denominationAccountDeb = "DebitAccountJon";
        String denominationAccountCred = "CreditAccountJon";
        String date = "2020-03-05";

        // Act
        NewPersonTransactionInfoDTO newPersonTransactionInfoDTO = new NewPersonTransactionInfoDTO(denominationCategory, type, description, amount, denominationAccountDeb, denominationAccountCred, date);

        // Assert
        assertEquals(amount, newPersonTransactionInfoDTO.getAmount());

    }

    @Test
    @DisplayName("Test For getDenominationAccountDeb()")
    void getDenominationAccountDeb() {

        // Arrange
        String denominationCategory = "HairStylist";
        String type = "debit";
        String description = "Pente0";
        double amount = 150.0;
        String denominationAccountDeb = "DebitAccountJon";
        String denominationAccountCred = "CreditAccountJon";
        String date = "2020-03-05";

        // Act
        NewPersonTransactionInfoDTO newPersonTransactionInfoDTO = new NewPersonTransactionInfoDTO(denominationCategory, type, description, amount, denominationAccountDeb, denominationAccountCred, date);

        // Assert
        assertEquals(denominationAccountDeb, newPersonTransactionInfoDTO.getDenominationAccountDeb());

    }

    @Test
    @DisplayName("Test For getDenominationAccountCred()")
    void getDenominationAccountCred() {

        // Arrange
        String denominationCategory = "HairStylist";
        String type = "debit";
        String description = "Pente0";
        double amount = 150.0;
        String denominationAccountDeb = "DebitAccountJon";
        String denominationAccountCred = "CreditAccountJon";
        String date = "2020-03-05";

        // Act
        NewPersonTransactionInfoDTO newPersonTransactionInfoDTO = new NewPersonTransactionInfoDTO(denominationCategory, type, description, amount, denominationAccountDeb, denominationAccountCred, date);

        // Assert
        assertEquals(denominationAccountCred, newPersonTransactionInfoDTO.getDenominationAccountCred());

    }

    @Test
    @DisplayName("Test For getDate()")
    void getDate() {

        // Arrange
        String denominationCategory = "HairStylist";
        String type = "debit";
        String description = "Pente0";
        double amount = 150.0;
        String denominationAccountDeb = "DebitAccountJon";
        String denominationAccountCred = "CreditAccountJon";
        String date = "2020-03-05";

        // Act
        NewPersonTransactionInfoDTO newPersonTransactionInfoDTO = new NewPersonTransactionInfoDTO(denominationCategory, type, description, amount, denominationAccountDeb, denominationAccountCred, date);

        // Assert
        assertEquals(date, newPersonTransactionInfoDTO.getDate());

    }

    //Test set's

    @Test
    @DisplayName("Test For setDenominationCategory()")
    void setDenominationCategory() {

        // Arrange
        String denominationCategory = "HairStylist";
        String type = "debit";
        String description = "Pente0";
        double amount = 150.0;
        String denominationAccountDeb = "DebitAccountJon";
        String denominationAccountCred = "CreditAccountJon";
        String date = "2020-03-05";

        // InfoDTO
        NewPersonTransactionInfoDTO newPersonTransactionInfoDTO = new NewPersonTransactionInfoDTO(denominationCategory, type, description, amount, denominationAccountDeb, denominationAccountCred, date);

        // Act
        String newDenominationCategory = "Food";
        newPersonTransactionInfoDTO.setDenominationCategory(newDenominationCategory);

        // Assert
        assertEquals(newDenominationCategory, newPersonTransactionInfoDTO.getDenominationCategory());
        assertEquals(type, newPersonTransactionInfoDTO.getType());
        assertEquals(description, newPersonTransactionInfoDTO.getDescription());
        assertEquals(amount, newPersonTransactionInfoDTO.getAmount());
        assertEquals(denominationAccountDeb, newPersonTransactionInfoDTO.getDenominationAccountDeb());
        assertEquals(denominationAccountCred, newPersonTransactionInfoDTO.getDenominationAccountCred());
        assertEquals(date, newPersonTransactionInfoDTO.getDate());

    }

    @Test
    @DisplayName("Test For setType()")
    void setType() {

        // Arrange
        String denominationCategory = "HairStylist";
        String type = "debit";
        String description = "Pente0";
        double amount = 150.0;
        String denominationAccountDeb = "DebitAccountJon";
        String denominationAccountCred = "CreditAccountJon";
        String date = "2020-03-05";

        // InfoDTO
        NewPersonTransactionInfoDTO newPersonTransactionInfoDTO = new NewPersonTransactionInfoDTO(denominationCategory, type, description, amount, denominationAccountDeb, denominationAccountCred, date);

        // Act
        String newType = "credit";
        newPersonTransactionInfoDTO.setType(newType);

        // Assert
        assertEquals(denominationCategory, newPersonTransactionInfoDTO.getDenominationCategory());
        assertEquals(newType, newPersonTransactionInfoDTO.getType());
        assertEquals(description, newPersonTransactionInfoDTO.getDescription());
        assertEquals(amount, newPersonTransactionInfoDTO.getAmount());
        assertEquals(denominationAccountDeb, newPersonTransactionInfoDTO.getDenominationAccountDeb());
        assertEquals(denominationAccountCred, newPersonTransactionInfoDTO.getDenominationAccountCred());
        assertEquals(date, newPersonTransactionInfoDTO.getDate());

    }

    @Test
    @DisplayName("Test For setDescription()")
    void setDescription() {

        // Arrange
        String denominationCategory = "HairStylist";
        String type = "debit";
        String description = "Pente0";
        double amount = 150.0;
        String denominationAccountDeb = "DebitAccountJon";
        String denominationAccountCred = "CreditAccountJon";
        String date = "2020-03-05";

        // InfoDTO
        NewPersonTransactionInfoDTO newPersonTransactionInfoDTO = new NewPersonTransactionInfoDTO(denominationCategory, type, description, amount, denominationAccountDeb, denominationAccountCred, date);

        // Act
        String newDescription = "Pente5";
        newPersonTransactionInfoDTO.setDescription(newDescription);

        // Assert
        assertEquals(denominationCategory, newPersonTransactionInfoDTO.getDenominationCategory());
        assertEquals(type, newPersonTransactionInfoDTO.getType());
        assertEquals(newDescription, newPersonTransactionInfoDTO.getDescription());
        assertEquals(amount, newPersonTransactionInfoDTO.getAmount());
        assertEquals(denominationAccountDeb, newPersonTransactionInfoDTO.getDenominationAccountDeb());
        assertEquals(denominationAccountCred, newPersonTransactionInfoDTO.getDenominationAccountCred());
        assertEquals(date, newPersonTransactionInfoDTO.getDate());

    }

    @Test
    @DisplayName("Test For setAmount()")
    void setAmount() {

        // Arrange
        String denominationCategory = "HairStylist";
        String type = "debit";
        String description = "Pente0";
        double amount = 150.0;
        String denominationAccountDeb = "DebitAccountJon";
        String denominationAccountCred = "CreditAccountJon";
        String date = "2020-03-05";

        // InfoDTO
        NewPersonTransactionInfoDTO newPersonTransactionInfoDTO = new NewPersonTransactionInfoDTO(denominationCategory, type, description, amount, denominationAccountDeb, denominationAccountCred, date);

        // Act
        double newAmount = 120.0;
        newPersonTransactionInfoDTO.setAmount(newAmount);

        // Assert
        assertEquals(denominationCategory, newPersonTransactionInfoDTO.getDenominationCategory());
        assertEquals(type, newPersonTransactionInfoDTO.getType());
        assertEquals(description, newPersonTransactionInfoDTO.getDescription());
        assertEquals(newAmount, newPersonTransactionInfoDTO.getAmount());
        assertEquals(denominationAccountDeb, newPersonTransactionInfoDTO.getDenominationAccountDeb());
        assertEquals(denominationAccountCred, newPersonTransactionInfoDTO.getDenominationAccountCred());
        assertEquals(date, newPersonTransactionInfoDTO.getDate());

    }

    @Test
    @DisplayName("Test For setDenominationAccountDeb()")
    void setDenominationAccountDeb() {

        // Arrange
        String denominationCategory = "HairStylist";
        String type = "debit";
        String description = "Pente0";
        double amount = 150.0;
        String denominationAccountDeb = "DebitAccountJon";
        String denominationAccountCred = "CreditAccountJon";
        String date = "2020-03-05";

        // InfoDTO
        NewPersonTransactionInfoDTO newPersonTransactionInfoDTO = new NewPersonTransactionInfoDTO(denominationCategory, type, description, amount, denominationAccountDeb, denominationAccountCred, date);

        // Act
        String newDenominationAccountDeb = "DebitAccountPaul";
        newPersonTransactionInfoDTO.setDenominationAccountDeb(newDenominationAccountDeb);

        // Assert
        assertEquals(denominationCategory, newPersonTransactionInfoDTO.getDenominationCategory());
        assertEquals(type, newPersonTransactionInfoDTO.getType());
        assertEquals(description, newPersonTransactionInfoDTO.getDescription());
        assertEquals(amount, newPersonTransactionInfoDTO.getAmount());
        assertEquals(newDenominationAccountDeb, newPersonTransactionInfoDTO.getDenominationAccountDeb());
        assertEquals(denominationAccountCred, newPersonTransactionInfoDTO.getDenominationAccountCred());
        assertEquals(date, newPersonTransactionInfoDTO.getDate());

    }

    @Test
    @DisplayName("Test For setDenominationAccountCred()")
    void setDenominationAccountCred() {

        // Arrange
        String denominationCategory = "HairStylist";
        String type = "debit";
        String description = "Pente0";
        double amount = 150.0;
        String denominationAccountDeb = "DebitAccountJon";
        String denominationAccountCred = "CreditAccountJon";
        String date = "2020-03-05";

        // InfoDTO
        NewPersonTransactionInfoDTO newPersonTransactionInfoDTO = new NewPersonTransactionInfoDTO(denominationCategory, type, description, amount, denominationAccountDeb, denominationAccountCred, date);

        // Act
        String newDenominationAccountCred = "CreditAccountPaul";
        newPersonTransactionInfoDTO.setDenominationAccountCred(newDenominationAccountCred);

        // Assert
        assertEquals(denominationCategory, newPersonTransactionInfoDTO.getDenominationCategory());
        assertEquals(type, newPersonTransactionInfoDTO.getType());
        assertEquals(description, newPersonTransactionInfoDTO.getDescription());
        assertEquals(amount, newPersonTransactionInfoDTO.getAmount());
        assertEquals(denominationAccountDeb, newPersonTransactionInfoDTO.getDenominationAccountDeb());
        assertEquals(newDenominationAccountCred, newPersonTransactionInfoDTO.getDenominationAccountCred());
        assertEquals(date, newPersonTransactionInfoDTO.getDate());

    }

    @Test
    @DisplayName("Test For setDate()")
    void setDate() {

        // Arrange
        String denominationCategory = "HairStylist";
        String type = "debit";
        String description = "Pente0";
        double amount = 150.0;
        String denominationAccountDeb = "DebitAccountJon";
        String denominationAccountCred = "CreditAccountJon";
        String date = "2020-03-05";

        // InfoDTO
        NewPersonTransactionInfoDTO newPersonTransactionInfoDTO = new NewPersonTransactionInfoDTO(denominationCategory, type, description, amount, denominationAccountDeb, denominationAccountCred, date);

        // Act
        String newDate = "2020-03-07";
        newPersonTransactionInfoDTO.setDate(newDate);

        // Assert
        assertEquals(denominationCategory, newPersonTransactionInfoDTO.getDenominationCategory());
        assertEquals(type, newPersonTransactionInfoDTO.getType());
        assertEquals(description, newPersonTransactionInfoDTO.getDescription());
        assertEquals(amount, newPersonTransactionInfoDTO.getAmount());
        assertEquals(denominationAccountDeb, newPersonTransactionInfoDTO.getDenominationAccountDeb());
        assertEquals(denominationAccountCred, newPersonTransactionInfoDTO.getDenominationAccountCred());
        assertEquals(newDate, newPersonTransactionInfoDTO.getDate());

    }

    // EQUALS

    @Test
    @DisplayName("Test For equals() | Success")
    void equalsSuccess() {

        // Arrange

        //First DTO
        String firstDenominationCategory = "HairStylist";
        String firstType = "debit";
        String firstDescription = "Pente0";
        double firstAmount = 150.0;
        String firstDenominationAccountDeb = "DebitAccountJon";
        String firstDenominationAccountCred = "CreditAccountJon";
        String firstDate = "2020-03-05";

        NewPersonTransactionInfoDTO firstNewPersonTransactionInfoDTO = new NewPersonTransactionInfoDTO(firstDenominationCategory, firstType, firstDescription, firstAmount, firstDenominationAccountDeb, firstDenominationAccountCred, firstDate);

        //Second DTO
        String secondDenominationCategory = "HairStylist";
        String secondType = "debit";
        String secondDescription = "Pente0";
        double secondAmount = 150.0;
        String secondDenominationAccountDeb = "DebitAccountJon";
        String secondDenominationAccountCred = "CreditAccountJon";
        String secondDate = "2020-03-05";

        NewPersonTransactionInfoDTO secondNewPersonTransactionInfoDTO = new NewPersonTransactionInfoDTO(secondDenominationCategory, secondType, secondDescription, secondAmount, secondDenominationAccountDeb, secondDenominationAccountCred, secondDate);

        // Act
        boolean result = firstNewPersonTransactionInfoDTO.equals(secondNewPersonTransactionInfoDTO);

        // Assert
        assertEquals(true, result);
    }

    @Test
    @DisplayName("Test For equals() | Fail | Null")
    void equalsFailNull() {

        // Arrange
        String denominationCategory = "HairStylist";
        String type = "debit";
        String description = "Pente0";
        double amount = 150.0;
        String denominationAccountDeb = "DebitAccountJon";
        String denominationAccountCred = "CreditAccountJon";
        String date = "2020-03-05";

        NewPersonTransactionInfoDTO newPersonTransactionInfoDTO = new NewPersonTransactionInfoDTO(denominationCategory, type, description, amount, denominationAccountDeb, denominationAccountCred, date);

        // Act
        boolean result = newPersonTransactionInfoDTO.equals(null);

        // Assert
        assertEquals(false, result);
    }

    @Test
    @DisplayName("Test For equals() | Same object | Success")
    void equalsSuccess_SameObject() {

        // Arrange
        String denominationCategory = "HairStylist";
        String type = "debit";
        String description = "Pente0";
        double amount = 150.0;
        String denominationAccountDeb = "DebitAccountJon";
        String denominationAccountCred = "CreditAccountJon";
        String date = "2020-03-05";

        NewPersonTransactionInfoDTO newPersonTransactionInfoDTO = new NewPersonTransactionInfoDTO(denominationCategory, type, description, amount, denominationAccountDeb, denominationAccountCred, date);

        // Act
        boolean result = newPersonTransactionInfoDTO.equals(newPersonTransactionInfoDTO);

        // Assert
        assertEquals(true, result);
    }

    @Test
    @DisplayName("Test For equals() | Object from different Class| Fail")
    void equalsFailObjectFromDifferentClass() {

        // Arrange

        //First DTO
        String firstDenominationCategory = "HairStylist";
        String firstType = "debit";
        String firstDescription = "Pente0";
        double firstAmount = 150.0;
        String firstDenominationAccountDeb = "DebitAccountJon";
        String firstDenominationAccountCred = "CreditAccountJon";
        String date = "2020-03-05";

        NewPersonTransactionInfoDTO newPersonTransactionInfoDTO = new NewPersonTransactionInfoDTO(firstDenominationCategory, firstType, firstDescription, firstAmount, firstDenominationAccountDeb, firstDenominationAccountCred, date);

        //Object from different Class
        String string = "Bug killer";

        // Act
        boolean result = newPersonTransactionInfoDTO.equals(string);

        // Assert
        assertEquals(false, result);
    }

    @Test
    @DisplayName("Test For equals() | Different Category denomination | Fail")
    void equalsFailDifferentCategoryDenomination() {

        // Arrange

        //First DTO
        String firstDenominationCategory = "HairStylist";
        String firstType = "debit";
        String firstDescription = "Pente0";
        double firstAmount = 150.0;
        String firstDenominationAccountDeb = "DebitAccountJon";
        String firstDenominationAccountCred = "CreditAccountJon";
        String firstDate = "2020-03-05";

        NewPersonTransactionInfoDTO firstNewPersonTransactionInfoDTO = new NewPersonTransactionInfoDTO(firstDenominationCategory, firstType, firstDescription, firstAmount, firstDenominationAccountDeb, firstDenominationAccountCred, firstDate);

        //Second DTO
        String secondDenominationCategory = "Food";
        String secondType = "debit";
        String secondDescription = "Pente0";
        double secondAmount = 150.0;
        String secondDenominationAccountDeb = "DebitAccountJon";
        String secondDenominationAccountCred = "CreditAccountJon";
        String secondDate = "2020-03-05";

        NewPersonTransactionInfoDTO secondNewPersonTransactionInfoDTO = new NewPersonTransactionInfoDTO(secondDenominationCategory, secondType, secondDescription, secondAmount, secondDenominationAccountDeb, secondDenominationAccountCred, secondDate);

        // Act
        boolean result = firstNewPersonTransactionInfoDTO.equals(secondNewPersonTransactionInfoDTO);

        // Assert
        assertEquals(false, result);

    }

    @Test
    @DisplayName("Test For equals() | Different Type | Fail")
    void equalsFailDifferentType() {

        // Arrange

        //First DTO
        String firstDenominationCategory = "HairStylist";
        String firstType = "debit";
        String firstDescription = "Pente0";
        double firstAmount = 150.0;
        String firstDenominationAccountDeb = "DebitAccountJon";
        String firstDenominationAccountCred = "CreditAccountJon";
        String firstDate = "2020-03-05";

        NewPersonTransactionInfoDTO firstNewPersonTransactionInfoDTO = new NewPersonTransactionInfoDTO(firstDenominationCategory, firstType, firstDescription, firstAmount, firstDenominationAccountDeb, firstDenominationAccountCred, firstDate);

        //Second DTO
        String secondDenominationCategory = "HairStylist";
        String secondType = "credit";
        String secondDescription = "Pente0";
        double secondAmount = 150.0;
        String secondDenominationAccountDeb = "DebitAccountJon";
        String secondDenominationAccountCred = "CreditAccountJon";
        String secondDate = "2020-03-05";

        NewPersonTransactionInfoDTO secondNewPersonTransactionInfoDTO = new NewPersonTransactionInfoDTO(secondDenominationCategory, secondType, secondDescription, secondAmount, secondDenominationAccountDeb, secondDenominationAccountCred, secondDate);

        // Act
        boolean result = firstNewPersonTransactionInfoDTO.equals(secondNewPersonTransactionInfoDTO);

        // Assert
        assertEquals(false, result);

    }

    @Test
    @DisplayName("Test For equals() | Different Description | Fail")
    void equalsFailDifferentDescription() {

        // Arrange

        //First DTO
        String firstDenominationCategory = "HairStylist";
        String firstType = "debit";
        String firstDescription = "Pente0";
        double firstAmount = 150.0;
        String firstDenominationAccountDeb = "DebitAccountJon";
        String firstDenominationAccountCred = "CreditAccountJon";
        String firstDate = "2020-03-05";

        NewPersonTransactionInfoDTO firstNewPersonTransactionInfoDTO = new NewPersonTransactionInfoDTO(firstDenominationCategory, firstType, firstDescription, firstAmount, firstDenominationAccountDeb, firstDenominationAccountCred, firstDate);

        //Second DTO
        String secondDenominationCategory = "HairStylist";
        String secondType = "debit";
        String secondDescription = "Pente5";
        double secondAmount = 150.0;
        String secondDenominationAccountDeb = "DebitAccountJon";
        String secondDenominationAccountCred = "CreditAccountJon";
        String secondDate = "2020-03-05";

        NewPersonTransactionInfoDTO secondNewPersonTransactionInfoDTO = new NewPersonTransactionInfoDTO(secondDenominationCategory, secondType, secondDescription, secondAmount, secondDenominationAccountDeb, secondDenominationAccountCred, secondDate);

        // Act
        boolean result = firstNewPersonTransactionInfoDTO.equals(secondNewPersonTransactionInfoDTO);

        // Assert
        assertEquals(false, result);

    }

    @Test
    @DisplayName("Test For equals() | Different Amount | Fail")
    void equalsFailDifferentAmount() {

        // Arrange

        //First DTO
        String firstDenominationCategory = "HairStylist";
        String firstType = "debit";
        String firstDescription = "Pente0";
        double firstAmount = 150.0;
        String firstDenominationAccountDeb = "DebitAccountJon";
        String firstDenominationAccountCred = "CreditAccountJon";
        String firstDate = "2020-03-05";

        NewPersonTransactionInfoDTO firstNewPersonTransactionInfoDTO = new NewPersonTransactionInfoDTO(firstDenominationCategory, firstType, firstDescription, firstAmount, firstDenominationAccountDeb, firstDenominationAccountCred, firstDate);

        //Second DTO
        String secondDenominationCategory = "HairStylist";
        String secondType = "debit";
        String secondDescription = "Pente0";
        double secondAmount = 120.0;
        String secondDenominationAccountDeb = "DebitAccountJon";
        String secondDenominationAccountCred = "CreditAccountJon";
        String secondDate = "2020-03-05";

        NewPersonTransactionInfoDTO secondNewPersonTransactionInfoDTO = new NewPersonTransactionInfoDTO(secondDenominationCategory, secondType, secondDescription, secondAmount, secondDenominationAccountDeb, secondDenominationAccountCred, secondDate);

        // Act
        boolean result = firstNewPersonTransactionInfoDTO.equals(secondNewPersonTransactionInfoDTO);

        // Assert
        assertEquals(false, result);

    }

    @Test
    @DisplayName("Test For equals() | Different debit account denomination | Fail")
    void equalsFailDifferentAccountDebDenomination() {

        // Arrange

        //First DTO
        String firstDenominationCategory = "HairStylist";
        String firstType = "debit";
        String firstDescription = "Pente0";
        double firstAmount = 150.0;
        String firstDenominationAccountDeb = "DebitAccountJon";
        String firstDenominationAccountCred = "CreditAccountJon";
        String firstDate = "2020-03-05";

        NewPersonTransactionInfoDTO firstNewPersonTransactionInfoDTO = new NewPersonTransactionInfoDTO(firstDenominationCategory, firstType, firstDescription, firstAmount, firstDenominationAccountDeb, firstDenominationAccountCred, firstDate);

        //Second DTO
        String secondDenominationCategory = "HairStylist";
        String secondType = "debit";
        String secondDescription = "Pente0";
        double secondAmount = 150.0;
        String secondDenominationAccountDeb = "DebitAccountPaulo";
        String secondDenominationAccountCred = "CreditAccountJon";
        String secondDate = "2020-03-05";

        NewPersonTransactionInfoDTO secondNewPersonTransactionInfoDTO = new NewPersonTransactionInfoDTO(secondDenominationCategory, secondType, secondDescription, secondAmount, secondDenominationAccountDeb, secondDenominationAccountCred, secondDate);

        // Act
        boolean result = firstNewPersonTransactionInfoDTO.equals(secondNewPersonTransactionInfoDTO);

        // Assert
        assertEquals(false, result);

    }

    @Test
    @DisplayName("Test For equals() | Different credit account denomination | Fail")
    void equalsFailDifferentAccountCredDenomination() {

        // Arrange

        //First DTO
        String firstDenominationCategory = "HairStylist";
        String firstType = "debit";
        String firstDescription = "Pente0";
        double firstAmount = 150.0;
        String firstDenominationAccountDeb = "DebitAccountJon";
        String firstDenominationAccountCred = "CreditAccountJon";
        String firstDate = "2020-03-05";

        NewPersonTransactionInfoDTO firstNewPersonTransactionInfoDTO = new NewPersonTransactionInfoDTO(firstDenominationCategory, firstType, firstDescription, firstAmount, firstDenominationAccountDeb, firstDenominationAccountCred, firstDate);

        //Second DTO
        String secondDenominationCategory = "HairStylist";
        String secondType = "debit";
        String secondDescription = "Pente0";
        double secondAmount = 150.0;
        String secondDenominationAccountDeb = "DebitAccountJon";
        String secondDenominationAccountCred = "CreditAccountPaulo";
        String secondDate = "2020-03-05";

        NewPersonTransactionInfoDTO secondNewPersonTransactionInfoDTO = new NewPersonTransactionInfoDTO(secondDenominationCategory, secondType, secondDescription, secondAmount, secondDenominationAccountDeb, secondDenominationAccountCred, secondDate);

        // Act
        boolean result = firstNewPersonTransactionInfoDTO.equals(secondNewPersonTransactionInfoDTO);

        // Assert
        assertEquals(false, result);

    }

    @Test
    @DisplayName("Test For equals() | Different date | Fail")
    void equalsFailDifferentDate() {

        // Arrange

        //First DTO
        String firstDenominationCategory = "HairStylist";
        String firstType = "debit";
        String firstDescription = "Pente0";
        double firstAmount = 150.0;
        String firstDenominationAccountDeb = "DebitAccountJon";
        String firstDenominationAccountCred = "CreditAccountJon";
        String firstDate = "2020-03-05";

        NewPersonTransactionInfoDTO firstNewPersonTransactionInfoDTO = new NewPersonTransactionInfoDTO(firstDenominationCategory, firstType, firstDescription, firstAmount, firstDenominationAccountDeb, firstDenominationAccountCred, firstDate);

        //Second DTO
        String secondDenominationCategory = "HairStylist";
        String secondType = "debit";
        String secondDescription = "Pente0";
        double secondAmount = 150.0;
        String secondDenominationAccountDeb = "DebitAccountJon";
        String secondDenominationAccountCred = "CreditAccountJon";
        String secondDate = "2020-03-07";

        NewPersonTransactionInfoDTO secondNewPersonTransactionInfoDTO = new NewPersonTransactionInfoDTO(secondDenominationCategory, secondType, secondDescription, secondAmount, secondDenominationAccountDeb, secondDenominationAccountCred, secondDate);

        // Act
        boolean result = firstNewPersonTransactionInfoDTO.equals(secondNewPersonTransactionInfoDTO);

        // Assert
        assertEquals(false, result);

    }

    @Test
    @DisplayName("Test For US08_DTO() | Success")
    void hashCodeSuccess() {

        // Arrange
        String denominationCategory = "HairStylist";
        String type = "debit";
        String description = "Pente0";
        double amount = 150.0;
        String denominationAccountDeb = "DebitAccountJon";
        String denominationAccountCred = "CreditAccountJon";
        String date = "2020-03-05";

        //DTO
        NewPersonTransactionInfoDTO runners_CreatePersonTransactionDTO = new NewPersonTransactionInfoDTO(denominationCategory, type, description, amount, denominationAccountDeb, denominationAccountCred, date);


        // Act
        int runnersHash = runners_CreatePersonTransactionDTO.hashCode();
        int expectedHash = -1650483522;

        // Assert
        assertEquals(expectedHash, runnersHash);

    }

    @Test
    @DisplayName("Test For hashcode() | Different")
    void hashcodeDifferent() {

        // Arrange

        //First DTO
        String firstDenominationCategory = "HairStylist";
        String firstType = "debit";
        String firstDescription = "Pente0";
        double firstAmount = 150.0;
        String firstDenominationAccountDeb = "DebitAccountJon";
        String firstDenominationAccountCred = "CreditAccountJon";
        String firstDate = "2020-03-05";

        NewPersonTransactionInfoDTO firstNewPersonTransactionInfoDTO = new NewPersonTransactionInfoDTO(firstDenominationCategory, firstType, firstDescription, firstAmount, firstDenominationAccountDeb, firstDenominationAccountCred, firstDate);

        //Second DTO
        String secondDenominationCategory = "Food";
        String secondType = "debit";
        String secondDescription = "Pente0";
        double secondAmount = 150.0;
        String secondDenominationAccountDeb = "DebitAccountJon";
        String secondDenominationAccountCred = "CreditAccountJon";
        String secondDate = "2020-03-05";

        NewPersonTransactionInfoDTO secondNewPersonTransactionInfoDTO = new NewPersonTransactionInfoDTO(secondDenominationCategory, secondType, secondDescription, secondAmount, secondDenominationAccountDeb, secondDenominationAccountCred, secondDate);

        // Act
        int firstHashcode = firstNewPersonTransactionInfoDTO.hashCode();
        int secondHashcode = secondNewPersonTransactionInfoDTO.hashCode();

        // Assert
        assertNotEquals(firstHashcode, secondHashcode);

    }

}