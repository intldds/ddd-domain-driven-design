package com.finance.project.dtos.dtosAssemblers;

import com.finance.project.dtos.dtos.CreatePersonDTO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The type Create person dto assembler.
 */

public class CreatePersonDTOAssembler {

    /**
     * Create dto from primitve types create group dto.
     *
     * @param email         the email
     * @param name          the name
     * @param birthdate     the birthdate
     * @param birthplace    the birthplace
     * @return the create person dto
     */

    public static CreatePersonDTO createDTOFromPrimitiveTypes(String email, String name, String birthdate, String birthplace) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate localDateBirthdate = LocalDate.parse(birthdate, formatter);

        return new CreatePersonDTO(email, name, localDateBirthdate, birthplace);
    }
}
