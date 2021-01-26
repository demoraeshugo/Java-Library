public class Book {
    private String number; // a 5-digit serial number unique to the book
    private String name;
    private boolean checkedOut;
    private Date datePublished;

    Book(String number, String name, Date datePublished) {
        this.number = number;
        this.name = name;
        this.datePublished = datePublished;
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj;
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
        return "Book#" + this.number + "::" + this.name + "::" + datePublished + "::" + isAvailable;
    }

    void checkOut() {
        this.checkedOut = true;
    }

    void checkIn() {
        this.checkedOut = false;
    }
}