package com.finance.project.dtos.dtosAssemblers;

import com.finance.project.dtos.dtos.CategoriesDTO;
import com.finance.project.domainLayer.domainEntities.vosShared.CategoryID;

import java.util.ArrayList;
import java.util.List;

public class CategoriesDTOAssembler {

    public static CategoriesDTO createDTOFromDomainObject(List<CategoryID> categoriesIDs) {
        List<String> categories = new ArrayList<>();

        for(CategoryID categoryID : categoriesIDs){
            categories.add(categoryID.getDenomination().getDenomination());
        }

        CategoriesDTO categoriesDTO = new CategoriesDTO(categories);
        return categoriesDTO;
    }
}
