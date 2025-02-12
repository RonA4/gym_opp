package gym.Exception;
/**
 * Exception thrown when an operation involving a client fails due to the client not being registered.
 * This exception can occur in two scenarios:
  (1) When attempting to enroll a client in a lesson without gym registration.
  (2) When attempting to unregister a client who has not yet.
 */

public class ClientNotRegisteredException extends Exception {
    private static final String ERROR_MSG_REGISTER_LESSON = "Error: The client is not registered with the gym and cannot enroll in lessons";
    private static final String ERROR_MSG_REGISTER_GYM = "Error: Registration is required before attempting to unregister";

    /**
     * Constructs a new ClientNotRegisteredException with a specific error message.
     * @param isRegisterGym ---> if the client register to gym.
     */
    public ClientNotRegisteredException(boolean isRegisterGym) {
        super(isRegisterGym ? ERROR_MSG_REGISTER_GYM : ERROR_MSG_REGISTER_LESSON);
    }
}
