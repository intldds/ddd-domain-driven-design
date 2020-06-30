package com.finance.project.domainLayer.domainEntities.vosShared;

import com.finance.project.domainLayer.entitiesInterfaces.OwnerID;
import com.finance.project.domainLayer.entitiesInterfaces.ValueObject;

import java.util.Objects;

/**
 * The type Category id.
 */
public class CategoryID implements ValueObject {

    private Denomination denomination;
    private OwnerID ownerID;


    //Constructor

    /**
     * Create category id category id.
     *
     * @param denomination the denomination
     * @param ownerID      the owner id
     * @return the category id
     */
    public static CategoryID createCategoryID(String denomination, OwnerID ownerID) {
        return new CategoryID(denomination, ownerID);
    }

    private CategoryID(String denomination, OwnerID ownerID) {
        if (denomination == null) {
            throw new IllegalArgumentException("CategoryID not created due to the fact that the denomination parameter hasn't a valid argument");
        } else if (ownerID == null) {
            throw new IllegalArgumentException("CategoryID not created due to the fact that the ownerID parameter hasn't a valid argument");
        }
        this.denomination = Denomination.createDenomination(denomination);
        this.ownerID = ownerID;
    }

    //get denomination

    /**
     * Gets denomination.
     *
     * @return the denomination
     */
    public Denomination getDenomination() {
        return denomination;
    }

    //Get OwnerID

    /**
     * Gets owner id.
     *
     * @return the owner id
     */
    public OwnerID getOwnerID() {
        return ownerID;
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
        if (!(o instanceof CategoryID)) return false;
        CategoryID that = (CategoryID) o;
        return denomination.equals(that.denomination) &&
                ownerID.equals(that.ownerID);
    }

    //HashCode

    /**
     * Hash code int.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        return Objects.hash(denomination, ownerID);
    }
}
