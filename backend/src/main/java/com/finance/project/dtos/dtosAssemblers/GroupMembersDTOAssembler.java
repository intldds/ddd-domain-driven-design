package com.finance.project.dtos.dtosAssemblers;

import com.finance.project.domainLayer.domainEntities.vosShared.PersonID;
import com.finance.project.dtos.dtos.GroupMembersDTO;

import java.util.ArrayList;
import java.util.List;

public class GroupMembersDTOAssembler {

    public static GroupMembersDTO createDTOFromDomainObject(List<PersonID> members) {
        List<String> groupMembers = new ArrayList<>();

        for(PersonID personID : members){
            groupMembers.add(personID.getEmail().getEmail());
        }

        GroupMembersDTO groupMembersDTO = new GroupMembersDTO(groupMembers);
        return groupMembersDTO;
    }
}
