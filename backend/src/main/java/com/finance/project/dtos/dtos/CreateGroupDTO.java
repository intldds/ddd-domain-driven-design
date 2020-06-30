package com.finance.project.dtos.dtos;

import com.finance.project.domainLayer.domainEntities.vosShared.LedgerID;

import java.time.LocalDate;
import java.util.Objects;

/**
 * The type Create group dto.
 */
public class CreateGroupDTO {

    private String email;
    private String denomination;
    private String description;
    private String personInCharge;
    private String dateOfCreation;
    private String ledgerID;


    /**
     * Instantiates a new Create group dto.
     *
     * @param email        the email
     * @param denomination the denomination
     * @param description  the description
     */
    public CreateGroupDTO(String email, String denomination, String description) {
        this.email = email;
        this.denomination = denomination;
        this.description = description;
    }

    /**
     * Create GroupDTO with the parameters referred below
     * @param email
     * @param denomination
     * @param personInCharge
     * @param description
     */

    public CreateGroupDTO(String email, String denomination, String personInCharge,
                          String description) {
        this.email = email;
        this.denomination = denomination;
        this.description = description;
        this.personInCharge= personInCharge;
        this.dateOfCreation= LocalDate.now().toString();
        this.ledgerID= LedgerID.createLedgerID().toString();
    }


    /**
     * Sets DateOfCreation
     * @return
     */
    public void setDateOfCreation(String dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    /**
     * Sets ledgerId
     * @return
     */

    public void setLedgerID(String ledgerID) {
        this.ledgerID = ledgerID;
    }

    /**
     *
     * @return
     */
    public String getDateOfCreation() {
        return dateOfCreation;
    }

    /**
     *
     * @return
     */

    public String getLedgerID() {
        return ledgerID;
    }

    /**
     *
     * @return
     */

    public String getPersonInCharge() {
        return personInCharge;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets denomination.
     *
     * @return the denomination
     */
    public String getDenomination() {
        return denomination;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
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
        if (!(o instanceof CreateGroupDTO)) return false;
        CreateGroupDTO that = (CreateGroupDTO) o;
        return Objects.equals(getEmail(), that.getEmail()) &&
                Objects.equals(getDenomination(), that.getDenomination()) &&
                Objects.equals(getDescription(), that.getDescription());
    }

    /**
     * Hash code int.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        return Objects.hash(email, denomination, description);
    }

}