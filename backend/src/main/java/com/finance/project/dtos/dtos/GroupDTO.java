package com.finance.project.dtos.dtos;

import org.springframework.hateoas.RepresentationModel;

import java.util.Objects;

public class GroupDTO extends RepresentationModel<GroupDTO> {

    private String denomination;
    private String description;
    private String dateOfCreation;
    private String ledger;

    public GroupDTO(String denomination, String description, String dateOfCreation) {
        this.denomination = denomination;
        this.description = description;
        this.dateOfCreation = dateOfCreation;
        this.ledger = "";
    }

    public GroupDTO(String denomination, String description, String dateOfCreation, String ledger) {
        this.denomination = denomination;
        this.description = description;
        this.dateOfCreation = dateOfCreation;
        this.ledger = ledger;
    }

    public GroupDTO() {
    }

    // Getters & Setters

    public String getDenomination() {
        return denomination;
    }

    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(String dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public String getLedger() {
        return ledger;
    }

    public void setLedger(String ledgerID) {
        this.ledger = ledgerID;
    }


    // Equals & hashCode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupDTO groupDTO = (GroupDTO) o;
        return Objects.equals(getDenomination(), groupDTO.getDenomination()) &&
                Objects.equals(getDescription(), groupDTO.getDescription()) &&
                Objects.equals(getDateOfCreation(), groupDTO.getDateOfCreation());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDenomination(), getDescription(), getDateOfCreation());
    }
}
