package com.finance.project.dtos.dtos;

import java.util.Objects;

public class GroupMemberClearanceDTO {

    private String memberID;
    private String clearance;

    public GroupMemberClearanceDTO(String memberID, String clearance) {
        this.memberID = memberID;
        this.clearance = clearance;
    }

    public GroupMemberClearanceDTO() {

    }

    public String getMemberID() {
        return memberID;
    }

    public String getClearance() {
        return clearance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GroupMemberClearanceDTO)) return false;
        GroupMemberClearanceDTO that = (GroupMemberClearanceDTO) o;
        return Objects.equals(memberID, that.memberID) &&
                Objects.equals(clearance, that.clearance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberID, clearance);
    }
}
