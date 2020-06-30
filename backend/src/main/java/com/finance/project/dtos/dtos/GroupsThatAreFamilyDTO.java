package com.finance.project.dtos.dtos;

import org.springframework.hateoas.RepresentationModel;

import java.util.List;
import java.util.Objects;

/**
 * The type Groups that are family dto.
 */
public class GroupsThatAreFamilyDTO extends RepresentationModel<GroupsThatAreFamilyDTO> {

    private List<GroupIDDTO> groupThatAreFamily;

    /**
     * Instantiates a new Groups that are family dto.
     *
     * @param groupThatAreFamily the group that are family
     */
    public GroupsThatAreFamilyDTO(List<GroupIDDTO> groupThatAreFamily) {
        this.groupThatAreFamily = groupThatAreFamily;
    }

    /**
     * Gets group that are family.
     *
     * @return the group that are family
     */
    public List<GroupIDDTO> getGroupThatAreFamily() {
        return groupThatAreFamily;
    }

    /**
     * Equals boolean.
     *
     * @param o the o
     * @return the boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GroupsThatAreFamilyDTO)) return false;
        GroupsThatAreFamilyDTO that = (GroupsThatAreFamilyDTO) o;
        return groupThatAreFamily.equals(that.groupThatAreFamily);
    }

    /**
     * Hash code int.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        return Objects.hash(groupThatAreFamily);
    }
}
