package com.finance.project.applicationLayer.applicationServices.groupServices;

import com.finance.project.controllerLayer.integrationTests.AbstractTest;
import com.finance.project.domainLayer.domainEntities.aggregates.group.Group;
import com.finance.project.domainLayer.domainEntities.vosShared.*;
import com.finance.project.domainLayer.exceptions.InvalidArgumentsBusinessException;
import com.finance.project.domainLayer.exceptions.NotFoundArgumentsBusinessException;
import com.finance.project.domainLayer.repositoriesInterfaces.ICategoryRepository;
import com.finance.project.domainLayer.repositoriesInterfaces.IGroupRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import com.finance.project.dtos.dtos.CreateGroupCategoryDTO;
import com.finance.project.dtos.dtos.GroupDTO;
import com.finance.project.dtos.dtosAssemblers.CreateGroupCategoryDTOAssembler;
import com.finance.project.dtos.dtosAssemblers.GroupDTOAssembler;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class CreateGroupCategoryServiceTest extends AbstractTest {

    @Mock
    private IGroupRepository groupRepository;
    @Mock
    private ICategoryRepository categoryRepository;

    private CreateGroupCategoryService createGroupCategoryService;
    private Group fontesFamily;
    private GroupID fontesFamilyID;

    @BeforeEach
    public void init() {

        //Manuel
        String manuelEmail = "manuel@gmail.com";
        PersonID manuelPersonID = PersonID.createPersonID(manuelEmail);

        //Ilda
        String ildaEmail = "ilda@gmail.com";
        PersonID ildaPersonID = PersonID.createPersonID(ildaEmail);

        //Paulo
        String pauloEmail = "paulo@gmail.com";
        PersonID pauloPersonID = PersonID.createPersonID(pauloEmail);

        //Helder
        String helderEmail = "helder@gmail.com";
        PersonID helderPersonID = PersonID.createPersonID(helderEmail);

        // Group
        String dateOfCreation = "2020-06-01";
        String fontesFamilyDenomination = "Fontes Family";
        String fontesFamilyDescription = "All members from Fontes family";

        this.fontesFamily = Group.createGroup(fontesFamilyDenomination, fontesFamilyDescription, dateOfCreation, manuelPersonID);
        this.fontesFamilyID = GroupID.createGroupID(fontesFamilyDenomination);
        fontesFamily.addPersonInCharge(ildaPersonID);
        fontesFamily.addMember(pauloPersonID);
        fontesFamily.addMember(helderPersonID);

        //Salary
        String salaryDenomination = "Salary";
        CategoryID salaryID = CategoryID.createCategoryID(salaryDenomination, fontesFamilyID);
        fontesFamily.addCategory(salaryID);

        //Draw Money
        String drawMoneyDenomination = "Draw Money";
        CategoryID drawMoneyID = CategoryID.createCategoryID(drawMoneyDenomination, fontesFamilyID);
        fontesFamily.addCategory(drawMoneyID);

        //IRS
        String irsDenomination = "IRS";
        CategoryID irsID = CategoryID.createCategoryID(irsDenomination, fontesFamilyID);
        fontesFamily.addCategory(irsID);

        //Food
        String foodDenomination = "Food";
        CategoryID foodID = CategoryID.createCategoryID(foodDenomination, fontesFamilyID);
        fontesFamily.addCategory(foodID);

        //Water Bill
        String waterBillDenomination = "Water Bill";
        CategoryID waterBillID = CategoryID.createCategoryID(waterBillDenomination, fontesFamilyID);
        fontesFamily.addCategory(waterBillID);

        //Netflix
        String netflixDenomination = "Netflix";
        CategoryID netflixID = CategoryID.createCategoryID(netflixDenomination, fontesFamilyID);
        fontesFamily.addCategory(netflixID);

    }

    // Sucess

    @Test
    @DisplayName("Test For createCategoryAsPeopleInCharge() | Fontes Family | Equipment | Success")
    void createCategoryAsPeopleInCharge_FontesFamily_Success() {

        // Arrange
        String personEmail = "manuel@gmail.com";
        String groupDenomination = "Fontes Family";
        String groupDescription = "All members from Fontes family";
        LocalDate dateOfCreation = LocalDate.of(2020, 06, 01);

        String categoryDenomination = "Equipment";

        // To Search
        CategoryID categoryID = CategoryID.createCategoryID(categoryDenomination, fontesFamilyID);

        // Returning an Optional<Group> Fontes Family
        Mockito.when(groupRepository.findById(fontesFamilyID)).thenReturn(Optional.of(fontesFamily));

        // Returning False
        Mockito.when(categoryRepository.existsById(categoryID)).thenReturn(false);

        // DTO
        CreateGroupCategoryDTO createGroupCategoryDTO = CreateGroupCategoryDTOAssembler.createDTOFromPrimitiveTypes(personEmail, groupDenomination, categoryDenomination);

        // Expected GroupDTO
        GroupDTO expectedGroupDTO = GroupDTOAssembler.createDTOFromDomainObject(Denomination.createDenomination(groupDenomination), Description.createDescription(groupDescription), DateOfCreation.createDateOfCreation(dateOfCreation));

        // Service
        createGroupCategoryService = new CreateGroupCategoryService(groupRepository, categoryRepository);

        // Act
        GroupDTO result = createGroupCategoryService.createCategoryAsPeopleInCharge(createGroupCategoryDTO);

        // Assert
        assertEquals(expectedGroupDTO, result);
    }


    // Person is not in charge

    @Test
    @DisplayName("Test For createCategoryAsPeopleInCharge() | Fontes Family | Equipment | Fail | Person Not In Charge")
    void createCategoryAsPeopleInCharge_Runners_Fail_PeopleNotInCharge() {

        // Arrange
        String personEmail = "paulo@gmail.com";
        String groupDenomination = "Fontes Family";
        String categoryDenomination = "Equipment";

        // Returning an Optional<Group> Fontes Family
        Mockito.when(groupRepository.findById(fontesFamilyID)).thenReturn(Optional.of(fontesFamily));

        // DTO
        CreateGroupCategoryDTO createGroupCategoryDTO = CreateGroupCategoryDTOAssembler.createDTOFromPrimitiveTypes(personEmail, groupDenomination, categoryDenomination);

        // Service
        createGroupCategoryService = new CreateGroupCategoryService(groupRepository, categoryRepository);

        // Act
        Throwable thrown = assertThrows(InvalidArgumentsBusinessException.class, () -> createGroupCategoryService.createCategoryAsPeopleInCharge(createGroupCategoryDTO));

        // Assert
        assertEquals(thrown.getMessage(), CreateGroupCategoryService.PERSON_NOT_IN_CHARGE);
    }


    // Category already exists - throw exception

    @Test
    @DisplayName("Test For createCategoryAsPeopleInCharge() | Fontes Family | Salary | Fail | Category Already Exists")
    void createCategoryAsPeopleInCharge_House_Fail_PeopleNotInCharge() {

        // Arrange
        String personEmail = "manuel@gmail.com";
        String groupDenomination = "Fontes Family";
        String categoryDenomination = "Salary";

        // To Search
        CategoryID categoryID = CategoryID.createCategoryID(categoryDenomination, fontesFamilyID);

        // Returning an Optional<Group> Fontes Family
        Mockito.when(groupRepository.findById(fontesFamilyID)).thenReturn(Optional.of(fontesFamily));

        // Returning False
        Mockito.when(categoryRepository.existsById(categoryID)).thenReturn(true);

        // DTO
        CreateGroupCategoryDTO createGroupCategoryDTO = CreateGroupCategoryDTOAssembler.createDTOFromPrimitiveTypes(personEmail, groupDenomination, categoryDenomination);

        // Service
        createGroupCategoryService = new CreateGroupCategoryService(groupRepository, categoryRepository);

        // Act
        Throwable thrown = assertThrows(InvalidArgumentsBusinessException.class, () -> createGroupCategoryService.createCategoryAsPeopleInCharge(createGroupCategoryDTO));

        // Assert
        assertEquals(thrown.getMessage(), CreateGroupCategoryService.CATEGORY_ALREADY_EXIST);
    }

    @Test
    @DisplayName("Test For createCategoryAsPeopleInCharge() | Fontes Family | Equipment | Fail | Group Does Not Exist")
    void createCategoryAsPeopleInCharge_Runners_Fail_GroupDoesNotExist() {

        // Arrange
        String personEmail = "paulo@gmail.com";
        String groupDenomination = "Friends";
        String categoryDenomination = "Equipment";

        // Returning an Optional<Group> Fontes Family
        Mockito.when(groupRepository.findById(fontesFamilyID)).thenReturn(Optional.empty());

        // DTO
        CreateGroupCategoryDTO createGroupCategoryDTO = CreateGroupCategoryDTOAssembler.createDTOFromPrimitiveTypes(personEmail, groupDenomination, categoryDenomination);

        // Service
        createGroupCategoryService = new CreateGroupCategoryService(groupRepository, categoryRepository);

        // Act
        Throwable thrown = assertThrows(NotFoundArgumentsBusinessException.class, () -> createGroupCategoryService.createCategoryAsPeopleInCharge(createGroupCategoryDTO));

        // Assert
        assertEquals(thrown.getMessage(), CreateGroupCategoryService.GROUP_DOES_NOT_EXIST);
    }

}