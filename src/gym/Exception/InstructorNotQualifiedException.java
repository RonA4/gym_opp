package gym.Exception;
/**
 * Exception thrown when an instructor is assigned to a session type they are not qualified to conduct.
 */
public class InstructorNotQualifiedException extends Exception {

    private final static String ERROR_MSG = "Error: Instructor is not qualified to conduct this session type.";

    /**
     * Constructs a new DuplicateClientException with a specific error message.
     */
    public InstructorNotQualifiedException() {
        super(ERROR_MSG);
    }
}
