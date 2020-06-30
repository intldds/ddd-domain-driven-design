package com.finance.project.dataModel.dataModel;

import org.hibernate.annotations.Fetch;
import com.finance.project.domainLayer.domainEntities.vosShared.LedgerID;
import com.finance.project.domainLayer.domainEntities.vosShared.PersonID;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "persons")
public class PersonJpa {

	@EmbeddedId
	private PersonID id;
	private String name;
	private String birthdate;
	private String birthplace;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id", referencedColumnName = "id")
	private AddressJpa address;

	@OneToOne(cascade = CascadeType.ALL)
	private LedgerJpa ledger;

	@OneToOne
	@JoinColumn(name = "father_id")
	private PersonJpa father;

	@OneToOne
	@JoinColumn(name = "mother_id")
	private PersonJpa mother;

	@OneToMany(mappedBy = "id.personJpa", cascade = CascadeType.ALL)
	@Fetch(org.hibernate.annotations.FetchMode.SUBSELECT)
	private List<SiblingJpa> siblings;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@Fetch(org.hibernate.annotations.FetchMode.SUBSELECT)
	protected List<CategoryJpa> categories;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@Fetch(org.hibernate.annotations.FetchMode.SUBSELECT)
	protected List<AccountJpa> accounts;


	// CONSTRUCTOR


	public PersonJpa() {
	}

	public PersonJpa(String id, String name, String birthdate, String birthplace) {
		this.id = PersonID.createPersonID(id);

		this.name = name;
		this.birthdate = birthdate;
		this.birthplace = birthplace;
		this.siblings = new ArrayList<>();
		this.categories = new ArrayList<>();
		this.ledger = new LedgerJpa(LedgerID.createLedgerID());
		this.siblings = new ArrayList<>();
		this.accounts = new ArrayList<>();
	}

	public PersonJpa(PersonID id, String name, String birthdate, String birthplace) {
		this.id = id;

		this.name = name;
		this.birthdate = birthdate;
		this.birthplace = birthplace;

		this.siblings = new ArrayList<>();
		this.categories = new ArrayList<>();
		this.ledger = new LedgerJpa(LedgerID.createLedgerID());
		this.siblings = new ArrayList<>();
		this.accounts = new ArrayList<>();
	}


	// GETTERS AND SETTERS


	public PersonID getId() {
		return id;
	}

	public void setId(PersonID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public String getBirthplace() {
		return birthplace;
	}

	public void setBirthplace(String birthplace) {
		this.birthplace = birthplace;
	}

	public AddressJpa getAddress() {
		return address;
	}

	public void setAddress(AddressJpa address) {
		this.address = address;
	}

	public LedgerJpa getLedger() {
		return ledger;
	}

	public void setLedger(LedgerJpa ledger) {
		this.ledger = ledger;
	}

	public PersonJpa getFather() {
		return father;
	}

	public void setFather(PersonJpa father) {
		this.father = father;
	}

	public PersonJpa getMother() {
		return mother;
	}

	public void setMother(PersonJpa mother) {
		this.mother = mother;
	}

	public List<SiblingJpa> getSiblings() {
		return siblings;
	}

	public void setSiblings(List<SiblingJpa> siblings) {
		this.siblings = siblings;
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
		return "Person {" +
				"id='" + id.toString() + '\'' +
				'}';
	}


	// EQUALS AND HASCODE


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		PersonJpa personJpa = (PersonJpa) o;
		return Objects.equals(id, personJpa.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	// OTHER STUFF

	public boolean addSibling(PersonID siblingID) {
		SiblingJpa siblingJpa = new SiblingJpa(this, siblingID);

		return siblings.add(siblingJpa);
	}

	public boolean addAccount(String personID, String denomination, String description) {
		AccountJpa accountJpa = new AccountJpa(personID,denomination,description);

		return accounts.add(accountJpa);
	}
}
