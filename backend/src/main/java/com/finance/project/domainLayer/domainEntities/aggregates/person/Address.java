package com.finance.project.domainLayer.domainEntities.aggregates.person;

import com.finance.project.domainLayer.entitiesInterfaces.ValueObject;

import java.util.Objects;

/**
 * The type Address.
 */
public class Address implements ValueObject {

    private String street;
    private String doorNumber;
    private String postCode;
    private String city;
    private String country;


    //constructor with all attributes

    /**
     * Create address address.
     *
     * @param street     the street
     * @param doorNumber the door number
     * @param postCode   the post code
     * @param city       the city
     * @param country    the country
     * @return the address
     */
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

    //make an address

    /**
     * Instantiates a new Address.
     *
     * @param p the p
     */
    public Address(Address p) {
        this.street = p.street;
        this.doorNumber = p.doorNumber;
        this.postCode = p.postCode;
        this.city = p.city;
        this.country = p.country;
    }


    /**
     * Gets street.
     *
     * @return the street
     */
    public String getStreet() {
        return street;
    }

    /**
     * Gets door number.
     *
     * @return the door number
     */
    public String getDoorNumber() {
        return doorNumber;
    }

    /**
     * Gets post code.
     *
     * @return the post code
     */
    public String getPostCode() {
        return postCode;
    }

    /**
     * Gets city.
     *
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * Gets country.
     *
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    //hashCode

    /**
     * Hash code int.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        return Objects.hash(street, doorNumber, postCode, city, country);
    }


    //address equals

    /**
     * Equals boolean.
     *
     * @param o the o
     * @return the boolean
     */
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
