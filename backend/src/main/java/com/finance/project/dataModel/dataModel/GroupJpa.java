package com.finance.project.dataModel.dataModel;

import org.hibernate.annotations.Fetch;
import org.springframework.transaction.annotation.Transactional;
import com.finance.project.domainLayer.domainEntities.vosShared.GroupID;
import com.finance.project.domainLayer.domainEntities.vosShared.LedgerID;
import com.finance.project.domainLayer.domainEntities.vosShared.PersonID;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "groups")
public class GroupJpa {

	@EmbeddedId
	private GroupID id;

	private String description;

	private String dateOfCreation;

	@OneToOne(cascade = CascadeType.ALL)
	private LedgerJpa ledger;

	@OneToMany(mappedBy = "id.groupJpa", cascade = CascadeType.ALL)
	@Fetch(org.hibernate.annotations.FetchMode.SUBSELECT)
	private List<AdminJpa> administrators;

	@OneToMany(mappedBy = "id.groupJpa", cascade = CascadeType.ALL)
	@Fetch(org.hibernate.annotations.FetchMode.SUBSELECT)
	private List<MemberJpa> members;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@Fetch(org.hibernate.annotations.FetchMode.SUBSELECT)
	protected List<CategoryJpa> categories;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@Fetch(org.hibernate.annotations.FetchMode.SUBSELECT)
	protected List<AccountJpa> accounts;

	// CONSTRUCTORS


	public GroupJpa() {
	}

	public GroupJpa(String denomination, String description, String dateOfCreation) {
		this.id = GroupID.createGroupID(denomination);
		this.description = description;
		this.dateOfCreation = dateOfCreation;
		this.administrators = new ArrayList<AdminJpa>();
		this.members = new ArrayList<MemberJpa>();
		this.categories = new ArrayList<>();
		this.accounts = new ArrayList<>();
		this.ledger = new LedgerJpa(LedgerID.createLedgerID());
	}

	public GroupJpa(GroupID id, String description, String dateOfCreation) {
		this.id = id;
		this.description = description;
		this.dateOfCreation = dateOfCreation;
		this.administrators = new ArrayList<AdminJpa>();
		this.members = new ArrayList<MemberJpa>();
		this.categories = new ArrayList<>();
		this.accounts = new ArrayList<>();
		this.ledger = new LedgerJpa(LedgerID.createLedgerID());
	}

	// GETTERS AND SETTERS


	public GroupID getId() {
		return id;
	}

	public void setId(GroupID id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDateOfCreation() {
		return dateOfCreation;
	}

	public void setDateOfCreation(String dateOfCreation) {
		this.dateOfCreation = dateOfCreation;
	}

	public LedgerJpa getLedger() {
		return ledger;
	}

	public void setLedger(LedgerJpa ledger) {
		this.ledger = ledger;
	}

	public List<AdminJpa> getAdministrators() {
		return administrators;
	}

	public void setAdministrators(List<AdminJpa> administrators) {
		this.administrators = administrators;
	}

	public List<MemberJpa> getMembers() {
		return members;
	}

	public void setMembers(List<MemberJpa> members) {
		this.members = members;
	}

	public List<CategoryJpa> getCategories() {
		return categories;
	}

	public void setCategories(List<CategoryJpa> categories) {
		this.categories = categories;
	}

	public List<AccountJpa> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<AccountJpa> accounts) {
		this.accounts = accounts;
	}

	// TO STRING

	public String toString() {
		return "Group {" +
				"id='" + id.toString() + '\'' +
				'}';
	}

	// EQUALS AND HASHCODE


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		GroupJpa groupJpa = (GroupJpa) o;
		return Objects.equals(id, groupJpa.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}


	// OTHER STUFF


	@Transactional

	public boolean addAdministrator(PersonID adminID) {
		AdminJpa adminJpa = new AdminJpa(this, adminID);

		return administrators.add(adminJpa);
	}

	public boolean addMember(PersonID memberID) {
		MemberJpa memberJpa = new MemberJpa(this, memberID);

		return members.add(memberJpa);
	}

	public boolean addAccount(String groupID, String denomination, String description) {
		AccountJpa accountJpa = new AccountJpa(groupID,denomination,description);

		return accounts.add(accountJpa);
	}
}
