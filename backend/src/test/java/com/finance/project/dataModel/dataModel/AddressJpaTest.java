package com.finance.project.dataModel.dataModel;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddressJpaTest {

    //Constructor

    @Test
    @DisplayName("Test for createAddressJpa()")
    public void checkCreateAddressJpa() {

        //Arrange

        String portoStreet = "Rua Direita do Viso";
        String portoDoorNumber = "59";
        String portoPostCode = "4250 - 198";
        String portoCity = "Porto";
        String portoCountry = "Portugal";

        //Act

        AddressJpa addressJpa = new AddressJpa(portoStreet, portoDoorNumber, portoPostCode, portoCity, portoCountry);

        //Assert

        assertEquals(addressJpa.getStreet(), portoStreet);
        assertEquals(addressJpa.getDoorNumber(), portoDoorNumber);
        assertEquals(addressJpa.getPostCode(), portoPostCode);
        assertEquals(addressJpa.getCity(), portoCity);
        assertEquals(addressJpa.getCountry(), portoCountry);

    }


    @Test
    @DisplayName("Test for createAddressJpa()")
    public void checkCreateAddressJpa_EmptyConstructor() {

        //Arrange

        String portoStreet = "Rua Direita do Viso";
        String portoDoorNumber = "59";
        String portoPostCode = "4250 - 198";
        String portoCity = "Porto";
        String portoCountry = "Portugal";
        long id = 111122222;

        //Act

        AddressJpa addressJpa = new AddressJpa();
        addressJpa.setStreet(portoStreet);
        addressJpa.setDoorNumber(portoDoorNumber);
        addressJpa.setPostCode(portoPostCode);
        addressJpa.setCity(portoCity);
        addressJpa.setCountry(portoCountry);
        addressJpa.setId(id);

        //Assert

        assertEquals(addressJpa.getStreet(), portoStreet);
        assertEquals(addressJpa.getDoorNumber(), portoDoorNumber);
        assertEquals(addressJpa.getPostCode(), portoPostCode);
        assertEquals(addressJpa.getCity(), portoCity);
        assertEquals(addressJpa.getCountry(), portoCountry);
        assertEquals(addressJpa.getId(), id);


    }

    //ToString


    @Test
    @DisplayName("Test for AddressJpa_ToString()")
    public void checkAddressJpa_ToString() {

        //Arrange

        String portoStreet = "Rua Direita do Viso";
        String portoDoorNumber = "59";
        String portoPostCode = "4250 - 198";
        String portoCity = "Porto";
        String portoCountry = "Portugal";

        //Act

        AddressJpa addressJpa = new AddressJpa(portoStreet, portoDoorNumber, portoPostCode, portoCity, portoCountry);

        //Assert

        assertEquals("AddressJpa{id=0, street='Rua Direita do Viso', doorNumber='59', postCode='4250 - 198', city='Porto', country='Portugal'}", addressJpa.toString());

    }


    //Equals

    @Test
    @DisplayName("Test for Equals AddressJpa() : Same Object")
    public void checkEqualsAddressJpa_SameObject() {

        //Arrange

        String portoStreet = "Rua Direita do Viso";
        String portoDoorNumber = "59";
        String portoPostCode = "4250 - 198";
        String portoCity = "Porto";
        String portoCountry = "Portugal";

        //Act

        AddressJpa addressJpa = new AddressJpa(portoStreet, portoDoorNumber, portoPostCode, portoCity, portoCountry);

        boolean result = addressJpa.equals(addressJpa);

        //Assert

        assertEquals(true, result);


    }

    @Test
    @DisplayName("Test for Equals AddressJpa() : HappyCase")
    public void checkEqualsAddressJpa_HappyCase() {

        //Arrange

        String portoStreet = "Rua Direita do Viso";
        String portoDoorNumber = "59";
        String portoPostCode = "4250 - 198";
        String portoCity = "Porto";
        String portoCountry = "Portugal";

        //Act

        AddressJpa addressJpa = new AddressJpa(portoStreet, portoDoorNumber, portoPostCode, portoCity, portoCountry);
        AddressJpa newAddressJpa = new AddressJpa(portoStreet, portoDoorNumber, portoPostCode, portoCity, portoCountry);

        boolean result = addressJpa.equals(newAddressJpa);

        //Assert

        assertEquals(true, result);

    }

    @Test
    @DisplayName("Test for Equals AddressJpa() : null")
    public void checkEqualsAddressJpa_null() {

        //Arrange

        String portoStreet = "Rua Direita do Viso";
        String portoDoorNumber = "59";
        String portoPostCode = "4250 - 198";
        String portoCity = "Porto";
        String portoCountry = "Portugal";

        //Act

        AddressJpa addressJpa = new AddressJpa(portoStreet, portoDoorNumber, portoPostCode, portoCity, portoCountry);
        AddressJpa newAddressJpa = null;

        boolean result = addressJpa.equals(newAddressJpa);

        //Assert

        assertEquals(false, result);
    }


    @Test
    @DisplayName("Test for Equals AddressJpa() : not Instance of")
    public void checkEqualsAddressJpa_notInstanceOf() {

        //Arrange

        String portoStreet = "Rua Direita do Viso";
        String portoDoorNumber = "59";
        String portoPostCode = "4250 - 198";
        String portoCity = "Porto";
        String portoCountry = "Portugal";

        //Act

        AddressJpa addressJpa = new AddressJpa(portoStreet, portoDoorNumber, portoPostCode, portoCity, portoCountry);
        AddressJpa newAddressJpa = null;

        boolean result = addressJpa.equals(portoCity);

        //Assert

        assertEquals(false, result);
    }



    @Test
    @DisplayName("Test for addressJpa - Different Id")
    public void checkCreateAddressJpa_DifferentID() {

        //Arrange

        String portoStreet = "Rua Direita do Viso";
        String portoDoorNumber = "59";
        String portoPostCode = "4250 - 198";
        String portoCity = "Porto";
        String portoCountry = "Portugal";
        long id = 111122222;
        long newId = 1121331;

        //Act addressJpa

        AddressJpa addressJpa = new AddressJpa(portoStreet, portoDoorNumber, portoPostCode, portoCity, portoCountry);
        addressJpa.setId(id);

       //Act newAdrressJpa

        AddressJpa newAddressJpa = new AddressJpa(portoStreet, portoDoorNumber, portoPostCode, portoCity, portoCountry);
        addressJpa.setId(newId);

        boolean result = addressJpa.equals(newAddressJpa);

        //Assert

       assertEquals(false, result);


    }

    @Test
    @DisplayName("Test for addressJpa - Different Street")
    public void checkCreateAddressJpa_DifferentStreet() {

        //Arrange

        String portoStreet = "Rua Direita do Viso";
        String portoDoorNumber = "59";
        String portoPostCode = "4250 - 198";
        String portoCity = "Porto";
        String portoCountry = "Portugal";
        long id = 111122222;

        String lisboaStreet = "Rua do Comercio";


        //Act addressJpa

        AddressJpa addressJpa = new AddressJpa(portoStreet, portoDoorNumber, portoPostCode, portoCity, portoCountry);


        //Act newAddressJpa

        AddressJpa newAddressJpa = new AddressJpa(portoStreet, portoDoorNumber, portoPostCode, portoCity, portoCountry);
        addressJpa.setStreet(lisboaStreet);

        boolean result = addressJpa.equals(newAddressJpa);

        //Assert

        assertEquals(false, result);


    }

    @Test
    @DisplayName("Test for addressJpa - Different DoorNumber")
    public void checkCreateAddressJpa_DifferentDoorNumber() {

        //Arrange

        String portoStreet = "Rua Direita do Viso";
        String portoDoorNumber = "59";
        String portoPostCode = "4250 - 198";
        String portoCity = "Porto";
        String portoCountry = "Portugal";
        long id = 111122222;

        String newPortoDoorNumber = "60";

        //Act addressJpa

        AddressJpa addressJpa = new AddressJpa(portoStreet, portoDoorNumber, portoPostCode, portoCity, portoCountry);

        //Act newAdrressJpa

        AddressJpa newAddressJpa = new AddressJpa(portoStreet, portoDoorNumber, portoPostCode, portoCity, portoCountry);
        addressJpa.setDoorNumber(newPortoDoorNumber);

        boolean result = addressJpa.equals(newAddressJpa);

        //Assert

        assertEquals(false, result);
    }


    @Test
    @DisplayName("Test for addressJpa - Different PostCode")
    public void checkCreateAddressJpa_DifferentPostCode() {

        //Arrange

        String portoStreet = "Rua Direita do Viso";
        String portoDoorNumber = "59";
        String portoPostCode = "4250 - 198";
        String portoCity = "Porto";
        String portoCountry = "Portugal";
        long id = 111122222;

        String newPortoPostCode = "4250 - 200";

        //Act addressJpa

        AddressJpa addressJpa = new AddressJpa(portoStreet, portoDoorNumber, portoPostCode, portoCity, portoCountry);

        //Act newAdrressJpa

        AddressJpa newAddressJpa = new AddressJpa(portoStreet, portoDoorNumber, portoPostCode, portoCity, portoCountry);
        addressJpa.setPostCode(newPortoPostCode);

        boolean result = addressJpa.equals(newAddressJpa);

        //Assert

        assertEquals(false, result);
    }

    @Test
    @DisplayName("Test for addressJpa - Different City")
    public void checkCreateAddressJpa_DifferentCity() {

        //Arrange

        String portoStreet = "Rua Direita do Viso";
        String portoDoorNumber = "59";
        String portoPostCode = "4250 - 198";
        String portoCity = "Porto";
        String portoCountry = "Portugal";
        long id = 111122222;

        String lisboaCity = "Lisboa";

        //Act addressJpa

        AddressJpa addressJpa = new AddressJpa(portoStreet, portoDoorNumber, portoPostCode, portoCity, portoCountry);


        //Act newAdrressJpa

        AddressJpa newAddressJpa = new AddressJpa(portoStreet, portoDoorNumber, portoPostCode, portoCity, portoCountry);
        addressJpa.setCity(lisboaCity);

        boolean result = addressJpa.equals(newAddressJpa);

        //Assert

        assertEquals(false, result);
    }

    @Test
    @DisplayName("Test for addressJpa - Different Country")
    public void checkCreateAddressJpa_DifferentCountry() {

        //Arrange

        String portoStreet = "Rua Direita do Viso";
        String portoDoorNumber = "59";
        String portoPostCode = "4250 - 198";
        String portoCity = "Porto";
        String portoCountry = "Portugal";
        long id = 111122222;

        String brasilCountry = "Brasil";

        //Act addressJpa

        AddressJpa addressJpa = new AddressJpa(portoStreet, portoDoorNumber, portoPostCode, portoCity, portoCountry);


        //Act newAdrressJpa

        AddressJpa newAddressJpa = new AddressJpa(portoStreet, portoDoorNumber, portoPostCode, portoCity, portoCountry);
        addressJpa.setCountry(brasilCountry);

        boolean result = addressJpa.equals(newAddressJpa);

        //Assert

        assertEquals(false, result);
    }


    @Test
    @DisplayName("Test for addressJpa - all Set Differents")
    public void checkCreateAddressJpa_allDifferents() {

        //Arrange first address

        String portoStreet = "Rua Direita do Viso";
        String portoDoorNumber = "59";
        String portoPostCode = "4250 - 198";
        String portoCity = "Porto";
        String portoCountry = "Portugal";
        long id = 111122222;

        //Arrange second address

        String newStreet = "Rua Alegria";
        String newDoorNumber = "60";
        String newPostCode = "4250 - 200";
        String newCity = "Lisboa";
        String newCountry = "Espanha";
        long newId = 111122225;



        //Act addressJpa

        AddressJpa addressJpa = new AddressJpa(portoStreet, portoDoorNumber, portoPostCode, portoCity, portoCountry);
        addressJpa.setId(id);

        //Act newAdrressJpa

        AddressJpa newAddressJpa = new AddressJpa(newStreet, newDoorNumber, newPostCode, newCity, newCountry);
        newAddressJpa.setId(newId);


        boolean result = addressJpa.equals(newAddressJpa);

        //Assert

        assertFalse(addressJpa.equals(newAddressJpa));
        assertEquals(false, result);
    }



    //HashCode

    @Test
    @DisplayName("Test for HashCode AddressJpa() : HappyCase")
    public void checkHashCodeAddressJpa_HappyCase() {

        //Arrange

        String portoStreet = "Rua Direita do Viso";
        String portoDoorNumber = "59";
        String portoPostCode = "4250 - 198";
        String portoCity = "Porto";
        String portoCountry = "Portugal";

        //Act

        AddressJpa addressJpa = new AddressJpa(portoStreet, portoDoorNumber, portoPostCode, portoCity, portoCountry);
        AddressJpa newAddressJpa = new AddressJpa(portoStreet, portoDoorNumber, portoPostCode, portoCity, portoCountry);

        boolean result = addressJpa.hashCode()==newAddressJpa.hashCode();

        //Assert

        assertTrue(addressJpa.hashCode()==newAddressJpa.hashCode());
        assertTrue(addressJpa.equals(addressJpa));
        assertEquals(true, result);

    }

    @Test
    @DisplayName("Test for HashCode AddressJpa() : SadCase")
    public void checkHashCodeAddressJpa_SadCase() {

        //Arrange

        String portoStreet = "Rua Direita do Viso";
        String portoDoorNumber = "59";
        String portoPostCode = "4250 - 198";
        String portoCity = "Porto";
        String portoCountry = "Portugal";


        //Arrange Second Address

        String newStreet = "Rua Alegria";
        String newDoorNumber = "60";
        String newPostCode = "4250 - 200";
        String newCity = "Lisboa";
        String newCountry = "Espanha";
        long newId = 111122225;

        //Act

        AddressJpa addressJpa = new AddressJpa(portoStreet, portoDoorNumber, portoPostCode, portoCity, portoCountry);
        AddressJpa newAddressJpa = new AddressJpa(newStreet , newDoorNumber, newPostCode, newCity, newCountry);

        boolean result = addressJpa.hashCode()==newAddressJpa.hashCode();

        //Assert

        assertFalse(addressJpa.hashCode()==newAddressJpa.hashCode());
        assertEquals(false, result);

    }


}