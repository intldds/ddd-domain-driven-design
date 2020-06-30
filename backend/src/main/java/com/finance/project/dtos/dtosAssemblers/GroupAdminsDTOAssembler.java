package com.finance.project.dtos.dtosAssemblers;

import com.finance.project.dtos.dtos.GroupAdminsDTO;
import com.finance.project.domainLayer.domainEntities.vosShared.PersonID;

import java.util.ArrayList;
import java.util.List;

public class GroupAdminsDTOAssembler {

    public static GroupAdminsDTO createDTOFromDomainObject(List<PersonID> peopleInCharge) {
        List<String> admins = new ArrayList<>();

        for(PersonID personID : peopleInCharge){
            admins.add(personID.getEmail().getEmail());
        }

        GroupAdminsDTO groupAdminsDTO = new GroupAdminsDTO(admins);
        return groupAdminsDTO;
    }
}
