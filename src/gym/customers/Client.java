package gym.customers;
import gym.Gym;

public class Client extends Person {

    /**
     * Constructs a new Client object using the details of an existing Person.
     * @param person ---> the whose details will be used to create the client.
     */
    public Client(Person person) {
        super(person);
    }

    /**
     * Deducts the specified session price from the client's balance and adds it to the gym's balance.
     * @param price ---> the price of the session to deduct from the client's balance
     */
    public void payForSession(int price) {
       mBalance.addToBalance(-price);
        Gym.getInstance().addBalance(price);
    }

}
