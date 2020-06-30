package com.finance.project.dtos.dtosAssemblers;

import com.finance.project.dtos.dtos.GroupMemberClearanceDTO;

public class GroupMemberClearanceDTOAssembler {

    public static GroupMemberClearanceDTO createDTOFromDomainObject(String memberID, String clearance) {

        GroupMemberClearanceDTO groupMemberClearanceDTO = new GroupMemberClearanceDTO(memberID, clearance);
        return groupMemberClearanceDTO;
    }

}
