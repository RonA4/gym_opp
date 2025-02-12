package gym.management.observerable;

/**
 * Represents an entity that manages a group of members and can send notifications to them.
 * This interface is part of the Observer design pattern, where the `Sender` acts as the observable,
 *managing and notifying members (observers) of updates.
 * @param <T> --->  the type of members managed by this sender, which must implement the Member interface.
 */
public interface Sender<T extends Member> {
    /**
     * Registers a new member to receive notifications.
     * @param member --->the member to be registered.
     */
    void register(T member);
    /**
     * Unregisters a member, removing them from the notification list.
     * @param member ---> the member to be unregistered.
     */
    void unregister(T member);
    /**
     * Sends a notification message to all registered members.
     * @param msg ---> the message to be sent to all members.
     */
    void notifyMembers(String msg);

}