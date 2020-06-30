package com.finance.project.dtos.dtosAssemblers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.finance.project.dtos.dtos.PersonEmailDTO;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Ala Matos
 */
class PersonEmailDTOAssemblerTest {

    @Test
    @DisplayName("Test for PersonEmailDTOAssembler constructor | Happy path")
    void createPersonEmailDTOAssembler() {

//        Arrange the email
        String email = "hulk@gmarvel.com";

//        Act
        PersonEmailDTO personEmailDTO = PersonEmailDTOAssembler.createPersonEmailDTO(email);

//        Expected PersonEmailDTO
        PersonEmailDTO expectedPersonEmailDTO = new PersonEmailDTO(email);

//        Assert
        assertEquals(expectedPersonEmailDTO, personEmailDTO);

    }

    @Test
    @DisplayName("Test for PersonEmailDTOAssembler constructor | Sad path")
    void createPersonEmailDTOAssemblerSadPath() {

//        Arrange the email
        String email = "hulk@gmarvel.com";
        String secondaryEmail = "wolverine@gmarvel.com";

//        Act
        PersonEmailDTO personEmailDTO = PersonEmailDTOAssembler.createPersonEmailDTO(secondaryEmail);

//        Expected PersonEmailDTO
        PersonEmailDTO expectedPersonEmailDTO = new PersonEmailDTO(email);

//        Assert
        assertNotEquals(expectedPersonEmailDTO, personEmailDTO);

    }

    @Test
    @DisplayName("Test for PersonEmailDTOAssembler constructor | Null Email | Sad path")
    void createPersonEmailDTOAssemblerNullEmail() {

//        Arrange the email
        String email = null;

//        Act
        PersonEmailDTO personEmailDTO = PersonEmailDTOAssembler.createPersonEmailDTO(email);

//        Expected PersonEmailDTO
        PersonEmailDTO expectedPersonEmailDTO = new PersonEmailDTO(email);

//        Assert
        assertEquals(expectedPersonEmailDTO, personEmailDTO);

    }


    @Test
    @DisplayName("Test for PersonEmailDTOAssembler constructor | new Assembler")
    void createPersonEmailDTOAssemblerNewAssembler() {

//        Arrange the email
        String email = "hulk@gmarvel.com";

//        Act

        PersonEmailDTOAssembler personEmailDTOAssembler = new PersonEmailDTOAssembler();
        PersonEmailDTO personEmailDTO = personEmailDTOAssembler.createPersonEmailDTO(email);

//        Expected PersonEmailDTO
        PersonEmailDTO expectedPersonEmailDTO = new PersonEmailDTO(email);

//        Assert
        assertEquals(expectedPersonEmailDTO, personEmailDTO);

    }
}