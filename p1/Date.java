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

    private int getDaysInMonth() {
        int numDays;
        switch (month) {
            case 1: case 3: case 5:
            case 7: case 8: case 10:
            case 12:
                numDays = 31;
                break;
            case 4: case 6:
            case 9: case 11:
                numDays = 30;
                break;
            case 2:
                if (((year % 4 == 0) & !(year % 100 == 0)) || (year % 400 == 0)) {
                    numDays = 29;
                } else {
                    numDays = 28;
                }
                break;
            default:
                numDays = 0;
                break;
        }
        return numDays;
    }

    public boolean isValid() {
        boolean isValid = true;
        Date today = new Date();
        int yearLeftBound = 1900;
        int yearRightBound = today.year;
        int monthLeftBound = 1;
        int monthRightBound = 12;

        // year less than 1900 or a date beyond today’s date is invalid.
        if(year < yearLeftBound || year > yearRightBound
                || (year == yearRightBound && month > today.month)
                || (year == yearRightBound && month == today.month && day > today.day)) {
            isValid = false;
        }

        if(month < monthLeftBound || month > monthRightBound ) {
            isValid = false;
        }

        if(day > getDaysInMonth()) {
            isValid = false;
        }

        return isValid;
     }
}
