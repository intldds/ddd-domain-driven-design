package com.finance.project.dtos.dtosAssemblers;

import com.finance.project.dtos.dtos.CheckIfSiblingsDTO;

/**
 * The type Check if siblings dto assembler.
 *
 * @author Ala Matos
 */
public class CheckIfSiblingsDTOAssembler {
    /**
     * Create dto from primitive types check if siblings dto.
     *
     * @param email         the email
     * @param siblingsEmail the siblings email
     * @return the check if siblings dto
     */
    public static CheckIfSiblingsDTO createDTOFromPrimitiveTypes(String email, String siblingsEmail) {
        CheckIfSiblingsDTO checkIfSiblingsDTO = new CheckIfSiblingsDTO(email, siblingsEmail);
        return checkIfSiblingsDTO;
    }
}
