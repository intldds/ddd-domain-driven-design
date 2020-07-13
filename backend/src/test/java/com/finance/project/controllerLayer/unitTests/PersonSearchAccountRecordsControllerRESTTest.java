package com.finance.project.controllerLayer.unitTests;

import com.finance.project.applicationLayer.applicationServices.personServices.PersonSearchAccountRecordsService;
import com.finance.project.controllerLayer.controllersREST.personControllers.PersonSearchAccountRecordsControllerREST;
import com.finance.project.controllerLayer.integrationTests.AbstractTest;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PersonSearchAccountRecordsControllerRESTTest extends AbstractTest {

    @Mock
    private PersonSearchAccountRecordsService personSearchAccountRecordsService;

    @Autowired
    private PersonSearchAccountRecordsControllerREST personSearchAccountRecordsControllerREST;



}