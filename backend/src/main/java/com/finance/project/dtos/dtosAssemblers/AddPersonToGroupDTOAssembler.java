package com.finance.project.dtos.dtosAssemblers;

import com.finance.project.dtos.dtos.AddPersonToGroupDTO;

/**
 * The type Add person to group dto assembler.
 *
 * @author Elisabete_Cavaleiro
 */


public class AddPersonToGroupDTOAssembler {

    /**
     * Create data transfer object primitives add person to group dto.
     *
     * @param email        the email
     * @param denomination the denomination
     * @return the add person to group dto
     */
    public static AddPersonToGroupDTO createDataTransferObject_Primitives(String email, String denomination){
    return new AddPersonToGroupDTO(email, denomination);
}
}