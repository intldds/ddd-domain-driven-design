package com.finance.project.dataModel.dataModel;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "addresses")
public class AddressJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String street;
    private String doorNumber;
    private String postCode;
    private String city;
    private String country;

    // CONSTRUCTOR

    public AddressJpa(String street, String doorNumber, String postCode, String city, String country) {
        this.street = street;
        this.doorNumber = doorNumber;
        this.postCode = postCode;
        this.city = city;
        this.country = country;
    }

    public AddressJpa() {
    }


    // GETTERS AND SETTERS

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getDoorNumber() {
        return doorNumber;
    }

    public void setDoorNumber(String doorNumber) {
        this.doorNumber = doorNumber;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }


    // TO STRING


    @Override
    public String toString() {
        return "AddressJpa{" +
                "id=" + id +
                ", street='" + street + '\'' +
                ", doorNumber='" + doorNumber + '\'' +
                ", postCode='" + postCode + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                '}';
    }


    // EQUALS AND HASHCODE


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressJpa that = (AddressJpa) o;
        return id == that.id &&
                Objects.equals(street, that.street) &&
                Objects.equals(doorNumber, that.doorNumber) &&
                Objects.equals(postCode, that.postCode) &&
                Objects.equals(city, that.city) &&
                Objects.equals(country, that.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, street, doorNumber, postCode, city, country);
    }
}
