/***
 * @author Hugo DeMorales
 * @author Jonathan Dong
 */

import java.util.function.Function;

public class Library {
    private Book[] books; // array-based implementation of the bag data structure
    private int numBooks; // the number of books currently in the bag
    private final int sizeFactor = 4; // initialize here for use in constructor

    // default constructor to create an empty bag
    public Library() {
        books = new Book[sizeFactor];
        numBooks = 0;
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
        for(int i = 0; i < books.length; i++){
            newBag[i] = books[i];
        }

        // set new array as the books property
        books = newBag;
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

    // checks out a book if available
    public boolean checkOut(Book book) {
        int indexOfBook = find(book);

        // if book not found or is already checked out
        if(indexOfBook == -1 || books[indexOfBook].getCheckedOut()) {
            return false;
        }

        books[indexOfBook].checkOut();
        return true;
    }

    // returns a book if possible
    public boolean returns(Book book) {
        int indexOfBook = find(book);

        if(indexOfBook == -1) {
            return false;
        }

        books[indexOfBook].checkIn();
        return true;

    }

    private void merge(Book[] books, int start, int mid, int end, Function<Book[], Boolean> fn) {
        int start2 = mid + 1;

        Book[] compare = new Book[] { books[mid], books[start2] };

        // If the direct merge is already sorted
        if(fn.apply(compare)) {
            return;
        }

        // Two pointers to maintain start
        // of both arrays to merge
        while (start <= mid && start2 <= end) {

            // If element 1 is in right place
            compare[0] = books[start];
            compare[1] = books[start2];
            if (fn.apply(compare)) {
                start++;
            }
            else {
                Book book = books[start2];
                int index = start2;

                // Shift all the elements between element 1
                // element 2, right by 1.
                while (index != start) {
                    books[index] = books[index - 1];
                    index--;
                }
                books[start] = book;

                // Update all the pointers
                start++;
                mid++;
                start2++;
            }
        }
    }

    /* l is for left index and r is right index of the
       sub-array of arr to be sorted */
    private void mergeSort(Book[] books, int left, int right, Function<Book[], Boolean> fn )
    {
        if (left < right) {

            // Same as (left + right) / 2, but avoids overflow
            // for large left and right
            int middle = left + (right - left) / 2;

            // Sort first and second halves
            mergeSort(books, left, middle, fn);
            mergeSort(books, middle + 1, right, fn);

            merge(books, left, middle, right, fn);
        }
    }

    private Function<Book[], Boolean> byNumber =
            parameter -> Book.isGreaterNumber(parameter[0], parameter[1]) ;

    private Function<Book[], Boolean> byDate =
            parameter -> Date.isGreaterDate(parameter[0].getDatePublished(), parameter[1].getDatePublished());

    // print the list of books in the bag
    public void print() {
        for (Book book : books) {
            if (book == null) {
                System.out.println("null");
                continue;
            }
            System.out.println(book.toString());
        }
    }

    // print the list of books by datePublished (ascending)
    public void printByDate() {
        mergeSort(books, 0, numBooks-1, byDate);
        print();
    }

    // print the list of books by number (ascending)
    public void printByNumber() {
        mergeSort(books, 0, numBooks-1, byNumber);
        print();
    }
}
