package com.finance.project.dtos.dtos;

import com.finance.project.domainLayer.domainEntities.vosShared.LedgerID;

import java.time.LocalDate;
import java.util.Objects;


public class CreateGroupDTO {

    private String email;
    private String denomination;
    private String description;
    private String personInCharge;
    private String dateOfCreation;
    private String ledgerID;


    public CreateGroupDTO(String email, String denomination, String description) {
        this.email = email;
        this.denomination = denomination;
        this.description = description;
    }


    // with personInCharge
    public CreateGroupDTO(String email, String denomination, String personInCharge,
                          String description) {
        this.email = email;
        this.denomination = denomination;
        this.description = description;
        this.personInCharge = personInCharge;
        this.dateOfCreation = LocalDate.now().toString();
        this.ledgerID = LedgerID.createLedgerID().toString();
    }


    // Setters & Getters
    public void setDateOfCreation(String dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public void setLedgerID(String ledgerID) {
        this.ledgerID = ledgerID;
    }

    public String getDateOfCreation() {
        return dateOfCreation;
    }

    public String getLedgerID() {
        return ledgerID;
    }

    public String getPersonInCharge() {
        return personInCharge;
    }

    public String getEmail() {
        return email;
    }

    public String getDenomination() {
        return denomination;
    }

    public String getDescription() {
        return description;
    }


    // Equals & hashCode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreateGroupDTO)) return false;
        CreateGroupDTO that = (CreateGroupDTO) o;
        return Objects.equals(getEmail(), that.getEmail()) &&
                Objects.equals(getDenomination(), that.getDenomination()) &&
                Objects.equals(getDescription(), that.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, denomination, description);
    }

}