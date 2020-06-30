package com.finance.project.dtos.dtosAssemblers;

import com.finance.project.domainLayer.domainEntities.vosShared.PersonID;
import com.finance.project.dtos.dtos.SiblingsDTO;

import java.util.ArrayList;
import java.util.List;

public class SiblingsDTOAssembler {

    public static SiblingsDTO createDTOFromDomainObject(List<PersonID> siblings) {
        List<String> personSiblings = new ArrayList<>();

        for(PersonID personID : siblings){
            personSiblings.add(personID.getEmail().getEmail());
        }

        SiblingsDTO siblingsDTO = new SiblingsDTO(personSiblings);
        return siblingsDTO;
    }
}
