package com.finance.project.dtos.dtosAssemblers;

import com.finance.project.dtos.dtos.GroupDTO;
import com.finance.project.dtos.dtos.GroupListDTO;

import java.util.List;

public class GroupListDTOAssembler {

    public static GroupListDTO createDTOFromDomainObject(List<GroupDTO> listOfGroupDTO) {

        GroupListDTO groupListDTO = new GroupListDTO(listOfGroupDTO);
        return  groupListDTO;
    }
}
