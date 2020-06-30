package com.finance.project.dtos.dtos;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeleteGroupTransactionDTOTest {

    @Test
    @DisplayName("Test For DeleteGroupTransaction Constructor ")
    void DeleteGroupTransaction_Constructor() {

        // Arrange

        int transactionNumber = 1;
        String groupDenomination = "FridayFootball";
        String personGroupMemberEmail = "henrique@sapo.pt";

        // Act

        DeleteGroupTransactionDTO deleteGroupTransactionDTO = new DeleteGroupTransactionDTO(transactionNumber,groupDenomination,personGroupMemberEmail);

        //Assert

        assertEquals(transactionNumber,deleteGroupTransactionDTO.getTransactionNumber());
        assertEquals(groupDenomination,deleteGroupTransactionDTO.getGroupDenomination());
        assertEquals(personGroupMemberEmail,deleteGroupTransactionDTO.getPersonGroupMemberEmail());
    }

    //Equals - Same Object


    @Test
    @DisplayName("Test For DeleteGroupTransaction Equals - Same Object ")
    void DeleteGroupTransaction_EqualsSameObject() {

        // Arrange

        int transactionNumber = 1;
        String groupDenomination = "FridayFootball";
        String personGroupMemberEmail = "henrique@sapo.pt";

        // Act

        DeleteGroupTransactionDTO deleteGroupTransactionDTO = new DeleteGroupTransactionDTO(transactionNumber,groupDenomination,personGroupMemberEmail);

        //Assert

        assertTrue(deleteGroupTransactionDTO.equals(deleteGroupTransactionDTO));
    }

    @Test
    @DisplayName("Test For DeleteGroupTransaction Equals - Different Object - Same information")
    void DeleteGroupTransaction_EqualsDifferentObjectSameInformation() {

        // Arrange

        int transactionNumber = 1;
        String groupDenomination = "FridayFootball";
        String personGroupMemberEmail = "henrique@sapo.pt";

        // Arrange

        int transactionNumber1 = 1;
        String groupDenomination1 = "FridayFootball";
        String personGroupMemberEmail1 = "henrique@sapo.pt";

        // Act

        DeleteGroupTransactionDTO deleteGroupTransactionDTO1 = new DeleteGroupTransactionDTO(transactionNumber,groupDenomination,personGroupMemberEmail);
        DeleteGroupTransactionDTO deleteGroupTransactionDTO2 = new DeleteGroupTransactionDTO(transactionNumber1,groupDenomination1,personGroupMemberEmail1);

        //Assert

        assertTrue(deleteGroupTransactionDTO1.equals(deleteGroupTransactionDTO2));
    }

    @Test
    @DisplayName("Test For DeleteGroupTransaction Equals - Different Object - Different information")
    void DeleteGroupTransaction_EqualsDifferentObjectDifferentInformation() {

        // Arrange

        int transactionNumber1 = 1;
        String groupDenomination1 = "FridayFootball";
        String personGroupMemberEmail1 = "henrique@sapo.pt";

        int transactionNumber2 = 2;
        String groupDenomination2 = "ThursdayFootball";
        String personGroupMemberEmail2 = "francisco@sapo.pt";

        // Act

        DeleteGroupTransactionDTO deleteGroupTransactionDTO1 = new DeleteGroupTransactionDTO(transactionNumber1,groupDenomination1,personGroupMemberEmail1);
        DeleteGroupTransactionDTO deleteGroupTransactionDTO2 = new DeleteGroupTransactionDTO(transactionNumber2,groupDenomination2,personGroupMemberEmail2);

        boolean result = deleteGroupTransactionDTO1.equals(deleteGroupTransactionDTO2);

        //Assert

        assertEquals(false, result);
        assertFalse(deleteGroupTransactionDTO1.equals(deleteGroupTransactionDTO2));
        assertFalse(deleteGroupTransactionDTO1.equals(groupDenomination1)); // not instance of
        assertFalse(deleteGroupTransactionDTO1.equals(null)); //null
    }

    @Test
    @DisplayName("Test For DeleteGroupTransaction Equals - Different Object - Different number")
    void DeleteGroupTransaction_EqualsDifferentObjectDifferentNumber() {

        // Arrange

        int transactionNumber1 = 1;
        String groupDenomination1 = "FridayFootball";
        String personGroupMemberEmail1 = "henrique@sapo.pt";

        int transactionNumber2 = 2;

        // Act

        DeleteGroupTransactionDTO deleteGroupTransactionDTO1 = new DeleteGroupTransactionDTO(transactionNumber1,groupDenomination1,personGroupMemberEmail1);
        DeleteGroupTransactionDTO deleteGroupTransactionDTO2 = new DeleteGroupTransactionDTO(transactionNumber2,groupDenomination1,personGroupMemberEmail1);

        boolean result = deleteGroupTransactionDTO1.equals(deleteGroupTransactionDTO2);

        //Assert

        assertEquals(false, result);
        assertFalse(deleteGroupTransactionDTO1.equals(deleteGroupTransactionDTO2));
    }


    @Test
    @DisplayName("Test For DeleteGroupTransaction Equals - Different Object - Different denomination")
    void DeleteGroupTransaction_EqualsDifferentObjectDifferentDenomination() {

        // Arrange

        int transactionNumber1 = 1;
        String groupDenomination1 = "FridayFootball";
        String personGroupMemberEmail1 = "henrique@sapo.pt";

        String groupDenomination2 = "ThursdayFootball";


        // Act

        DeleteGroupTransactionDTO deleteGroupTransactionDTO1 = new DeleteGroupTransactionDTO(transactionNumber1,groupDenomination1,personGroupMemberEmail1);
        DeleteGroupTransactionDTO deleteGroupTransactionDTO2 = new DeleteGroupTransactionDTO(transactionNumber1,groupDenomination2,personGroupMemberEmail1);

        boolean result = deleteGroupTransactionDTO1.equals(deleteGroupTransactionDTO2);

        //Assert

        assertEquals(false, result);
        assertFalse(deleteGroupTransactionDTO1.equals(deleteGroupTransactionDTO2));
    }

    @Test
    @DisplayName("Test For DeleteGroupTransaction Equals - Different Object - Different groupMember")
    void DeleteGroupTransaction_EqualsDifferentObjectDifferentGroupMember() {

        // Arrange

        int transactionNumber1 = 1;
        String groupDenomination1 = "FridayFootball";
        String personGroupMemberEmail1 = "henrique@sapo.pt";

        String personGroupMemberEmail2 = "francisco@sapo.pt";

        // Act

        DeleteGroupTransactionDTO deleteGroupTransactionDTO1 = new DeleteGroupTransactionDTO(transactionNumber1,groupDenomination1,personGroupMemberEmail1);
        DeleteGroupTransactionDTO deleteGroupTransactionDTO2 = new DeleteGroupTransactionDTO(transactionNumber1,groupDenomination1,personGroupMemberEmail2);

        boolean result = deleteGroupTransactionDTO1.equals(deleteGroupTransactionDTO2);

        //Assert

        assertEquals(false, result);
        assertFalse(deleteGroupTransactionDTO1.equals(deleteGroupTransactionDTO2));
    }

    //HashCode

    @Test
    @DisplayName("Test For DeleteGroupTransaction HashCode")
    void DeleteGroupTransaction_HashCode() {

        // Arrange

        int transactionNumber = 1;
        String groupDenomination = "FridayFootball";
        String personGroupMemberEmail = "henrique@sapo.pt";

        // Act

        DeleteGroupTransactionDTO deleteGroupTransactionDTO = new DeleteGroupTransactionDTO(transactionNumber,groupDenomination,personGroupMemberEmail);
        int hashCode = deleteGroupTransactionDTO.hashCode();
        int expectedHashCode = -1212954386;

        //Assert

        assertEquals(expectedHashCode,hashCode);
    }




}