package com.finance.project.dtos.dtos;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class GroupDTOTest {

    @Test
    @DisplayName("GroupDTO - Test Constructor with Parameters")
    void groupDTO_ConstructorWithParametersTest() {

        //Arrange
        String groupDenomination = "Sunday Runners";
        String groupDescription = "All members from Sunday Runners group";
        String groupDateOfCreation = LocalDate.now().toString();

        //Act
        GroupDTO groupDTO = new GroupDTO(groupDenomination, groupDescription, groupDateOfCreation);

        //Assert
        assertEquals(groupDenomination, groupDTO.getDenomination());
        assertEquals(groupDescription, groupDTO.getDescription());
        assertEquals(groupDateOfCreation, groupDTO.getDateOfCreation());
    }

    @Test
    @DisplayName("GroupDTO - Test Constructor2 with Parameters")
    void groupDTO_Constructor2WithParametersTest() {

        //Arrange
        String groupDenomination = "Sunday Runners";
        String groupDescription = "All members from Sunday Runners group";
        String groupDateOfCreation = LocalDate.now().toString();
        String ledger = "";

        //Act
        GroupDTO groupDTO = new GroupDTO(groupDenomination, groupDescription, groupDateOfCreation, ledger);

        //Assert
        assertEquals(groupDenomination, groupDTO.getDenomination());
        assertEquals(groupDescription, groupDTO.getDescription());
        assertEquals(groupDateOfCreation, groupDTO.getDateOfCreation());
        assertEquals(ledger, groupDTO.getLedger());
    }

    @Test
    @DisplayName("GroupDTO - Test Constructor without Parameters")
    void groupDTO_ConstructorWithoutParametersTest() {

        //Arrange
        String groupDenomination = "Sunday Runners";
        String groupDescription = "All members from Sunday Runners group";
        String groupDateOfCreation = LocalDate.now().toString();
        String ledger = "";

        //Act
        GroupDTO groupDTO = new GroupDTO();
        groupDTO.setDenomination(groupDenomination);
        groupDTO.setDescription(groupDescription);
        groupDTO.setDateOfCreation(groupDateOfCreation);
        groupDTO.setLedger(ledger);

        //Assert
        assertEquals(groupDenomination, groupDTO.getDenomination());
        assertEquals(groupDescription, groupDTO.getDescription());
        assertEquals(groupDateOfCreation, groupDTO.getDateOfCreation());
        assertEquals(ledger, groupDTO.getLedger());
    }

    @Test
    @DisplayName("GroupDTO - Test Equals || Same Object")
    void groupDTO_EqualsTest_SameObject() {

        //Arrange
        String groupDenomination = "Sunday Runners";
        String groupDescription = "All members from Sunday Runners group";
        String groupDateOfCreation = LocalDate.now().toString();

        //Act
        GroupDTO groupDTO1 = new GroupDTO(groupDenomination, groupDescription, groupDateOfCreation);

        //Assert

        assertTrue(groupDTO1.equals(groupDTO1));
    }

    @Test
    @DisplayName("GroupDTO - Test Equals || Different Object - Same information")
    void groupDTO_EqualsTest_DifferentObjectSameInformation() {

        //Arrange
        String groupDenomination1 = "Sunday Runners";
        String groupDescription1 = "All members from Sunday Runners group";
        String groupDateOfCreation1 = LocalDate.now().toString();

        String groupDenomination2 = "Sunday Runners";
        String groupDescription2 = "All members from Sunday Runners group";
        String groupDateOfCreation2 = LocalDate.now().toString();

        //Act
        GroupDTO groupDTO1 = new GroupDTO(groupDenomination1, groupDescription1, groupDateOfCreation1);
        GroupDTO groupDTO2 = new GroupDTO(groupDenomination2, groupDescription2, groupDateOfCreation2);

        //Assert
        assertTrue(groupDTO1.equals(groupDTO2));
    }

    @Test
    @DisplayName("GroupDTO - Test Equals || Different Object")
    void groupDTO_EqualsTest_DifferentObject() {

        //Arrange

        //Group Sunday Runners
        String sundayRunnersDenomination = "Sunday Runners";
        String sundayRunnersDescription = "All members from Sunday Runners group";
        LocalDate sundayRunnersDateOfCreation = LocalDate.of(1979, 07, 25);

        //Group Silva Family
        String silvaFamilyDenomination = "Silva Family";
        String silvaFamilyDescription = "All members from Silva family";
        LocalDate silvaFamilyDateOfCreation = LocalDate.of(1975, 07, 25);

        //Act
        GroupDTO groupDTO1 = new GroupDTO(sundayRunnersDenomination, sundayRunnersDescription, sundayRunnersDateOfCreation.toString());
        GroupDTO groupDTO2 = new GroupDTO(silvaFamilyDenomination, silvaFamilyDescription, silvaFamilyDateOfCreation.toString());
        GroupDTO groupDTO3 = null;

        boolean result = groupDTO1.equals(groupDTO2);

        //Assert
        assertEquals(false, result);
        assertFalse(groupDTO1.equals(groupDTO2));
        assertFalse(groupDTO1.equals(silvaFamilyDateOfCreation)); //not same instance
        assertFalse(groupDTO1.equals(groupDTO3)); //object is null
    }

    @Test
    @DisplayName("GroupDTO - Test Equals || Different Denomination")
    void groupDTO_EqualsTest_DifferentDenomination() {

        //Arrange

        //Group Sunday Runners
        String sundayRunnersDenomination = "Sunday Runners";
        String sundayRunnersDescription = "All members from Sunday Runners group";
        String sundayRunnersDateOfCreation = LocalDate.now().toString();

        //Group Silva Family
        String silvaFamilyDenomination = "Silva Family";
        String silvaFamilyDescription = "All members from Silva family";
        String silvaFamilyDateOfCreation = LocalDate.now().toString();

        //Act
        GroupDTO groupDTO1 = new GroupDTO(sundayRunnersDenomination, sundayRunnersDescription, sundayRunnersDateOfCreation);
        GroupDTO groupDTO2 = new GroupDTO(silvaFamilyDenomination, sundayRunnersDescription, sundayRunnersDateOfCreation);


        boolean result = groupDTO1.equals(groupDTO2);

        //Assert
        assertEquals(false, result);
        assertFalse(groupDTO1.equals(groupDTO2));

    }

    @Test
    @DisplayName("GroupDTO - Test Equals || Different Description")
    void groupDTO_EqualsTest_DifferentDescription() {

        //Arrange

        //Group Sunday Runners
        String sundayRunnersDenomination = "Sunday Runners";
        String sundayRunnersDescription = "All members from Sunday Runners group";
        String sundayRunnersDateOfCreation = LocalDate.now().toString();

        //Group Silva Family
        String silvaFamilyDenomination = "Silva Family";
        String silvaFamilyDescription = "All members from Silva family";
        String silvaFamilyDateOfCreation = LocalDate.now().toString();

        //Act
        GroupDTO groupDTO1 = new GroupDTO(sundayRunnersDenomination, sundayRunnersDescription, sundayRunnersDateOfCreation);
        GroupDTO groupDTO2 = new GroupDTO(sundayRunnersDenomination, silvaFamilyDescription, sundayRunnersDateOfCreation);


        boolean result = groupDTO1.equals(groupDTO2);

        //Assert
        assertEquals(false, result);
        assertFalse(groupDTO1.equals(groupDTO2));

    }


    @Test
    @DisplayName("GroupDTO - Test Equals || Different DateOfCreation")
    void groupDTO_EqualsTest_DifferentDateOfCreation() {

        //Arrange

        //Group Sunday Runners
        String sundayRunnersDenomination = "Sunday Runners";
        String sundayRunnersDescription = "All members from Sunday Runners group";
        LocalDate sundayRunnersDateOfCreation = LocalDate.of(1973, 07, 25);

        //Group Silva Family
        String silvaFamilyDenomination = "Silva Family";
        String silvaFamilyDescription = "All members from Silva family";
        LocalDate silvaFamilyDateOfCreation = LocalDate.of(1975, 07, 25);

        //Act
        GroupDTO groupDTO1 = new GroupDTO(sundayRunnersDenomination, sundayRunnersDescription, sundayRunnersDateOfCreation.toString());
        GroupDTO groupDTO2 = new GroupDTO(sundayRunnersDenomination, sundayRunnersDescription, silvaFamilyDateOfCreation.toString());


        boolean result = groupDTO1.equals(groupDTO2);

        //Assert
        assertEquals(false, result);
        assertFalse(groupDTO1.equals(groupDTO2));

    }

    @Test
    @DisplayName("GroupDTO - Test Hash Code")
    void groupDTO_HashCodeTest() {

        //Arrange
        String groupDenomination = "Sunday Runners";
        String groupDescription = "All members from Sunday Runners group";
        String groupDateOfCreation = LocalDate.of(2020, 04, 28).toString();

        //Act
        GroupDTO groupDTO = new GroupDTO(groupDenomination, groupDescription, groupDateOfCreation);

        int groupDTOHashcode = groupDTO.hashCode();
        int expectedHashCode = 562793166;

        //Assert
        assertEquals(expectedHashCode, groupDTOHashcode);
    }

}