package com.finance.project.dataModel.dataAssemblers;

import com.finance.project.domainLayer.entitiesInterfaces.OwnerID;
import org.springframework.stereotype.Service;
import com.finance.project.domainLayer.domainEntities.aggregates.category.Category;
import com.finance.project.domainLayer.domainEntities.vosShared.GroupID;
import com.finance.project.domainLayer.domainEntities.vosShared.PersonID;
import com.finance.project.dataModel.dataModel.CategoryJpa;

@Service
public class CategoryDomainDataAssembler {

    public CategoryJpa toData(Category category) {

        String id = "";

        if (category.getCategoryID().getOwnerID() instanceof PersonID) {
            PersonID personID = (PersonID) category.getCategoryID().getOwnerID();
            id = personID.getEmail().getEmail();
        }

        if (category.getCategoryID().getOwnerID() instanceof GroupID) {
            GroupID groupID = (GroupID) category.getCategoryID().getOwnerID();
            id = groupID.getDenomination().getDenomination();
        }

        CategoryJpa categoryJpa = new CategoryJpa(id, category.getCategoryID().getDenomination().getDenomination());

        return categoryJpa;
    }

    public Category toDomain(CategoryJpa categoryJpa) {

        OwnerID ownerID;

        if (categoryJpa.getId().getOwnerID().contains("@")) {
            ownerID = PersonID.createPersonID(categoryJpa.getId().getOwnerID());
        } else {
            ownerID = GroupID.createGroupID(categoryJpa.getId().getOwnerID());
        }

        Category category = Category.createCategory(categoryJpa.getId().getDenomination(), ownerID);

        return category;
    }
}