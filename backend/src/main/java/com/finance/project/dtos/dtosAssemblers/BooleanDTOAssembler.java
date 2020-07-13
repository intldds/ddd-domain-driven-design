package com.finance.project.dtos.dtosAssemblers;

import com.finance.project.dtos.dtos.BooleanDTO;


public class BooleanDTOAssembler {

    public static BooleanDTO createDTOFromPrimitiveTypes(boolean result, String msg){
        return new BooleanDTO(result,msg);
    }
}
