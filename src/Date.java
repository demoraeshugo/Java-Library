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

    public static boolean isGreaterDate(Date date1, Date date2) {
        return (date1.getYear() < date2.getYear())
                || ( date1.getYear() == date2.getYear() && date1.getMonth() < date2.getMonth() )
                || ( date1.getYear() == date2.getYear() && date1.getMonth() == date2.getMonth() && date1.getDay() <= date2.getDay());
    }

    private int getFebruaryDays() {
        if (((year % Quadrennial == 0) & !(year % Centennial == 0)) || (year % Quatercentennial == 0)) {
            return 29;
        } else {
            return 28;
        }
    }

    private int getDaysInMonth() {
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
                    Calendar.FEBRUARY -> getFebruaryDays();
            default -> 0;
        };
    }

    public boolean isValid() {
        Date today = new Date();

        int yearLeftBound = 1900;
        int yearRightBound = today.year;

        int monthLeftBound = 1;
        int monthRightBound = 12;

        boolean validYear = (year >= yearLeftBound || year < yearRightBound
                || (year == yearRightBound && month < today.month)
                || (year == yearRightBound && month == today.month && day <= today.day));
        boolean validMonth = month >= monthLeftBound && month <= monthRightBound;
        boolean validDay = day <= getDaysInMonth();

        return validYear && validMonth && validDay;
    }

    @Override
    public String toString() {
        return getMonth() + "/" + getDay() + "/" + getYear();
    }
}
