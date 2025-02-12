package gym.management;

import gym.customers.Person;
import gym.management.Sessions.typeSessions.SessionType;
import gym.management.factoryOfPerson.PersonType;

import java.util.ArrayList;
/**
 * The Instructor class represents an instructor at the gym. It extends the  GymWorker class and includes
 * additional functionality specific to instructors, such as the types of sessions they are certified to teach and their ability to
 * teach those sessions.
 */
public class Instructor extends GymWorker {
    private final ArrayList<SessionType> mSessionTypes;
    /**
     * Constructs a newInstructor  instance with the specified person details, salary, and the types of sessions they are certified to teach.
     * @param person ---> The  Person object containing the details of the instructor.
     * @param salary ---> The salary of the instructor (typically per hour).
     * @param sessionTypes ---> A list of SessionType objects representing the types of sessions the instructor is certified to teach.
     */
    public Instructor(Person person, int salary, ArrayList<SessionType> sessionTypes) {
        super(person, salary);
        this.mSessionTypes = sessionTypes;
    }
/**
 * Checks if the instructor is certified to teach the specified session type.
 * @param sessionType ---> The session type to check.
 * @return true ---> if the instructor is certified to teach the specified session type, false otherwise.
 */
    public boolean canTeachSession(SessionType sessionType) {
        return mSessionTypes.contains(sessionType);
    }

    /**
     * Provides a string representation of the instructor, including their personal details, role, salary, and the sessions they are certified to teach.
     * @return  ---> A string that includes the instructor's details, their certified classes, and formatted salary.
     */
    @Override
    public String toString() {
        return String.format(
                "%s | Certified Classes: %s"
                , super.toString(),
                getCertifiedClassesFormatted()
        );
    }
    /**
     * Returns a formatted string of the types of sessions the instructor is certified to teach.
     * @return ---> A string containing the names of the certified sessions, separated by commas.
     */
    private String getCertifiedClassesFormatted() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < mSessionTypes.size(); i++) {
            sb.append(mSessionTypes.get(i).name());
            if (i < mSessionTypes.size() - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }

    /**
     * Gets the type of worker for the instructor, which is  PersonType.Instructor.
     * @return ---> The type of worker.
     */
    @Override
    public PersonType getTypeOfWorker() {
        return PersonType.Instructor;
    }
    /**
     * Returns the instructor's salary formatted as a string.
     * @return --->  A string representing the instructor's salary (e.g., "Salary per Hour: 50").
     */
    @Override
    public String getSalaryStringFormatted() {
        return "Salary per Hour: " + this.mSalary;
    }
}
