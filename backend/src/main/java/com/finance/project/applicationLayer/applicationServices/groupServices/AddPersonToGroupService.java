package com.finance.project.applicationLayer.applicationServices.groupServices;

import com.finance.project.domainLayer.exceptions.InvalidArgumentsBusinessException;
import com.finance.project.domainLayer.exceptions.NotFoundArgumentsBusinessException;
import com.finance.project.domainLayer.repositoriesInterfaces.IGroupRepository;
import com.finance.project.domainLayer.repositoriesInterfaces.IPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.finance.project.dtos.dtos.AddPersonToGroupDTO;
import com.finance.project.dtos.dtos.GroupDTO;
import com.finance.project.dtos.dtosAssemblers.GroupDTOAssembler;
import com.finance.project.domainLayer.domainEntities.aggregates.group.Group;
import com.finance.project.domainLayer.domainEntities.vosShared.GroupID;
import com.finance.project.domainLayer.domainEntities.vosShared.PersonID;

import java.util.Optional;


@Service
public class AddPersonToGroupService {

    @Autowired
    private final IPersonRepository personRepository;
    @Autowired
    private final IGroupRepository groupRepository;


    /**
     * The constant PERSON_DOES_NOT_EXIST.
     */
    public static final String PERSON_DOES_NOT_EXIST = "Person does not exist";
    /**
     * The constant GROUP_DOES_NOT_EXIST.
     */
    public static final String GROUP_DOES_NOT_EXIST = "Group does not exist";
    /**
     * The constant PERSON_ALREADY_EXIST_IN_THE_GROUP.
     */
    public static final String PERSON_ALREADY_EXIST_IN_THE_GROUP = "Person is already member";


    public AddPersonToGroupService(IPersonRepository personRepository, IGroupRepository groupRepository) {
        this.personRepository = personRepository;
        this.groupRepository = groupRepository;
    }


    @Transactional
    public GroupDTO addPersonToGroup(AddPersonToGroupDTO addPersonToGroupDTO) {

        Group group;

        PersonID memberID = PersonID.createPersonID(addPersonToGroupDTO.getEmail());

        boolean personExist = personRepository.exists(memberID);

        if (!personExist) {
            throw new InvalidArgumentsBusinessException(PERSON_DOES_NOT_EXIST);

        } else {
            GroupID groupID = GroupID.createGroupID(addPersonToGroupDTO.getDenomination());

            Optional<Group> opGroup = groupRepository.findById(groupID);

            if (!opGroup.isPresent()) {
                throw new NotFoundArgumentsBusinessException(GROUP_DOES_NOT_EXIST);

            } else {
                group = opGroup.get();

                boolean isPersonAMember = group.isPersonAlreadyMember(memberID);

                if (isPersonAMember) {
                    throw new InvalidArgumentsBusinessException(PERSON_ALREADY_EXIST_IN_THE_GROUP);

                } else {
                    group.addMember(memberID);
                    groupRepository.addAndSaveMember(group, memberID);
                }
            }
            GroupDTO groupDTO = GroupDTOAssembler.createDTOFromDomainObject(
                    group.getGroupID().getDenomination(),
                    group.getDescription(),
                    group.getDateOfCreation());

            return groupDTO;
        }
    }
}