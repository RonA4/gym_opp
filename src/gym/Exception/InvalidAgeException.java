package gym.Exception;
/**
 * Exception thrown when trying to register a customer who does not meet the minimum age requirement.
 * This exception is usually raised when a customer's age is calculated to be under 18,Based on the date of birth provided during the registration process
 */
public class InvalidAgeException extends Exception {
    private final static String ERROR_MSG = "Error: Client must be at least 18 years old to register";

    /**
     * Constructs a new DuplicateClientException with a specific error message.
     */
    public InvalidAgeException() {
        super(ERROR_MSG);
    }
}
