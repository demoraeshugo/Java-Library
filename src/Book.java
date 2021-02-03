/***
 * @author Hugo De Moraes
 * @author Jonathan Dong
 */

//import java.util.Objects;

public class Book {
    private final String number; // a 5-digit serial number unique to the book
    private String name;
    private boolean checkedOut;
    private Date datePublished;

    Book(String number, String name, Date datePublished) {
        this.number = number;
        this.name = name;
        this.datePublished = datePublished;
    }

    Book(String number) {
        this.number = number;
    }

    public String getBookNumber(){
        return this.number;
    }

    public Date getDatePublished(){
        return this.datePublished;
    }


    public int compareByNumber(Book b) {
        if(Integer.parseInt(this.getBookNumber()) > Integer.parseInt(b.getBookNumber())){
            return 1;
        }
        else if (Integer.parseInt(this.getBookNumber()) == Integer.parseInt(b.getBookNumber())){
            return 0;
        }
        else {
            return -1;
        }


    }

    public int compareByDate(Book b) {
        if(this.getDatePublished().getYear() < b.getDatePublished().getYear()){
            return -1;
        }
        else if (this.getDatePublished().getYear() > b.getDatePublished().getYear()){
            return 1;

        }
        else {
            if (this.getDatePublished().getMonth() < b.getDatePublished().getMonth()){
                return -1;
            }
            else if ( this.getDatePublished().getMonth() > b.getDatePublished().getMonth()){
                return 1;
            }
            else{
                if ( this.getDatePublished().getDay() < b.getDatePublished().getDay()){
                    return -1;
                }
                else if(this.getDatePublished().getDay() > b.getDatePublished().getDay()){
                    return 1;
                }
                else {
                    return 0;
                }
            }
        }


    }



    @Override
    public boolean equals(Object obj) {
//        if(this == obj) {
//            return true;
//        }
//
//        if(obj == null || getClass() != obj.getClass()) {
//            return false;
//        }
//
//        Book targetBook = (Book) obj;
//        return Objects.equals(number, targetBook.number);
        if(this == obj) {
            return true;
        }

        if(obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Book targetBook = (Book) obj;
        return ( targetBook.number.equals(((Book) obj).number));


    }



    @Override
    public String toString() {
        String isAvailable;

        if (this.checkedOut) {
            isAvailable = "is not available";
        } else {
            isAvailable = "is available";
        }

        // ex: Book#10007::Design Patterns::5/30/1996::is available.
        return "Book#" + number + "::" + name + "::" + datePublished.toString() + "::" + isAvailable;
    }

    public static String getCurrentSerialNumber() {
        return String.valueOf(currentSerialNumber++);
    }

    public boolean getCheckedOut() {
        return checkedOut;
    }

    public void checkOut() {
        this.checkedOut = true;
    }

    public void checkIn() {
        this.checkedOut = false;
    }
}