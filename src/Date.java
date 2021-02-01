public class Date {
    private final int year;
    private final int month;
    private final int day;

    //constant declarations
    public final int JANUARYMONTHNUM = 1;
    public final int FEBRUARYMONTHNUM = 2;
    public final int MARCHMONTHNUM = 3;
    public final int APRILMONTHNUM = 4;
    public final int MAYMONTHNUM = 5;
    public final int JUNEMONTHNUM = 6;
    public final int JULYMONTHNUM = 7;
    public final int AUGUSTMONTHNUM = 8;
    public final int SEPTEMBERMONTHNUM = 9;
    public final int OCTOBERMONTHNUM = 10;
    public final int NOVEMBERMONTHNUM = 11;
    public final int DECEMBERMONTHNUM = 12;





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

    private boolean isLeapYear(int checkLeapYear) {
        if (checkLeapYear % 4 == 0) {
            if (checkLeapYear % 100 == 0) {
                if (checkLeapYear % 400 == 0)
                    return true;
                else
                    return false;
            } else
                return true;
        } else
            return false;
    }

    private int getDaysInMonth(int inputMonth, int inputYear) {
        //   Month[] monthArray = Month.values();

        // subtract 1 for array based matching
//        return switch(monthArray[month-1]){
//            case JANUARY, MARCH, MAY, JULY, AUGUST, OCTOBER, DECEMBER -> 31;
//            case APRIL, JUNE, SEPTEMBER, NOVEMBER -> 30;
//            case FEBRUARY -> getFebruaryDays();
        //   };
        //temporarily returning 0 until i can fix that ^
        int numDays = 0;

        switch (inputMonth) {
            case JANUARYMONTHNUM:
            case MARCHMONTHNUM:
            case MAYMONTHNUM:
            case JULYMONTHNUM:
            case OCTOBERMONTHNUM:
            case DECEMBERMONTHNUM:
                numDays = 31;
                break;
            case APRILMONTHNUM:
            case JUNEMONTHNUM:
            case SEPTEMBERMONTHNUM:
            case NOVEMBERMONTHNUM:
                numDays = 30;
                break;
            case FEBRUARYMONTHNUM:
                if (isLeapYear(inputYear)){
                numDays= 29;
                }
                else numDays= 28;

        }
        return numDays;
    }
    //Am i gonna have to change 30, 29 and 28 to constants too good god

    public boolean isValid() {
        Date today = new Date();
        int yearLeftBound = 1900;
        int yearRightBound = today.year;
        int monthLeftBound = 1;
        int monthRightBound = 12;

        // check for invalid year
        if (year < yearLeftBound || year > yearRightBound
                || (year == yearRightBound && month > today.month)
                || (year == yearRightBound && month == today.month && day > today.day)) {
            return false;
            // check for invalid month
        } else if (month < monthLeftBound || month > monthRightBound) {
            return false;
            // check for invalid day
        } else return this.day <= getDaysInMonth(this.month, this.year);
    }

    /**
     * Used for implementing test cases for the isValid Method
     */
    public static void main(String args[]) {
        Date a1 = new Date();
        System.out.println(a1.year);
    }
}
