package gym.management.Sessions.factoryOfsession;
import gym.customers.Client;
import gym.customers.ForumType;
import gym.management.DateUtils;
import gym.management.Instructor;
import gym.management.Sessions.typeSessions.SessionType;
import gym.management.observerable.Member;
import gym.management.observerable.Sender;
import java.util.ArrayList;
import java.util.List;

/**
 * The  Session class represents a gym session. It contains details such as session type, date, forum type, instructor,
   and the list of clients registered for the session.
 *It implements the Sender interface to notify clients about session updates.
 * This class is abstract, meaning it is intended to be subclassed for specific types of gym sessions.
 */
public abstract class Session implements Sender<Client> {
    private SessionType sessionType;
    private String dateTime;
    private final ForumType forumType;
    private final Instructor instructor;
    private List<Client> clientList = new ArrayList<>();

    /**
     * Constructs a new  Session with the specified details.
     * @param sessionType ---> The type of the session .
     * @param dateTime ---> The date and time of the session.
     * @param forumType ---> The type of forum  where the session will take place.
     * @param instructor ---> The instructor leading the session.
     */
    public Session(SessionType sessionType, String dateTime, ForumType forumType, Instructor instructor) {
        this.sessionType = sessionType;
        this.dateTime = dateTime;
        this.forumType = forumType;
        this.instructor = instructor;
    }

    /**
     * Gets the session type.
     * @return ---> The session type.
     */
    public SessionType getSessionType() {
        return this.sessionType;
    }

    /**
     * Gets the date and time of the session.
     * @return ---> The session date and time.
     */

    public String getDateTime() {
        return this.dateTime;
    }
    /**
     * Gets the instructor leading the session.
     * @return --->  The instructor.
     */
    public Instructor getInstructor() {
        return instructor;
    }

    /**
     * Gets the forum type of the session.
     * @return ---> The forum type.
     */
    public ForumType getForumType() {
        return forumType;
    }


    /**
     * Compares the session date with a given date.
     * @param date ---> The date to compare with.
     * @return ---> true if the session date is the same as the given date,  false otherwise.
     */
    public boolean isDateEqual(String date) {
        return DateUtils.areDatesEqual(dateTime, date);
    }

    /**
     * Checks if a client is already registered for the session.
     * @param client --->  The client to check.
     * @return ---> true if the client is already registered, false otherwise.
     */
    public boolean isClientAlreadyRegistered(Client client) {
        return clientList.contains(client);
    }
    /**
     * Checks if the maximum number of participants for the session has been reached.
     * @return ---> true if the maximum number of clients has been reached,  false otherwise.
     */
    public boolean isMaximumClientReached() {
        return clientList.size() >= getParticipantLimit();
    }



    /**
     * Checks if the session date is in the past.
     * @return ---> true if  the session is in the past, false otherwise.
     */
    public boolean isInThePast() {
        return DateUtils.isInThePast(dateTime);
    }
    /**
     * Provides a string representation of the session, including the type, date, forum, instructor, and the number of registered participants.
     * @return ---> a string describing the session.
     */
    @Override
    public String toString() {
        return String.format("Session Type: %s | Date: %s | Forum: %s | Instructor: %s | Participants: %d/%d",
                sessionType, dateTime, getForumType().name(), instructor.getName(), clientList.size(), getParticipantLimit());
    }
    /**
     * Registers a client for the session, provided the maximum number of participants has not been reached.
     * @param member ---> The client to register.
     */

    @Override
    public void register(Client member) {
        if (!isMaximumClientReached()) {
            clientList.add(member);
        }
    }
    /**
     * Unregisters a client from the session.
     * @param member ---> The client to unregister.
     */
    @Override
    public void unregister(Client member) {
        clientList.remove(member);

    }

    /**
     * Notifies all registered clients about a message.
     * @param msg ---> The message to send to all clients.
     */
    @Override
    public void notifyMembers(String msg) {
        for (Member member : clientList) {
            member.update(msg);
        }
    }
    /**
     * Abstract method to get the participant limit for the session.
     * @return ---> The maximum number of clients allowed in the session.
     */
    public abstract int getParticipantLimit();

    /**
     * Abstract method to get the price of the session.
     * @return --->  The price of the session.
     */
    public abstract int getPrice();
}
