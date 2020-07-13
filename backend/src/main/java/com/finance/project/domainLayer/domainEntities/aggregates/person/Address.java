package com.finance.project.domainLayer.domainEntities.aggregates.person;

import com.finance.project.domainLayer.entitiesInterfaces.ValueObject;

import java.util.Objects;


public class Address implements ValueObject {

    private String street;
    private String doorNumber;
    private String postCode;
    private String city;
    private String country;


    // Constructor

    public static Address createAddress(String street, String doorNumber, String postCode, String city, String country) {
        return new Address(street, doorNumber, postCode, city, country);
    }


    private Address(String street, String doorNumber, String postCode, String city, String country) {
        if (street != null && doorNumber != null && postCode != null && city != null && country != null) {
            this.street = street;
            this.doorNumber = doorNumber;
            this.postCode = postCode;
            this.city = city;
            this.country = country;
        } else if (street == null) {
            throw new IllegalArgumentException("Address not created due to the invalid street parameter given");
        } else if (doorNumber == null) {
            throw new IllegalArgumentException("Address not created due to the invalid doorNumber parameter given");
        } else if (postCode == null) {
            throw new IllegalArgumentException("Address not created due to the invalid postCode parameter given");
        } else if (city == null) {
            throw new IllegalArgumentException("Address not created due to the invalid city parameter given");
        } else {
            throw new IllegalArgumentException("Address not created due to the invalid country parameter given");
        }

    }

    // make an address

    public Address(Address p) {
        this.street = p.street;
        this.doorNumber = p.doorNumber;
        this.postCode = p.postCode;
        this.city = p.city;
        this.country = p.country;
    }


    // Getters

    public String getStreet() {
        return street;
    }

    public String getDoorNumber() {
        return doorNumber;
    }

    public String getPostCode() {
        return postCode;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    // hashCode & equals

    @Override
    public int hashCode() {
        return Objects.hash(street, doorNumber, postCode, city, country);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(street, address.street) &&
                Objects.equals(doorNumber, address.doorNumber) &&
                Objects.equals(postCode, address.postCode) &&
                Objects.equals(city, address.city) &&
                Objects.equals(country, address.country);
    }
}
