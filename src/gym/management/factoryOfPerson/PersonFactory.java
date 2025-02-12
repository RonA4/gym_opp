package gym.management.factoryOfPerson;
import gym.customers.Client;
import gym.customers.Person;
import gym.management.Instructor;
import gym.management.Secretary;

/**
 * Factory class for creating different types of people involved in the gym management system.
 */
public class PersonFactory {
    /**
     * Creates a specific type of person based on the provided PersonType.
     * @param type   ---> The type of person to create (Client, Instructor, or Secretary).
     * @param params --->  params A object containing the necessary parameters to create the person.
     * @return ---> A new instance of Person, specifically a  Client,  Instructor, or  Secretary.
     */
    public static Person create(PersonType type, PersonFactoryParams params) {
        Person person = params.getPerson();
        switch (type) {
            case Client:
                return new Client(person);
            case Instructor:
                return new Instructor(person, params.getSalary(), params.getSessionTypes());
            case Secretary:
                return new Secretary(person, params.getSalary(), params.getPersonList(), params.getSessions(), params.getHistoryActionList());
            default:
                throw new IllegalArgumentException("Unknown person type: " + type);
        }
    }
}