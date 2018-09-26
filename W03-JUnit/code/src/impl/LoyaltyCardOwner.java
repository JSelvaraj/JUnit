package impl;

import interfaces.ILoyaltyCardOwner;

/**
 * This class represents loyalty card owners.
 *
 */
public class LoyaltyCardOwner implements ILoyaltyCardOwner {

    private String email;
    private String name;

    /*
    This constructor takes in an email and name for the loyaltyCardOwnerClass.
    @param email is a string containing the email of the customer
    @param name is a string containing the name of the customer
     */

    public LoyaltyCardOwner(String email, String name) {
        this.email = email;
        this.name = name;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getName() {
        return name;
    }

}
