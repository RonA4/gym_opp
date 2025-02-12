package gym.management.Sessions.typeSessions;
import gym.customers.ForumType;
import gym.management.Instructor;
import gym.management.Sessions.factoryOfsession.Session;
/**
 * Represents a "ThaiBoxing" session in the gym management system.
 * This session has a fixed price and a limited number of participants.
 * It extends the  Session class and provides specific implementations for price and participant limit.
 */
public class ThaiBoxing extends Session {
    /**
     * price ---> The fixed price for a "ThaiBoxing" session.
     * limit --->  The maximum number of participants .
     */
    private static int price = 100;
    private static int limit = 20;
    /**
     * Constructs a new "ThaiBoxing" session with the specified details.
     * @param sessionType ---> the type of the session .
     * @param date       ---> the date of the session in the appropriate format.
     * @param forumType   --->  the forum type associated with the session.
     * @param instructor  --->  the instructor leading the session.
     */
    public ThaiBoxing(SessionType sessionType, String date, ForumType forumType, Instructor instructor) {
        super(sessionType, date, forumType, instructor);
    }
    /**
     * Returns the price of the "ThaiBoxing" session.
     * @return ---> the price of lesson.
     */
    @Override
    public int getPrice() {
        return price;
    }
    /**
     * Returns the maximum number of participants allowed in the session.
     * @return ---> the fixed participant limit.
     */
    @Override
    public int getParticipantLimit() {
        return limit;
    }
}
