package gym.Exception;

/**
 * An exception is thrown when a duplicate client registration attempt is detected.
 * This exception is raised in two scenarios:
   (1) When trying to register a customer who is already registered in the gym.
   (2) When trying to register a client for a class he is already registered for.
 */

public class DuplicateClientException extends Exception {
    private static final String ERROR_MSG_REGISTER_GYM = "Error: The client is already registered";
    private static final String ERROR_MSG_REGISTER_LESSON = "Error: The client is already registered for this lesson";

    /**
     * Constructs a new DuplicateClientException with a specific error message.
     * @param isRegisterGym ---> if the client register to the lesson.
     */
    public DuplicateClientException(boolean isRegisterGym) {
        super(isRegisterGym ? ERROR_MSG_REGISTER_GYM : ERROR_MSG_REGISTER_LESSON);
    }
}
