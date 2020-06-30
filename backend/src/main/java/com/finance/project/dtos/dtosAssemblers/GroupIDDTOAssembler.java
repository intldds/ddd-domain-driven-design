package com.finance.project.dtos.dtosAssemblers;

import com.finance.project.dtos.dtos.GroupIDDTO;
import com.finance.project.domainLayer.domainEntities.vosShared.GroupID;

/**
 * The type Group iddto assembler.
 */
public class GroupIDDTOAssembler {

    /**
     * Create dto from domain object group iddto.
     *
     * @param groupID the group id
     * @return the group iddto
     */
    public static GroupIDDTO createDTOFromDomainObject(GroupID groupID) {
        GroupIDDTO groupIDDTO = new GroupIDDTO(groupID.getDenomination().getDenomination());
        return groupIDDTO;
    }
}
