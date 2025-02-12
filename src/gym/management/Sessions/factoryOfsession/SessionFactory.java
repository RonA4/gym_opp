package gym.management.Sessions.factoryOfsession;
import gym.customers.ForumType;
import gym.management.Instructor;
import gym.management.Sessions.typeSessions.*;
/**
 * The SessionFactory class is responsible for creating instances of different types of gym sessions.
 *  * It takes the session type, date, forum type, and instructor as inputs and returns the appropriate session object.
 *  * This follows the Factory Design Pattern to abstract the creation of session objects.
 */
public class SessionFactory {
    /**
     * Creates a session of the specified type.
     * @param sessionType ---> The type of session to create .
     * @param date ---> The date of the session.
     * @param forumType ---> The type of forum where the session will take place.
     * @param instructor ---> The instructor who will be leading the session.
     * @return A session of the specified type with the provided details.
     * @throws IllegalArgumentException ---> If the session type is unknown or unsupported.
     */
    public static Session createSession(SessionType sessionType, String date, ForumType forumType, Instructor instructor) {
        switch (sessionType) {
            case Pilates:
                return new Pilates(sessionType, date, forumType, instructor);
            case MachinePilates:
                return new MachinePilates(sessionType, date, forumType, instructor);
            case ThaiBoxing:
                return new ThaiBoxing(sessionType, date, forumType, instructor);
            case Ninja:
                return new Ninja(sessionType, date, forumType, instructor);
            default:
                throw new IllegalArgumentException("Unknown session type: " + sessionType);
        }
    }
}
