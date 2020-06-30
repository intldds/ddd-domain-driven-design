package com.finance.project.dtos.dtosAssemblers;

import com.finance.project.dtos.dtos.GroupAllMembersDTO;
import com.finance.project.dtos.dtos.GroupMemberClearanceDTO;

import java.util.List;

public class GroupAllMembersDTOAssembler {

    public static GroupAllMembersDTO createDTOFromDomainObject(List<GroupMemberClearanceDTO> allMembers) {

        GroupAllMembersDTO groupAllMembersDTO = new GroupAllMembersDTO(allMembers);
        return groupAllMembersDTO;
    }
}
