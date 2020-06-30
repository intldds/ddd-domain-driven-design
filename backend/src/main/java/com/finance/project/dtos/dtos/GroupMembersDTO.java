package com.finance.project.dtos.dtos;

import org.springframework.hateoas.RepresentationModel;

import java.util.List;
import java.util.Objects;

public class GroupMembersDTO extends RepresentationModel<GroupMembersDTO> {

    private List<String> members;

    public GroupMembersDTO(List<String> members) {
        this.members = members;
    }

    public GroupMembersDTO() {

    }

    public List<String> getMembers() {
        return members;
    }

    public void setMembers(List<String> members) {
        this.members = members;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupMembersDTO that = (GroupMembersDTO) o;
        return Objects.equals(getMembers(), that.getMembers());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getMembers());
    }
}
