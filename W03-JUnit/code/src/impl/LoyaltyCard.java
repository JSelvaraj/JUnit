package impl;

import common.InsufficientPointsException;
import interfaces.ILoyaltyCard;
import interfaces.ILoyaltyCardOwner;

/**
 * This class represents a Loyalty Card, recording information relating to an owners use of the card.
 *
 */
public class LoyaltyCard implements ILoyaltyCard {

    private ILoyaltyCardOwner owner = null;
    private int number_of_uses = 0;
    private int number_of_points = 0;

    /**
    * This constructor takes in a ILoyaltyCardOwner object to create a loyaltyCard object.
    * @param loyaltyCardOwner an object that represents the owner of the card see: LoyaltyCardOwner.java.
     */
    public LoyaltyCard(ILoyaltyCardOwner loyaltyCardOwner) {
        this.owner = loyaltyCardOwner;
    }

    @Override
    public ILoyaltyCardOwner getOwner() {
        return owner;
    }

    @Override
    public int getNumberOfUses() {
        return number_of_uses;
    }

    @Override
    public int getNumberOfPoints() {
        return number_of_points;
    }

    @Override
    public void addPoints(int points) {
        if (points < 0) {
            return;
        }
        number_of_points += points;
    }

    @Override
    public void usePoints(int points) throws InsufficientPointsException {
            if (points > number_of_points) {
                throw new InsufficientPointsException();
            }
            number_of_points -= points;
            number_of_uses++;
    }

}
