package com.finance.project.dtos.dtosAssemblers;

import com.finance.project.dtos.dtos.GroupIDDTO;
import com.finance.project.dtos.dtos.GroupsThatAreFamilyDTO;

import java.util.List;

/**
 * The type Groups that are family dto assembler.
 */
public class GroupsThatAreFamilyDTOAssembler {

    /**
     * Create dto from domain object groups that are family dto.
     *
     * @param listOfGroupIDDTO the list of group iddto
     * @return the groups that are family dto
     */
    public static GroupsThatAreFamilyDTO createDTOFromDomainObject(List<GroupIDDTO> listOfGroupIDDTO) {

        GroupsThatAreFamilyDTO groupsThatAreFamilyDTO = new GroupsThatAreFamilyDTO(listOfGroupIDDTO);
        return groupsThatAreFamilyDTO;
    }
}
