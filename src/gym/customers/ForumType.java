package gym.customers;
/**
 * An enumeration representing the types of forums (session restrictions) in the gym.
 * Determines the eligibility criteria for clients to join sessions based on their attributes.
 * All ---> The session is open to all clients, regardless of gender or age.
 * Female --->  The session is restricted to female clients only.
 * Male ---> The session is restricted to male clients only.
 * Seniors --->  The session is restricted to senior clients (typically aged 65 and above).
 */
public enum ForumType {
    All,
    Female,
    Male,
    Seniors
}
