public class Date {
    private final int year;
    private final int month;
    private final int day;

    // taking mm/dd/yyyy and create a Date object
    public Date(String date) {
        String[] tokens = date.split("/");

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

    enum Month {
        JANUARY,
        FEBRUARY,
        MARCH,
        APRIL,
        MAY,
        JUNE,
        JULY,
        AUGUST,
        SEPTEMBER,
        OCTOBER,
        NOVEMBER,
        DECEMBER
    }

    private int getFebruaryDays() {
        if (((year % 4 == 0) & !(year % 100 == 0)) || (year % 400 == 0)) {
            return 29;
        } else {
            return 28;
        }
    }

    private int getDaysInMonth() {
        Month[] monthArray = Month.values();

        // subtract 1 for array based matching
        return switch(monthArray[month-1]){
            case JANUARY, MARCH, MAY, JULY, AUGUST, OCTOBER, DECEMBER -> 31;
            case APRIL, JUNE, SEPTEMBER, NOVEMBER -> 30;
            case FEBRUARY -> getFebruaryDays();
        };
    }

    public boolean isValid() {
        Date today = new Date();
        int yearLeftBound = 1900;
        int yearRightBound = today.year;
        int monthLeftBound = 1;
        int monthRightBound = 12;

        // check for invalid year
        if(year < yearLeftBound || year > yearRightBound
                || (year == yearRightBound && month > today.month)
                || (year == yearRightBound && month == today.month && day > today.day)) {
            return false;
        // check for invalid month
        } else if(month < monthLeftBound || month > monthRightBound ) {
            return false;
        // check for invalid day
        } else return day <= getDaysInMonth();
    }
}
