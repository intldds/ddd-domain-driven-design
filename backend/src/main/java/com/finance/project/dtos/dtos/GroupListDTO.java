package com.finance.project.dtos.dtos;

import org.springframework.hateoas.RepresentationModel;

import java.util.List;

public class GroupListDTO extends RepresentationModel<GroupListDTO> {

    private List<GroupDTO> groups;

    public GroupListDTO(List<GroupDTO> groups) {
        this.groups = groups;
    }

    public GroupListDTO() {
    }

    public List<GroupDTO> getGroups() {
        return groups;
    }

    public void setGroups(List<GroupDTO> groups) {
        this.groups = groups;
    }

}