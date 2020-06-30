package com.finance.project.controllerLayer.unitTests;

import com.finance.project.applicationLayer.applicationServices.otherServices.CheckSiblingsService;
import com.finance.project.controllerLayer.controllersREST.otherControllers.CheckSiblingsControllerREST;
import com.finance.project.controllerLayer.integrationTests.AbstractTest;
import com.finance.project.domainLayer.exceptions.InvalidArgumentsBusinessException;
import com.finance.project.dtos.dtosAssemblers.BooleanDTOAssembler;
import com.finance.project.dtos.dtosAssemblers.CheckIfSiblingsDTOAssembler;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.finance.project.dtos.dtos.BooleanDTO;
import com.finance.project.dtos.dtos.CheckIfSiblingsDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


/**
 * @author Ala Matos
 */
public class CheckSiblingsControllerRESTTest extends AbstractTest {



    @Autowired
    private CheckSiblingsControllerREST _checkSiblingsControllerREST;
    @Mock
    private CheckSiblingsService checkSiblingsService;

    /*

    @Test
    @DisplayName("Test Controller_US01 - hulk | wolverine | siblings")
    void controller_US01() throws Exception {

//        Arrange people

        String pauloEmail = "miguel@gmail.com";
        String helderEmail = "bernardo@gmail.com";


//        Arrange expected result

        boolean result = true;
        String expectedMsg = CheckSiblingsService.SUCCESS;
        BooleanDTO expectedSiblingsAnalysis = BooleanDTOAssembler.createDTOFromPrimitiveTypes(result, expectedMsg);


//        Arrange checkIfSiblingsDTO

        CheckIfSiblingsDTO checkIfSiblingsDTO = CheckIfSiblingsDTOAssembler.createDTOFromPrimitiveTypes(pauloEmail, helderEmail);

        //Act
        // Mock the behaviour of the service's createCategoryAsPersonInCharge method,
        // so it does not depend on other parts (e.g. DB)
        Mockito.when(checkSiblingsService.checkIfSiblings(checkIfSiblingsDTO))
                .thenReturn(expectedSiblingsAnalysis);

//      Act expected object
        ResponseEntity<Object> expectedSiblingsResult = new ResponseEntity<>(expectedSiblingsAnalysis, HttpStatus.OK);

//      Act actual result
        ResponseEntity<Object> actualResultSiblings = _checkSiblingsControllerREST.checkIfSiblings(pauloEmail, helderEmail);

//        Assert
        assertEquals(expectedSiblingsResult, actualResultSiblings);
    }


     */


    @Test
    @DisplayName("Test Controller_US01 - hulk | ironMan | Not siblings")
    void controller_US01NotSiblings() throws Exception {

//        Arrange people

        String pauloEmail = "miguel@gmail.com";
        String ruiEmail = "francis@gmail.com";


//        Arrange expected result

        boolean resultNotSiblings = false;
        String expectedMsgNotSiblings = CheckSiblingsService.FAIL;
        BooleanDTO expectedNotSiblingsAnalysis = BooleanDTOAssembler.createDTOFromPrimitiveTypes(resultNotSiblings, expectedMsgNotSiblings);

//        Arrange checkIfSiblingsDTO
        CheckIfSiblingsDTO checkINotSiblingsDTO = CheckIfSiblingsDTOAssembler.createDTOFromPrimitiveTypes(pauloEmail, ruiEmail);

//        Act
        // Mock the behaviour of the service's createCategoryAsPersonInCharge method,
        // so it does not depend on other parts (e.g. DB)
        Mockito.when(checkSiblingsService.checkIfSiblings(checkINotSiblingsDTO))
                .thenThrow(new InvalidArgumentsBusinessException(CheckSiblingsService.FAIL));

//       Act expected object
//        ResponseEntity<Object> expectedNotSiblingsResult = new ResponseEntity<>(expectedNotSiblingsAnalysis, HttpStatus.OK);

//       Act actual result

        Throwable thrown = assertThrows(InvalidArgumentsBusinessException.class, () -> _checkSiblingsControllerREST.checkIfSiblings(pauloEmail, ruiEmail));

//        Assert

        assertEquals(thrown.getMessage(), "Not Siblings");
    }


}
