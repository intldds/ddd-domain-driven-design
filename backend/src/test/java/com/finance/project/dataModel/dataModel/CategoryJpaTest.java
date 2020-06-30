package com.finance.project.dataModel.dataModel;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryJpaTest {

    //Constructor

    @Test
    @DisplayName("CategoryJpa | constructor")
    public void categoryJpaConstructor(){

        //Arrange

        String ownerID = "alexandre@gmail.com";
        String salaryDenomination = "Salary";

        //Act

        CategoryJpa categoryJpa = new CategoryJpa(ownerID, salaryDenomination);

        //Assert

        assertEquals(categoryJpa.getId().getOwnerID(), ownerID);
        assertEquals(categoryJpa.getId().getDenomination(), salaryDenomination);

    }

    //Set ID

    @Test
    @DisplayName("CategoryJpa | Set ID")
    public void categoryJpaSetID(){

        //Arrange

        String ownerID = "alexandre@gmail.com";
        String salaryDenomination = "Salary";

        String newOwnerID = "paulo@gmail.com";
        String otherDenomination = "Salary";

        AbstractIdJpa abstractIdJpa = new AbstractIdJpa(newOwnerID, otherDenomination);


        //Act

        CategoryJpa categoryJpa = new CategoryJpa(ownerID, salaryDenomination);
        categoryJpa.setId(abstractIdJpa);

        //Assert

        assertEquals(categoryJpa.getId(), abstractIdJpa);

    }

    //ToString

    @Test
    @DisplayName("CategoryJpa | ToString")
    public void categoryJpaToString(){

        //Arrange

        String ownerID = "alexandre@gmail.com";
        String salaryDenomination = "Salary";

        //Act

        CategoryJpa categoryJpa = new CategoryJpa(ownerID, salaryDenomination);

        //Assert

        assertEquals("CategoryJpa{id=AbstractIdJpa{ownerID='alexandre@gmail.com', denomination='Salary'}}",categoryJpa.toString() );

    }


    //Equals

    @Test
    @DisplayName("CategoryJpa | Same Object")
    public void categoryJpaSameObject(){

        //Arrange

        String ownerID = "alexandre@gmail.com";
        String salaryDenomination = "Salary";

        //Act

        CategoryJpa categoryJpa = new CategoryJpa(ownerID, salaryDenomination);

        boolean result = categoryJpa.equals(categoryJpa);

        //Assert

        assertEquals(true, result);


    }

    @Test
    @DisplayName("CategoryJpa | HappyCase")
    public void categoryJpaHappyCase(){

        //Arrange

        String ownerID = "alexandre@gmail.com";
        String salaryDenomination = "Salary";

        //Act

        CategoryJpa categoryJpa = new CategoryJpa(ownerID, salaryDenomination);
        CategoryJpa newCategoryJpa = new CategoryJpa(ownerID, salaryDenomination);

        boolean result = categoryJpa.equals(newCategoryJpa);

        //Assert

        assertEquals(true, result);

    }

    @Test
    @DisplayName("CategoryJpa |null")
    public void categoryJpaNull(){

        //Arrange

        String ownerID = "alexandre@gmail.com";
        String salaryDenomination = "Salary";

        //Act

        CategoryJpa categoryJpa = new CategoryJpa(ownerID, salaryDenomination);
        CategoryJpa newCategoryJpa = null;

        boolean result = categoryJpa.equals(newCategoryJpa);

        //Assert

        assertEquals(false, result);

    }

    @Test
    @DisplayName("CategoryJpa | No instance of")
    public void categoryJpaNoInstanceOf(){

        //Arrange

        String ownerID = "alexandre@gmail.com";
        String salaryDenomination = "Salary";

        //Act

        CategoryJpa categoryJpa = new CategoryJpa(ownerID, salaryDenomination);
        CategoryJpa newCategoryJpa = new CategoryJpa(ownerID, salaryDenomination);

        boolean result = categoryJpa.equals(salaryDenomination);

        //Assert

        assertEquals(false, result);

    }

    @Test
    @DisplayName("CategoryJpa | Sad Case")
    public void categoryJpaDifferentID(){

        //Arrange

        String ownerID = "alexandre@gmail.com";
        String salaryDenomination = "Salary";

        String newOwnerID = "paulo@gmail.com";
        String otherDenomination = "Salary";

        AbstractIdJpa abstractIdJpa = new AbstractIdJpa(newOwnerID, otherDenomination);


        //Act

        CategoryJpa categoryJpa = new CategoryJpa(ownerID, salaryDenomination);
        CategoryJpa newCategoryJpa = new CategoryJpa(ownerID, salaryDenomination);
        newCategoryJpa.setId(abstractIdJpa);

        boolean result = categoryJpa.equals(salaryDenomination);

        //Assert

        assertEquals(false, result);

    }

    //HashCode

    @Test
    @DisplayName("CategoryJpa |HashCode")
    public void categoryJpaHashCode(){

        //Arrange

        String ownerID = "alexandre@gmail.com";
        String salaryDenomination = "Salary";

        //Act

        CategoryJpa categoryJpa = new CategoryJpa(ownerID, salaryDenomination);
        CategoryJpa newCategoryJpa = new CategoryJpa(ownerID, salaryDenomination);



        boolean result = categoryJpa.hashCode()==newCategoryJpa.hashCode();

        //Assert

        assertTrue(categoryJpa.hashCode()==categoryJpa.hashCode());
        assertTrue(categoryJpa.hashCode()==newCategoryJpa.hashCode());
        assertEquals(true, result);


    }

    @Test
    @DisplayName("CategoryJpa |HashCode : SadCase")
    public void categoryJpaHashCode_SadCase(){

        //Arrange

        String ownerID = "alexandre@gmail.com";
        String salaryDenomination = "Salary";

        String newOwnerID = "paulo@gmail.com";
        String otherDenomination = "Outcome";

        //Act

        CategoryJpa categoryJpa = new CategoryJpa(ownerID, salaryDenomination);
        CategoryJpa newCategoryJpa = new CategoryJpa(newOwnerID, otherDenomination);

        boolean result = categoryJpa.hashCode()==newCategoryJpa.hashCode();

        //Assert

        assertFalse(categoryJpa.hashCode()==newCategoryJpa.hashCode());
        assertEquals(false, result);


    }

}