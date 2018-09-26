package test;

import common.InsufficientPointsException;
import interfaces.ILoyaltyCard;
import org.junit.Test;
import org.junit.Before;

import common.AbstractFactoryClient;
import interfaces.ILoyaltyCardOwner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * This is a JUnit test class for the loyalty card ADT.
 *
 */
public class Tests extends AbstractFactoryClient {

    //This variable was made to speed up the process of testing the ILoyaltyCard class
    private ILoyaltyCardOwner testLoyaltyCardOwner = null;

    /**
     *  Used for the tests made for the loyaltyCard class to speed up the testing process.
     */
    @Before
    public void setUpLoyaltyCardOwner() {
        testLoyaltyCardOwner = getFactory().makeLoyaltyCardOwner("jack@jack.com", "Jack");
    }
    /**
     * This checks that the factory was able to call a sensible constructor to get a non-null instance of ILoyaltyCardOwner.
     */
    @Test
    public void loyaltyCardOwnerCreationNonNull() {
        ILoyaltyCardOwner loyaltyCardOwner = getFactory().makeLoyaltyCardOwner("jon@jon.com", "Jon");
        assertFalse(loyaltyCardOwner == null);
    }
    /**
     * This checks that the an instance of ILoyaltyCardOwner has the correct information in the correct fields.
     */
    @Test
    public void loyaltyCardOwnerInputCorrect() {
        ILoyaltyCardOwner loyaltyCardOwner = getFactory().makeLoyaltyCardOwner("jon@jon.com", "Jon");
        assertTrue(loyaltyCardOwner.getEmail().equals("jon@jon.com"));
        assertTrue(loyaltyCardOwner.getName().equals("Jon"));
    }
    /**
     * This checks that the an instance of ILoyaltyCardOwner has the correct information in the correct fields for a different
     * set of data.
     */
    @Test
    public void loyaltyCardOwnerInputCorrect2() {
        ILoyaltyCardOwner loyaltyCardOwner = getFactory().makeLoyaltyCardOwner("jane@jon.com", "Jane");
        assertTrue(loyaltyCardOwner.getEmail().equals("jane@jon.com"));
        assertTrue(loyaltyCardOwner.getName().equals("Jane"));
    }
    /**
     * This checks that when two ILoyaltyCardOwner Objects are made, that they both are instantiated correctly.
     */
    @Test
    public void separateLoyaltyCardOwnerObjects() {
        ILoyaltyCardOwner loyaltyCardOwner = getFactory().makeLoyaltyCardOwner("jane@jon.com", "Jane");
        ILoyaltyCardOwner loyaltyCardOwner2 = getFactory().makeLoyaltyCardOwner("jon@jon.com", "Jon");
        assertTrue(loyaltyCardOwner.getEmail().equals("jane@jon.com"));
        assertTrue(loyaltyCardOwner.getName().equals("Jane"));
        assertTrue(loyaltyCardOwner2.getEmail().equals("jon@jon.com"));
        assertTrue(loyaltyCardOwner2.getName().equals("Jon"));
    }

    /**
     * This checks that if any of the parameters for the makeLoyaltyCardOwner method are blank, that a new
     * object is not created.
     */
    @Test
    public void loyaltyCardOwnerBlankInput() {
        ILoyaltyCardOwner loyaltyCardOwner = getFactory().makeLoyaltyCardOwner("", "");
        assertTrue(loyaltyCardOwner == null);
    }

//    /*
//     * This test ensures that if a null value is given to either of the fields in the ILoyaltyCardOwner object that it
//     * catches it and informs the user.
//     */
//    @Test(expected = NullPointerException.class)
//    public void nullValueLoyaltyCardOwnerFields() throws NullPointerException {
//        ILoyaltyCardOwner loyaltyCardOwner = getFactory().makeLoyaltyCardOwner(null, null);
//    }

    /**
     * This checks the factory was able to call a sensible constructor to get a non-null instance of ILoyaltyCard.
     */
    @Test
    public void loyaltyCardCreationNonNull() {
        ILoyaltyCard loyaltyCard = getFactory().makeLoyaltyCard(testLoyaltyCardOwner);
        assertFalse(loyaltyCard == null);
    }

    /**
     * This checks that the getOwner method returns the correct loyaltyCardOwner.
     */
    @Test
    public void loyaltyCardGetOwnerValue() {
        ILoyaltyCard loyaltyCard = getFactory().makeLoyaltyCard(testLoyaltyCardOwner);
        assertTrue(loyaltyCard.getOwner() == testLoyaltyCardOwner);
    }

    /**
     * This checks that the getNumberOfUses method correctly returns the initial number of uses as 0.
     */
    @Test
    public void loyaltyCardInitialGetNumberOfUsesValue() {
        ILoyaltyCard loyaltyCard = getFactory().makeLoyaltyCard(testLoyaltyCardOwner);
        assertTrue(loyaltyCard.getNumberOfUses() == 0);
    }
    /**
     * This checks that the getNumberOfPoints method correctly returns 0 as the initial number of points is correctly.
     */
    @Test
    public void loyaltyCardInitialGetNumberOfPointsValue() {
        ILoyaltyCard loyaltyCard = getFactory().makeLoyaltyCard(testLoyaltyCardOwner);
        assertTrue(loyaltyCard.getNumberOfPoints() == 0);
    }

    /**
     * This checks that the addPoints method correctly adds points to the number_of_points field.
     */
    @Test
    public void checkAddPointsMethod() {
        ILoyaltyCard loyaltyCard = getFactory().makeLoyaltyCard(testLoyaltyCardOwner);
        loyaltyCard.addPoints(20);
        assertTrue(loyaltyCard.getNumberOfPoints() == 20);
    }

    /**
     * This checks that the usePoints method takes away the correct number of points from an
     * instance of the object.
     * @throws InsufficientPointsException if more points are being used than are available.
     */
    @Test
    public void checkUsePointsMethod() throws InsufficientPointsException {
        ILoyaltyCard loyaltyCard = getFactory().makeLoyaltyCard(testLoyaltyCardOwner);
        loyaltyCard.addPoints(20);
        loyaltyCard.usePoints(5);
        assertTrue(loyaltyCard.getNumberOfPoints() == 15);
    }

    /**
     * This initializes a loyalty card object with 20 points and tries to remove 25 points.
     * @throws InsufficientPointsException as more points are being used than are available.
     */
    @Test (expected = InsufficientPointsException.class)
    public void insufficientPointsException() throws InsufficientPointsException {
        ILoyaltyCard loyaltyCard = getFactory().makeLoyaltyCard(testLoyaltyCardOwner);
        loyaltyCard.addPoints(20);
        loyaltyCard.usePoints(25);
    }

    /**
     * This checks that addPoints method still works when 0 points are added.
     */
    @Test
    public void addPointsWrongType() {
        ILoyaltyCard loyaltyCard = getFactory().makeLoyaltyCard(testLoyaltyCardOwner);
        loyaltyCard.addPoints(0);
        assertTrue(loyaltyCard.getNumberOfPoints() == 0);
    }

    /**
     * This checks that the addPoints method doesn't allow a negative number to be used.
     */
    @Test
    public void addPointsNegativeNumber() {
        ILoyaltyCard loyaltyCard = getFactory().makeLoyaltyCard(testLoyaltyCardOwner);
        loyaltyCard.addPoints(-20);
        assertFalse(loyaltyCard.getNumberOfPoints() < 0);
    }



}
