package com.finance.project.dtos.dtos;

import org.springframework.hateoas.RepresentationModel;

import java.util.List;
import java.util.Objects;

public class SiblingsDTO extends RepresentationModel<SiblingsDTO> {

    private List<String> siblings;

    public SiblingsDTO(List<String> siblings) {
        this.siblings = siblings;
    }

    public SiblingsDTO() {

    }

    public List<String> getSiblings() {
        return siblings;
    }

    public void setSiblings(List<String> siblings) {
        this.siblings = siblings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SiblingsDTO that = (SiblingsDTO) o;
        return Objects.equals(getSiblings(), that.getSiblings());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getSiblings());
    }
}
