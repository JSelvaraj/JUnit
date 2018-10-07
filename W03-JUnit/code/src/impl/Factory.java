package impl;

import interfaces.IFactory;
import interfaces.ILoyaltyCard;
import interfaces.ILoyaltyCardOperator;
import interfaces.ILoyaltyCardOwner;


/**
 * This class implements a singleton factory.
 *
 */
public final class Factory implements IFactory {

    private static IFactory factoryInstance = null;

    private Factory() {

    }

    /**
     * Method which returns an instance of the singleton Factory class.
     * @return the instance of the Factory
     */
    public static IFactory getInstance() {
        if (factoryInstance == null) {
            factoryInstance = new Factory();
        }
        return factoryInstance;
    }

    @Override
    public ILoyaltyCardOwner makeLoyaltyCardOwner(String email, String name) {
        if (email.equals("") || name.equals("") || name.equals(null) || email.equals(null)) {
            throw new IllegalArgumentException();
        }
        ILoyaltyCardOwner new_owner = new LoyaltyCardOwner(email, name);
        return new_owner;
    }

    @Override
    public ILoyaltyCard makeLoyaltyCard(ILoyaltyCardOwner loyaltyCardOwner) {
        if (loyaltyCardOwner == null) {
            throw new IllegalArgumentException();
        }
        ILoyaltyCard new_card = new LoyaltyCard(loyaltyCardOwner);

        return new_card;
    }

    @Override
    public ILoyaltyCardOperator makeLoyaltyCardOperator() {
        ILoyaltyCardOperator loyaltyCardOperator = new LoyaltyCardOperator();
        return loyaltyCardOperator;
    }
}
