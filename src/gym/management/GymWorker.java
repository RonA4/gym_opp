package gym.management;
import gym.Gym;
import gym.customers.Person;
import gym.management.factoryOfPerson.PersonType;
/**
 * The GymWorker class represents a worker at the gym.
 * It extends the Person class and includes details specific to gym workers, such as salary and role.
 */
public abstract class GymWorker extends Person {

    protected int mSalary;
    /**
     * Constructs a new  GymWorker instance with the specified person details and salary.
     * @param person ---> The  Person object containing the details of the worker.
     * @param salary ---> The salary of the gym worker.
     */
    public GymWorker(Person person, int salary) {
        super(person);
        this.mSalary = salary;
    }

    /**
     * Provides a string representation of the gym worker, including personal details, role, and formatted salary.
     * @return ---> A string that includes the worker's personal information, role, and formatted salary.
     */
    @Override
    public String toString() {
        return String.format(
                "%s | Role: %s | %s",
                super.toString(),
                getTypeOfWorker().name(),
                getSalaryStringFormatted());
    }

    /**
     * Abstract method to get the type of worker.
     * @return  ---> The type of worker.
     */
    public abstract PersonType getTypeOfWorker();

    /**
     * Abstract method to get the salary of the worker in a formatted string.
     * @return ---> The formatted salary string.
     */
    public abstract String getSalaryStringFormatted();

    /**

     * Pays the salary to the gym worker. The gym's balance is decreased by the worker's salary,
     * and the worker's balance is updated.
     */
    protected void paySalary() {
        Gym.getInstance().subBalance(mSalary);
        this.mBalance.addToBalance(mSalary);
    }
}

