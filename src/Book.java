
/**
 * Book class defines the abstract data type Book, which is designed with a title (represented by name)
 * a date published, a serial number, and variable to track if checked out. The first Book initialized will
 * have a serial number of 10001 and books created subsequently will be incremented by 1. Multiple books will
 * be held by the Library class.
 *
 * @author Hugo De Moraes, Jonathan Dong
 *
 */
public class Book {
    private final String number; // a 5-digit serial number unique to the book
    private String name;
    private boolean checkedOut;
    private Date datePublished;
    static int currentSerialNumber = 10001;

    /**
     * Constructor for Book Object, creates book with a given name and datePublished
     * @param name name of the Book
     * @param datePublished publish date of Book given as Date object
     */
    Book( String name, Date datePublished) {
        this.number = getCurrentSerialNumber();
        this.name = name;
        this.datePublished = datePublished;
    }

    /**
     * Constructor for Book Object, creates book with a given bookNumber
     * @param number bookNumber of Book to be created
     */
    Book(String number) {
        this.number = number;
    }

    /**
     * getter method for number attribute of Book
     * @return String number of Book
     */
    public String getBookNumber(){
        return this.number;
    }
    /**
     * getter method for date attribute of Book
     * @return Date object of Book
     */
    public Date getDatePublished(){
        return this.datePublished;
    }

    /**
     * Compares two Books based on their number attribute
     * @param book Book to compare
     * @return 1 if number of current Book > number of Book to compare
     *         0 if number of current Book = number of Book to compare
     *        -1 if number of current Book < number of Book to compare
     */
    public int compareByNumber(Book book) {
        if(Integer.parseInt(this.getBookNumber()) > Integer.parseInt(book.getBookNumber())){
            return 1;
        }
        else if (Integer.parseInt(this.getBookNumber()) == Integer.parseInt(book.getBookNumber())){
            return 0;
        }
        else {
            return -1;
        }


    }

    /**
     * Compares two Books based on their datePublished attribute
     * @param book Book to compare
     * @return 1 if number of current Book > number of Book to compare
     *         0 if number of current Book = number of Book to compare
     *        -1 if number of current Book < number of Book to compare
     */
    public int compareByDate(Book book) {
        if(this.getDatePublished().getYear() < book.getDatePublished().getYear()){              //first compare years
            return -1;
        }
        else if (this.getDatePublished().getYear() > book.getDatePublished().getYear()){
            return 1;

        }
        else {
            if (this.getDatePublished().getMonth() < book.getDatePublished().getMonth()){       //second compare months
                return -1;
            }
            else if ( this.getDatePublished().getMonth() > book.getDatePublished().getMonth()){
                return 1;
            }
            else{
                if ( this.getDatePublished().getDay() < book.getDatePublished().getDay()){      //lastly compare days
                    return -1;
                }
                else if(this.getDatePublished().getDay() > book.getDatePublished().getDay()){
                    return 1;
                }
                else {
                    return 0;
                }
            }
        }
    }


    /**
     * Overriden equals method to determine if two Books are equal
     * @param obj Book Object to be evaluated for equality
     * @return true if number of each Book is the same
     */
    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }

        if(obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Book targetBook = (Book) obj;
        return ( targetBook.number.equals(this.number));
    }

    /**
     * Overriden toString method converts a Book to its String representation
     * @return a String containing the book number, book name, and datePublished
     */
    @Override
    public String toString() {
        String isAvailable;

        if (this.checkedOut) {    //first check if Book is checked out or not
            isAvailable = "is not available";
        } else {
            isAvailable = "is available";
        }

        // ex: Book#10007::Design Patterns::5/30/1996::is available.
        return "Book#" + number + "::" + name + "::" + datePublished.toString() + "::" + isAvailable;
    }

    /**
     *getter method for incremented currentSerialNumber returned as an String
     * @return String value of currentSerialNumber+1
     */
    public static String getCurrentSerialNumber() {
        return String.valueOf(currentSerialNumber++);
    }

    /**
     *getter method for checkedOut boolean
     * @return value of boolean variable checkedOut
     */
    public boolean getCheckedOut() {
        return checkedOut;
    }

    /**
     *setter method for checkedOut boolean, sets checkedOut as true
     */
    public void checkOut() {
        this.checkedOut = true;
    }

    /**
     * setter method for checkedOut boolean, sets checkedOut as false
     */
    public void checkIn() {
        this.checkedOut = false;
    }
}