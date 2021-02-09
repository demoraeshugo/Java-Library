
/**
 * Library class is a container class that is designed to hold Book objects. Library also provides
 * a variety of methods to make changes to its bag of Books such as adding, removing and checking out
 * Books, as well as different ways of sorting Books. Library also controls the printing of Books by either number
 * or by datePublished
 *
 * @author Hugo De Moraes, Jonathan Dong
 *
 */

public class Library {
    private Book[] books; // array-based implementation of the bag data structure
    private int numBooks; // the number of books currently in the bag
    private final int sizeFactor = 4; // initialize here for use in constructor


    /**
     * default constructor to create an empty bag with numBooks = 0
     */
    public Library() {
        books = new Book[sizeFactor];
        numBooks = 0;
    }

    /**
     * sorts Library by book number
     */
    public void sortByNumber(){
        int n = numBooks;

        // One by one move boundary of unsorted subarray
        for (int i = 0; i < n-1; i++)
        {
            // Find the minimum element in unsorted array
            int min_idx = i;
            for (int j = i+1; j < n; j++)
                if (books[j].compareByNumber(books[min_idx]) == -1)
                    min_idx = j;

            // Swap the found minimum element with the first
            // element
            Book temp = books[min_idx];
            books[min_idx] = books[i];
            books[i] = temp;
        }
    }

    /**
     * sorts Library by datePublished
     */
    public void sortByDate(){
        int n = numBooks;

        // One by one move boundary of unsorted subarray
        for (int i = 0; i < n-1; i++)
        {
            // Find the minimum element in unsorted array
            int min_idx = i;
            for (int j = i+1; j < n; j++)
                if (books[j].compareByDate(books[min_idx]) == -1)
                    min_idx = j;

            // Swap the found minimum element with the first
            // element
            Book temp = books[min_idx];
            books[min_idx] = books[i];
            books[i] = temp;
        }
    }

    /**
     * helper method to find a book in the bag
     * @return index of book in books array
     *         -1 if book is not in books array
     */
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

    /**
     * helper method to grow the capacity by 4
     */
    private void grow() {
        // create new array of length current + 4
        Book[] newBag = new Book[books.length + sizeFactor];

        // copy over all elements from current to new array
        for(int i = 0; i < books.length; i++){
            newBag[i] = books[i];
        }

        // set new array as the books property
        books = newBag;
    }


    /**
     * adds a book to the bag, grows bag if needed
     * @param book Book object to be added
     */
    public void add(Book book) {
        if (numBooks == books.length - 1) {
            grow();
        }

        books[numBooks] = book;
        numBooks++;
    }

    /**
     * removes a book if it exists
     * @param book Book object to be removed
     * @return true if book is removed successfully
     *         false if book is not found
     */
    public boolean remove(Book book) {
        int indexOfBook = find(book);

        // case book not found
        if(indexOfBook == -1) {
            return false;
        }

        for(int i = 0; i < books.length; i++) {
            // last elem will always be null by virtue of one being removed
            if(i+1 == books.length) {
                books[i] = null;
                break;
            }
            // from the target index to end, shift elements to the left
            if(i >= indexOfBook) {
                books[i] = books[i+1];
            }
        }

        numBooks--;
        return true;
    }

    /**
     * sets checkedOut attribute of Book to be true if possible
     * @param book Book object to be checked out
     * @return true if book is successfully checked out
     *         false if book is not found or is already checked out
     */
    public boolean checkOut(Book book) {
        int indexOfBook = find(book);

        // if book not found or is already checked out
        if(indexOfBook == -1 || books[indexOfBook].getCheckedOut()) {
            return false;
        }

        books[indexOfBook].checkOut();
        return true;
    }


    /**
     * returns a book if possible
     * @param book Book object to be returned
     * @return true if book is successfully returned
     *         false if book is not found
     */
    public boolean returns(Book book) {
        int indexOfBook = find(book);

        if(indexOfBook == -1) {
            return false;
        }

        books[indexOfBook].checkIn();
        return true;

    }

    /**
     * checks if number of Books in Library is 0
     * @return true if numBooks = 0
     */
    public boolean isEmpty() {
        return numBooks == 0;
    }


    /**
     * helper method to print the list of books in the bag
     */
    public void print() {
        for (Book book : books) {
            if (book == null) {
                continue;
            }
            System.out.println(book.toString());
        }
    }

    /**
     * deals with the PA command to print the list of books in the bag in current sequence
     */
    public void printDefault() {
        if(isEmpty()) {                                     //first checks if books array is empty
            System.out.println(IoFields.invalidPrintLog);
        } else {
            System.out.println(IoFields.printLog);
            print();
            System.out.println(IoFields.printEndLog);
        }
    }

    /**
     *    print the list of books by datePublished (ascending)
     */
    public void printByDate() {
        if(isEmpty()) {
            System.out.println(IoFields.invalidPrintLog);
        } else {
            sortByDate();
            System.out.println(IoFields.printByDateLog);
            print();
            System.out.println(IoFields.printEndLog);
        }
    }

    /**
     * print the list of books by number (ascending)
     */
    public void printByNumber() {
        if(isEmpty()) {
            System.out.println(IoFields.invalidPrintLog);
        } else {
            sortByNumber();
            System.out.println(IoFields.printByNumberLog);
            print();
            System.out.println(IoFields.printEndLog);
        }
    }
}
