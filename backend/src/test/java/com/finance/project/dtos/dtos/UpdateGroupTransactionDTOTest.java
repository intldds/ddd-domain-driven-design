package com.finance.project.dtos.dtos;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UpdateGroupTransactionDTOTest {

    @Test
    @DisplayName("UpdateGroupTransactionDTO_Constructor - Constructor")
    void updateGroupTransactionDTO_Constructor() {

        //Arrange

        //Transaction

        int transactionNumber1 = 23;
        String groupDenomination1 = "MondayFootball";
        String personGroupMemberEmail1 = "joana@gmail.com";
        String categoryDenomination1 = "Football";
        String accountToDebitName1 = "GroupAccount";
        String accountToCreditName1 = "London sports Hall";
        double transactionAmount1 = 50.00;
        String transactionType1 = "credit";
        String transactionDescription1 = "Expenses of monday football";

        //Act

        UpdateGroupTransactionDTO updateGroupTransactionDTO = new UpdateGroupTransactionDTO(transactionNumber1,groupDenomination1,personGroupMemberEmail1,categoryDenomination1,accountToDebitName1,accountToCreditName1,transactionAmount1,transactionType1,transactionDescription1);

        //Assert

        assertEquals(transactionNumber1,updateGroupTransactionDTO.getTransactionNumber());
        assertEquals(groupDenomination1,updateGroupTransactionDTO.getGroupDenomination());
        assertEquals(personGroupMemberEmail1,updateGroupTransactionDTO.getPersonGroupMemberEmail());
        assertEquals(categoryDenomination1,updateGroupTransactionDTO.getCategoryDenomination());
        assertEquals(accountToDebitName1,updateGroupTransactionDTO.getAccountToDebitName());
        assertEquals(accountToCreditName1,updateGroupTransactionDTO.getAccountToCreditName());
        assertEquals(transactionAmount1,updateGroupTransactionDTO.getTransactionAmount());
        assertEquals(transactionType1,updateGroupTransactionDTO.getTransactionType());
        assertEquals(transactionDescription1,updateGroupTransactionDTO.getTransactionDescription());
    }

    @Test
    @DisplayName("UpdateGroupTransactionDTO_Equals - Same object")
    void updateGroupTransactionDTO_EqualsSameObject() {

        //Arrange

        //Transaction

        int transactionNumber1 = 23;
        String groupDenomination1 = "MondayFootball";
        String personGroupMemberEmail1 = "joana@gmail.com";
        String categoryDenomination1 = "Football";
        String accountToDebitName1 = "GroupAccount";
        String accountToCreditName1 = "London sports Hall";
        double transactionAmount1 = 50.00;
        String transactionType1 = "credit";
        String transactionDescription1 = "Expenses of monday football";

        //Act

        UpdateGroupTransactionDTO updateGroupTransactionDTO = new UpdateGroupTransactionDTO(transactionNumber1,groupDenomination1,personGroupMemberEmail1,categoryDenomination1,accountToDebitName1,accountToCreditName1,transactionAmount1,transactionType1,transactionDescription1);

        //Assert

        assertTrue(updateGroupTransactionDTO.equals(updateGroupTransactionDTO));
    }

    @Test
    @DisplayName("UpdateGroupTransactionDTO_Equals - Different object - Same information")
    void updateGroupTransactionDTO_EqualsDifferentObjectSameInformation() {

        //Arrange

        //Transaction

        int transactionNumber1 = 23;
        String groupDenomination1 = "MondayFootball";
        String personGroupMemberEmail1 = "joana@gmail.com";
        String categoryDenomination1 = "Football";
        String accountToDebitName1 = "GroupAccount";
        String accountToCreditName1 = "London sports Hall";
        double transactionAmount1 = 50.00;
        String transactionType1 = "credit";
        String transactionDescription1 = "Expenses of monday football";

        //Act

        UpdateGroupTransactionDTO updateGroupTransactionDTO1 = new UpdateGroupTransactionDTO(transactionNumber1,groupDenomination1,personGroupMemberEmail1,categoryDenomination1,accountToDebitName1,accountToCreditName1,transactionAmount1,transactionType1,transactionDescription1);
        UpdateGroupTransactionDTO updateGroupTransactionDTO2 = new UpdateGroupTransactionDTO(transactionNumber1,groupDenomination1,personGroupMemberEmail1,categoryDenomination1,accountToDebitName1,accountToCreditName1,transactionAmount1,transactionType1,transactionDescription1);
        //Assert

        assertTrue(updateGroupTransactionDTO1.equals(updateGroupTransactionDTO2));
    }

    @Test
    @DisplayName("UpdateGroupTransactionDTO_Equals - Different object")
    void updateGroupTransactionDTO_EqualsDifferentObject() {

        //Arrange

        //Transaction1

        int transactionNumber1 = 23;
        String groupDenomination1 = "MondayFootball";
        String personGroupMemberEmail1 = "joana@gmail.com";
        String categoryDenomination1 = "Football";
        String accountToDebitName1 = "GroupAccount";
        String accountToCreditName1 = "London sports Hall";
        double transactionAmount1 = 50.00;
        String transactionType1 = "credit";
        String transactionDescription1 = "Expenses of monday football";

        //Transaction2

        int transactionNumber2 = 2;
        String groupDenomination2 = "MondayTennis";
        String personGroupMemberEmail2 = "joao@gmail.com";
        String categoryDenomination2 = "Tennis";
        String accountToDebitName2 = "PrivateAccount";
        String accountToCreditName2 = "London sports Hall - Tennis";
        double transactionAmount2 = 20.00;
        String transactionType2 = "credit";
        String transactionDescription2 = "Expenses of monday tennis";

        //Act

        UpdateGroupTransactionDTO updateGroupTransactionDTO1 = new UpdateGroupTransactionDTO(transactionNumber1,groupDenomination1,personGroupMemberEmail1,categoryDenomination1,accountToDebitName1,accountToCreditName1,transactionAmount1,transactionType1,transactionDescription1);
        UpdateGroupTransactionDTO updateGroupTransactionDTO2 = new UpdateGroupTransactionDTO(transactionNumber2,groupDenomination2,personGroupMemberEmail2,categoryDenomination2,accountToDebitName2,accountToCreditName2,transactionAmount2,transactionType2,transactionDescription2);


        boolean result = updateGroupTransactionDTO1.equals(updateGroupTransactionDTO2);

        //Assert

        assertEquals(false, result);
        assertFalse(updateGroupTransactionDTO1.equals(updateGroupTransactionDTO2));
        assertFalse(updateGroupTransactionDTO1.equals(transactionAmount1)); // Not instance of
    }

    @Test
    @DisplayName("UpdateGroupTransactionDTO_Equals - Different transactionNumber")
    void updateGroupTransactionDTO_EqualsDifferentTransactionNumber() {

        //Arrange

        //Transaction1

        int transactionNumber1 = 23;
        String groupDenomination1 = "MondayFootball";
        String personGroupMemberEmail1 = "joana@gmail.com";
        String categoryDenomination1 = "Football";
        String accountToDebitName1 = "GroupAccount";
        String accountToCreditName1 = "London sports Hall";
        double transactionAmount1 = 50.00;
        String transactionType1 = "credit";
        String transactionDescription1 = "Expenses of monday football";

        //Transaction2

        int transactionNumber2 = 2;


        //Act

        UpdateGroupTransactionDTO updateGroupTransactionDTO1 = new UpdateGroupTransactionDTO(transactionNumber1,groupDenomination1,personGroupMemberEmail1,categoryDenomination1,accountToDebitName1,accountToCreditName1,transactionAmount1,transactionType1,transactionDescription1);
        UpdateGroupTransactionDTO updateGroupTransactionDTO2 = new UpdateGroupTransactionDTO(transactionNumber2,groupDenomination1,personGroupMemberEmail1,categoryDenomination1,accountToDebitName1,accountToCreditName1,transactionAmount1,transactionType1,transactionDescription1);


        boolean result = updateGroupTransactionDTO1.equals(updateGroupTransactionDTO2);

        //Assert

        assertEquals(false, result);
        assertFalse(updateGroupTransactionDTO1.equals(updateGroupTransactionDTO2));
    }

    @Test
    @DisplayName("UpdateGroupTransactionDTO_Equals - Different groupDenomination")
    void updateGroupTransactionDTO_EqualsDifferentGroupDenomination() {

        //Arrange

        //Transaction1

        int transactionNumber1 = 23;
        String groupDenomination1 = "MondayFootball";
        String personGroupMemberEmail1 = "joana@gmail.com";
        String categoryDenomination1 = "Football";
        String accountToDebitName1 = "GroupAccount";
        String accountToCreditName1 = "London sports Hall";
        double transactionAmount1 = 50.00;
        String transactionType1 = "credit";
        String transactionDescription1 = "Expenses of monday football";

        //Transaction2

        String groupDenomination2 = "MondayTennis";


        //Act

        UpdateGroupTransactionDTO updateGroupTransactionDTO1 = new UpdateGroupTransactionDTO(transactionNumber1,groupDenomination1,personGroupMemberEmail1,categoryDenomination1,accountToDebitName1,accountToCreditName1,transactionAmount1,transactionType1,transactionDescription1);
        UpdateGroupTransactionDTO updateGroupTransactionDTO2 = new UpdateGroupTransactionDTO(transactionNumber1,groupDenomination2,personGroupMemberEmail1,categoryDenomination1,accountToDebitName1,accountToCreditName1,transactionAmount1,transactionType1,transactionDescription1);


        boolean result = updateGroupTransactionDTO1.equals(updateGroupTransactionDTO2);

        //Assert

        assertEquals(false, result);
        assertFalse(updateGroupTransactionDTO1.equals(updateGroupTransactionDTO2));
    }


    @Test
    @DisplayName("UpdateGroupTransactionDTO_Equals - Different personGroupMemberEmail1")
    void updateGroupTransactionDTO_EqualsDifferentpPersonGroupMemberEmail1() {

        //Arrange

        //Transaction1

        int transactionNumber1 = 23;
        String groupDenomination1 = "MondayFootball";
        String personGroupMemberEmail1 = "joana@gmail.com";
        String categoryDenomination1 = "Football";
        String accountToDebitName1 = "GroupAccount";
        String accountToCreditName1 = "London sports Hall";
        double transactionAmount1 = 50.00;
        String transactionType1 = "credit";
        String transactionDescription1 = "Expenses of monday football";

        //Transaction2

        String personGroupMemberEmail2 = "joao@gmail.com";

        //Act

        UpdateGroupTransactionDTO updateGroupTransactionDTO1 = new UpdateGroupTransactionDTO(transactionNumber1,groupDenomination1,personGroupMemberEmail1,categoryDenomination1,accountToDebitName1,accountToCreditName1,transactionAmount1,transactionType1,transactionDescription1);
        UpdateGroupTransactionDTO updateGroupTransactionDTO2 = new UpdateGroupTransactionDTO(transactionNumber1,groupDenomination1,personGroupMemberEmail2,categoryDenomination1,accountToDebitName1,accountToCreditName1,transactionAmount1,transactionType1,transactionDescription1);


        boolean result = updateGroupTransactionDTO1.equals(updateGroupTransactionDTO2);

        //Assert

        assertEquals(false, result);
        assertFalse(updateGroupTransactionDTO1.equals(updateGroupTransactionDTO2));
    }

    @Test
    @DisplayName("UpdateGroupTransactionDTO_Equals - Different categoryDenomination")
    void updateGroupTransactionDTO_EqualsDifferentCategoryDenomination() {

        //Arrange

        //Transaction1

        int transactionNumber1 = 23;
        String groupDenomination1 = "MondayFootball";
        String personGroupMemberEmail1 = "joana@gmail.com";
        String categoryDenomination1 = "Football";
        String accountToDebitName1 = "GroupAccount";
        String accountToCreditName1 = "London sports Hall";
        double transactionAmount1 = 50.00;
        String transactionType1 = "credit";
        String transactionDescription1 = "Expenses of monday football";

        //Transaction2

        String categoryDenomination2 = "Tennis";


        //Act

        UpdateGroupTransactionDTO updateGroupTransactionDTO1 = new UpdateGroupTransactionDTO(transactionNumber1,groupDenomination1,personGroupMemberEmail1,categoryDenomination1,accountToDebitName1,accountToCreditName1,transactionAmount1,transactionType1,transactionDescription1);
        UpdateGroupTransactionDTO updateGroupTransactionDTO2 = new UpdateGroupTransactionDTO(transactionNumber1,groupDenomination1,personGroupMemberEmail1,categoryDenomination2,accountToDebitName1,accountToCreditName1,transactionAmount1,transactionType1,transactionDescription1);


        boolean result = updateGroupTransactionDTO1.equals(updateGroupTransactionDTO2);

        //Assert

        assertEquals(false, result);
        assertFalse(updateGroupTransactionDTO1.equals(updateGroupTransactionDTO2));

    }

    @Test
    @DisplayName("UpdateGroupTransactionDTO_Equals - Different accountToDebitName")
    void updateGroupTransactionDTO_EqualsDifferentAccountToDebitName() {

        //Arrange

        //Transaction1

        int transactionNumber1 = 23;
        String groupDenomination1 = "MondayFootball";
        String personGroupMemberEmail1 = "joana@gmail.com";
        String categoryDenomination1 = "Football";
        String accountToDebitName1 = "GroupAccount";
        String accountToCreditName1 = "London sports Hall";
        double transactionAmount1 = 50.00;
        String transactionType1 = "credit";
        String transactionDescription1 = "Expenses of monday football";

        //Transaction2

        String accountToDebitName2 = "PrivateAccount";



        //Act

        UpdateGroupTransactionDTO updateGroupTransactionDTO1 = new UpdateGroupTransactionDTO(transactionNumber1,groupDenomination1,personGroupMemberEmail1,categoryDenomination1,accountToDebitName1,accountToCreditName1,transactionAmount1,transactionType1,transactionDescription1);
        UpdateGroupTransactionDTO updateGroupTransactionDTO2 = new UpdateGroupTransactionDTO(transactionNumber1,groupDenomination1,personGroupMemberEmail1,categoryDenomination1,accountToDebitName2,accountToCreditName1,transactionAmount1,transactionType1,transactionDescription1);


        boolean result = updateGroupTransactionDTO1.equals(updateGroupTransactionDTO2);

        //Assert

        assertEquals(false, result);
        assertFalse(updateGroupTransactionDTO1.equals(updateGroupTransactionDTO2));

    }

    @Test
    @DisplayName("UpdateGroupTransactionDTO_Equals - Different accountToCreditName")
    void updateGroupTransactionDTO_EqualsDifferentAccountToCreditName() {

        //Arrange

        //Transaction1

        int transactionNumber1 = 23;
        String groupDenomination1 = "MondayFootball";
        String personGroupMemberEmail1 = "joana@gmail.com";
        String categoryDenomination1 = "Football";
        String accountToDebitName1 = "GroupAccount";
        String accountToCreditName1 = "London sports Hall";
        double transactionAmount1 = 50.00;
        String transactionType1 = "credit";
        String transactionDescription1 = "Expenses of monday football";

        //Transaction2

        String accountToCreditName2 = "London sports Hall - Tennis";


        //Act

        UpdateGroupTransactionDTO updateGroupTransactionDTO1 = new UpdateGroupTransactionDTO(transactionNumber1,groupDenomination1,personGroupMemberEmail1,categoryDenomination1,accountToDebitName1,accountToCreditName1,transactionAmount1,transactionType1,transactionDescription1);
        UpdateGroupTransactionDTO updateGroupTransactionDTO2 = new UpdateGroupTransactionDTO(transactionNumber1,groupDenomination1,personGroupMemberEmail1,categoryDenomination1,accountToDebitName1,accountToCreditName2,transactionAmount1,transactionType1,transactionDescription1);


        boolean result = updateGroupTransactionDTO1.equals(updateGroupTransactionDTO2);

        //Assert

        assertEquals(false, result);
        assertFalse(updateGroupTransactionDTO1.equals(updateGroupTransactionDTO2));
    }

    @Test
    @DisplayName("UpdateGroupTransactionDTO_Equals - TransactionAmount")
    void updateGroupTransactionDTO_EqualsDifferentTransactionAmount() {

        //Arrange

        //Transaction1

        int transactionNumber1 = 23;
        String groupDenomination1 = "MondayFootball";
        String personGroupMemberEmail1 = "joana@gmail.com";
        String categoryDenomination1 = "Football";
        String accountToDebitName1 = "GroupAccount";
        String accountToCreditName1 = "London sports Hall";
        double transactionAmount1 = 50.00;
        String transactionType1 = "credit";
        String transactionDescription1 = "Expenses of monday football";

        //Transaction2

        double transactionAmount2 = 20.00;


        //Act

        UpdateGroupTransactionDTO updateGroupTransactionDTO1 = new UpdateGroupTransactionDTO(transactionNumber1,groupDenomination1,personGroupMemberEmail1,categoryDenomination1,accountToDebitName1,accountToCreditName1,transactionAmount1,transactionType1,transactionDescription1);
        UpdateGroupTransactionDTO updateGroupTransactionDTO2 = new UpdateGroupTransactionDTO(transactionNumber1,groupDenomination1,personGroupMemberEmail1,categoryDenomination1,accountToDebitName1,accountToCreditName1,transactionAmount2,transactionType1,transactionDescription1);


        boolean result = updateGroupTransactionDTO1.equals(updateGroupTransactionDTO2);

        //Assert

        assertEquals(false, result);
        assertFalse(updateGroupTransactionDTO1.equals(updateGroupTransactionDTO2));
    }

    @Test
    @DisplayName("UpdateGroupTransactionDTO_Equals - Different TransactionType")
    void updateGroupTransactionDTO_EqualsDifferentTransactionType() {

        //Arrange

        //Transaction1

        int transactionNumber1 = 23;
        String groupDenomination1 = "MondayFootball";
        String personGroupMemberEmail1 = "joana@gmail.com";
        String categoryDenomination1 = "Football";
        String accountToDebitName1 = "GroupAccount";
        String accountToCreditName1 = "London sports Hall";
        double transactionAmount1 = 50.00;
        String transactionType1 = "credit";
        String transactionDescription1 = "Expenses of monday football";

        //Transaction2
        String transactionType2 = "debit";


        //Act

        UpdateGroupTransactionDTO updateGroupTransactionDTO1 = new UpdateGroupTransactionDTO(transactionNumber1,groupDenomination1,personGroupMemberEmail1,categoryDenomination1,accountToDebitName1,accountToCreditName1,transactionAmount1,transactionType1,transactionDescription1);
        UpdateGroupTransactionDTO updateGroupTransactionDTO2 = new UpdateGroupTransactionDTO(transactionNumber1,groupDenomination1,personGroupMemberEmail1,categoryDenomination1,accountToDebitName1,accountToCreditName1,transactionAmount1,transactionType2,transactionDescription1);


        boolean result = updateGroupTransactionDTO1.equals(updateGroupTransactionDTO2);

        //Assert

        assertEquals(false, result);
        assertFalse(updateGroupTransactionDTO1.equals(updateGroupTransactionDTO2));
    }

    @Test
    @DisplayName("UpdateGroupTransactionDTO_Equals - Different transactionDescription")
    void updateGroupTransactionDTO_EqualsDifferentTransactionDescription() {

        //Arrange

        //Transaction1

        int transactionNumber1 = 23;
        String groupDenomination1 = "MondayFootball";
        String personGroupMemberEmail1 = "joana@gmail.com";
        String categoryDenomination1 = "Football";
        String accountToDebitName1 = "GroupAccount";
        String accountToCreditName1 = "London sports Hall";
        double transactionAmount1 = 50.00;
        String transactionType1 = "credit";
        String transactionDescription1 = "Expenses of monday football";

        //Transaction2

        String transactionDescription2 = "Expenses of monday tennis";

        //Act

        UpdateGroupTransactionDTO updateGroupTransactionDTO1 = new UpdateGroupTransactionDTO(transactionNumber1,groupDenomination1,personGroupMemberEmail1,categoryDenomination1,accountToDebitName1,accountToCreditName1,transactionAmount1,transactionType1,transactionDescription1);
        UpdateGroupTransactionDTO updateGroupTransactionDTO2 = new UpdateGroupTransactionDTO(transactionNumber1,groupDenomination1,personGroupMemberEmail1,categoryDenomination1,accountToDebitName1,accountToCreditName1,transactionAmount1,transactionType1,transactionDescription2);


        boolean result = updateGroupTransactionDTO1.equals(updateGroupTransactionDTO2);

        //Assert

        assertEquals(false, result);
        assertFalse(updateGroupTransactionDTO1.equals(updateGroupTransactionDTO2));
    }

    @Test
    @DisplayName("UpdateGroupTransactionDTO_Equals - Different object: Null")
    void updateGroupTransactionDTO_EqualsDifferentObjectNull() {

        //Arrange

        //Transaction1

        int transactionNumber1 = 23;
        String groupDenomination1 = "MondayFootball";
        String personGroupMemberEmail1 = "joana@gmail.com";
        String categoryDenomination1 = "Football";
        String accountToDebitName1 = "GroupAccount";
        String accountToCreditName1 = "London sports Hall";
        double transactionAmount1 = 50.00;
        String transactionType1 = "credit";
        String transactionDescription1 = "Expenses of monday football";

        //Act

        UpdateGroupTransactionDTO updateGroupTransactionDTO1 = new UpdateGroupTransactionDTO(transactionNumber1,groupDenomination1,personGroupMemberEmail1,categoryDenomination1,accountToDebitName1,accountToCreditName1,transactionAmount1,transactionType1,transactionDescription1);
        UpdateGroupTransactionDTO updateGroupTransactionDTO2 = null;

        boolean result = updateGroupTransactionDTO1.equals(updateGroupTransactionDTO2);

        //Assert

        assertEquals(false, result);
        assertFalse(updateGroupTransactionDTO1.equals(updateGroupTransactionDTO2));

    }

    @Test
    @DisplayName("UpdateGroupTransactionDTO_HashCode")
    void updateGroupTransactionDTO_HashCode() {

        //Arrange

        //Transaction

        int transactionNumber1 = 23;
        String groupDenomination1 = "MondayFootball";
        String personGroupMemberEmail1 = "joana@gmail.com";
        String categoryDenomination1 = "Football";
        String accountToDebitName1 = "GroupAccount";
        String accountToCreditName1 = "London sports Hall";
        double transactionAmount1 = 50.00;
        String transactionType1 = "credit";
        String transactionDescription1 = "Expenses of monday football";

        //Act

        UpdateGroupTransactionDTO updateGroupTransactionDTO = new UpdateGroupTransactionDTO(transactionNumber1,groupDenomination1,personGroupMemberEmail1,categoryDenomination1,accountToDebitName1,accountToCreditName1,transactionAmount1,transactionType1,transactionDescription1);

        //Assert

        int hashCode = updateGroupTransactionDTO.hashCode();
        int expectedHashCode = -943762652;

        assertEquals(expectedHashCode,hashCode);
    }

}