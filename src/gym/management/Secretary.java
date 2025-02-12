package gym.management;
import gym.Exception.ClientNotRegisteredException;
import gym.Exception.DuplicateClientException;
import gym.Exception.InstructorNotQualifiedException;
import gym.Exception.InvalidAgeException;
import gym.customers.Client;
import gym.customers.ForumType;
import gym.customers.Gender;
import gym.customers.Person;
import gym.management.observerable.GymWorkerNotifier;
import gym.management.Sessions.factoryOfsession.Session;
import gym.management.Sessions.factoryOfsession.SessionFactory;
import gym.management.Sessions.typeSessions.SessionType;
import gym.management.factoryOfPerson.PersonFactory;
import gym.management.factoryOfPerson.PersonFactoryParams;
import gym.management.factoryOfPerson.PersonType;
import java.util.ArrayList;
import java.util.List;
/**
 * The  Secretary class represents a secretary working at the gym. It extends the GymWorkerNotifier
 * class and includes various functionalities, such as registering/unregistering clients, hiring instructors,
 * adding sessions, managing session registrations, and handling salary payments. The secretary also manages
 * the gym's history of actions and communications.
 */
public class Secretary extends GymWorkerNotifier {
    private List<Session> mSessionList;
    private List<String> mHistoryActionList;
    private boolean mIsCurrentlyWorkingInTheGym = true;
    /**
     * Constructs a new  Secretary instance with the specified person details, salary, session list, and action history.
     * @param person ---> The Person object containing the details of the secretary.
     * @param salary  ---> The salary of the secretary (typically per month).
     * @param personList ---> A list of all persons in the gym.
     * @param sessionList  ---> A list of all sessions in the gym.
     * @param historyActionList --->  A list of the history of actions performed by the secretary.
     */
    public Secretary(Person person, int salary, List<Person> personList, List<Session> sessionList, List<String> historyActionList) {
        super(person, salary, personList);
        this.mSessionList = sessionList;
        this.mHistoryActionList = historyActionList;
        addHistoryAction("A new secretary has started working at the gym: " + person.getName());
    }

    /**
     * Registers a new client to the gym.
     * @param person ---> The Person object representing the new client to register.
     * @return ---> The newly registered  Client object.
     * @throws ---> InvalidAgeException If the client's age is less than 18.
     * @throws --->  DuplicateClientException If the client is already registered at the gym.
     */
    public Client registerClient(Person person) throws InvalidAgeException, DuplicateClientException {
        verifySecretlyWorkingInGym();
        int age = DateUtils.getAgeFromDateString(person.getDate());
        if (age < 18) {
            throw new InvalidAgeException();
        }
        if (isClientRegisteredToGym(person)) {
            throw new DuplicateClientException(true);
        }
        Client newClient = (Client) PersonFactory.create(PersonType.Client, new PersonFactoryParams(person));
        register(newClient);
        addHistoryAction("Registered new client: " + newClient.getName());
        return newClient;
    }
    /**
     * Unregisters a client from the gym.
     * @param person ---> The {@code Person} object representing the client to unregister.
     * @throws ClientNotRegisteredException  ---> If the client is not registered at the gym.
     */
    public void unregisterClient(Person person) throws ClientNotRegisteredException {
        verifySecretlyWorkingInGym();
        if (!isClientRegisteredToGym(person)) {
            throw new ClientNotRegisteredException(true);
        }
        addHistoryAction("Unregistered client: " + person.getName());
        unregister(person);
    }
    /**
     * Hires a new instructor and registers them in the gym.
     * @param person ---> The  Person object representing the new instructor.
     * @param salary ---> The salary of the instructor (typically per hour).
     * @param sessionTypes ---> A list of session types the instructor is certified to teach.
     * @return ---> The newly hired  Instructor object.
     */
    public Instructor hireInstructor(Person person, int salary, ArrayList<SessionType> sessionTypes) {
        verifySecretlyWorkingInGym();
        PersonFactoryParams params = new PersonFactoryParams(person);
        params.setSalary(salary);
        params.setSessionTypes(sessionTypes);
        Instructor newInstructor = (Instructor) PersonFactory.create(PersonType.Instructor, params);
        register(newInstructor);
        addHistoryAction(String.format("Hired new instructor: %s with salary per hour: %d", person.getName(), salary));
        return newInstructor;
    }
    /**
     * Adds a new session to the gym's session list, after verifying the instructor's qualifications.
     * @param sessionType ---> The type of the session to add.
     * @param dateTime  ---> The date and time of the session.
     * @param forumType ---> The forum type for the session.
     * @param instructor --->  The instructor teaching the session.
     * @return The newly created  Session object.
     * @throws InstructorNotQualifiedException ---> If the instructor is not qualified to teach the specified session type.
     */
    public Session addSession(SessionType sessionType, String dateTime, ForumType forumType, Instructor instructor) throws InstructorNotQualifiedException {
        verifySecretlyWorkingInGym();
        if (!instructor.canTeachSession(sessionType)) {
            throw new InstructorNotQualifiedException();
        }
        Session session = SessionFactory.createSession(sessionType, dateTime, forumType, instructor);
        mSessionList.add(session);
        addHistoryAction(String.format("Created new session: %s on %s with instructor: %s", sessionType.name(), DateUtils.convertToIsoFormat(session.getDateTime()), instructor.getName()));
        return session;
    }

    /**
     * Registers a client to a session after verifying various conditions, such as age, gender, balance, and session availability.
     * @param client ---> The Client to register.
     * @param session  ---> The Session to register the client for.
     * @throws ClientNotRegisteredException ---> If the client is not registered at the gym.
     * @throws DuplicateClientException ---> If the client is already registered for the session.
     */
    public void registerClientToLesson(Client client, Session session) throws ClientNotRegisteredException, DuplicateClientException {
        verifySecretlyWorkingInGym();
        boolean isFailed = true;
        if (session.isInThePast()) {
            addHistoryAction("Failed registration: Session is not in the future");
            isFailed = false;
        }

        if (session.isMaximumClientReached()) {
            addHistoryAction("Failed registration: No available spots for session");
            isFailed = false;
        }
        if (!isClientRegisteredToGym(client)) {
            throw new ClientNotRegisteredException(false);
        }
        if (session.isClientAlreadyRegistered(client)) {
            throw new DuplicateClientException(false);
        }
        ForumType sessionForumType = session.getForumType();
        switch (sessionForumType) {
            case All:
                break;
            case Female: {
                if (client.getGender() != Gender.Female) {
                    addHistoryAction("Failed registration: Client's gender doesn't match the session's gender requirements");
                    isFailed = false;
                }
                break;
            }
            case Male:
                if (client.getGender() != Gender.Male) {
                    addHistoryAction("Failed registration: Client's gender doesn't match the session's gender requirements");
                    isFailed = false;
                }
                break;
            case Seniors:
                if (DateUtils.getAgeFromDateString(client.getDate()) < 65) {
                    addHistoryAction("Failed registration: Client doesn't meet the age requirements for this session (Seniors)");
                    isFailed = false;
                }
                break;
        }
        if (session.getPrice() > client.getBalance()) {
            addHistoryAction("Failed registration: Client doesn't have enough balance");
            isFailed = false;
        }
        if (!isFailed) {
            return;
        }
        client.payForSession(session.getPrice());
        session.register(client);
        addHistoryAction(String.format("Registered client: %s to session: " +
                        "%s on %s for price: %d"
                , client.getName(), session.getSessionType().name(),
                DateUtils.convertToIsoFormat(session.getDateTime()), session.getPrice()));
    }
    /**
     * Checks if the client is already registered at the gym.
     * @param person ---> Person object representing the client to check if registered.
     * @return ---> true if the client is registered, otherwise false.
     */
    private boolean isClientRegisteredToGym(Person person) {
        for (Person ptr : members) {
            if (ptr instanceof Client && ptr.equals(person)) {
                return true;
            }
        }
        return false;
    }
    /**
     * Sends a message to everyone registered for a specific session.
     * @param session ---> the session to which the message will be sent.
     * @param msg ---> the message to send.
     */
    public void notify(Session session, String msg) {
        verifySecretlyWorkingInGym();
        addHistoryAction(
                String.format("A message was sent to everyone registered for session " +
                                "%s on %s : %s",
                        session.getSessionType().name(), DateUtils.convertToIsoFormat(session.getDateTime()), msg)
        );
        session.notifyMembers(msg);
    }
    /**
     * Sends a message to everyone registered for a session held on a specific date.
     * @param date ---> the date of the session.
     * @param msg ---> the message to send.
     */
    public void notify(String date, String msg) {
        verifySecretlyWorkingInGym();
        for (Session session : mSessionList) {
            if (session.isDateEqual(date)) {
                addHistoryAction(
                        String.format(
                                "A message was sent to everyone registered for a session on %s : %s",
                                DateUtils.convertToFormat(date), msg
                        )

                );
                session.notifyMembers(msg);
            }
        }
    }
    /**
     * Sends a message to all gym clients.
     * @param msg ---> the message to send.
     */
    public void notify(String msg) {
        verifySecretlyWorkingInGym();
        addHistoryAction(String.format("A message was sent to all gym clients: %s", msg));
        notifyMembers(msg);
    }

    /**
     * Pays salaries to all employees, including the secretary and instructors.
     */
    public void paySalaries() {
        verifySecretlyWorkingInGym();
        paySalary();
        for (Session session : mSessionList) {
            session.getInstructor().paySalary();
        }
        addHistoryAction("Salaries have been paid to all employees");
    }
    /**
     * Prints all actions performed by the secretary at the gym.
     */
    public void printActions() {
        verifySecretlyWorkingInGym();
        for (String action : mHistoryActionList) {
            System.out.println(action);
        }
    }
    /**
     * Verifies if the secretary is still working at the gym. If not, throws an exception.
     * @throws NullPointerException ---> if the secretary is no longer working at the gym.
     */
    private void verifySecretlyWorkingInGym() {
        if (!mIsCurrentlyWorkingInTheGym) {
            throw new NullPointerException("not working anymore");
        }
    }
    /**
     * Destroys the object, freeing resources by resetting the lists
     * and settings related to this object.
     */
    public void destroy() {
        mHistoryActionList = null;
        mSessionList = null;
        members = null;
        mIsCurrentlyWorkingInTheGym = false;
    }
    /**
     * Adds an action to the secretary's action history.
     * @param action ---> the action to add to the history.
     */
    private void addHistoryAction(String action) {
        mHistoryActionList.add(action);
    }
    /**
     * Returns the type of worker for the secretary.
     * @return ---> PersonType.Secretary since this is a secretary.
     */
    @Override
    public PersonType getTypeOfWorker() {
        return PersonType.Secretary;
    }
    /**
     * Returns the salary of the secretary in a readable format (monthly salary).
     * @return ---> a string representing the monthly salary of the secretary.
     */
    @Override
    public String getSalaryStringFormatted() {
        return "Salary per Month: " + mSalary;
    }


}