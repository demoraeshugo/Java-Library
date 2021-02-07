
/**
 * Date class provides the structure for the Date object, which contains a year, month and day.
 * It also contains informative methods to determine how many days are in each month, and whether
 * a certain Date object is valid. The Book class has a attribute datePublished which is of type Date.
 *
 * @author Hugo De Moraes, Jonathan Dong
 *
 */
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Date {
    private final int year;
    private final int month;
    private final int day;
    private final int QUADRENNIAL = 4;
    private final int CENTENNIAL = 100;
    private final int QUATERCENTENNIAL = 400;
    private final int FEB_DAYS_LEAPYEAR = 29;
    private final int FEB_DAYS_NONLEAPYEAR = 28;
    private final int MAX_MONTH_NUM = 12;
    private final int MIN_MONTH_NUM = 1;
    private final int DAYS_IN_30DAY_MONTH = 30;
    private final int DAYS_IN_31DAY_MONTH = 31;


    /**
     * Constructor for Date Object based on given string in the format of mm/dd/yyyy
     * @param date String representation of date to be converted to Date Object
     */
    public Date(String date) {                      // taking mm/dd/yyyy and create a Date object
        String[] tokens = date.split("/");   // splits string date into respective tokens

        month = Integer.parseInt(tokens[0]);
        day = Integer.parseInt(tokens[1]);
        year = Integer.parseInt(tokens[2]);
    }

    /**
     * default Constructor for Date Object based on today's date
     */
    public Date() {
        String[] tokens = java.time.LocalDate.now().toString().split("-"); // splits todays date into tokens

        year = Integer.parseInt(tokens[0]);
        month = Integer.parseInt(tokens[1]);
        day = Integer.parseInt(tokens[2]);
    }

    /**
     * getter method to return year attribute of Date object
     * @return year attribute of Date object
     */
    public int getYear() {
        return year;
    }

    /**
     * getter method to return month attribute of Date object
     * @return month attribute of Date object
     */
    public int getMonth() {
        return month;
    }

    /**
     * getter method to return day attribute of Date object
     * @return day attribute of Date object
     */
    public int getDay() {
        return day;
    }

    /**
     * returns the number of days in February based on year attribute of Date object
     * @return 29 if year is leap year, 28 if year is not
     */
    private int getFebruaryDays() {
        if (((year % QUADRENNIAL == 0) & !(year % CENTENNIAL == 0)) || (year % QUATERCENTENNIAL == 0)) {
            return FEB_DAYS_LEAPYEAR;
        } else {
            return FEB_DAYS_NONLEAPYEAR;
        }
    }

    /**
     * calculates number of days in month based off specific month
     * @return number of days in month
     */
    private int getDaysInMonth() {

        int indexOfMonth = month-1; // this.month is 1 based 1:12, Calendar class is 0 based 0:11

        if(month > MAX_MONTH_NUM || month < MIN_MONTH_NUM) { //checks if month is between 1 and 12
            return 0;
        }

        return switch(indexOfMonth){
            case
                            Calendar.JANUARY,
                            Calendar.MARCH,
                            Calendar.MAY,
                            Calendar.JULY,
                            Calendar.AUGUST,
                            Calendar.OCTOBER,
                            Calendar.DECEMBER -> DAYS_IN_31DAY_MONTH;
            case
                            Calendar.APRIL,
                            Calendar.JUNE,
                            Calendar.SEPTEMBER,
                            Calendar.NOVEMBER -> DAYS_IN_30DAY_MONTH;
            case
                            Calendar.FEBRUARY -> getFebruaryDays();
            default -> -1;
        };
    }

    /**
     * checks if year of Date object is valid
     * @return true if year is between 1900 and today's year
     */
    private boolean isValidYear() {
        Date today = new Date();
        int yearLeftBound = 1900;
        int yearRightBound = today.year;

        return (year >= yearLeftBound && year < yearRightBound)
                || (year == yearRightBound && month < today.month)
                || (year == yearRightBound && month == today.month && day <= today.day);
    }
    /**
     * checks if month of Date object is valid
     * @return true if month is between 1 and 12
     */
    private boolean isValidMonth() {

        return month >= MIN_MONTH_NUM && month <=MAX_MONTH_NUM;
    }

    /**
     * checks if day of Date object is valid
     * @return true if day is between 1 and num days in month
     */
    private boolean isValidDay() {
        return day <= getDaysInMonth() && day > 0;
    }

    /**
     * checks if Date Object is valid
     * @return true if year, month and day is valid
     */
    public boolean isValid() {
        return isValidYear() && isValidMonth() && isValidDay();
    }

    /**
     * overrided toString method gives string representation of Date Object
     * @return String containing month, day and year of Date
     */
    @Override
    public String toString() {
        return month + "/" + day + "/" + year;
    }

    public static void main(String[] args) {
        String[] validTests = { "02/29/2004", "02/29/2008", "12/31/1998", "04/30/2009", "02/25/2020" };
        String[] invalidTests = { "02/29/2003", "04/31/2010", "12/20/2021", "01/01/1850", "01/00/2020" };
        String validTestCase = "test %s :: %s :: returns %s :: status passing%n";
        String invalidTestCase = "test %s :: %s :: returns %s :: status failing%n";
        boolean result;
        boolean expected;

        System.out.printf("%n-----------------------------------%n");
        System.out.println("It should return true for each date");
        System.out.println("-----------------------------------");
        expected = true;
        for(int i = 0; i < validTests.length; i++) {

            Date test = new Date(validTests[i]);
            result = test.isValid();

            if(result == expected) {
                System.out.printf(validTestCase, i+1, validTests[i], result);
            } else {
                System.out.printf(invalidTestCase, i+1, validTests[i], result);
            }
        }

        System.out.printf("%n------------------------------------%n");
        System.out.println("It should return false for each date");
        System.out.println("------------------------------------");
        expected = false;
        for(int i = 0; i < invalidTests.length; i++) {

            Date test = new Date(invalidTests[i]);
            result = test.isValid();

            if(result == expected) {
                System.out.printf(validTestCase, i+1, invalidTests[i], result);
            } else {
                System.out.printf(invalidTestCase, i+1, invalidTests[i], result);
            }
        }
    }
}
