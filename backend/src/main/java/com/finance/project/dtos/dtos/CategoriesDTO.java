package com.finance.project.dtos.dtos;

import org.springframework.hateoas.RepresentationModel;

import java.util.List;
import java.util.Objects;

public class CategoriesDTO extends RepresentationModel<CategoriesDTO> {

    private List<String> categories;

    public CategoriesDTO(List<String> categories) {
        this.categories = categories;
    }

    public CategoriesDTO() {

    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoriesDTO that = (CategoriesDTO) o;
        return Objects.equals(getCategories(), that.getCategories());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getCategories());
    }
}
