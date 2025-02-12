package gym.management;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
/**
 * Utility class for date and time-related operations in the gym management system.
 * Provides methods for parsing, comparing, and formatting dates and times.
 */
public class DateUtils {
    /**
     *  DATE_FORMAT_PERSON ---> The date format used for personal information.
     *  DATE_FORMAT_WITH_TIME --->  The date and time format used for sessions or events.
     */
    private static final String DATE_FORMAT_PERSON = "dd-MM-yyyy";
    private static final String DATE_FORMAT_WITH_TIME = "dd-MM-yyyy HH:mm";

    /**
     * Calculates the age in years from a given date string.
     * @param dateString ---> dateString the date string in the format "dd-MM-yyyy".
     * @return ---> the age in years.
     * @throws DateTimeParseException ---> DateTimeParseException if the date string is not in the expected format.
     */
    public static int getAgeFromDateString(String dateString) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT_PERSON);
        LocalDate birthDate = LocalDate.parse(dateString, formatter);
        return Period.between(birthDate, LocalDate.now()).getYears();
    }


    /**
     * Checks if a given session date and time matches a specific date.
     * @param sessionDateTime ---> the session date and time in the format "dd-MM-yyyy HH:mm".
     * @param dateString  --->     the date string in the format "dd-MM-yyyy".
     * @return --->  true if the dates are equal, false otherwise.
     * @throws DateTimeParseException ---> if either date string is not in the expected format.
     */
    public static boolean areDatesEqual(String sessionDateTime, String dateString) throws DateTimeParseException {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT_WITH_TIME);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT_PERSON);

        LocalDateTime dateTime = LocalDateTime.parse(sessionDateTime, dateTimeFormatter);
        LocalDate date = LocalDate.parse(dateString, dateFormatter);

        return dateTime.toLocalDate().equals(date);
    }
    /**
     * Converts a date and time string from the format "dd-MM-yyyy HH:mm" to  format.
     * @param dateTime ---> the date and time string in the format "dd-MM-yyyy HH:mm".
     * @return ---> the formatted date and time string in  format "yyyy-MM-dd'T'HH:mm".
     */

    public static String convertToIsoFormat(String dateTime) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime localDateTime = LocalDateTime.parse(dateTime, inputFormatter);
        return localDateTime.format(outputFormatter);
    }

    /**
     * Converts a date string from the format "dd-MM-yyyy" to the format "yyyy-MM-dd".
     * @param dateTime the input date string in the format "dd-MM-yyyy"
     * @return the formatted date string in the format "yyyy-MM-dd"
     */
    public static String convertToFormat(String dateTime) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(dateTime, inputFormatter);
        return localDate.format(outputFormatter);
    }
    /**
     * Checks if a given date and time is in the past.
     * @param dateTime ---> the date and time string in the format "dd-MM-yyyy HH:mm".
     * @return --->  true if the given date and time is before the current date and time,  false otherwise.
     */
    public static boolean isInThePast(String dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT_WITH_TIME);
        try {
            LocalDateTime inputDateTime = LocalDateTime.parse(dateTime, formatter);
            LocalDateTime now = LocalDateTime.now();
            return inputDateTime.isBefore(now);
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
