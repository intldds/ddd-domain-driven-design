package com.finance.project.domainLayer.domainEntities.aggregates.person;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddressTest {

    // constructor test
    @Test
    public void checkConstructor() {

        //Arrange
        String street = "rua da saudade";
        String doorNumber = "12";
        String postCode = "4000-121";
        String city = "Porto";
        String country = "Portugal";

        //Act
        Address address = Address.createAddress(street, doorNumber, postCode, city, country);

        //Assert
        assertEquals(street, address.getStreet());
        assertEquals(doorNumber, address.getDoorNumber());
        assertEquals(postCode, address.getPostCode());
        assertEquals(city, address.getCity());
        assertEquals(country, address.getCountry());

    }

    //test make an address

    @Test
    public void checkMakeAnAddress() {

        //Arrange
        String street = "rua da saudade";
        String doorNumber = "12";
        String postCode = "4000-121";
        String city = "Porto";
        String country = "Portugal";

        //Act
        Address address = Address.createAddress(street, doorNumber, postCode, city, country);
        Address addressC = new Address(address);

        //Assert
        assertEquals(address, addressC);


    }

    //test equals
    @Test
    @DisplayName("check equals - same Object | Address")
    public void checkEqualsAddress_sameObject() {

        //Arrange
        String streetA = "rua da saudade";
        String doorNumberA = "1";
        String postCodeA = "4000-222";
        String cityA = "Porto";
        String countryA = "Portugal";


        //Act

        Address addressA = Address.createAddress(streetA, doorNumberA, postCodeA, cityA, countryA);


        //Assert

        assertEquals(addressA, addressA);

    }

    @Test
    public void checkEqualsAddress_true_all_points() {

        //Arrange
        String streetA = "rua da saudade";
        String doorNumberA = "1";
        String postCodeA = "4000-222";
        String cityA = "Porto";
        String countryA = "Portugal";

        String streetB = "rua da saudade";
        String doorNumberB = "1";
        String postCodeB = "4000-222";
        String cityB = "Porto";
        String countryB = "Portugal";


        //Act

        Address addressA = Address.createAddress(streetA, doorNumberA, postCodeA, cityA, countryA);
        Address addressB = Address.createAddress(streetB, doorNumberB, postCodeB, cityB, countryB);


        // boolean result= enderecoA.equals(enderecoB);

        //Assert

        assertEquals(addressA, addressB);

    }

    @Test
    @DisplayName("Test the Address - equals() - False | Not Same Class ")
    public void testAddressEqualsFalseNotSameClass() {

        //Arrange
        String street = "rua da saudade";
        String doorNumber = "12";
        String postCode = "4000-121";
        String city = "Porto";
        String country = "Portugal";

        Address address = Address.createAddress(street, doorNumber, postCode, city, country);

        String string = "Bug killer";


        //Act
        boolean result = address.equals(string);

        //Assert
        assertEquals(false, result);
    }

    @Test
    @DisplayName("Test the Address - equals() - False | Null ")
    public void testAddressEqualsFalseNull() {

        //Arrange
        String street = "rua da saudade";
        String doorNumber = "12";
        String postCode = "4000-121";
        String city = "Porto";
        String country = "Portugal";

        Address address = Address.createAddress(street, doorNumber, postCode, city, country);


        //Act
        boolean result = address.equals(null);

        //Assert
        assertEquals(false, result);
    }

    @Test
    public void checkEqualsAdddress_false_different_street() {

        //Arrange
        String streetA = "rua da alegria";
        String doorNumberA = "1";
        String postCodeA = "4000-222";
        String cityA = "Porto";
        String countryA = "Portugal";

        String streetB = "rua da saudade";
        String doorNumberB = "1";
        String postCodeB = "4000-222";
        String cityB = "Porto";
        String countryB = "Portugal";


        //Act

        Address addressA = Address.createAddress(streetA, doorNumberA, postCodeA, cityA, countryA);
        Address addressB = Address.createAddress(streetB, doorNumberB, postCodeB, cityB, countryB);


        //Assert

        assertNotEquals(addressA, addressB);

    }

    @Test
    public void checkEqualsAddress_false_different_doorNumber() {

        //Arrange
        String streetA = "rua da alegria";
        String doorNumberA = "1";
        String postCodeA = "4000-222";
        String cityA = "Porto";
        String countryA = "Portugal";

        String streetB = "rua da alegria";
        String doorNumberB = "2";
        String postCodeB = "4000-222";
        String cityB = "Porto";
        String countryB = "Portugal";


        //Act

        Address addressA = Address.createAddress(streetA, doorNumberA, postCodeA, cityA, countryA);
        Address addressB = Address.createAddress(streetB, doorNumberB, postCodeB, cityB, countryB);


        //Assert

        assertNotEquals(addressA, addressB);

    }

    @Test
    public void checkEqualsAddress_false_different_postCode() {

        //Arrange
        String streetA = "rua da alegria";
        String doorNumberA = "2";
        String postCodeA = "4000";
        String cityA = "Porto";
        String countryA = "Portugal";

        String streetB = "rua da alegria";
        String doorNumberB = "2";
        String postCodeB = "4000-222";
        String cityB = "Porto";
        String countryB = "Portugal";


        //Act

        Address addressA = Address.createAddress(streetA, doorNumberA, postCodeA, cityA, countryA);
        Address addressB = Address.createAddress(streetB, doorNumberB, postCodeB, cityB, countryB);


        //Assert

        assertNotEquals(addressA, addressB);

    }

    @Test
    public void checkEqualsAddress_false_different_city() {

        //Arrange
        String streetA = "rua da alegria";
        String doorNumberA = "1";
        String postCodeA = "4000";
        String cityA = "Porto";
        String countryA = "Portugal";

        String streetB = "rua da saudade";
        String doorNumberB = "2";
        String postCodeB = "4000-222";
        String cityB = "Lisboa";
        String countryB = "Portugal";


        //Act

        Address addressA = Address.createAddress(streetA, doorNumberA, postCodeA, cityA, countryA);
        Address addressB = Address.createAddress(streetB, doorNumberB, postCodeB, cityB, countryB);


        //Assert

        assertNotEquals(addressA, addressB);

    }

    @Test
    public void checkEqualsAddress_false_different_country() {

        //Arrange
        String streetA = "rua da alegria";
        String doorNumberA = "1";
        String postCodeA = "4000";
        String cityA = "Porto";
        String countryA = "Portugal";

        String streetB = "rua da saudade";
        String doorNumberB = "2";
        String postCodeB = "4000-222";
        String cityB = "Lisboa";
        String countryB = "Brasil";


        //Act

        Address addressA = Address.createAddress(streetA, doorNumberA, postCodeA, cityA, countryA);
        Address addressB = Address.createAddress(streetB, doorNumberB, postCodeB, cityB, countryB);


        //Assert

        assertNotEquals(addressA, addressB);

    }

    @Test
    @DisplayName("Test the Address constructor with a null street parameter")
    public void testAddressConstructorWithoutStreet() {
        //Arrange
        String wolverineStreet = null;
        String wolverineDoorNumber = "2";
        String wolverinePostCode = "4453";
        String wolverineCity = "TomorrowLand";
        String wolverineCountry = "La la Land";
        //Act and Assert
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> Address.createAddress(wolverineStreet, wolverineDoorNumber, wolverinePostCode, wolverineCity, wolverineCountry));
        assertEquals("Address not created due to the invalid street parameter given", thrown.getMessage());

    }

    @Test
    @DisplayName("Test the Address constructor with a null doorNumber parameter")
    public void testAddressConstructorWithoutDoorNumber() {
        //Arrange
        String wolverineStreet = "Street far far away";
        String wolverineDoorNumber = null;
        String wolverinePostCode = "4453";
        String wolverineCity = "TomorrowLand";
        String wolverineCountry = "La la Land";
        //Act and Assert
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> Address.createAddress(wolverineStreet, wolverineDoorNumber, wolverinePostCode, wolverineCity, wolverineCountry));
        assertEquals("Address not created due to the invalid doorNumber parameter given", thrown.getMessage());

    }

    @Test
    @DisplayName("Test the Address constructor with a null street parameter")
    public void testAddressConstructorWithoutPostCode() {
        //Arrange
        String wolverineStreet = "Street far far away";
        String wolverineDoorNumber = "2";
        String wolverinePostCode = null;
        String wolverineCity = "TomorrowLand";
        String wolverineCountry = "La la Land";
        //Act and Assert
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> Address.createAddress(wolverineStreet, wolverineDoorNumber, wolverinePostCode, wolverineCity, wolverineCountry));
        assertEquals("Address not created due to the invalid postCode parameter given", thrown.getMessage());

    }

    @Test
    @DisplayName("Test the Address constructor with a null street parameter")
    public void testAddressConstructorWithoutCity() {
        //Arrange
        String wolverineStreet = "Street far far away";
        String wolverineDoorNumber = "2";
        String wolverinePostCode = "4453";
        String wolverineCity = null;
        String wolverineCountry = "La la Land";
        //Act and Assert
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> Address.createAddress(wolverineStreet, wolverineDoorNumber, wolverinePostCode, wolverineCity, wolverineCountry));
        assertEquals("Address not created due to the invalid city parameter given", thrown.getMessage());

    }

    @Test
    @DisplayName("Test the Address constructor with a null street parameter")
    public void testAddressConstructorWithoutCountry() {
        //Arrange
        String wolverineStreet = "Street far far away";
        String wolverineDoorNumber = "2";
        String wolverinePostCode = "4453";
        String wolverineCity = "TomorrowLand";
        String wolverineCountry = null;
        //Act and Assert
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> Address.createAddress(wolverineStreet, wolverineDoorNumber, wolverinePostCode, wolverineCity, wolverineCountry));
        assertEquals("Address not created due to the invalid country parameter given", thrown.getMessage());

    }

    @Test
    public void checkEqualsCityUpperCase() {

        //Arrange
        String streetA = "rua da alegria";
        String doorNumberA = "1";
        String postCodeA = "4000";
        String cityA = "Porto";
        String countryA = "Portugal";

        String streetB = "rua da alegria";
        String doorNumberB = "1";
        String postCodeB = "4000";
        String cityB = "Lisboa";
        String countryB = "Portugal";


        //Act

        Address addressA = Address.createAddress(streetA, doorNumberA, postCodeA, cityA, countryA);
        Address addressB = Address.createAddress(streetB, doorNumberB, postCodeB, cityB, countryB);


        //Assert

        assertNotEquals(addressA, addressB);

    }

    @Test
    public void checkEqualsCountryUpperCase() {

        //Arrange
        String streetA = "rua da alegria";
        String doorNumberA = "1";
        String postCodeA = "4000";
        String cityA = "Porto";
        String countryA = "Portugal";

        String streetB = "rua da alegria";
        String doorNumberB = "1";
        String postCodeB = "4000";
        String cityB = "Porto";
        String countryB = "Spain";


        //Act

        Address addressA = Address.createAddress(streetA, doorNumberA, postCodeA, cityA, countryA);
        Address addressB = Address.createAddress(streetB, doorNumberB, postCodeB, cityB, countryB);


        //Assert

        assertNotEquals(addressA, addressB);

    }

    //test for hashCode

    @Test
    public void verificyEqualsAndHashCode() {
        Address x = Address.createAddress("rua da saudade", "21", "4435-123", "Porto", "Portugal");
        Address y = Address.createAddress("rua da saudade", "21", "4435-123", "Porto", "Portugal");

        assertTrue(x.equals(y) && y.equals(x));
        assertTrue(x.hashCode() == y.hashCode());
    }


    @Test
    public void verificyEqualsAndHashCode_VerifyHashCodeValue() {
        Address x = Address.createAddress("rua da saudade", "21", "4435-123", "Porto", "Portugal");
        Address y = Address.createAddress("rua da saudade", "21", "4435-123", "Porto", "Portugal");

        //Expected
        Integer expectedHashCode = y.hashCode();

        assertEquals(expectedHashCode, y.hashCode());
    }


    //test hashcode

    @Test
    @DisplayName("Test hashcode for Address - SadCase")
    public void verifyHashCode_False() {

        //Arrange
        Address x = Address.createAddress("rua da saudade", "21", "4435-123", "Porto", "Portugal");
        Address y = Address.createAddress("rua da saudade", "21", "4435-1234", "Rio de Janeiro", "Brasil");


        //Assert

        assertFalse(x.hashCode() == y.hashCode());
    }

}
/*      PARA APAGAR

 //test clone

    @Test
    public void testClone() {
        //Arrange
        String street = "rua da saudade";
        String doorNumber = "12";
        String postCode = "4000-121";
        String city = "Porto";
        String country = "Portugal";

        //Act

        Address address = Address.createAddress(street, doorNumber, postCode, city, country);
        Address cloneAddress = address.clone();

        //Assert

        assertEquals(address, cloneAddress);
    }

    @Test
    public void checkEqualsAddress_false_different_DoorNumberA_null() {

        //Arrange
        String streetA = "rua da alegria";
        String doorNumberA = "";
        String postCodeA = "4000-222";
        String cityA = "Porto";
        String countryA = "Portugal";

        String streetB = "rua da alegria";
        String doorNumberB = "123";
        String postCodeB = "4000-222";
        String cityB = "Porto";
        String countryB = "Portugal";


        //Act

        Address addressA = Address.createAddress(streetA, doorNumberA, postCodeA, cityA, countryA);
        Address addressB = Address.createAddress(streetB, doorNumberB, postCodeB, cityB, countryB);
        addressA.setDoorNumber(null);


        //Assert

        assertNotEquals(addressA, addressB);

    }

    @Test
    public void checkEqualsAddress_false_different_DoorNumberB_null() {

        //Arrange
        String streetA = "rua da alegria";
        String doorNumberA = "123";
        String postCodeA = "4000-222";
        String cityA = "Porto";
        String countryA = "Portugal";

        String streetB = "rua da alegria";
        String doorNumberB = "";
        String postCodeB = "4000-222";
        String cityB = "Porto";
        String countryB = "Portugal";


        //Act

        Address addressA = Address.createAddress(streetA, doorNumberA, postCodeA, cityA, countryA);
        Address addressB = Address.createAddress(streetB, doorNumberB, postCodeB, cityB, countryB);
        addressB.setDoorNumber(null);


        //Assert

        assertNotEquals(addressA, addressB);

    }

@Test
    public void checkEqualsAdddress_false_streetA_null() {

        //Arrange
        String streetA = "";
        String doorNumberA = "1";
        String postCodeA = "4000-222";
        String cityA = "Porto";
        String countryA = "Portugal";

        String streetB = "rua da saudade";
        String doorNumberB = "1";
        String postCodeB = "4000-222";
        String cityB = "Porto";
        String countryB = "Portugal";


        //Act

        Address addressA = Address.createAddress(streetA, doorNumberA, postCodeA, cityA, countryA);
        Address addressB = Address.createAddress(streetB, doorNumberB, postCodeB, cityB, countryB);
        addressA.setStreet(null);


        //Assert

        assertNotEquals(addressA, addressB);

    }

    @Test
    public void checkEqualsAdddress_false_streetB_null() {

        //Arrange
        String streetA = "Rua da Saudade";
        String doorNumberA = "1";
        String postCodeA = "4000-222";
        String cityA = "Porto";
        String countryA = "Portugal";

        String streetB = "";
        String doorNumberB = "1";
        String postCodeB = "4000-222";
        String cityB = "Porto";
        String countryB = "Portugal";


        //Act

        Address addressA = Address.createAddress(streetA, doorNumberA, postCodeA, cityA, countryA);
        Address addressB = Address.createAddress(streetB, doorNumberB, postCodeB, cityB, countryB);
        addressB.setStreet(null);


        //Assert

        assertNotEquals(addressA, addressB);

    }

    @Test
    public void checkEqualsAdddress_false_PostCodeA_null() {

        //Arrange
        String streetA = "rua da saudade";
        String doorNumberA = "1";
        String postCodeA = "";
        String cityA = "Porto";
        String countryA = "Portugal";

        String streetB = "rua da saudade";
        String doorNumberB = "1";
        String postCodeB = "4000-222";
        String cityB = "Porto";
        String countryB = "Portugal";


        //Act

        Address addressA = Address.createAddress(streetA, doorNumberA, postCodeA, cityA, countryA);
        Address addressB = Address.createAddress(streetB, doorNumberB, postCodeB, cityB, countryB);
        addressA.setPostCode(null);


        //Assert

        assertNotEquals(addressA, addressB);

    }

    @Test
    public void checkEqualsAdddress_false_PostCodeB_null() {

        //Arrange
        String streetA = "rua da saudade";
        String doorNumberA = "1";
        String postCodeA = "4000";
        String cityA = "Porto";
        String countryA = "Portugal";

        String streetB = "rua da saudade";
        String doorNumberB = "1";
        String postCodeB = "";
        String cityB = "Porto";
        String countryB = "Portugal";


        //Act

        Address addressA = Address.createAddress(streetA, doorNumberA, postCodeA, cityA, countryA);
        Address addressB = Address.createAddress(streetB, doorNumberB, postCodeB, cityB, countryB);
        addressB.setPostCode(null);


        //Assert

        assertNotEquals(addressA, addressB);

    }

    @Test
    public void checkEqualsAddress_false_CityA_null() {

        //Arrange
        String streetA = "rua da alegria";
        String doorNumberA = "1";
        String postCodeA = "4000";
        String cityA = "";
        String countryA = "Portugal";

        String streetB = "rua da saudade";
        String doorNumberB = "2";
        String postCodeB = "4000-222";
        String cityB = "Lisboa";
        String countryB = "Portugal";


        //Act

        Address addressA = Address.createAddress(streetA, doorNumberA, postCodeA, cityA, countryA);
        Address addressB = Address.createAddress(streetB, doorNumberB, postCodeB, cityB, countryB);
        addressA.setCity(null);


        //Assert

        assertNotEquals(addressA, addressB);

    }

    @Test
    public void checkEqualsAddress_false_CityB_null() {

        //Arrange
        String streetA = "rua da alegria";
        String doorNumberA = "1";
        String postCodeA = "4000";
        String cityA = "Porto";
        String countryA = "Portugal";

        String streetB = "rua da saudade";
        String doorNumberB = "2";
        String postCodeB = "4000-222";
        String cityB = "";
        String countryB = "Portugal";


        //Act

        Address addressA = Address.createAddress(streetA, doorNumberA, postCodeA, cityA, countryA);
        Address addressB = Address.createAddress(streetB, doorNumberB, postCodeB, cityB, countryB);
        addressB.setCity(null);


        //Assert

        assertNotEquals(addressA, addressB);

    }

     @Test
    public void checkEqualsAddress_false_countryA_null() {

        //Arrange
        String streetA = "rua da alegria";
        String doorNumberA = "1";
        String postCodeA = "4000";
        String cityA = "Porto";
        String countryA = "";

        String streetB = "rua da saudade";
        String doorNumberB = "2";
        String postCodeB = "4000-222";
        String cityB = "Porto";
        String countryB = "Portugal";


        //Act

        Address addressA = Address.createAddress(streetA, doorNumberA, postCodeA, cityA, countryA);
        Address addressB = Address.createAddress(streetB, doorNumberB, postCodeB, cityB, countryB);
        addressA.setCountry(null);


        //Assert

        assertNotEquals(addressA, addressB);

    }

    @Test
    public void checkEqualsAddress_false_CountryB_null() {

        //Arrange
        String streetA = "rua da alegria";
        String doorNumberA = "1";
        String postCodeA = "4000";
        String cityA = "Porto";
        String countryA = "Portugal";

        String streetB = "rua da saudade";
        String doorNumberB = "2";
        String postCodeB = "4000-222";
        String cityB = "Porto";
        String countryB = "";


        //Act

        Address addressA = Address.createAddress(streetA, doorNumberA, postCodeA, cityA, countryA);
        Address addressB = Address.createAddress(streetB, doorNumberB, postCodeB, cityB, countryB);
        addressB.setCountry(null);


        //Assert

        assertNotEquals(addressA, addressB);

    }


    @Test
    public void checkEqualsAddress_true_street_null() {

        //Arrange
        String streetA = "";
        String doorNumberA = "1";
        String postCodeA = "4000-222";
        String cityA = "Lisboa";
        String countryA = "Portugal";

        String streetB = "";
        String doorNumberB = "1";
        String postCodeB = "4000-222";
        String cityB = "Lisboa";
        String countryB = "Portugal";


        //Act

        Address addressA = Address.createAddress(streetA, doorNumberA, postCodeA, cityA, countryA);
        Address addressB = Address.createAddress(streetB, doorNumberB, postCodeB, cityB, countryB);
        addressA.setStreet(null);
        addressB.setStreet(null);


        //Assert

        assertEquals(addressA, addressB);

    }

    @Test
    public void checkEqualsAddress_true_doorNumber_null() {

        //Arrange
        String streetA = "rua da alegria";
        String doorNumberA = "";
        String postCodeA = "4000-222";
        String cityA = "Porto";
        String countryA = "Portugal";

        String streetB = "rua da alegria";
        String doorNumberB = "";
        String postCodeB = "4000-222";
        String cityB = "Porto";
        String countryB = "Portugal";


        //Act

        Address addressA = Address.createAddress(streetA, doorNumberA, postCodeA, cityA, countryA);
        Address addressB = Address.createAddress(streetB, doorNumberB, postCodeB, cityB, countryB);
        addressA.setDoorNumber(null);
        addressB.setDoorNumber(null);


        //Assert

        assertEquals(addressA, addressB);

    }

    @Test
    public void checkEqualsAddress_true_postCode_null() {

        //Arrange
        String streetA = "rua da alegria";
        String doorNumberA = "1";
        String postCodeA = "";
        String cityA = "Porto";
        String countryA = "Portugal";

        String streetB = "rua da alegria";
        String doorNumberB = "1";
        String postCodeB = "";
        String cityB = "Porto";
        String countryB = "Portugal";


        //Act

        Address addressA = Address.createAddress(streetA, doorNumberA, postCodeA, cityA, countryA);
        Address addressB = Address.createAddress(streetB, doorNumberB, postCodeB, cityB, countryB);
        addressA.setPostCode(null);
        addressB.setPostCode(null);


        //Assert

        assertEquals(addressA, addressB);

    }

    @Test
    public void checkEqualsAddress_true_city_null() {

        //Arrange
        String streetA = "rua da alegria";
        String doorNumberA = "1";
        String postCodeA = "4000";
        String cityA = "";
        String countryA = "Portugal";

        String streetB = "rua da alegria";
        String doorNumberB = "1";
        String postCodeB = "4000";
        String cityB = "";
        String countryB = "Portugal";


        //Act

        Address addressA = Address.createAddress(streetA, doorNumberA, postCodeA, cityA, countryA);
        Address addressB = Address.createAddress(streetB, doorNumberB, postCodeB, cityB, countryB);
        addressA.setCity(null);
        addressB.setCity(null);


        //Assert

        assertEquals(addressA, addressB);

    }

    @Test
    public void checkEqualsAddress_true_country_null() {

        //Arrange
        String streetA = "rua da alegria";
        String doorNumberA = "1";
        String postCodeA = "4000";
        String cityA = "Porto";
        String countryA = "";

        String streetB = "rua da alegria";
        String doorNumberB = "1";
        String postCodeB = "4000";
        String cityB = "Porto";
        String countryB = "";


        //Act

        Address addressA = Address.createAddress(streetA, doorNumberA, postCodeA, cityA, countryA);
        Address addressB = Address.createAddress(streetB, doorNumberB, postCodeB, cityB, countryB);
        addressA.setCountry(null);
        addressB.setCountry(null);


        //Assert

        assertEquals(addressA, addressB);

    }

    @Test
    public void checkEqualsCityUpperCaseCityANull() {

        //Arrange
        String streetA = "rua da alegria";
        String doorNumberA = "1";
        String postCodeA = "4000";
        String cityA = "Porto";
        String countryA = "Portugal";

        String streetB = "rua da alegria";
        String doorNumberB = "1";
        String postCodeB = "4000";
        String cityB = "Lisboa";
        String countryB = "Portugal";


        //Act

        Address addressA = Address.createAddress(streetA, doorNumberA, postCodeA, cityA, countryA);
        Address addressB = Address.createAddress(streetB, doorNumberB, postCodeB, cityB, countryB);
        addressA.setCity(null);


        //Assert

        assertNotEquals(addressA, addressB);

    }

     @Test
    public void checkEqualsCountryUpperCaseCountryANull() {

        //Arrange
        String streetA = "rua da alegria";
        String doorNumberA = "1";
        String postCodeA = "4000";
        String cityA = "Porto";
        String countryA = "Portugal";

        String streetB = "rua da alegria";
        String doorNumberB = "1";
        String postCodeB = "4000";
        String cityB = "Porto";
        String countryB = "Spain";


        //Act

        Address addressA = Address.createAddress(streetA, doorNumberA, postCodeA, cityA, countryA);
        Address addressB = Address.createAddress(streetB, doorNumberB, postCodeB, cityB, countryB);
        addressA.setCountry(null);


        //Assert

        assertNotEquals(addressA, addressB);

    }

    //test sets

    @Test
    public void checkSetStreet() {

        //Arrange
        String street = "rua da alegria";
        String doorNumber = "12";
        String postCode = "4000-121";
        String city = "Porto";
        String country = "Portugal";
        String streetB = "rua da saudade";

        //Act
        Address address = Address.createAddress(street, doorNumber, postCode, city, country);
        address.setStreet(streetB);


        //Assert
        assertEquals(address.getStreet(), streetB);

    }

    @Test
    public void checkSetDoorNumber() {

        //Arrange
        String street = "rua da alegria";
        String doorNumber = "12";
        String postCode = "4000-121";
        String city = "Porto";
        String country = "Portugal";
        String numberB = "20";

        //Act
        Address address = Address.createAddress(street, doorNumber, postCode, city, country);
        address.setDoorNumber(numberB);


        //Assert
        assertEquals(address.getDoorNumber(), numberB);

    }

    @Test
    public void checkPostCode() {

        //Arrange
        String street = "rua da alegria";
        String doorNumber = "12";
        String postCodeA = "4000-121";
        String city = "Porto";
        String country = "Portugal";
        String postCodeB = "4000-002";

        //Act
        Address address = Address.createAddress(street, doorNumber, postCodeA, city, country);
        address.setPostCode(postCodeB);


        //Assert
        assertEquals(address.getPostCode(), postCodeB);

    }

    @Test
    public void checkCity() {

        //Arrange
        String street = "rua da alegria";
        String doorNumber = "12";
        String postCode = "4000-121";
        String city = "Porto";
        String country = "Portugal";
        String cityB = "Lisboa";

        //Act
        Address address = Address.createAddress(street, doorNumber, postCode, city, country);
        address.setCity(cityB);


        //Assert
        assertEquals(address.getCity(), cityB);

    }

    @Test
    public void checkCountry() {

        //Arrange
        String street = "rua da alegria";
        String doorNumber = "12";
        String postCode = "4000-121";
        String city = "Porto";
        String country = "Portugal";
        String countryB = "Brasil";

        //Act
        Address address = Address.createAddress(street, doorNumber, postCode, city, country);
        address.setCountry(countryB);


        //Assert
        assertEquals(address.getCountry(), countryB);

    }

    @Test
    @DisplayName("Test hashcode for Address - HappyCase")
    public void verificyEqualsAndHashCode_VerifyHashWithClone() {
        //Arrange
        Address x = Address.createAddress("rua da saudade", "21", "4435-123", "Porto", "Portugal");

        //Act

        Address y = x.clone();


        assertEquals(y.hashCode(), y.hashCode());
    }

    @Test
    @DisplayName("Test hashcode for Address - HappyCase")
    public void verifyHashCode_VerifyHashWithClone() {
        //Arrange
        Address x = Address.createAddress("rua da saudade", "21", "4435-123", "Porto", "Portugal");


        //Act

        Address y = x.clone();

        //Assert

        assertTrue(y.hashCode() == y.hashCode());


    }
 */







/*WTF(Person)?
    //test equals
    @Test
    @DisplayName("check equals - Not Instance of Object | Address")
    public void checkEqualsAddress_NotInstanceOfObject() {

        //Arrange

        //Arrange address
        String streetA = "rua da saudade";
        String doorNumberA = "1";
        String postCodeA = "4000-222";
        String cityA = "Porto";
        String countryA = "Portugal";

        //Arrange Person

        String name = "Joana";
        LocalDate birthdate = LocalDate.of(1987, 01, 11);
        String nameMother = "Ana";
        LocalDate birthdateMother = LocalDate.of(1987, 01, 11);
        String nameFather = "João";
        LocalDate birthdateFather = LocalDate.of(1987, 01, 11);
        Person mother = new Person(nameMother, birthdateMother);
        Person father = new Person(nameFather, birthdateFather);
        Person personJoana = new Person(name, birthdate, mother, father);


        //Act

        Address addressA = Address.createAddress(streetA, doorNumberA, postCodeA, cityA, countryA);


        //Assert

        assertFalse(addressA.equals(personJoana));

    }
 */