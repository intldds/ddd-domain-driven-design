package com.finance.project.dtos.dtosAssemblers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.finance.project.dtos.dtos.CheckIfSiblingsDTO;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Ala Matos
 */
class CheckIfSiblingsDTOAssemblerTest {

    @Test
    @DisplayName("Test the US01_Assembler - Hulk | Wolverine | All the paramaters are right")
    public void testUS01_AssemblerAllParametersAreRight() {

        //Arrange
        String hulkEmail = "hulk@marvel.com";
        String wolverineEmail = "wolverine@marvel.com";

        //Act

        CheckIfSiblingsDTO hulkDTO = new CheckIfSiblingsDTO(hulkEmail, wolverineEmail);

        //Assert
        assertEquals(hulkEmail, hulkDTO.getEmail());
        assertEquals(wolverineEmail, hulkDTO.getSiblingEmail());

    }

    @Test
    @DisplayName("Test the US01_Assembler - Hulk | Iron Man | Only one parameter is right")
    public void testUS01_AssemblerOnlyOneParameter() {
        //Arrange
        String hulkEmail = "hulk@marvel.com";
        String wolverineEmail = "wolverine@marvel.com";
        String ironManEmail = "ironMan@marvel.com";
        //Act
        CheckIfSiblingsDTO hulkDTO = new CheckIfSiblingsDTO(hulkEmail, wolverineEmail);

        //Assert
        assertEquals(hulkEmail, hulkDTO.getEmail());
        assertNotEquals(ironManEmail, hulkDTO.getSiblingEmail());

    }

    @Test
    @DisplayName("Test the US01_Assembler - Hulk | Iron Man | None parameters are right")
    public void testUS01_AssemblerNoneParameters() {

        //Arrange
        String hulkEmail = "hulk@marvel.com";
        String wolverineEmail = "wolverine@marvel.com";
        String ironManEmail = "ironMan@marvel.com";
        String aquamanEmail = "aquaman@marvel.com";

        //Act
        CheckIfSiblingsDTO hulkDTO = new CheckIfSiblingsDTO(hulkEmail, wolverineEmail);

        //Assert
        assertNotEquals(ironManEmail, hulkDTO.getEmail());
        assertNotEquals(aquamanEmail, hulkDTO.getSiblingEmail());

    }

    @Test
    @DisplayName("Test CheckIfSiblingsDTOAssembler")
    public void testCheckIfSiblingsAssembler() {

        //Arrange
        String hulkEmail = "hulk@marvel.com";
        String wolverineEmail = "wolverine@marvel.com";

        //Act

        CheckIfSiblingsDTOAssembler checkIfSiblingsDTOAssembler = new CheckIfSiblingsDTOAssembler();
        CheckIfSiblingsDTO checkIfSiblingsDTO = checkIfSiblingsDTOAssembler.createDTOFromPrimitiveTypes(hulkEmail,wolverineEmail);

        //Expected
        CheckIfSiblingsDTO hulkDTO = new CheckIfSiblingsDTO(hulkEmail, wolverineEmail);


        //Assert
        assertEquals(hulkDTO,checkIfSiblingsDTO);
    }


}