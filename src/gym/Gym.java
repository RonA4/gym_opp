package gym;
import gym.customers.Client;
import gym.customers.Person;
import gym.management.Sessions.factoryOfsession.Session;
import gym.management.factoryOfPerson.PersonFactory;
import gym.management.factoryOfPerson.PersonFactoryParams;
import gym.management.factoryOfPerson.PersonType;
import gym.management.GymWorker;
import gym.management.Secretary;
import java.util.ArrayList;
import java.util.List;
/**
 * Represents the Gym system, including gym details, clients, employees, sessions, and balance management.
 * The class follows the Singleton design pattern, ensuring that there is only one instance of the gym.
 */
public class Gym {

    private String mName;
    private Secretary mSecretary;
    private final List<Person> mPersonsList = new ArrayList<>();
    private final List<Session> mSessions = new ArrayList<>();
    private final List<String> mHistoryActionList = new ArrayList<>();
    private int mBalance = 0;

    private static Gym inst = null;

    private Gym() {
    }
    /**
     * Returns the singleton instance of the Gym.
     * @return the unique Gym instance.
     */
    public static Gym getInstance() {
        if (inst == null) {
            inst = new Gym();
        }
        return inst;
    }


    /**
     * Sets the gym's secretary and initializes the necessary parameters for the secretary.
     * If there is an existing secretary, it is destroyed and replaced.
     * @param person ---> the person object representing the secretary.
     * @param salary ---> the salary of the secretary.
     */
    public void setSecretary(Person person, int salary) {
        PersonFactoryParams params = new PersonFactoryParams(person);
        params.setSalary(salary);
        params.setPersonList(mPersonsList);
        params.setSessions(mSessions);
        params.setHistoryActionList(mHistoryActionList);
        if (mSecretary != null) {
            mSecretary.destroy();
        }
        mSecretary = (Secretary) PersonFactory.create(PersonType.Secretary, params);
    }
    /**
     * Adds a specified balance amount to the gym's balance.
     * @param  ---> balance the amount to add to the gym's balance.
     */
    public void addBalance(int balance) {
        this.mBalance += balance;
    }
    /**
     * Subtracts a specified balance amount from the gym's balance.
     * @param balance ---> the amount to subtract from the gym's balance.
     */
    public void subBalance(int balance) {
        this.mBalance -= balance;
    }
    /**
     * Returns the gym's secretary.
     * @return ---> the gym's secretary.
     */
    public Secretary getSecretary() {
        return mSecretary;
    }

    /**
     * Sets the name of the gym.
     * @param name ---> the name of the gym.
     */
    public void setName(String name) {
        mName = name;
    }

    /**
     * Returns a string representation of the gym, including the gym's name, secretary, balance,
     * clients, employees, and sessions.
     * @return ---> the string representation of the gym.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.
                append("Gym Name: ").append(mName).append("\n").
                append("Gym Secretary: ").append(mSecretary.toString()).append("\n").
                append("Gym Balance: ").append(mBalance).append("\n").
                append("\n").append("Clients Data:").append("\n");


        for (Person person : mPersonsList) {
            if (person instanceof Client) {
                sb.append(person).append("\n");
            }
        }
        sb.append("\n").append("Employees Data:").append("\n");
        for (Person person : mPersonsList) {
            if (person instanceof GymWorker) {
                sb.append(person).append("\n");
            }
        }
        sb.append(mSecretary).append("\n");
        sb.append("\n").append("Sessions Data:").append("\n");
        for (Session session : mSessions) {
            sb.append(session).append("\n");
        }
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }


}
