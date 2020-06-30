package com.finance.project.applicationLayer.applicationServices.groupServices;

import com.finance.project.domainLayer.exceptions.InvalidArgumentsBusinessException;
import com.finance.project.domainLayer.exceptions.NotFoundArgumentsBusinessException;
import com.finance.project.domainLayer.repositoriesInterfaces.ICategoryRepository;
import com.finance.project.domainLayer.repositoriesInterfaces.IGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.finance.project.dtos.dtos.CreateGroupCategoryDTO;
import com.finance.project.dtos.dtos.GroupDTO;
import com.finance.project.dtos.dtosAssemblers.GroupDTOAssembler;
import com.finance.project.domainLayer.domainEntities.aggregates.group.Group;
import com.finance.project.domainLayer.domainEntities.vosShared.CategoryID;
import com.finance.project.domainLayer.domainEntities.vosShared.GroupID;
import com.finance.project.domainLayer.domainEntities.vosShared.PersonID;

import java.util.Optional;


@Service
public class CreateGroupCategoryService {

    @Autowired
    private IGroupRepository groupRepository;
    @Autowired
    private ICategoryRepository categoryRepository;

    /**
     * The constant SUCCESS.
     */
//Return messages
    /**
     * The constant SUCCESS.
     */
    public final static String SUCCESS = "Category created and added";
    /**
     * The constant PERSON_NOT_IN_CHARGE.
     */
    public final static String PERSON_NOT_IN_CHARGE = "Person is not in charge";
    /**
     * The constant CATEGORY_ALREADY_EXIST.
     */
    public final static String CATEGORY_ALREADY_EXIST = "Category already exist";
    /**
     * The constant GROUP_DOES_NOT_EXIST.
     */
    public final static String GROUP_DOES_NOT_EXIST = "Group does not exist";

    //US005.1 Como responsável de grupo, quero criar categoria e associá-la ao grupo.

    /**
     * Instantiates a new Us 005 1 create group category service.
     *
     * @param groupRepository    the group repository
     * @param categoryRepository the category repository
     */
    public CreateGroupCategoryService(IGroupRepository groupRepository, ICategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
        this.groupRepository = groupRepository;
    }

    /**
     * Create category as people in charge boolean dto.
     *
     * @param createGroupCategoryDTO the create group category dto
     * @return the boolean dto
     */
    public GroupDTO createCategoryAsPeopleInCharge(CreateGroupCategoryDTO createGroupCategoryDTO) {

        Group group;

        GroupID groupID = GroupID.createGroupID(createGroupCategoryDTO.getGroupDenomination());

        Optional<Group> opGroup = groupRepository.findById(groupID);

        if (!opGroup.isPresent()) {

            throw new NotFoundArgumentsBusinessException(GROUP_DOES_NOT_EXIST);

        } else {

            group = opGroup.get();

            //If Person is PeopleInCharge of a group, he/she already exists in personRepository

            PersonID personID = PersonID.createPersonID(createGroupCategoryDTO.getPersonEmail());
            boolean isPeopleInCharge = group.isPersonPeopleInCharge(personID);

            CategoryID categoryID = CategoryID.createCategoryID(createGroupCategoryDTO.getCategoryDenomination(), groupID);
            boolean categoryExistsInRepo = categoryRepository.existsById(categoryID);

            if (!isPeopleInCharge) {

                throw new InvalidArgumentsBusinessException(PERSON_NOT_IN_CHARGE);

            } else if (categoryExistsInRepo) {

                throw new InvalidArgumentsBusinessException(CATEGORY_ALREADY_EXIST);

            } else {

                group.addCategory(CategoryID.createCategoryID(createGroupCategoryDTO.getCategoryDenomination(), groupID));
                groupRepository.addAndSaveCategory(group);

            }
        }

        return GroupDTOAssembler.createDTOFromDomainObject(group.getGroupID().getDenomination(), group.getDescription(), group.getDateOfCreation());
    }
}
