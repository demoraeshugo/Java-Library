/***
 * @author Hugo De Moraes
 * @author Jonathan Dong
 */

import java.util.Calendar;

public class Date {
    private final int year;
    private final int month;
    private final int day;
    private static final int Quadrennial = 4;
    private static final int Centennial = 100;
    private static final int Quatercentennial = 400;

    // taking mm/dd/yyyy and create a Date object
    public Date(String date) {
        String[] tokens = date.split("/"); // getTokens(date);

        month = Integer.parseInt(tokens[0]);
        day = Integer.parseInt(tokens[1]);
        year = Integer.parseInt(tokens[2]);
    }

    // return today’s date
    public Date() {
        String[] tokens = java.time.LocalDate.now().toString().split("-");

        year = Integer.parseInt(tokens[0]);
        month = Integer.parseInt(tokens[1]);
        day = Integer.parseInt(tokens[2]);
    }

    private int getYear() {
        return year;
    }

    private int getMonth() {
        return month;
    }

    private int getDay() {
        return day;
    }

    private int getFebruaryDays() {
        if (((year % Quadrennial == 0) & !(year % Centennial == 0)) || (year % Quatercentennial == 0)) {
            return 29;
        } else {
            return 28;
        }
    }

    private int getDaysInMonth(int month) {
        // this.month is 1 based 1:12, Calendar class is 0 based 0:11
        int indexOfMonth = month-1;
        int maxMonth = 12;
        int minMonth = 1;

        if(month > maxMonth || month < minMonth) {
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
                            Calendar.DECEMBER -> 31;
            case
                    Calendar.APRIL,
                            Calendar.JUNE,
                            Calendar.SEPTEMBER,
                            Calendar.NOVEMBER -> 30;
            case
                    Calendar.FEBRUARY -> getFebruaryDays(year);
            default -> -1;
        };
    }

    private boolean isValidYear(int year) {
        Date today = new Date();
        int yearLeftBound = 1900;
        int yearRightBound = today.year;

        return (year >= yearLeftBound && year < yearRightBound)
                || (year == yearRightBound && month < today.month)
                || (year == yearRightBound && month == today.month && day <= today.day);
    }

    private boolean isValidMonth(int month) {
        int monthLeftBound = 1;
        int monthRightBound = 12;

        return month >= monthLeftBound && month <= monthRightBound;
    }

    private boolean isValidDay(int day) {
        return day <= getDaysInMonth(month) && day > 0;
    }

    public boolean isValid() {
        return isValidYear(year) && isValidMonth(month) && isValidDay(day);
    }

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
