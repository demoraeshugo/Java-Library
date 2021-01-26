public class Date {
    private int year;
    private int month;
    private int day;

    // taking mm/dd/yyyy and create a Date object
    public Date(String date) {
        String[] tokens = date.split("/");

        this.day = Integer.parseInt(tokens[0]);
        this.month = Integer.parseInt(tokens[1]);
        this.year = Integer.parseInt(tokens[2]);
    } 

    // return today’s date
    public Date() {
        /* Let's chat about this...
        String[] tokens = java.time.LocalDate.now().toString().split("-");

        this.day = Integer.parseInt(tokens[0]);
        this.month = Integer.parseInt(tokens[1]);
        this.year = Integer.parseInt(tokens[2]);

        return this;
        */
    } 

    public isValid() {
     }
}
