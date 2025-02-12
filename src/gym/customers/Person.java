package gym.customers;
import gym.management.DateUtils;
import gym.management.observerable.Member;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a person in the fitness system.
 * This class is used as a base class for other positions such as instructor, customer, secretary
 * It implements the Member interface, which allows a person to receive notifications.
 * mId --->  A static ID counter to ensure unique identification for each person.
 * mName ---> The name of the person.
 * mBalance --->  The balance associated with the person.
 * mGender --->  The gender of the person.
 * mDate ---> The date of birth of the person in string format.
 * id --->  The unique ID assigned to the person.
 * mNotifications ---> A list of notifications received by the person.
 */
public class Person implements Member {
    private static int mId = 1111;
    private String mName;
    protected Balance mBalance;
    private Gender mGender;
    private String mDate;
    private int id;
    private final List<String> mNotifications = new ArrayList<>();

    /**
     * Constructs a new Person with the specified details.
     * @param name    --->   the name of the person.
     * @param balance ---> the initial balance of the person.
     * @param gender  --->  the gender of the person.
     * @param date    --->   the birthdate of the person.
     */
    public Person(String name, int balance, Gender gender, String date) {
        this.mName = name;
        this.mBalance = new Balance(balance);
        this.mGender = gender;
        this.mDate = date;
        this.id = mId++;

    }

    /**
     * Copy constructor.
     * Creates a new Person object by copying details from another person.
     * @param person --->  the person whose details will be copied.
     */
    public Person(Person person) {
        this.mName = person.mName;
        this.mBalance = person.mBalance;
        this.mGender = person.mGender;
        this.mDate = person.mDate;
        this.id = person.id;
    }

    /**
     * Returns the name of the person.
     * @return ---> the name of the person
     */
    public String getName() {
        return mName;
    }

    /**
     * Returns the current balance of the person.
     * @return ---> the balance amount
     */

    public int getBalance() {
        return mBalance.getBalance();
    }

    /**
     * Returns the gender of the person.
     * @return ---> the gender of the person
     */
    public Gender getGender() {
        return mGender;
    }

    /**
     * Returns the birthdate of the person.
     * @return ---> the birthdate as a string.
     */
    public String getDate() {
        return mDate;
    }

    /**
     * Checks if this person is equal to another object.
     * Two persons are considered equal if their IDs are the same.
     * @param o ---> the object to compare with
     * @return ---> true if the IDs match; otherwise false.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return this.id == person.id;
    }

    /**
     * Receives a notification message and adds it to the list of notifications.
     * @param msg ---> the message to be received
     */
    @Override
    public void update(String msg) {
        mNotifications.add(msg);
    }

    /**
     * Returns the list of notifications received by the person.
     * @return ---> a list of notification messages
     */
    public List<String> getNotifications() {
        return mNotifications;
    }


    /**
     * Returns a formatted string containing the person's details, including:
     * ID, name, gender, birthday, age, and balance.
     * @return ---> a string representation of the person's details
     */
    @Override
    public String toString() {
        return String.format("ID: %d | Name: %s | Gender: %s | Birthday: %s | Age: %d | Balance: %d",
                this.id,
                mName,
                mGender,
                mDate,
                DateUtils.getAgeFromDateString(mDate),
                mBalance.getBalance()
        );
    }
}
