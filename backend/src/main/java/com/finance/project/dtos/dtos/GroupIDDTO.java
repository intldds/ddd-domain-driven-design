package com.finance.project.dtos.dtos;

import org.springframework.hateoas.RepresentationModel;

import java.util.Objects;

/**
 * The type Group iddto.
 */
public class GroupIDDTO extends RepresentationModel<GroupIDDTO> {

    private String denomination;

    /**
     * Instantiates a new Group iddto.
     *
     * @param groupID the group id
     */
    public GroupIDDTO(String groupID) {
        this.denomination = groupID;
    }

    /**
     * Gets denomination.
     *
     * @return the denomination
     */
    public String getDenomination() { return denomination; }

    /**
     * Equals boolean.
     *
     * @param o the o
     * @return the boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GroupIDDTO)) return false;
        GroupIDDTO that = (GroupIDDTO) o;
        return denomination.equals(that.denomination);
    }

    /**
     * Hash code int.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        return Objects.hash(denomination);
    }
}
