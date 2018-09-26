package test;

import interfaces.ILoyaltyCard;
import org.junit.*;

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
    private static ILoyaltyCardOwner testLoyaltyCardOwner = getFactory().makeLoyaltyCardOwner("jack@jack.com", "Jack");

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
//    * This test ensures that if a null value is given to either of the fields in the ILoyaltyCardOwner object that it
//    * catches it and informs the user.
//     */
//    @Test(expected = NullPointerException.class)
//    public void nullValueLoyaltyCardOwnerFields() throws NullPointerException {
//        ILoyaltyCardOwner loyaltyCardOwner = getFactory().makeLoyaltyCardOwner(null, null);
//    }

    /*
    * This checks the factory was able to call a sensible constructor to get a non-null instance of ILoyaltyCard
     */
    @Test
    public void loyaltyCardCreationNonNull() {
        ILoyaltyCard loyaltyCard = getFactory().makeLoyaltyCard(testLoyaltyCardOwner);
        assertFalse(loyaltyCard == null);
    }

}
