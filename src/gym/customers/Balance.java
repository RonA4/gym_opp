package gym.customers;

/**
 * Represents a person's financial balance (client, instructor, secretary) in the fitness system.
 * Provides methods for retrieving and updating the balance.
 */
public class Balance {
    private int balance;

    /**
     * Constructs a new Balance object with the specified initial balance.
     *
     * @param balance --->  the initial balance amount
     */
    public Balance(int balance) {
        this.balance = balance;
    }

    /**
     * Returns the current balance.
     *
     * @return ---> the current balance amount.
     */
    public int getBalance() {
        return balance;
    }

    /**
     * Adds the specified amount to the current balance.
     *
     * @param amount ---> the amount to add to the balance.
     */
    public void addToBalance(int amount) {
        this.balance += amount;
    }

}
