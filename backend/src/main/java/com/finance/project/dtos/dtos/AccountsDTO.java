package com.finance.project.dtos.dtos;

import org.springframework.hateoas.RepresentationModel;

import java.util.List;
import java.util.Objects;

public class AccountsDTO extends RepresentationModel<AccountsDTO> {

    private List<AccountDTO> accounts;

    public AccountsDTO(List<AccountDTO> accounts) {
        this.accounts = accounts;
    }

    public AccountsDTO() {

    }

    public List<AccountDTO> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<AccountDTO> accounts) {
        this.accounts = accounts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountsDTO that = (AccountsDTO) o;
        return Objects.equals(getAccounts(), that.getAccounts());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getAccounts());
    }
}
