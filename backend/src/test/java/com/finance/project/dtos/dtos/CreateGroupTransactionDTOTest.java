package com.finance.project.dtos.dtos;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CreateGroupTransactionDTOTest {

    @Test
    @DisplayName("Test DTO for creating group transaction - Happy path")
    public void createGroupTransactionDTO_HappyPath() {
        //Arrange
        String groupDenomination = "School friends";
        String personGroupMemberEmail = "maria@gmail.com";
        String categoryDenomination = "Gatherings";
        String accountToDebitName = "School friends funds";
        String accountToCreditName = "Restaurant La vita è bella";
        double transactionAmount = 250.00;
        String transactionType = "Debit";
        String transactionDescription = "Dinner party";
        String transactionDate = "2020-06-18";


        //Act
        CreateGroupTransactionDTO createGroupTransactionDTO = new CreateGroupTransactionDTO(groupDenomination, personGroupMemberEmail, categoryDenomination, accountToDebitName, accountToCreditName, transactionAmount, transactionType, transactionDescription, transactionDate);

        //Assert
        assertEquals(groupDenomination, createGroupTransactionDTO.getGroupDenomination());
        assertEquals(personGroupMemberEmail, createGroupTransactionDTO.getPersonGroupMemberEmail());
        assertEquals(categoryDenomination, createGroupTransactionDTO.getCategoryDenomination());
        assertEquals(accountToDebitName, createGroupTransactionDTO.getAccountToDebitName());
        assertEquals(accountToCreditName, createGroupTransactionDTO.getAccountToCreditName());
        assertEquals(transactionAmount, createGroupTransactionDTO.getTransactionAmount());
        assertEquals(transactionType, createGroupTransactionDTO.getTransactionType());
        assertEquals(transactionDescription, createGroupTransactionDTO.getTransactionDescription());
        assertEquals(transactionDate, createGroupTransactionDTO.getDate());
    }

    @Test
    @DisplayName("Test DTO getters - getGroupDenomination()")
    public void getGroupDenomination() {
        //Arrange
        String groupDenomination = "School friends";
        String personGroupMemberEmail = "maria@gmail.com";
        String categoryDenomination = "Gatherings";
        String accountToDebitName = "School friends funds";
        String accountToCreditName = "Restaurant La vita è bella";
        double transactionAmount = 250.00;
        String transactionType = "Debit";
        String transactionDescription = "Dinner party";
        String transactionDate = "2020-06-18";


        //Act
        CreateGroupTransactionDTO createGroupTransactionDTO = new CreateGroupTransactionDTO(groupDenomination, personGroupMemberEmail, categoryDenomination, accountToDebitName, accountToCreditName, transactionAmount, transactionType, transactionDescription, transactionDate);

        //Assert
        assertEquals(groupDenomination, createGroupTransactionDTO.getGroupDenomination());
    }

    @Test
    @DisplayName("Test DTO getters - getPersonGroupMemberEmail()")
    public void getPersonGroupMemberEmail() {
        //Arrange
        String groupDenomination = "School friends";
        String personGroupMemberEmail = "maria@gmail.com";
        String categoryDenomination = "Gatherings";
        String accountToDebitName = "School friends funds";
        String accountToCreditName = "Restaurant La vita è bella";
        double transactionAmount = 250.00;
        String transactionType = "Debit";
        String transactionDescription = "Dinner party";
        String transactionDate = "2020-06-18";

        //Act
        CreateGroupTransactionDTO createGroupTransactionDTO = new CreateGroupTransactionDTO(groupDenomination, personGroupMemberEmail, categoryDenomination, accountToDebitName, accountToCreditName, transactionAmount, transactionType, transactionDescription, transactionDate);

        //Assert
        assertEquals(personGroupMemberEmail, createGroupTransactionDTO.getPersonGroupMemberEmail());
    }

    @Test
    @DisplayName("Test DTO getters - getCategoryDenomination()")
    public void getCategoryDenomination() {
        //Arrange
        String groupDenomination = "School friends";
        String personGroupMemberEmail = "maria@gmail.com";
        String categoryDenomination = "Gatherings";
        String accountToDebitName = "School friends funds";
        String accountToCreditName = "Restaurant La vita è bella";
        double transactionAmount = 250.00;
        String transactionType = "Debit";
        String transactionDescription = "Dinner party";
        String transactionDate = "2020-06-18";

        //Act
        CreateGroupTransactionDTO createGroupTransactionDTO = new CreateGroupTransactionDTO(groupDenomination, personGroupMemberEmail, categoryDenomination, accountToDebitName, accountToCreditName, transactionAmount, transactionType, transactionDescription, transactionDate);

        //Assert
        assertEquals(categoryDenomination, createGroupTransactionDTO.getCategoryDenomination());
    }

    @Test
    @DisplayName("Test DTO getters - getAccountToDebitName()")
    public void getAccountToDebitName() {
        //Arrange
        String groupDenomination = "School friends";
        String personGroupMemberEmail = "maria@gmail.com";
        String categoryDenomination = "Gatherings";
        String accountToDebitName = "School friends funds";
        String accountToCreditName = "Restaurant La vita è bella";
        double transactionAmount = 250.00;
        String transactionType = "Debit";
        String transactionDescription = "Dinner party";
        String transactionDate = "2020-06-18";

        //Act
        CreateGroupTransactionDTO createGroupTransactionDTO = new CreateGroupTransactionDTO(groupDenomination, personGroupMemberEmail, categoryDenomination, accountToDebitName, accountToCreditName, transactionAmount, transactionType, transactionDescription, transactionDate);

        //Assert
        assertEquals(accountToDebitName, createGroupTransactionDTO.getAccountToDebitName());
    }

    @Test
    @DisplayName("Test DTO getters - getAccountToCreditName()")
    public void getAccountToCreditName() {
        //Arrange
        String groupDenomination = "School friends";
        String personGroupMemberEmail = "maria@gmail.com";
        String categoryDenomination = "Gatherings";
        String accountToDebitName = "School friends funds";
        String accountToCreditName = "Restaurant La vita è bella";
        double transactionAmount = 250.00;
        String transactionType = "Debit";
        String transactionDescription = "Dinner party";
        String transactionDate = "2020-06-18";

        //Act
        CreateGroupTransactionDTO createGroupTransactionDTO = new CreateGroupTransactionDTO(groupDenomination, personGroupMemberEmail, categoryDenomination, accountToDebitName, accountToCreditName, transactionAmount, transactionType, transactionDescription, transactionDate);

        //Assert
        assertEquals(accountToCreditName, createGroupTransactionDTO.getAccountToCreditName());
    }

    @Test
    @DisplayName("Test DTO getters - getTransactionAmount()")
    public void getTransactionAmount() {
        //Arrange
        String groupDenomination = "School friends";
        String personGroupMemberEmail = "maria@gmail.com";
        String categoryDenomination = "Gatherings";
        String accountToDebitName = "School friends funds";
        String accountToCreditName = "Restaurant La vita è bella";
        double transactionAmount = 250.00;
        String transactionType = "Debit";
        String transactionDescription = "Dinner party";
        String transactionDate = "2020-06-18";

        //Act
        CreateGroupTransactionDTO createGroupTransactionDTO = new CreateGroupTransactionDTO(groupDenomination, personGroupMemberEmail, categoryDenomination, accountToDebitName, accountToCreditName, transactionAmount, transactionType, transactionDescription, transactionDate);

        //Assert
        assertEquals(transactionAmount, createGroupTransactionDTO.getTransactionAmount());
    }

    @Test
    @DisplayName("Test DTO getters - getTransactionType()")
    public void getTransactionType() {
        //Arrange
        String groupDenomination = "School friends";
        String personGroupMemberEmail = "maria@gmail.com";
        String categoryDenomination = "Gatherings";
        String accountToDebitName = "School friends funds";
        String accountToCreditName = "Restaurant La vita è bella";
        double transactionAmount = 250.00;
        String transactionType = "Debit";
        String transactionDescription = "Dinner party";
        String transactionDate = "2020-06-18";

        //Act
        CreateGroupTransactionDTO createGroupTransactionDTO = new CreateGroupTransactionDTO(groupDenomination, personGroupMemberEmail, categoryDenomination, accountToDebitName, accountToCreditName, transactionAmount, transactionType, transactionDescription, transactionDate);

        //Assert
        assertEquals(transactionType, createGroupTransactionDTO.getTransactionType());
    }

    @Test
    @DisplayName("Test DTO getters - getTransactionDescription()")
    public void getTransactionDescription() {
        //Arrange
        String groupDenomination = "School friends";
        String personGroupMemberEmail = "maria@gmail.com";
        String categoryDenomination = "Gatherings";
        String accountToDebitName = "School friends funds";
        String accountToCreditName = "Restaurant La vita è bella";
        double transactionAmount = 250.00;
        String transactionType = "Debit";
        String transactionDescription = "Dinner party";
        String transactionDate = "2020-06-18";

        //Act
        CreateGroupTransactionDTO createGroupTransactionDTO = new CreateGroupTransactionDTO(groupDenomination, personGroupMemberEmail, categoryDenomination, accountToDebitName, accountToCreditName, transactionAmount, transactionType, transactionDescription, transactionDate);

        //Assert
        assertEquals(transactionDescription, createGroupTransactionDTO.getTransactionDescription());
    }

    @Test
    @DisplayName("Test for equals() and hasCode() - Happy path")
    public void testEqualsAndHashCode_HappyPath() {
        //Arrange
        String groupDenomination1 = "School friends";
        String personGroupMemberEmail1 = "maria@gmail.com";
        String categoryDenomination1 = "Gatherings";
        String accountToDebitName1 = "School friends funds";
        String accountToCreditName1 = "Restaurant La vita è bella";
        double transactionAmount1 = 250.00;
        String transactionType1 = "Debit";
        String transactionDescription1 = "Dinner party";
        String transactionDate1 = "2020-06-18";
        CreateGroupTransactionDTO createGroupTransactionDTO1 = new CreateGroupTransactionDTO(groupDenomination1, personGroupMemberEmail1, categoryDenomination1, accountToDebitName1, accountToCreditName1, transactionAmount1, transactionType1, transactionDescription1, transactionDate1);

        String groupDenomination2 = "School friends";
        String personGroupMemberEmail2 = "maria@gmail.com";
        String categoryDenomination2 = "Gatherings";
        String accountToDebitName2 = "School friends funds";
        String accountToCreditName2 = "Restaurant La vita è bella";
        double transactionAmount2 = 250.00;
        String transactionType2 = "Debit";
        String transactionDescription2 = "Dinner party";
        String transactionDate2 = "2020-06-18";
        CreateGroupTransactionDTO createGroupTransactionDTO2 = new CreateGroupTransactionDTO(groupDenomination2, personGroupMemberEmail2, categoryDenomination2, accountToDebitName2, accountToCreditName2, transactionAmount2, transactionType2, transactionDescription2, transactionDate2);

        //Act
        boolean resultEquals = createGroupTransactionDTO1.equals(createGroupTransactionDTO2);
        boolean resultHashCode = (createGroupTransactionDTO1.hashCode() == createGroupTransactionDTO2.hashCode());

        //Assert
        assertEquals(true, resultEquals);
        assertEquals(true, resultHashCode);
    }

    @Test
    @DisplayName("Test for equals() and hasCode() - Same object")
    public void testEqualsAndHashCode_SameObject() {
        String groupDenomination = "School friends";
        String personGroupMemberEmail = "maria@gmail.com";
        String categoryDenomination = "Gatherings";
        String accountToDebitName = "School friends funds";
        String accountToCreditName = "Restaurant La vita è bella";
        double transactionAmount = 250.00;
        String transactionType = "Debit";
        String transactionDescription = "Dinner party";
        String transactionDate = "2020-06-18";
        CreateGroupTransactionDTO createGroupTransactionDTO = new CreateGroupTransactionDTO(groupDenomination, personGroupMemberEmail, categoryDenomination, accountToDebitName, accountToCreditName, transactionAmount, transactionType, transactionDescription, transactionDate);

        //Act
        boolean resultEquals = createGroupTransactionDTO.equals(createGroupTransactionDTO);
        boolean resultHashCode = (createGroupTransactionDTO.hashCode() == createGroupTransactionDTO.hashCode());

        //Assert
        assertEquals(true, resultEquals);
        assertEquals(true, resultHashCode);
    }

    @Test
    @DisplayName("Test for equals() - Fail (null object)")
    public void testEquals_FailNullObject() {
        //Arrange
        String groupDenomination1 = "School friends";
        String personGroupMemberEmail1 = "maria@gmail.com";
        String categoryDenomination1 = "Gatherings";
        String accountToDebitName1 = "School friends funds";
        String accountToCreditName1 = "Restaurant La vita è bella";
        double transactionAmount1 = 250.00;
        String transactionType1 = "Debit";
        String transactionDescription1 = "Dinner party";
        String transactionDate1 = "2020-06-18";
        CreateGroupTransactionDTO createGroupTransactionDTO1 = new CreateGroupTransactionDTO(groupDenomination1, personGroupMemberEmail1, categoryDenomination1, accountToDebitName1, accountToCreditName1, transactionAmount1, transactionType1, transactionDescription1, transactionDate1);

        CreateGroupTransactionDTO createGroupTransactionDTO2 = null;

        //Act
        boolean resultEquals = createGroupTransactionDTO1.equals(createGroupTransactionDTO2);

        //Assert
        assertEquals(false, resultEquals);
    }

    @Test
    @DisplayName("Test for equals() - Fail (different class)")
    public void testEquals_FailDiffClass() {
        //Arrange
        String groupDenomination1 = "School friends";
        String personGroupMemberEmail1 = "maria@gmail.com";
        String categoryDenomination1 = "Gatherings";
        String accountToDebitName1 = "School friends funds";
        String accountToCreditName1 = "Restaurant La vita è bella";
        double transactionAmount1 = 250.00;
        String transactionType1 = "Debit";
        String transactionDescription1 = "Dinner party";
        String transactionDate1 = "2020-06-18";
        CreateGroupTransactionDTO createGroupTransactionDTO1 = new CreateGroupTransactionDTO(groupDenomination1, personGroupMemberEmail1, categoryDenomination1, accountToDebitName1, accountToCreditName1, transactionAmount1, transactionType1, transactionDescription1, transactionDate1);

        String object = "Object from class String";

        //Act
        boolean resultEquals = createGroupTransactionDTO1.equals(object);
        boolean resultHashCode = (createGroupTransactionDTO1.hashCode() == object.hashCode());

        //Assert
        assertEquals(false, resultEquals);
        assertEquals(false, resultHashCode);
    }

    @Test
    @DisplayName("Test for equals() and hasCode() - Fail (different transaction amount)")
    public void testEqualsAndHashCode_FailDiffAmount() {
        //Arrange
        String groupDenomination1 = "School friends";
        String personGroupMemberEmail1 = "maria@gmail.com";
        String categoryDenomination1 = "Gatherings";
        String accountToDebitName1 = "School friends funds";
        String accountToCreditName1 = "Restaurant La vita è bella";
        double transactionAmount1 = 100.00;
        String transactionType1 = "Debit";
        String transactionDescription1 = "Dinner party";
        String transactionDate1 = "2020-06-18";
        CreateGroupTransactionDTO createGroupTransactionDTO1 = new CreateGroupTransactionDTO(groupDenomination1, personGroupMemberEmail1, categoryDenomination1, accountToDebitName1, accountToCreditName1, transactionAmount1, transactionType1, transactionDescription1, transactionDate1);

        String groupDenomination2 = "School friends";
        String personGroupMemberEmail2 = "maria@gmail.com";
        String categoryDenomination2 = "Gatherings";
        String accountToDebitName2 = "School friends funds";
        String accountToCreditName2 = "Restaurant La vita è bella";
        double transactionAmount2 = 250.00;
        String transactionType2 = "Debit";
        String transactionDescription2 = "Dinner party";
        String transactionDate2 = "2020-06-18";
        CreateGroupTransactionDTO createGroupTransactionDTO2 = new CreateGroupTransactionDTO(groupDenomination2, personGroupMemberEmail2, categoryDenomination2, accountToDebitName2, accountToCreditName2, transactionAmount2, transactionType2, transactionDescription2, transactionDate2);

        //Act
        boolean resultEquals = createGroupTransactionDTO1.equals(createGroupTransactionDTO2);
        boolean resultHashCode = (createGroupTransactionDTO1.hashCode() == createGroupTransactionDTO2.hashCode());

        //Assert
        assertEquals(false, resultEquals);
        assertEquals(false, resultHashCode);
    }

    @Test
    @DisplayName("Test for equals() and hasCode() - Fail (different group denomination)")
    public void testEqualsAndHashCode_FailDiffGroupName() {
        //Arrange
        String groupDenomination1 = "High School friends";
        String personGroupMemberEmail1 = "maria@gmail.com";
        String categoryDenomination1 = "Gatherings";
        String accountToDebitName1 = "School friends funds";
        String accountToCreditName1 = "Restaurant La vita è bella";
        double transactionAmount1 = 250.00;
        String transactionType1 = "Debit";
        String transactionDescription1 = "Dinner party";
        String transactionDate1 = "2020-06-18";
        CreateGroupTransactionDTO createGroupTransactionDTO1 = new CreateGroupTransactionDTO(groupDenomination1, personGroupMemberEmail1, categoryDenomination1, accountToDebitName1, accountToCreditName1, transactionAmount1, transactionType1, transactionDescription1, transactionDate1);

        String groupDenomination2 = "School friends";
        String personGroupMemberEmail2 = "maria@gmail.com";
        String categoryDenomination2 = "Gatherings";
        String accountToDebitName2 = "School friends funds";
        String accountToCreditName2 = "Restaurant La vita è bella";
        double transactionAmount2 = 250.00;
        String transactionType2 = "Debit";
        String transactionDescription2 = "Dinner party";
        String transactionDate2 = "2020-06-18";
        CreateGroupTransactionDTO createGroupTransactionDTO2 = new CreateGroupTransactionDTO(groupDenomination2, personGroupMemberEmail2, categoryDenomination2, accountToDebitName2, accountToCreditName2, transactionAmount2, transactionType2, transactionDescription2, transactionDate2);

        //Act
        boolean resultEquals = createGroupTransactionDTO1.equals(createGroupTransactionDTO2);
        boolean resultHashCode = (createGroupTransactionDTO1.hashCode() == createGroupTransactionDTO2.hashCode());

        //Assert
        assertEquals(false, resultEquals);
        assertEquals(false, resultHashCode);
    }

    @Test
    @DisplayName("Test for equals() and hasCode() - Fail (different group member email)")
    public void testEqualsAndHashCode_FailDiffMemberEmail() {
        //Arrange
        String groupDenomination1 = "School friends";
        String personGroupMemberEmail1 = "ana@gmail.com";
        String categoryDenomination1 = "Gatherings";
        String accountToDebitName1 = "School friends funds";
        String accountToCreditName1 = "Restaurant La vita è bella";
        double transactionAmount1 = 250.00;
        String transactionType1 = "Debit";
        String transactionDescription1 = "Dinner party";
        String transactionDate1 = "2020-06-18";
        CreateGroupTransactionDTO createGroupTransactionDTO1 = new CreateGroupTransactionDTO(groupDenomination1, personGroupMemberEmail1, categoryDenomination1, accountToDebitName1, accountToCreditName1, transactionAmount1, transactionType1, transactionDescription1, transactionDate1);

        String groupDenomination2 = "School friends";
        String personGroupMemberEmail2 = "maria@gmail.com";
        String categoryDenomination2 = "Gatherings";
        String accountToDebitName2 = "School friends funds";
        String accountToCreditName2 = "Restaurant La vita è bella";
        double transactionAmount2 = 250.00;
        String transactionType2 = "Debit";
        String transactionDescription2 = "Dinner party";
        String transactionDate2 = "2020-06-18";
        CreateGroupTransactionDTO createGroupTransactionDTO2 = new CreateGroupTransactionDTO(groupDenomination2, personGroupMemberEmail2, categoryDenomination2, accountToDebitName2, accountToCreditName2, transactionAmount2, transactionType2, transactionDescription2, transactionDate2);

        //Act
        boolean resultEquals = createGroupTransactionDTO1.equals(createGroupTransactionDTO2);
        boolean resultHashCode = (createGroupTransactionDTO1.hashCode() == createGroupTransactionDTO2.hashCode());

        //Assert
        assertEquals(false, resultEquals);
        assertEquals(false, resultHashCode);
    }

    @Test
    @DisplayName("Test for equals() and hasCode() - Fail (different category)")
    public void testEqualsAndHashCode_FailDiffCategory() {
        //Arrange
        String groupDenomination1 = "School friends";
        String personGroupMemberEmail1 = "maria@gmail.com";
        String categoryDenomination1 = "Allowance";
        String accountToDebitName1 = "School friends funds";
        String accountToCreditName1 = "Restaurant La vita è bella";
        double transactionAmount1 = 250.00;
        String transactionType1 = "Debit";
        String transactionDescription1 = "Dinner party";
        String transactionDate1 = "2020-06-18";
        CreateGroupTransactionDTO createGroupTransactionDTO1 = new CreateGroupTransactionDTO(groupDenomination1, personGroupMemberEmail1, categoryDenomination1, accountToDebitName1, accountToCreditName1, transactionAmount1, transactionType1, transactionDescription1, transactionDate1);

        String groupDenomination2 = "School friends";
        String personGroupMemberEmail2 = "maria@gmail.com";
        String categoryDenomination2 = "Gatherings";
        String accountToDebitName2 = "School friends funds";
        String accountToCreditName2 = "Restaurant La vita è bella";
        double transactionAmount2 = 250.00;
        String transactionType2 = "Debit";
        String transactionDescription2 = "Dinner party";
        String transactionDate2 = "2020-06-18";
        CreateGroupTransactionDTO createGroupTransactionDTO2 = new CreateGroupTransactionDTO(groupDenomination2, personGroupMemberEmail2, categoryDenomination2, accountToDebitName2, accountToCreditName2, transactionAmount2, transactionType2, transactionDescription2, transactionDate2);

        //Act
        boolean resultEquals = createGroupTransactionDTO1.equals(createGroupTransactionDTO2);
        boolean resultHashCode = (createGroupTransactionDTO1.hashCode() == createGroupTransactionDTO2.hashCode());

        //Assert
        assertEquals(false, resultEquals);
        assertEquals(false, resultHashCode);
    }

    @Test
    @DisplayName("Test for equals() and hasCode() - Fail (different debit account)")
    public void testEqualsAndHashCode_FailDiffDebitAccount() {
        //Arrange
        String groupDenomination1 = "School friends";
        String personGroupMemberEmail1 = "maria@gmail.com";
        String categoryDenomination1 = "Gatherings";
        String accountToDebitName1 = "High School friends funds";
        String accountToCreditName1 = "Restaurant La vita è bella";
        double transactionAmount1 = 250.00;
        String transactionType1 = "Debit";
        String transactionDescription1 = "Dinner party";
        String transactionDate1 = "2020-06-18";
        CreateGroupTransactionDTO createGroupTransactionDTO1 = new CreateGroupTransactionDTO(groupDenomination1, personGroupMemberEmail1, categoryDenomination1, accountToDebitName1, accountToCreditName1, transactionAmount1, transactionType1, transactionDescription1, transactionDate1);

        String groupDenomination2 = "School friends";
        String personGroupMemberEmail2 = "maria@gmail.com";
        String categoryDenomination2 = "Gatherings";
        String accountToDebitName2 = "School friends funds";
        String accountToCreditName2 = "Restaurant La vita è bella";
        double transactionAmount2 = 250.00;
        String transactionType2 = "Debit";
        String transactionDescription2 = "Dinner party";
        String transactionDate2 = "2020-06-18";
        CreateGroupTransactionDTO createGroupTransactionDTO2 = new CreateGroupTransactionDTO(groupDenomination2, personGroupMemberEmail2, categoryDenomination2, accountToDebitName2, accountToCreditName2, transactionAmount2, transactionType2, transactionDescription2, transactionDate2);

        //Act
        boolean resultEquals = createGroupTransactionDTO1.equals(createGroupTransactionDTO2);
        boolean resultHashCode = (createGroupTransactionDTO1.hashCode() == createGroupTransactionDTO2.hashCode());

        //Assert
        assertEquals(false, resultEquals);
        assertEquals(false, resultHashCode);
    }

    @Test
    @DisplayName("Test for equals() and hasCode() - Fail (different credit account)")
    public void testEqualsAndHashCode_FailDiffCreditAccount() {
        //Arrange
        String groupDenomination1 = "School friends";
        String personGroupMemberEmail1 = "maria@gmail.com";
        String categoryDenomination1 = "Gatherings";
        String accountToDebitName1 = "School friends funds";
        String accountToCreditName1 = "Restaurant Cherry";
        double transactionAmount1 = 250.00;
        String transactionType1 = "Debit";
        String transactionDescription1 = "Dinner party";
        String transactionDate1 = "2020-06-18";
        CreateGroupTransactionDTO createGroupTransactionDTO1 = new CreateGroupTransactionDTO(groupDenomination1, personGroupMemberEmail1, categoryDenomination1, accountToDebitName1, accountToCreditName1, transactionAmount1, transactionType1, transactionDescription1, transactionDate1);

        String groupDenomination2 = "School friends";
        String personGroupMemberEmail2 = "maria@gmail.com";
        String categoryDenomination2 = "Gatherings";
        String accountToDebitName2 = "School friends funds";
        String accountToCreditName2 = "Restaurant La vita è bella";
        double transactionAmount2 = 250.00;
        String transactionType2 = "Debit";
        String transactionDescription2 = "Dinner party";
        String transactionDate2 = "2020-06-18";
        CreateGroupTransactionDTO createGroupTransactionDTO2 = new CreateGroupTransactionDTO(groupDenomination2, personGroupMemberEmail2, categoryDenomination2, accountToDebitName2, accountToCreditName2, transactionAmount2, transactionType2, transactionDescription2, transactionDate2);

        //Act
        boolean resultEquals = createGroupTransactionDTO1.equals(createGroupTransactionDTO2);
        boolean resultHashCode = (createGroupTransactionDTO1.hashCode() == createGroupTransactionDTO2.hashCode());

        //Assert
        assertEquals(false, resultEquals);
        assertEquals(false, resultHashCode);
    }

    @Test
    @DisplayName("Test for equals() and hasCode() - Fail (different transaction type)")
    public void testEqualsAndHashCode_FailDiffTransactionType() {
        //Arrange
        String groupDenomination1 = "School friends";
        String personGroupMemberEmail1 = "maria@gmail.com";
        String categoryDenomination1 = "Gatherings";
        String accountToDebitName1 = "School friends funds";
        String accountToCreditName1 = "Restaurant La vita è bella";
        double transactionAmount1 = 250.00;
        String transactionType1 = "Credit";
        String transactionDescription1 = "Dinner party";
        String transactionDate1 = "2020-06-18";
        CreateGroupTransactionDTO createGroupTransactionDTO1 = new CreateGroupTransactionDTO(groupDenomination1, personGroupMemberEmail1, categoryDenomination1, accountToDebitName1, accountToCreditName1, transactionAmount1, transactionType1, transactionDescription1, transactionDate1);

        String groupDenomination2 = "School friends";
        String personGroupMemberEmail2 = "maria@gmail.com";
        String categoryDenomination2 = "Gatherings";
        String accountToDebitName2 = "School friends funds";
        String accountToCreditName2 = "Restaurant La vita è bella";
        double transactionAmount2 = 250.00;
        String transactionType2 = "Debit";
        String transactionDescription2 = "Dinner party";
        String transactionDate2 = "2020-06-18";
        CreateGroupTransactionDTO createGroupTransactionDTO2 = new CreateGroupTransactionDTO(groupDenomination2, personGroupMemberEmail2, categoryDenomination2, accountToDebitName2, accountToCreditName2, transactionAmount2, transactionType2, transactionDescription2, transactionDate2);

        //Act
        boolean resultEquals = createGroupTransactionDTO1.equals(createGroupTransactionDTO2);
        boolean resultHashCode = (createGroupTransactionDTO1.hashCode() == createGroupTransactionDTO2.hashCode());

        //Assert
        assertEquals(false, resultEquals);
        assertEquals(false, resultHashCode);
    }

    @Test
    @DisplayName("Test for equals() and hasCode() - Fail (different transaction description)")
    public void testEqualsAndHashCode_FailDiffTransactionDescription() {
        //Arrange
        String groupDenomination1 = "School friends";
        String personGroupMemberEmail1 = "maria@gmail.com";
        String categoryDenomination1 = "Gatherings";
        String accountToDebitName1 = "School friends funds";
        String accountToCreditName1 = "Restaurant La vita è bella";
        double transactionAmount1 = 250.00;
        String transactionType1 = "Debit";
        String transactionDescription1 = "Lunch party";
        String transactionDate1 = "2020-06-18";
        CreateGroupTransactionDTO createGroupTransactionDTO1 = new CreateGroupTransactionDTO(groupDenomination1, personGroupMemberEmail1, categoryDenomination1, accountToDebitName1, accountToCreditName1, transactionAmount1, transactionType1, transactionDescription1, transactionDate1);

        String groupDenomination2 = "School friends";
        String personGroupMemberEmail2 = "maria@gmail.com";
        String categoryDenomination2 = "Gatherings";
        String accountToDebitName2 = "School friends funds";
        String accountToCreditName2 = "Restaurant La vita è bella";
        double transactionAmount2 = 250.00;
        String transactionType2 = "Debit";
        String transactionDescription2 = "Dinner party";
        String transactionDate2 = "2020-06-18";
        CreateGroupTransactionDTO createGroupTransactionDTO2 = new CreateGroupTransactionDTO(groupDenomination2, personGroupMemberEmail2, categoryDenomination2, accountToDebitName2, accountToCreditName2, transactionAmount2, transactionType2, transactionDescription2, transactionDate2);

        //Act
        boolean resultEquals = createGroupTransactionDTO1.equals(createGroupTransactionDTO2);
        boolean resultHashCode = (createGroupTransactionDTO1.hashCode() == createGroupTransactionDTO2.hashCode());

        //Assert
        assertEquals(false, resultEquals);
        assertEquals(false, resultHashCode);
    }
}