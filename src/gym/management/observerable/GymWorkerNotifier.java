package gym.management.observerable;
import gym.customers.Person;
import gym.management.GymWorker;
import java.util.List;

/**
 * Represents a gym worker who is responsible for managing and notifying members about updates.
 * This class extends  GymWorker and implements the Sender interface to act as an observable entity.
 */
public abstract class GymWorkerNotifier extends GymWorker implements Sender<Person> {

    protected List<Person> members; // A list of registered members to be notified.

    /**
     *Constructs a new GymWorkerNotifier with the specified details.
     * @param person ---> the person representing the gym worker.
     * @param salary ---> the salary of the gym worker.
     * @param personList ---> the list of members managed by the gym worker.
     */
    public GymWorkerNotifier(Person person, int salary, List<Person> personList) {
        super(person, salary);
        members = personList;
    }

    /**
     * Registers a new member to the notification list.
     * @param member --->the member to be registered.
     */
    public void register(Person member) {
        members.add(member);
    }

    /**
     * Unregisters a member from the notification list.
     * @param member ---> the member to be unregistered.
     */
    public void unregister(Person member) {
        members.remove(member);
    }
    /**
     * Sends a notification message to all registered members.
     * @param msg ---> the message to be sent to all members.
     */
    @Override
    public void notifyMembers(String msg) {
        for (Member member : members) {
            member.update(msg);
        }
    }
}
