package com.finance.project.controllerLayer.unitTests.errorHandling;

import com.finance.project.controllerLayer.controllersREST.errorHandling.ApiError;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ApiErrorTest {

    @Test
    @DisplayName("ApiError - Test ApiError constructor: List Errors Parameter")
    void apiError_ConstructorTest_ListErrors() {

        //Arrange
        HttpStatus httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;
        String message = "Group already exist";
        String error = "Unprocessable entity";

        List<String> errors = new ArrayList<>();
        errors.add(error);

        //Act
        ApiError apiError = new ApiError(httpStatus, message, errors);

        //Assert
        assertEquals(httpStatus, apiError.getStatus());
        assertEquals(message, apiError.getMessage());
        assertEquals(errors, apiError.getErrors());
    }

    @Test
    @DisplayName("ApiError - Test ApiError constructor: String Error Parameter")
    void apiError_ConstructorTest_StringError() {

        //Arrange
        HttpStatus httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;
        String message = "Group already exist";
        String error = "Unprocessable entity";

        //Act
        ApiError apiError = new ApiError(httpStatus, message, error);


        //Expected
        List<String> errors = new ArrayList<>();
        errors.add(error);

        //Assert
        assertEquals(httpStatus, apiError.getStatus());
        assertEquals(message, apiError.getMessage());
        assertEquals(errors, apiError.getErrors());
    }

    @Test
    @DisplayName("ApiError - Test Set Methods")
    void apiError_SetMethodTest() {

        //Arrange
        HttpStatus httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;
        String message = "Group already exist";
        String error = "Unprocessable entity";

        HttpStatus httpStatus2 = HttpStatus.UNPROCESSABLE_ENTITY;
        String message2 = "Person does not exist";
        String error2 = "Unprocessable entity";

        List<String> errors = new ArrayList<>();
        errors.add(error);

        List<String> errors2 = new ArrayList<>();
        errors.add(error2);

        //Act
        ApiError apiError = new ApiError(httpStatus, message, errors);
        apiError.setStatus(httpStatus2);
        apiError.setMessage(message2);
        apiError.setErrors(errors2);

        //Assert
        assertEquals(httpStatus2, apiError.getStatus());
        assertEquals(message2, apiError.getMessage());
        assertEquals(errors2, apiError.getErrors());
    }

    @Test
    @DisplayName("ApiError - Test Equals : Same object")
    void apiError_EqualsTest_SameObject() {

        //Arrange
        HttpStatus httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;
        String message = "Group already exist";
        String error = "Unprocessable entity";

        List<String> errors = new ArrayList<>();
        errors.add(error);

        //Act
        ApiError apiError1 = new ApiError(httpStatus, message, errors);
        ApiError apiError2 = new ApiError(httpStatus, message, errors);

        //Assert
        assertEquals(apiError1, apiError2); // same information
        assertEquals(apiError1, apiError1); // same object
    }


    @Test
    @DisplayName("ApiError - Test Equals : Different object - Same status ")
    void apiError_EqualsTest_DifferentObjectSameStatus() {

        //Arrange

        //ApiError 1
        HttpStatus httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;
        String message = "Group already exist";
        String error = "Unprocessable entity";

        List<String> errors = new ArrayList<>();
        errors.add(error);

        //ApiError 2
        HttpStatus httpStatus2 = HttpStatus.UNPROCESSABLE_ENTITY;
        String message2 = "Person does not exist";
        String error2 = "Unprocessable entity";

        List<String> errors2 = new ArrayList<>();
        errors.add(error2);

        //Bug killer
        String bugKiller = "Bug Killer";

        //Act
        ApiError apiError1 = new ApiError(httpStatus, message, errors);
        ApiError apiError2 = new ApiError(httpStatus2, message2, errors2);
        ApiError apiError3 = null;

        //Assert
        assertNotEquals(apiError1, apiError2); // different object
        assertNotEquals(apiError1, apiError3); // one object is null
        assertNotEquals(apiError1, bugKiller); // not same instance
    }

    @Test
    @DisplayName("ApiError - Test Equals : Different object")
    void apiError_EqualsTest_DifferentObject() {

        //Arrange

        //ApiError 1
        HttpStatus httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;
        String message = "Group already exist";
        String error = "Unprocessable entity";

        List<String> errors = new ArrayList<>();
        errors.add(error);

        //ApiError 2
        HttpStatus httpStatus2 = HttpStatus.UNPROCESSABLE_ENTITY;
        String message2 = "Person does not exist";
        String error2 = "Unprocessable entity";

        List<String> errors2 = new ArrayList<>();
        errors.add(error2);

        //Bug killer
        String bugKiller = "Bug Killer";

        //Act
        ApiError apiError1 = new ApiError(httpStatus, message, errors);
        ApiError apiError2 = new ApiError(httpStatus2, message2, errors2);
        ApiError apiError3 = null;

        //Assert
        assertNotEquals(apiError1, apiError2); // different object
        assertNotEquals(apiError1, apiError3); // one object is null
        assertNotEquals(apiError1, bugKiller); // not same instance
    }

    @Test
    @DisplayName("ApiError - Test Equals : Different object - Different Status")
    void apiError_EqualsTest_DifferentObjectDifferentStatus() {

        //Arrange

        //ApiError 1
        HttpStatus httpStatus = HttpStatus.MULTI_STATUS;
        String message = "Group already exist";
        String error = "Unprocessable entity";

        List<String> errors = new ArrayList<>();
        errors.add(error);

        //ApiError 2
        HttpStatus httpStatus2 = HttpStatus.UNPROCESSABLE_ENTITY;
        String message2 = "Group already exist";
        String error2 = "Unprocessable entity";

        List<String> errors2 = new ArrayList<>();
        errors.add(error2);

        //Act
        ApiError apiError1 = new ApiError(httpStatus, message, errors);
        ApiError apiError2 = new ApiError(httpStatus2, message2, errors2);

        boolean result = apiError1.equals(apiError2);

        //Assert
        assertEquals(false,result);
        assertFalse(apiError1.equals(apiError2));
    }

    @Test
    @DisplayName("ApiError - Test Equals : Different object - Different Message")
    void apiError_EqualsTest_DifferentObjectDifferentMessage() {

        //Arrange

        //ApiError 1
        HttpStatus httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;
        String message = "Group already exist";
        String error = "Unprocessable entity";

        List<String> errors = new ArrayList<>();
        errors.add(error);

        //ApiError 2
        HttpStatus httpStatus2 = HttpStatus.UNPROCESSABLE_ENTITY;
        String message2 = "Person already exist";
        String error2 = "Unprocessable entity";

        List<String> errors2 = new ArrayList<>();
        errors.add(error2);

        //Act
        ApiError apiError1 = new ApiError(httpStatus, message, errors);
        ApiError apiError2 = new ApiError(httpStatus2, message2, errors2);

        boolean result = apiError1.equals(apiError2);

        //Assert
        assertEquals(false,result);
        assertFalse(apiError1.equals(apiError2));
    }

    @Test
    @DisplayName("ApiError - Test Equals : Different object - Different Error")
    void apiError_EqualsTest_DifferentObjectDifferentError() {

        //Arrange

        //ApiError 1
        HttpStatus httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;
        String message = "Group already exist";
        String error = "Unprocessable entity";

        List<String> errors = new ArrayList<>();
        errors.add(error);

        //ApiError 2
        HttpStatus httpStatus2 = HttpStatus.UNPROCESSABLE_ENTITY;
        String message2 = "Group already exist";
        String error2 = "Processable entity";

        List<String> errors2 = new ArrayList<>();
        errors.add(error2);

        //Act
        ApiError apiError1 = new ApiError(httpStatus, message, errors);
        ApiError apiError2 = new ApiError(httpStatus2, message2, errors2);

        boolean result = apiError1.equals(apiError2);

        //Assert
        assertEquals(false,result);
        assertFalse(apiError1.equals(apiError2));
    }

    @Test
    @DisplayName("ApiError - Test Hash Code")
    void apiError_HashCodeTest() {

        //Arrange
        HttpStatus httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;
        String message = "Group already exist";
        String error = "Unprocessable entity";

        List<String> errors = new ArrayList<>();
        errors.add(error);

        //Act
        ApiError apiError = new ApiError(httpStatus, message, errors);

        //Expected
        int hashMessage = apiError.getMessage().hashCode();
        int hashMessageExpected = 2093069614;

        //Assert
        assertEquals(hashMessageExpected, hashMessage);
    }

    @Test
    @DisplayName("ApiError - Test Hash Code - HappyCase")
    void apiError_HashCodeTestHappyCase() {

        //Arrange
        HttpStatus httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;
        String message = "Group already exist";
        String error = "Unprocessable entity";

        List<String> errors = new ArrayList<>();
        errors.add(error);

        //Act
        ApiError apiError = new ApiError(httpStatus, message, errors);


        //Expected

        boolean result = apiError.hashCode() == apiError.hashCode();

        //Assert
        assertTrue(apiError.hashCode() == apiError.hashCode());
        assertEquals(true,result);
    }

    @Test
    @DisplayName("ApiError - Test Hash Code - SadCase")
    void apiError_HashCodeTestSadeCase() {

        //Arrange
        HttpStatus httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;
        String message = "Group already exist";
        String error = "Unprocessable entity";

        List<String> errors = new ArrayList<>();
        errors.add(error);

        HttpStatus newhttpStatus = HttpStatus.OK;
        String newMessage = "Person already exist";
        String newError = "Unprocessable entity";

        List<String> newErrors = new ArrayList<>();
        newErrors.add(newError);

        //Act
        ApiError apiError = new ApiError(httpStatus, message, errors);
        ApiError newApiError = new ApiError(newhttpStatus,newMessage,newError);

        //Expected

        boolean result = apiError.hashCode() == newApiError.hashCode();

        //Assert
        assertFalse(apiError.hashCode() == newApiError.hashCode());
        assertEquals(false,result);
    }
}