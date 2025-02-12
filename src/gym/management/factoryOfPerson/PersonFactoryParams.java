package gym.management.factoryOfPerson;
import gym.customers.Person;
import gym.management.Sessions.factoryOfsession.Session;
import gym.management.Sessions.typeSessions.SessionType;
import java.util.ArrayList;
import java.util.List;
/**
 * Contains the parameters needed to create a person using PersonFactory.
 * This class provides setters and getters for all attributes used in the creation process, Such as salary, types of sessions and activity history.
 */
public class PersonFactoryParams {
    private int mSalary;
    private final Person mPerson;
    private ArrayList<SessionType> mSessionTypes;
    private List<Person> mPersonsList;
    private List<Session> mSessions;
    private List<String> mHistoryActionList;

    /**
     * Constructs a new PersonFactoryParams instance with a required Person object.
     *  This constructor ensures that every Person creation process starts with a valid Person.
     * @param person ---> The base  Person object used as a foundation for creating specialized roles.
     */
    public PersonFactoryParams(Person person) {
        mPerson = person;
    }

    /**
     * Retrieves the base Person object.
     * @return ---> person .
     */
    public Person getPerson() {
        return mPerson;
    }

    /**
     *Sets the salary for the person being created.
     * @param salary --->salary The salary value to assign.
     */
    public void setSalary(int salary) {
        this.mSalary = salary;
    }

    /**
     * Retrieves the salary assigned to the person.
     * @return --->  The salary value.
     */

    public int getSalary() {
        return mSalary;
    }

    /**
     * Retrieves the list of SessionType objects.
     * @return --->  A list of session types.
     */
    public ArrayList<SessionType> getSessionTypes() {
        return mSessionTypes;
    }

    /**
     *Sets the list of SessionType objects for the person.
     * @param sessionTypes --->  sessionTypes The list of session types the person is associated with.
     */
    public void setSessionTypes(ArrayList<SessionType> sessionTypes) {
        this.mSessionTypes = sessionTypes;
    }

    /**
     * Retrieves the list of person.
     * @return ---> A list of Person.
     */
    public List<Person> getPersonList() {
        return mPersonsList;
    }

    /**
     * Retrieves the list of Person objects managed or associated with this person.
     * @param personsList --->A list of persons.
     */
    public void setPersonList(List<Person> personsList) {
        mPersonsList = personsList;
    }

    /**
     *  Retrieves the list of  Session objects associated with the person.
     * @return --->  A list of sessions.
     */

    public List<Session> getSessions() {
        return mSessions;
    }
    /**
     *Sets the list of  Session objects for the person being created.
     * @return --->sessions The list of sessions the person is involved with.
     */

    public void setSessions(List<Session> sessions) {
        mSessions = sessions;
    }

    /**
     *Retrieves the list of actions performed by the person, represented as strings.
     * @param historyActionList --->  A list of action history strings.
     */
    public void setHistoryActionList(List<String> historyActionList) {
        mHistoryActionList = historyActionList;
    }

    /**
     * Sets the list of action history strings for the person.
     * @return ---> historyActionList The list of actions performed by the person.
     */
    public List<String> getHistoryActionList() {
        return mHistoryActionList;
    }
}
