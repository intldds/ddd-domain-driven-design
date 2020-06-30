package com.finance.project;

import com.finance.project.applicationLayer.applicationServices.groupServices.*;
import com.finance.project.applicationLayer.applicationServices.otherServices.CheckSiblingsService;
import com.finance.project.applicationLayer.applicationServices.otherServices.CheckGroupsFamilyService;
import com.finance.project.applicationLayer.applicationServices.personServices.*;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


@Profile("test")

@Configuration


public class TestConfiguration {

    // Person Services

    @MockBean
    public CreatePersonService createPersonService;

    @MockBean
    public CreatePersonCategoryService createPersonCategoryService;

    @MockBean
    public CreatePersonAccountService createPersonAccountService;

    @MockBean
    public CreatePersonTransactionService createPersonTransactionService;

    @MockBean
    public PersonSearchAccountRecordsService personSearchAccountRecordsService;


    // Group Services

    @MockBean
    public CreateGroupService createGroupService;

    @MockBean
    public AddPersonToGroupService uS003AddPersonToGroupService;

    @MockBean
    public CreateGroupCategoryService createGroupCategoryService;

    @MockBean
    public CreateGroupAccountService createGroupAccountService;

    @MockBean
    public CreateGroupTransactionService createGroupTransactionService;

    @MockBean
    public GroupSearchAccountRecordsService groupSearchAccountRecordsService;


    // Other Services

    @MockBean
    private CheckSiblingsService checkSiblingsService;

    @MockBean
    public CheckGroupsFamilyService checkGroupsFamilyService;

}
