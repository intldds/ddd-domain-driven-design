package com.finance.project.dtos.dtos;

import java.util.Objects;

/**
 * The type Create group category dto.
 */
public class CreateGroupCategoryDTO {

    private final String personEmail;
    private final String groupDenomination;
    private final String categoryDenomination;

    /**
     * Instantiates a new Create group category dto.
     *
     * @param personEmail          the person email
     * @param groupDenomination    the group denomination
     * @param categoryDenomination the category denomination
     */
    public CreateGroupCategoryDTO(String personEmail, String groupDenomination, String categoryDenomination){
        this.personEmail = personEmail;
        this.groupDenomination = groupDenomination;
        this.categoryDenomination = categoryDenomination;
    }

    /**
     * Gets category denomination.
     *
     * @return the category denomination
     */
    public String getCategoryDenomination() {
        return categoryDenomination;
    }

    /**
     * Gets group denomination.
     *
     * @return the group denomination
     */
    public String getGroupDenomination() {
        return groupDenomination;
    }

    /**
     * Gets person email.
     *
     * @return the person email
     */
    public String getPersonEmail() {
        return personEmail;
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
        CreateGroupCategoryDTO that = (CreateGroupCategoryDTO) o;
        return Objects.equals(personEmail, that.personEmail) &&
                Objects.equals(groupDenomination, that.groupDenomination) &&
                Objects.equals(categoryDenomination, that.categoryDenomination);
    }

    /**
     * Hash code int.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        return Objects.hash(personEmail, groupDenomination, categoryDenomination);
    }
}
