package gym.management.observerable;
/**
 * Represents a member in a gym management system that can be notified of updates.
 * This interface is part of the Observer design pattern, where the `Member` acts as an observer that receives notifications from an observable entity.
 */
public interface Member {
    /**
     * Updates the member with a specific message.
     * @param msg ---> the message sent to the member, providing relevant information or updates.
     */
    void update(String msg);
}