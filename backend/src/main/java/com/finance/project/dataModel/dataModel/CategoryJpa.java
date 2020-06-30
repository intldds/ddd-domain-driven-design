package com.finance.project.dataModel.dataModel;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;


@Entity
@Table(name="categories")
public class CategoryJpa implements Serializable {

    @EmbeddedId
    private AbstractIdJpa id;

    public CategoryJpa() {
    }

    public CategoryJpa(String ownerID, String denomination) {
        this.id = new AbstractIdJpa(ownerID,denomination);
    }

    public AbstractIdJpa getId() {
        return id;
    }

    public void setId(AbstractIdJpa id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "CategoryJpa{" +
                "id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryJpa that = (CategoryJpa) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
