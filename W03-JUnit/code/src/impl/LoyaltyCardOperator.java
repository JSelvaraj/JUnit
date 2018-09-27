package impl;

import common.AbstractFactoryClient;
import common.InsufficientPointsException;
import common.OwnerAlreadyRegisteredException;
import common.OwnerNotRegisteredException;
import interfaces.ILoyaltyCard;
import interfaces.ILoyaltyCardOperator;
import interfaces.ILoyaltyCardOwner;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

/**
 * This class represents a simple loyalty card operator.
 *
 */
public class LoyaltyCardOperator extends AbstractFactoryClient implements ILoyaltyCardOperator {

    private Hashtable loyaltyCardOwnerList;
    private static int hash_MODULO = 10;


    @Override
    public void registerOwner(ILoyaltyCardOwner loyaltyCardOwner) throws OwnerAlreadyRegisteredException {
        int key = loyaltyCardOwner.hashCode() % hash_MODULO;
        loyaltyCardOwnerList.put(key, loyaltyCardOwner);
    }

    @Override
    public void unregisterOwner(ILoyaltyCardOwner loyaltyCardOwner) throws OwnerNotRegisteredException {
        // TODO Auto-generated method stub
    }

    @Override
    public void processMoneyPurchase(String ownerEmail, int pence) throws OwnerNotRegisteredException {
        // TODO Auto-generated method stub
    }

    @Override
    public void processPointsPurchase(String ownerEmail, int pence)
            throws InsufficientPointsException, OwnerNotRegisteredException {
        // TODO Auto-generated method stub
    }

    @Override
    public int getNumberOfCustomers() {
        return 0;

    }

    @Override
    public int getTotalNumberOfPoints() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getNumberOfPoints(String ownerEmail) throws OwnerNotRegisteredException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getNumberOfUses(String ownerEmail) throws OwnerNotRegisteredException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public ILoyaltyCardOwner getMostUsed() throws OwnerNotRegisteredException {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * This method gets a checks if a loyalty card owner is currently registered.
     * @param key represents the key to find the associated value in the hashmap.
     * @returns the value associated witht the above key if it's in the map. Returns null otherwise.
     */
    public ILoyaltyCardOwner getLoyaltyCardOwner(int key) {
        ILoyaltyCardOwner loyaltyCardOwner = (ILoyaltyCardOwner) loyaltyCardOwnerList.get(key);
        return loyaltyCardOwner;
    }
}
