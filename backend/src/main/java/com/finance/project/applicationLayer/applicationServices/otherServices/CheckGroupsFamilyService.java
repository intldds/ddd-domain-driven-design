package com.finance.project.applicationLayer.applicationServices.otherServices;

import com.finance.project.domainLayer.domainEntities.vosShared.GroupID;
import com.finance.project.domainLayer.domainEntities.vosShared.PersonID;
import com.finance.project.domainLayer.exceptions.NotFoundArgumentsBusinessException;
import com.finance.project.domainLayer.repositoriesInterfaces.IGroupRepository;
import com.finance.project.domainLayer.repositoriesInterfaces.IPersonRepository;
import com.finance.project.dtos.dtosAssemblers.GroupIDDTOAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.finance.project.dtos.dtos.GroupDTO;
import com.finance.project.dtos.dtos.GroupIDDTO;
import com.finance.project.dtos.dtos.GroupMembersDTO;
import com.finance.project.dtos.dtos.GroupsThatAreFamilyDTO;
import com.finance.project.dtos.dtosAssemblers.GroupDTOAssembler;
import com.finance.project.dtos.dtosAssemblers.GroupMembersDTOAssembler;
import com.finance.project.dtos.dtosAssemblers.GroupsThatAreFamilyDTOAssembler;
import com.finance.project.domainLayer.domainEntities.aggregates.group.Group;
import com.finance.project.domainLayer.domainEntities.aggregates.person.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class CheckGroupsFamilyService {

    @Autowired
    private final IGroupRepository groupRepository;
    @Autowired
    private final IPersonRepository personRepository;


    /**
     * The constant GROUP_DOES_NOT_EXIST.
     */
    public static final String GROUP_DOES_NOT_EXIST = "Group does not exist";


    public CheckGroupsFamilyService(IGroupRepository groupRepository, IPersonRepository personRepository) {
        this.groupRepository = groupRepository;
        this.personRepository = personRepository;
    }

    public GroupsThatAreFamilyDTO groupsThatAreFamily() {
        List<Group> listGroups = this.groupRepository.findAll();
        List<GroupIDDTO> listToReturn = new ArrayList<>();
        GroupID groupID;
        for (Group group : listGroups) {
            groupID = isFamily(group);
            if (groupID != null) {
                listToReturn.add(GroupIDDTOAssembler.createDTOFromDomainObject(groupID));
            }
        }
        GroupsThatAreFamilyDTO groupsThatAreFamilyDTO = GroupsThatAreFamilyDTOAssembler.createDTOFromDomainObject(listToReturn);
        return groupsThatAreFamilyDTO;
    }


    // Check if a group is Family

    private GroupID isFamily(Group group) {
        List<PersonID> allMembers = group.getAllMembers();
        List<PersonID> allMembersAux = new ArrayList<>();
        boolean father = false;
        boolean mother = false;
        for (int i = 0; i < allMembers.size(); i++) {
            for (int j = 0; j < allMembers.size(); j++) {
                Optional<Person> opPersonA = this.personRepository.findById(allMembers.get(j));
                if (opPersonA.isPresent()) {
                    Person personA = opPersonA.get();
                    boolean isFather = allMembers.get(i).equals(personA.getFather());
                    boolean isMother = allMembers.get(i).equals(personA.getMother());
                    if ((i != j) && isFather || isMother) {

                        // Find father
                        if (!allMembersAux.contains(allMembers.get(i)) && isFather && !father) {
                            allMembersAux.add(allMembers.get(i));
                            father = true;
                        }
                        // Find mother
                        if (!allMembersAux.contains(allMembers.get(i)) && isMother && !mother) {
                            allMembersAux.add(allMembers.get(i));
                            mother = true;
                        }
                        // Add the children in case of find a father or a mother
                        if (!allMembersAux.contains(allMembers.get(j))) {
                            allMembersAux.add(allMembers.get(j));
                        }
                    }
                }
            }
        }

        // Return null if father or mother is null
        if (!mother || !father) {
            return null;
        }
        // Compare the List's
        for (PersonID personID : allMembers) {
            if (!allMembersAux.contains(personID)) {
                return null;
            }
        }
        return group.getGroupID();
    }


    // Getters

    @Transactional
    public GroupDTO getGroupByDenomination(String denomination) {
        Group group;
        GroupID groupID = GroupID.createGroupID(denomination);
        Optional<Group> opGroup = groupRepository.findById(groupID);
        if (!opGroup.isPresent()) {

            throw new NotFoundArgumentsBusinessException(GROUP_DOES_NOT_EXIST);

        } else {
            group = opGroup.get();
        }

        return GroupDTOAssembler.createDTOFromDomainObject(group.getGroupID().getDenomination(), group.getDescription(), group.getDateOfCreation());

    }

    @Transactional
    public GroupMembersDTO getGroupAllMembers(String denomination) {
        Group group;
        GroupID groupID = GroupID.createGroupID(denomination);
        Optional<Group> opGroup = groupRepository.findById(groupID);
        if (!opGroup.isPresent()) {

            throw new NotFoundArgumentsBusinessException(GROUP_DOES_NOT_EXIST);

        } else {
            group = opGroup.get();
        }
        List<PersonID> allMembers = group.getAllMembers();

        return GroupMembersDTOAssembler.createDTOFromDomainObject(allMembers);
    }
}