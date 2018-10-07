package test;

import common.*;
import impl.LoyaltyCardOperator;
import interfaces.ILoyaltyCard;
import interfaces.ILoyaltyCardOperator;
import org.junit.Test;
import org.junit.Before;

import interfaces.ILoyaltyCardOwner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * This is a JUnit test class for the loyalty card ADT.
 */
public class Tests extends AbstractFactoryClient {

    //This variables were made to speed up the process of testing
    private ILoyaltyCardOwner testLoyaltyCardOwner = null;
    private ILoyaltyCardOperator testOperator;

    /**
     * Used for the tests made for the loyaltyCard class to speed up the testing process.
     */
    @Before
    public void setUpLoyaltyCardOwner() {
        testLoyaltyCardOwner = getFactory().makeLoyaltyCardOwner("jack@jack.com", "Jack");
        testOperator = getFactory().makeLoyaltyCardOperator();
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
    @Test (expected = IllegalArgumentException.class)
    public void loyaltyCardOwnerBlankInput() {
        ILoyaltyCardOwner loyaltyCardOwner = getFactory().makeLoyaltyCardOwner("", "");
    }

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
     *
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
     *
     * @throws InsufficientPointsException as more points are being used than are available.
     */
    @Test(expected = InsufficientPointsException.class)
    public void insufficientPointsException() throws InsufficientPointsException {
        ILoyaltyCard loyaltyCard = getFactory().makeLoyaltyCard(testLoyaltyCardOwner);
        loyaltyCard.addPoints(20);
        loyaltyCard.usePoints(25);
    }

    /**
     * This checks that addPoints method still works when 0 points are added.
     */
    @Test
    public void add0Points() {
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

    /**
     * This checks that the number of uses field correctly updates when the the card is uses
     * and the usePoints method is called.
     *
     * @throws InsufficientPointsException if the number of points used is greater than the points
     *                                     on the card.
     */
    @Test
    public void checkNumberOfUses() throws InsufficientPointsException {
        ILoyaltyCard loyaltyCard = getFactory().makeLoyaltyCard(testLoyaltyCardOwner);
        assertTrue(loyaltyCard.getNumberOfUses() == 0);
        loyaltyCard.addPoints(20);
        loyaltyCard.usePoints(5);
        assertTrue(loyaltyCard.getNumberOfUses() == 1);
        loyaltyCard.usePoints(5);
        assertTrue(loyaltyCard.getNumberOfUses() == 2);
    }

    /**
     * This checks that when two separate ILoyaltyCard Objects are created with different owners, that the getOwner
     * correctly shows they have different owners.
     */
    @Test
    public void separateLoyaltyCardsGetsOwnerCorrectly() {
        ILoyaltyCard loyaltyCard = getFactory().makeLoyaltyCard(testLoyaltyCardOwner);
        ILoyaltyCard loyaltyCard2 = getFactory().makeLoyaltyCard(getFactory().makeLoyaltyCardOwner("jane@jon.com", "Jane"));
        assertFalse(loyaltyCard.getOwner() == loyaltyCard2.getOwner());
    }

    /**
     * This checks that when two separate ILoyaltyCard Objects are created, when one's number_of uses get changed,
     * only that object's number_of_uses change.
     *
     * @throws InsufficientPointsException if the number of points used is greater than the number of points on the card
     */
    @Test
    public void separateLoyaltyCardsUpdatesNumberOfUsesCorrectly() throws InsufficientPointsException {
        ILoyaltyCard loyaltyCard = getFactory().makeLoyaltyCard(testLoyaltyCardOwner);
        ILoyaltyCard loyaltyCard2 = getFactory().makeLoyaltyCard(testLoyaltyCardOwner);
        assertTrue(loyaltyCard.getNumberOfUses() == 0);
        loyaltyCard.addPoints(20);
        loyaltyCard.usePoints(5);
        assertTrue(loyaltyCard.getNumberOfUses() == 1);
        assertTrue(loyaltyCard2.getNumberOfUses() == 0);
    }

    /**
     * This checks that when two separate ILoyaltyCard Objects are created, when one's number_of_points get changed,
     * only that object's number_of_points change.
     *
     * @throws InsufficientPointsException if the number of points used is greater than the number of points on the card
     */
    @Test
    public void separateLoyaltyCardsUpdatesNumberOfPointsCorrectly() throws InsufficientPointsException {
        ILoyaltyCard loyaltyCard = getFactory().makeLoyaltyCard(testLoyaltyCardOwner);
        ILoyaltyCard loyaltyCard2 = getFactory().makeLoyaltyCard(testLoyaltyCardOwner);
        assertTrue(loyaltyCard.getNumberOfUses() == 0);
        loyaltyCard.addPoints(20);
        loyaltyCard2.addPoints(15);
        assertTrue(loyaltyCard.getNumberOfPoints() == 20);
        assertTrue(loyaltyCard2.getNumberOfPoints() == 15);
    }


    /**
     * This checks that the Loyalty Card Operator is initializes correctly.
     */
    @Test
    public void checkLoyaltyCardOwnerOperatorNotNull() {
        assertFalse(testOperator == null);
    }


    /**
     * This checks that an loyaltycard owner is successfully registered. If an Exception() is thrown it means
     * that the OwnerAlreadyRegisterdException was thrown at the first method call which is wrong.
     *
     * @throws OwnerAlreadyRegisteredException because a loyalty card owner object is added
     *                                         to the hashtable twice.
     * @throws Exception if an OwnerAlreadyRegisteredException is thrown when it shouldn't be thrown.
     */
    @Test(expected = OwnerAlreadyRegisteredException.class)
    public void registerLoyaltyCardOwner() throws OwnerAlreadyRegisteredException, Exception {
        try {
            testOperator.registerOwner(testLoyaltyCardOwner);
        } catch (OwnerAlreadyRegisteredException e) {
            throw new Exception();
        }
        testOperator.registerOwner(testLoyaltyCardOwner);
    }

    /**
     * This tests that loyalty card owners can be unregistered successfully. The unregisterOwner method is in a try/catch
     * block because if it throws an error here that means it is not functioning correctly.
     *
     * @throws OwnerAlreadyRegisteredException should not be thrown in this instance.
     * @throws OwnerNotRegisteredException     should be thrown as the method attempts to remove the same loyalty
     *                                         card owner twice.
     */
    @Test(expected = OwnerNotRegisteredException.class)
    public void checkUnregistOwner() throws OwnerAlreadyRegisteredException, OwnerNotRegisteredException, Exception {
        testOperator.registerOwner(testLoyaltyCardOwner);
        try {
            testOperator.unregisterOwner(testLoyaltyCardOwner);
        } catch (OwnerNotRegisteredException e) {
            throw new Exception();
        }
        testOperator.getNumberOfPoints(testLoyaltyCardOwner.getEmail());
    }

    /**
     * This checks that the method correctly returns the initial number of points on a loyalty card when it's
     * registered as zero.
     *
     * @throws OwnerAlreadyRegisteredException If the loyalty card owner is already registered.
     * @throws OwnerNotRegisteredException     If loyalty card owner is not registered.
     */
    @Test
    public void checkGetNumberOfPointsInitial() throws OwnerAlreadyRegisteredException, OwnerNotRegisteredException {
        testOperator.registerOwner(testLoyaltyCardOwner);
        assertTrue(testOperator.getNumberOfPoints(testLoyaltyCardOwner.getEmail()) == 0);
    }

    /**
     * This checks that the correct exception is thrown if the getNumberOfPoints method is called and there isn't
     * the loyalty card email is not registered.
     *
     * @throws OwnerNotRegisteredException because the owner being looked for has not been registered.
     */
    @Test(expected = OwnerNotRegisteredException.class)
    public void getNumberOfPointsFromUnregisteredEmail() throws OwnerNotRegisteredException {
        testOperator.getNumberOfPoints(testLoyaltyCardOwner.getEmail());
    }


    /**
     * This checks that the ProcessMoneyPurchase method of the LoyaltyCardOperator class can correctly add points to
     * a particular loyalty card.
     *
     * @throws OwnerAlreadyRegisteredException If the loyalty card owner is already registered.
     * @throws OwnerNotRegisteredException     If loyalty card owner is not registered.
     */
    @Test
    public void checkProcessMoneyPurchaseIsSuccessful()
            throws OwnerNotRegisteredException, OwnerAlreadyRegisteredException {
        testOperator.registerOwner(testLoyaltyCardOwner);
        testOperator.processMoneyPurchase(testLoyaltyCardOwner.getEmail(), 200);
        assertTrue(testOperator.getNumberOfPoints(testLoyaltyCardOwner.getEmail()) == 2);
    }

    /**
     * This checks that the ProcessMoneyPurchase method of the LoyaltyCardOperator class can correctly add only
     * a whole number of points to a particular loyalty card.
     *
     * @throws OwnerAlreadyRegisteredException If the loyalty card owner is already registered.
     * @throws OwnerNotRegisteredException     If loyalty card owner is not registered.
     */
    @Test
    public void checkProcessMoneyPurchaseNoDecimal()
            throws OwnerNotRegisteredException, OwnerAlreadyRegisteredException {
        testOperator.registerOwner(testLoyaltyCardOwner);
        testOperator.processMoneyPurchase(testLoyaltyCardOwner.getEmail(), 299);
        assertTrue(testOperator.getNumberOfPoints(testLoyaltyCardOwner.getEmail()) == 2);
    }

    /**
     * This checks that the method that processes purchases using points works successfully with normal inputs.
     *
     * @throws OwnerAlreadyRegisteredException If the loyalty card owner is already registered.
     * @throws OwnerNotRegisteredException     If loyalty card owner is not registered.
     * @throws InsufficientPointsException     If there are insufficient loyalty points on the loyalty card
     *                                         to cover the cost of the item.
     */
    @Test
    public void checkProcessPointsPurchaseIsSuccessful()
            throws OwnerNotRegisteredException, OwnerAlreadyRegisteredException, InsufficientPointsException {
        testOperator.registerOwner(testLoyaltyCardOwner);
        testOperator.processMoneyPurchase(testLoyaltyCardOwner.getEmail(), 2000);
        assertTrue(testOperator.getNumberOfPoints(testLoyaltyCardOwner.getEmail()) == 20);
        testOperator.processPointsPurchase(testLoyaltyCardOwner.getEmail(), 15);
        assertTrue(testOperator.getNumberOfPoints(testLoyaltyCardOwner.getEmail()) == 5);
    }

    /**
     * This checks that the method that processes purchases using points works successfully if the number of points
     * used equals the price of the purchase.
     *
     * @throws OwnerAlreadyRegisteredException If the loyalty card owner is already registered.
     * @throws OwnerNotRegisteredException     If loyalty card owner is not registered.
     * @throws InsufficientPointsException     If there are insufficient loyalty points on the loyalty card
     *                                         to cover the cost of the item.
     */
    @Test
    public void checkProcessPointsPurchaseCostEqualsPoints()
            throws OwnerNotRegisteredException, OwnerAlreadyRegisteredException, InsufficientPointsException {
        testOperator.registerOwner(testLoyaltyCardOwner);
        testOperator.processMoneyPurchase(testLoyaltyCardOwner.getEmail(), 2000);
        testOperator.processPointsPurchase(testLoyaltyCardOwner.getEmail(), 20);
        assertTrue(testOperator.getNumberOfPoints(testLoyaltyCardOwner.getEmail()) == 0);
    }

    /**
     * This checks that the correct exception is thrown when the user attempts to purchase something with points,
     * but don't have enough points.
     *
     * @throws OwnerAlreadyRegisteredException If the loyalty card owner is already registered.
     * @throws OwnerNotRegisteredException     If loyalty card owner is not registered.
     * @throws InsufficientPointsException     If there are insufficient loyalty points on the loyalty card
     *                                         to cover the cost of the item.
     */
    @Test(expected = InsufficientPointsException.class)
    public void checkProcessPointsInsufficientPoints()
            throws OwnerNotRegisteredException, OwnerAlreadyRegisteredException, InsufficientPointsException {
        testOperator.registerOwner(testLoyaltyCardOwner);
        testOperator.processMoneyPurchase(testLoyaltyCardOwner.getEmail(), 2000);
        testOperator.processPointsPurchase(testLoyaltyCardOwner.getEmail(), 25);
    }

    /**
     * This checks that the OwnerNotRegisterException is thrown when the the LoyaltyCardOperator object attempts to
     * add points to an email that is not registered.
     *
     * @throws OwnerNotRegisteredException If the loyaly card owner is not registered.
     * @throws InsufficientPointsException If there are insufficient loyalty points on the loyalty card associated with
     *                                     the account.
     */
    @Test(expected = OwnerNotRegisteredException.class)
    public void checkMoneyPurchaseWhenOwnerEmailNotRegistered()
            throws OwnerNotRegisteredException {
        testOperator.processMoneyPurchase(testLoyaltyCardOwner.getEmail(), 2000);
    }

    /**
     * This checks that the OwnerNotRegisterException is thrown when the the LoyaltyCardOperator object attempts to
     * use points for an email that is not registered.
     *
     * @throws OwnerNotRegisteredException If the loyalty card owner is not registered.
     * @throws InsufficientPointsException If there are insufficient loyalty points on the loyalty card associated with
     *                                     the account.
     */
    @Test(expected = OwnerNotRegisteredException.class)
    public void checkPointPurchaseWhenOwnerEmailNotRegistered()
            throws OwnerNotRegisteredException, InsufficientPointsException {
        testOperator.processPointsPurchase(testLoyaltyCardOwner.getEmail(), 2000);
    }

    /**
     * This checks that the getNumberOfCustomers method works correctly.
     *
     * @throws OwnerAlreadyRegisteredException if the loyalty card owner is already registered.
     */
    @Test
    public void checkGetNumberOfCustomers() throws OwnerAlreadyRegisteredException {
        testOperator.registerOwner(testLoyaltyCardOwner);
        assertTrue(testOperator.getNumberOfCustomers() == 1);
    }

    /**
     * This checks that the getTotalNumberOfPoints method returns the correct number of loyalty points currently stored
     * in the LoyaltyCardOperator object.
     *
     * @throws OwnerAlreadyRegisteredException If the loyalty card owner is already registered.
     * @throws OwnerNotRegisteredException     If loyalty card owner is not registered.
     */
    @Test
    public void checkGetTotalNumberOfPoints() throws OwnerNotRegisteredException, OwnerAlreadyRegisteredException {
        ILoyaltyCardOwner loyaltyCardOwner = getFactory().makeLoyaltyCardOwner("jon@jon.com", "Jon");
        testOperator.registerOwner(testLoyaltyCardOwner);
        testOperator.registerOwner(loyaltyCardOwner);
        testOperator.processMoneyPurchase(testLoyaltyCardOwner.getEmail(), 2000);
        testOperator.processMoneyPurchase(loyaltyCardOwner.getEmail(), 5000);
        assertTrue(testOperator.getTotalNumberOfPoints() == 70);
    }

    /**
     * This test checks that the getNumberOfUses method in the LoyaltyCardOperator class returns the correct value.
     *
     * @throws OwnerAlreadyRegisteredException if the owner email is already registered to a loyalty card.
     * @throws OwnerNotRegisteredException     if the owner email is not registered to a loyalty card.
     * @throws InsufficientPointsException     if there are insufficient points on the loyalty card to cover the cost of the
     *                                         purchase.
     */
    @Test
    public void checkOperatorNumberOfUses()
            throws OwnerAlreadyRegisteredException, OwnerNotRegisteredException, InsufficientPointsException {
        testOperator.registerOwner(testLoyaltyCardOwner);
        testOperator.processMoneyPurchase(testLoyaltyCardOwner.getEmail(), 200);
        testOperator.processPointsPurchase(testLoyaltyCardOwner.getEmail(), 1);
        assertTrue(testOperator.getNumberOfUses(testLoyaltyCardOwner.getEmail()) == 1);
    }

    /**
     * This checks that the OwnerNotRegisteredException is thrown if the LoyaltyCardOperatorObject tries to access a
     * loyalty card that has not been registered.
     *
     * @throws OwnerNotRegisteredException if the owner email is not registered.
     */
    @Test(expected = OwnerNotRegisteredException.class)
    public void checkOperatorNumberOfUsesUnregisteredOwnerEmail() throws OwnerNotRegisteredException {
        testOperator.getNumberOfUses(testLoyaltyCardOwner.getEmail());
    }

    /**
     * This test checks that the getMostUses method returns the correct loyalty card owner. It registers to loyalty card
     * owners with an email and has one of the owners make 2 purchases with points and the other make 1 purchase.
     * @throws OwnerAlreadyRegisteredException if the owner email is already registered to a loyalty card.
     * @throws OwnerNotRegisteredException     if the owner email is not registered to a loyalty card.
     * @throws InsufficientPointsException     if there are insufficient points on the loyalty card to cover the cost of the
     *                                         purchase.
     */
    @Test
    public void checkGetMostUses()
            throws OwnerAlreadyRegisteredException, OwnerNotRegisteredException, InsufficientPointsException {
        ILoyaltyCardOwner loyaltyCardOwner = getFactory().makeLoyaltyCardOwner("jon@jon.com", "Jon");
        testOperator.registerOwner(testLoyaltyCardOwner);
        testOperator.registerOwner(loyaltyCardOwner);
        testOperator.processMoneyPurchase(testLoyaltyCardOwner.getEmail(), 2000);
        testOperator.processMoneyPurchase(loyaltyCardOwner.getEmail(), 5000);
        testOperator.processPointsPurchase(testLoyaltyCardOwner.getEmail(), 10);
        testOperator.processPointsPurchase(testLoyaltyCardOwner.getEmail(), 10);
        testOperator.processPointsPurchase(loyaltyCardOwner.getEmail(), 5);
        assertTrue(testOperator.getMostUsed() == testLoyaltyCardOwner);
    }

    /**
     * This checks that if more than 1 loyalty card owner has the most number of loyalty card uses, the method will
     * select either of them but not a user that has fewer uses.
     * @throws OwnerAlreadyRegisteredException
     * @throws OwnerAlreadyRegisteredException if the owner email is already registered to a loyalty card.
     * @throws OwnerNotRegisteredException     if the owner email is not registered to a loyalty card.
     * @throws InsufficientPointsException     if there are insufficient points on the loyalty card to cover the cost of the
     *                                         purchase.
     */
    @Test
    public void checkGetMostUsesMultipleMostUsed()
            throws OwnerAlreadyRegisteredException, OwnerNotRegisteredException, InsufficientPointsException {
        ILoyaltyCardOwner loyaltyCardOwner = getFactory().makeLoyaltyCardOwner("jon@jon.com", "Jon");
        ILoyaltyCardOwner loyaltyCardOwner2 = getFactory().makeLoyaltyCardOwner("jane@jon.com", "Jon");
        testOperator.registerOwner(testLoyaltyCardOwner);
        testOperator.registerOwner(loyaltyCardOwner);
        testOperator.registerOwner(loyaltyCardOwner2);
        testOperator.processMoneyPurchase(testLoyaltyCardOwner.getEmail(), 2000);
        testOperator.processMoneyPurchase(loyaltyCardOwner.getEmail(), 5000);
        testOperator.processPointsPurchase(testLoyaltyCardOwner.getEmail(), 10);
        testOperator.processPointsPurchase(testLoyaltyCardOwner.getEmail(), 10);
        testOperator.processPointsPurchase(loyaltyCardOwner.getEmail(), 5);
        testOperator.processPointsPurchase(loyaltyCardOwner.getEmail(), 5);
        assertTrue(testOperator.getMostUsed() == testLoyaltyCardOwner || testOperator.getMostUsed() == loyaltyCardOwner);
    }

    /**
     * This checks that an OwnerNotRegisteredException is thrown if the method is called while no owners are registered.
     * @throws OwnerNotRegisteredException if there are no owners currently registered in the object.
     */
    @Test (expected = OwnerNotRegisteredException.class)
    public void getMostUsedNoOwners() throws OwnerNotRegisteredException {
        testOperator.getMostUsed();
    }

    /**
     * This method checks that an illegalargument exception is thrown if the makeloyaltyCard method is given a null
     * input.
     */
    @Test (expected = IllegalArgumentException.class)
    public void checkNullInputLoyaltyCardCreation() {
        ILoyaltyCard loyaltyCard = getFactory().makeLoyaltyCard(null);
    }

    /**
     * This method checks that an illegal argument exception is thrown if the registerOwner is given a null
     * input.
     * @throws OwnerAlreadyRegisteredException should not be thrown.
     */
    @Test (expected = IllegalArgumentException.class)
    public void checkNullInputLoyaltyCardOoperatorRegister() throws OwnerAlreadyRegisteredException {
        testOperator.registerOwner(null);
    }
    /**
     * This method checks that an illegal argument exception is thrown if the unregisterOwner is given a null
     * input.
     * @throws OwnerNotRegisteredException should not be thrown.
     */
    @Test (expected = IllegalArgumentException.class)
    public void checkNullInputLoyaltyCardOoperatorUnregister() throws OwnerNotRegisteredException {
        testOperator.unregisterOwner(null);
    }
    /**
     * This method checks that an illegal argument exception is thrown if the makeLoyaltyCardOwnermethod is given a null
     * input.
     * @throws OwnerAlreadyRegisteredException should not be thrown.
     */
    @Test (expected = IllegalArgumentException.class)
    public void checkNullInputsLoyaltyCardOwnerEmail() {
        ILoyaltyCardOwner loyaltyCardOwner = getFactory().makeLoyaltyCardOwner(null, "James");
    }
    /**
     * This method checks that an illegal argument exception is thrown if the makeLoyaltyCardOwnermethod is given a null
     * input.
     * @throws OwnerAlreadyRegisteredException should not be thrown.
     */
    @Test (expected = IllegalArgumentException.class)
    public void checkNullInputsLoyaltyCardOwnerName() {
        ILoyaltyCardOwner loyaltyCardOwner = getFactory().makeLoyaltyCardOwner("jon@jon.com", null);
    }

}
