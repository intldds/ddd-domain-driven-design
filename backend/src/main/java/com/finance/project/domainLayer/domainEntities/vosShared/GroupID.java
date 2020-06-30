package com.finance.project.domainLayer.domainEntities.vosShared;

import com.finance.project.domainLayer.entitiesInterfaces.OwnerID;
import com.finance.project.domainLayer.entitiesInterfaces.ValueObject;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.io.Serializable;
import java.util.Objects;

/**
 * The type Group id.
 */

@Embeddable
public class GroupID implements OwnerID, ValueObject, Serializable {

    @Embedded
    private Denomination denomination;


    //Constructor GroupID

    /**
     * Create group id group id.
     *
     * @param denomination the denomination
     * @return the group id
     */
    public static GroupID createGroupID(String denomination) {
        return new GroupID(denomination);
    }

    private GroupID(String denomination) {
        if (denomination == null || denomination.equals("")) {
            throw new IllegalArgumentException("GroupID not created. The denomination parameter can't be null or a empty string");
        } else {
            this.denomination = Denomination.createDenomination(denomination);
        }
    }

    public GroupID() {
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
        if (o == null || getClass() != o.getClass()) return false;
        GroupID groupID = (GroupID) o;
        return Objects.equals(denomination, groupID.denomination);
    }


    //Hash Code

    /**
     * Hash code int.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        return Objects.hash(denomination);
    }


    //Get methods

    /**
     * Gets denomination.
     *
     * @return the denomination
     */
    public Denomination getDenomination() {
        return denomination;
    }


    @Override
    public String toString() {
        return "GroupID{" +
                "denomination=" + denomination.toString() +
                '}';
    }
}
