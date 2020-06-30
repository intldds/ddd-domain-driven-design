package com.finance.project.domainLayer.domainEntities.aggregates.category;

import com.finance.project.domainLayer.domainEntities.vosShared.CategoryID;
import com.finance.project.domainLayer.entitiesInterfaces.Entity;
import com.finance.project.domainLayer.entitiesInterfaces.OwnerID;

import java.util.Objects;

/**
 * The type Category.
 */
public class Category implements Entity {

    private final CategoryID categoryID;

    //Constructor

    /**
     * Create category category.
     *
     * @param denomination the denomination
     * @param ownerID      the owner id
     * @return the category
     */
    public static Category createCategory(String denomination, OwnerID ownerID) {
        return new Category(denomination, ownerID);
    }

    private Category(String denomination, OwnerID ownerID) {
        if (denomination == null || denomination.equals("")) {
            throw new IllegalArgumentException("Category not created due to the fact that the denomination parameter hasn't a valid argument");
        } else if (ownerID == null) {
            throw new IllegalArgumentException("Category not created due to the fact that the ownerID parameter hasn't a valid argument");
        }
        this.categoryID = CategoryID.createCategoryID(denomination, ownerID);
    }


    //Get denomination

    /**
     * Gets category id.
     *
     * @return the category id
     */
    public CategoryID getCategoryID() {
        return categoryID;
    }

    //Equals

    /**
     * Equals boolean.
     *
     * @param o the o
     * @return the boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category)) return false;
        Category category = (Category) o;

        if (!this.categoryID.equals(category.categoryID)) {
            return false;
        }

        return true;
    }


    //hashcode

    /**
     * Hash code int.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        return Objects.hash(categoryID);
    }

}
