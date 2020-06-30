package com.finance.project.dtos.dtos;

import org.springframework.hateoas.RepresentationModel;

import java.util.List;
import java.util.Objects;

public class GroupAdminsDTO extends RepresentationModel<GroupAdminsDTO> {

    private List<String> peopleInCharge;

    public GroupAdminsDTO(List<String> peopleInCharge) {
        this.peopleInCharge = peopleInCharge;
    }

    public GroupAdminsDTO() {

    }

    public List<String> getPeopleInCharge() {
        return peopleInCharge;
    }

    public void setPeopleInCharge(List<String> peopleInCharge) {
        this.peopleInCharge = peopleInCharge;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupAdminsDTO that = (GroupAdminsDTO) o;
        return Objects.equals(getPeopleInCharge(), that.getPeopleInCharge());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getPeopleInCharge());
    }
}
