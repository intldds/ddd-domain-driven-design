package com.finance.project.dtos.dtosAssemblers;

import com.finance.project.dtos.dtos.PersonEmailDTO;

/**
 * @author Ala Matos
 */

public class PersonEmailDTOAssembler {

    /**
     * Create dto from primitive types boolean dto.
     *
     * @param email
     * @return the PersonEmailDTO
     */

    public static PersonEmailDTO createPersonEmailDTO(String email) {
        return new PersonEmailDTO(email);
    }
}
