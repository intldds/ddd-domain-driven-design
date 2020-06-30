package com.finance.project.dtos.dtos;


import java.util.Objects;

public class NewGroupTransactionInfoDTO {

    private String denominationCategory;     // Category denomination
    private String type;     // Transaction type
    private String description;     // Transaction description
    private double amount;     // Transaction amount
    private String denominationAccountDeb;     // Account denomination (debit)
    private String denominationAccountCred;     // Account denomination (credit)
    private String date;

    /**
     * Instantiates a NewPersonTransactionInfoDTO dto, with the following parameters.
     *
     * @param denominationCategory
     * @param type
     * @param description
     * @param amount
     * @param denominationAccountDeb
     * @param denominationAccountCred
     */

    public NewGroupTransactionInfoDTO(String denominationCategory, String type, String description, double amount, String denominationAccountDeb, String denominationAccountCred, String date) {

        this.denominationCategory = denominationCategory;
        this.type = type;
        this.description = description;
        this.amount = amount;
        this.denominationAccountDeb = denominationAccountDeb;
        this.denominationAccountCred = denominationAccountCred;
        this.date = date;

    }

    public NewGroupTransactionInfoDTO(){
    }

    /**
     * Gets denomination.
     *
     * @return
     */

    public String getDenominationCategory() {
        return denominationCategory;
    }

    /**
     * Gets type.
     *
     * @return
     */

    public String getType() {
        return type;
    }

    /**
     * Gets description.
     *
     * @return
     */

    public String getDescription() {
        return description;
    }

    /**
     * Gets amount.
     *
     * @return
     */

    public double getAmount() {
        return amount;
    }

    /**
     * Gets debit denomination Account.
     *
     * @return
     */

    public String getDenominationAccountDeb() {
        return denominationAccountDeb;
    }

    /**
     * Gets credit denomination Account.
     *
     * @return
     */

    public String getDenominationAccountCred() {
        return denominationAccountCred;
    }

    public String getDate() {
        return date;
    }

    /**
     * Sets denomination.
     *
     * @param denominationCategory
     */

    public void setDenominationCategory(String denominationCategory) {
        this.denominationCategory = denominationCategory;
    }

    /**
     * Sets type.
     *
     * @param type
     */

    public void setType(String type) {
        this.type = type;
    }

    /**
     * Sets description.
     *
     * @param description
     */

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Sets amount.
     *
     * @param amount
     */

    public void setAmount(double amount) {
        this.amount = amount;
    }
    /**
     * Sets debit denomination Account.
     *
     * @param denominationAccountDeb
     */

    public void setDenominationAccountDeb(String denominationAccountDeb) {
        this.denominationAccountDeb = denominationAccountDeb;
    }

    /**
     * Sets credit denomination Account.
     *
     * @param denominationAccountCred
     */

    public void setDenominationAccountCred(String denominationAccountCred) {
        this.denominationAccountCred = denominationAccountCred;
    }

    /**
     * Sets credit denomination Account.
     *
     * @param date
     */
    public void setDate(String date) {
        this.date = date;
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
        if (o == null || getClass() != o.getClass()) return false;
        NewGroupTransactionInfoDTO that = (NewGroupTransactionInfoDTO) o;
        return Objects.equals(denominationCategory, that.denominationCategory) &&
                Objects.equals(type, that.type) &&
                Objects.equals(description, that.description) &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(denominationAccountDeb, that.denominationAccountDeb) &&
                Objects.equals(denominationAccountCred, that.denominationAccountCred) &&
                Objects.equals(date, that.date);
    }

    /**
     * Hash code int
     *
     * @return an int.
     */

    @Override
    public int hashCode() {
        return Objects.hash(denominationCategory, type, description, amount, denominationAccountDeb, denominationAccountCred, date);
    }

}
