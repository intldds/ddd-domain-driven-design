package com.finance.project.dtos.dtos;

import org.springframework.hateoas.RepresentationModel;

import java.util.List;
import java.util.Objects;

public class GroupAllMembersDTO extends RepresentationModel<GroupAllMembersDTO> {

    private List<GroupMemberClearanceDTO> allMembers;

    public GroupAllMembersDTO(List<GroupMemberClearanceDTO> allMembers) {
        this.allMembers = allMembers;
    }

    public GroupAllMembersDTO() {
    }

    public List<GroupMemberClearanceDTO> getAllMembers() {
        return allMembers;
    }

    public void setAllMembers(List<GroupMemberClearanceDTO> allMembers) {
        this.allMembers = allMembers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GroupAllMembersDTO)) return false;
        if (!super.equals(o)) return false;
        GroupAllMembersDTO that = (GroupAllMembersDTO) o;
        return Objects.equals(getAllMembers(), that.getAllMembers());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getAllMembers());
    }
}
