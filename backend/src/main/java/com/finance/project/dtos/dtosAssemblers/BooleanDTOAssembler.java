package com.finance.project.dtos.dtosAssemblers;

import com.finance.project.dtos.dtos.BooleanDTO;

/**
 * The type Boolean dto assembler.
 */
public class BooleanDTOAssembler {

    /**
     * Create dto from primitive types boolean dto.
     *
     * @param result the result
     * @param msg    the msg
     * @return the boolean dto
     */
    public static BooleanDTO createDTOFromPrimitiveTypes(boolean result, String msg){
        return new BooleanDTO(result,msg);
    }
}
