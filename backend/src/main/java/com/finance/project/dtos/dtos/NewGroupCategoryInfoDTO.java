package com.finance.project.dtos.dtos;

import java.util.Objects;

public class NewGroupCategoryInfoDTO {

    //Deve ter sempre um construtor (vazio, pelo menos) + getters e setters

    private String categoryDenomination;

    public NewGroupCategoryInfoDTO(String categoryDenomination) {
        this.categoryDenomination = categoryDenomination;
    }

    public NewGroupCategoryInfoDTO() {
    }

    public String getCategoryDenomination() {
        return categoryDenomination;
    }

    public void setCategoryDenomination(String categoryDenomination) {
        this.categoryDenomination = categoryDenomination;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewGroupCategoryInfoDTO that = (NewGroupCategoryInfoDTO) o;
        return Objects.equals(getCategoryDenomination(), that.getCategoryDenomination());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCategoryDenomination());
    }
}
