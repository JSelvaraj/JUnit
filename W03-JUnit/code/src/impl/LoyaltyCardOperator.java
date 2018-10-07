package impl;

import common.AbstractFactoryClient;
import common.InsufficientPointsException;
import common.OwnerAlreadyRegisteredException;
import common.OwnerNotRegisteredException;
import interfaces.ILoyaltyCard;
import interfaces.ILoyaltyCardOperator;
import interfaces.ILoyaltyCardOwner;

import java.util.*;

/**
 * This class represents a simple loyalty card operator.
 *
 */
public class LoyaltyCardOperator extends AbstractFactoryClient implements ILoyaltyCardOperator {

    private Hashtable<String, ILoyaltyCard> loyaltyCardOwnerList =
            new Hashtable<String, ILoyaltyCard>();
    private static int rPENCE_PER_POINTS = 100;


    @Override
    public void registerOwner(ILoyaltyCardOwner loyaltyCardOwner) throws OwnerAlreadyRegisteredException {
        if (loyaltyCardOwner == null) {
            throw new IllegalArgumentException();
        }

        if (loyaltyCardOwnerList.get(loyaltyCardOwner.getEmail()) == null) {
            ILoyaltyCard loyaltyCard = getFactory().makeLoyaltyCard(loyaltyCardOwner);
            loyaltyCardOwnerList.put(loyaltyCardOwner.getEmail(), loyaltyCard);
        } else {
            throw new OwnerAlreadyRegisteredException();
        }
    }

    @Override
    public void unregisterOwner(ILoyaltyCardOwner loyaltyCardOwner) throws OwnerNotRegisteredException {
        if (loyaltyCardOwner == null) {
            throw new IllegalArgumentException();
        }
        if (loyaltyCardOwnerList.remove(loyaltyCardOwner.getEmail()) == null) {
            throw new OwnerNotRegisteredException();
        }
    }

    @Override
    public void processMoneyPurchase(String ownerEmail, int pence) throws OwnerNotRegisteredException {

        ILoyaltyCard loyaltyCard = loyaltyCardOwnerList.get(ownerEmail);
        if (loyaltyCard == null) {
            throw new OwnerNotRegisteredException();
        }
        int points = pence / rPENCE_PER_POINTS;
        loyaltyCard.addPoints(points);
        loyaltyCardOwnerList.put(ownerEmail, loyaltyCard);
    }

    @Override
    public void processPointsPurchase(String ownerEmail, int pence)
            throws InsufficientPointsException, OwnerNotRegisteredException {
        ILoyaltyCard loyaltyCard = loyaltyCardOwnerList.get(ownerEmail);
        if (loyaltyCard != null) { //if loyaltyCard == null that means that the email hasn't been registered.
            loyaltyCard.usePoints(pence);
            loyaltyCardOwnerList.put(ownerEmail, loyaltyCard);
        } else {
            throw new OwnerNotRegisteredException();
        }
    }

    @Override
    public int getNumberOfCustomers() {
        return loyaltyCardOwnerList.size();
    }

    @Override
    public int getTotalNumberOfPoints() {
        int total = 0;
        Collection<ILoyaltyCard> loyaltyCards = loyaltyCardOwnerList.values();
        for (ILoyaltyCard L: loyaltyCards) {
            total += L.getNumberOfPoints();
        }
        return total;
    }


    @Override
    public int getNumberOfPoints(String ownerEmail) throws OwnerNotRegisteredException {
        ILoyaltyCard loyaltyCard = loyaltyCardOwnerList.get(ownerEmail);
        if (loyaltyCard != null) {
            return loyaltyCard.getNumberOfPoints();
        } else {
            throw new OwnerNotRegisteredException();
        }
    }

    @Override
    public int getNumberOfUses(String ownerEmail) throws OwnerNotRegisteredException {
        ILoyaltyCard loyaltyCard = loyaltyCardOwnerList.get(ownerEmail);
        if (loyaltyCard != null) {
            return loyaltyCard.getNumberOfUses();
        } else {
            throw new OwnerNotRegisteredException();
        }

    }

    @Override
    public ILoyaltyCardOwner getMostUsed() throws OwnerNotRegisteredException {
        if (loyaltyCardOwnerList.size() == 0) {
            throw new OwnerNotRegisteredException();
        }
        ILoyaltyCard mostUsed = null;
        Collection<ILoyaltyCard> loyaltyCards = loyaltyCardOwnerList.values();
        for (ILoyaltyCard L: loyaltyCards) {
            if (mostUsed == null) {
                mostUsed = L;
            }
            if (L.getNumberOfUses() > mostUsed.getNumberOfUses()) {
                mostUsed = L;
            }
        }
        return mostUsed.getOwner();
    }
}
