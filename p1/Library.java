public class Library {
    private Book[] books; // array-based implementation of the bag data structure
    private int numBooks; // the number of books currently in the bag
    private int currentSerialNumber;
    private final int sizeFactor = 4;

    // default constructor to create an empty bag
    public Library() {
        books = new Book[sizeFactor];
        numBooks = 0;
        currentSerialNumber = 10001;
    } 

    // helper method to find a book in the bag
    private int find(Book book) {
        int indexOfBook = -1;
        for (int i = 0; i < books.length; i++) {
            if (books[i] != null && books[i].equals(book)) {
                indexOfBook = i;
                break;
            }
        }
        return indexOfBook;
    } 

    // helper method to grow the capacity by 4
    private void grow() {
        // create new array of length current + 4
        Book[] newBag = new Book[books.length + sizeFactor];
        // copy over all elements from current to new array
        int srcPos = 0;
        int destPos = 0;
        System.arraycopy(books, srcPos, newBag, destPos, books.length);
        // set new array as the books property
        books = newBag;
    }

    // helper method to generate serial numbers
    public String getNewSerialNumber() {
        return Integer.toString(currentSerialNumber++);
    }

    // adds a book to the bag
    // grows bag if needed
    public void add(Book book) {
        if (numBooks == books.length - 1) {
            grow();
        }
        books[numBooks] = book;
        numBooks++;
    }

    // removes a book if it exists
    public boolean remove(Book book) {
        int indexOfBook = find(book);
        if(indexOfBook != -1) {
            /* The method will copy all elements from the source array (books) starting one position right of
            the index. The elements will be copied into the same array (books) starting exactly at indexOfBook.
            The result will be a perceived shifting of all elements right of the element we wanted to remove. */
            System.arraycopy(books, indexOfBook + 1, books, indexOfBook, books.length - indexOfBook - 1);
            return true;
        } else {
            return false;
        }
    }

    // checks out a book if available
    public boolean checkOut(Book book) {
        int indexOfBook = find(book);
        if(indexOfBook != -1 && !books[indexOfBook].getCheckedOut()) {
            books[indexOfBook].checkOut();
            return true;
        } else {
            return false;
        }
    }

    // returns a book if possible
    public boolean returns(Book book) {
        int indexOfBook = find(book);
        if(indexOfBook != -1) {
            books[indexOfBook].checkIn();
            return true;
        } else {
            return false;
        }
    }

    // print the list of books in the bag
    public void print() {
        // Todo
    }

    // print the list of books by datePublished (ascending)
    public void printByDate() {
        // Todo
    }

    // print the list of books by number (ascending)
    public void printByNumber() {
        // Todo
    }
}
