package com.finance.project.dtos.dtos;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 */

class BooleanDTOTest {


    @Test
    @DisplayName("Test For BooleanDTO()")
    void _constructor_booleanDTO() {

        // Arrange

        boolean result = true;
        String msg = "Sucess";


        // Act

        BooleanDTO booleanDTO = new BooleanDTO(result, msg);


        // Assert

        assertEquals(result, booleanDTO.getResult());
        assertEquals(msg, booleanDTO.getMsg());
    }


    @Test
    @DisplayName("Test For getResult()")
    void getResult() {


        // Arrange

        boolean result = true;
        String msg = "Sucess";


        // Act

        BooleanDTO booleanDTO = new BooleanDTO(result, msg);


        // Assert

        assertEquals(result, booleanDTO.getResult());
    }


    @Test
    @DisplayName("Test For getMsg()")
    void getMsg() {


        // Arrange

        boolean result = true;
        String msg = "FAIL";


        // Act

        BooleanDTO booleanDTO = new BooleanDTO(result, msg);


        // Assert
        assertEquals(msg, booleanDTO.getMsg());
    }


    @Test
    @DisplayName("Test For equals() | Success")
    void equalsSuccess() {

        // ArrangeA

        boolean result = true;
        String msg = "Sucess";

        // ArrangeB
        boolean resultA = true;
        String msgA = "Sucess";


        // Act

        BooleanDTO booleanDTO = new BooleanDTO(result, msg);
        BooleanDTO booleanDTOA = new BooleanDTO(resultA, msgA);


        boolean resultEquals = booleanDTO.equals(booleanDTOA);

        // Assert
        assertEquals(true, resultEquals);
        assertTrue(resultEquals);

    }


    @Test
    @DisplayName("Test For equals() | Success | Same Object")
    void equalsSuccessSameObject() {


        // ArrangeA

        boolean result = true;
        String msg = "Sucess";


        // Act

        BooleanDTO booleanDTO = new BooleanDTO(result, msg);


        boolean resultEquals = booleanDTO.equals(booleanDTO);

        // Assert
        assertEquals(true, resultEquals);
        assertTrue(resultEquals);

    }

    @Test
    @DisplayName("Test For equals() | Fail | Msg")
    void equalsFailMsg() {

        // ArrangeA

        boolean result = true;
        String msg = "Sucess";

        // ArrangeB
        boolean resultA = true;
        String msgA = "Fail";


        // Act

        BooleanDTO booleanDTO = new BooleanDTO(result, msg);
        BooleanDTO booleanDTOA = new BooleanDTO(resultA, msgA);


        boolean resultEquals = booleanDTO.equals(booleanDTOA);

        // Assert
        assertEquals(false, resultEquals);
        assertFalse(resultEquals);

    }

    @Test
    @DisplayName("Test For equals() | Fail | Result")
    void equalsFailResult() {


        // ArrangeA

        boolean result = true;
        String msg = "Sucess";

        // ArrangeB
        boolean resultA = false;
        String msgA = "Sucess";


        // Act

        BooleanDTO booleanDTO = new BooleanDTO(result, msg);
        BooleanDTO booleanDTOA = new BooleanDTO(resultA, msgA);


        boolean resultEquals = booleanDTO.equals(booleanDTOA);

        // Assert
        assertEquals(false, resultEquals);
        assertFalse(resultEquals);
    }


    @Test
    @DisplayName("Test For equals() | Fail | Different Class")
    void equalsFailDifferentClass() {

        // ArrangeA

        boolean result = true;
        String msg = "Sucess";

        // ArrangeB
        boolean resultA = false;
        String msgA = "Sucess";


        // Act

        BooleanDTO booleanDTO = new BooleanDTO(result, msg);
        BooleanDTO booleanDTOA = new BooleanDTO(resultA, msgA);


        boolean resultEquals = booleanDTO.equals(msgA);

        // Assert
        assertEquals(false, resultEquals);
        assertFalse(resultEquals);
    }


    @Test
    @DisplayName("Test For equals() | Fail | Null")
    void equalsFailNull() {


        // ArrangeA

        boolean result = true;
        String msg = "Sucess";

        // ArrangeB
        boolean resultA = false;
        String msgA = "Sucess";


        // Act

        BooleanDTO booleanDTO = new BooleanDTO(result, msg);
        BooleanDTO booleanDTOA = new BooleanDTO(resultA, msgA);


        boolean resultEquals = booleanDTO.equals(null);

        // Assert
        assertEquals(false, resultEquals);
        assertFalse(resultEquals);
    }


    @Test
    @DisplayName("Test For BooleanDTO() | Success")
    void hashCodeSuccess() {

        // ArrangeA

        boolean result = true;
        String msg = "Sucess";

        // ArrangeB
        boolean resultA = true;
        String msgA = "Sucess";


        // Act

        BooleanDTO booleanDTO = new BooleanDTO(result, msg);
        BooleanDTO booleanDTOA = new BooleanDTO(resultA, msgA);

        boolean hashcodeResult = booleanDTO.hashCode()==booleanDTOA.hashCode();

        // Assert

        assertTrue(hashcodeResult);
        assertEquals(true, hashcodeResult);
        assertTrue(booleanDTO.hashCode()==booleanDTO.hashCode());


    }

    @Test
    @DisplayName("Test For BooleanDTO() | False")
    void hashCodeFail() {

        // ArrangeA

        boolean result = true;
        String msg = "Sucess";

        // ArrangeB
        boolean resultA = false;
        String msgA = "Fail";


        // Act

        BooleanDTO booleanDTO = new BooleanDTO(result, msg);
        BooleanDTO booleanDTOA = new BooleanDTO(resultA, msgA);

        boolean hashcodeResult = booleanDTO.hashCode()==booleanDTOA.hashCode();

        // Assert

        assertFalse(hashcodeResult);
        assertEquals(false, hashcodeResult);


    }


}

