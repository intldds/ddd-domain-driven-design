package com.finance.project.dataModel.dataModel;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "accounts")
public class AccountJpa implements Serializable {

    @EmbeddedId
    private AbstractIdJpa id;

    private String description;

    public AccountJpa() {
    }

    public AccountJpa(String ownerID, String denomination, String description) {
        this.id = new AbstractIdJpa(ownerID, denomination);
        this.description = description;
    }

    public AbstractIdJpa getId() {
        return id;
    }

    public void setId(AbstractIdJpa id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "AccountJpa{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountJpa that = (AccountJpa) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
