public class Library {
    private Book[] books; // array-based implementation of the bag data structure
    private int numBooks; // the number of books currently in the bag

    // default constructor to create an empty bag
    public Library() {
        this.books = new Book[4];
        this.numBooks = 0;
    } 

    // helper method to find a book in the bag
    private int find(Book book) {
        int indexOfBook = -1;
        for (int i = 0; i < this.books.length; i++) {
            if (this.books[i].equals(book)) {
                indexOfBook = i;
                break;
            }
        }
        return indexOfBook;
    } 

    // helper method to grow the capacity by 4
    private void grow() {
        // create new array of length current + 4
        Book[] newBag = new Book[this.books.length + 4];
        // copy over all elements from current to new array
        System.arraycopy(this.books, 0, newBag, 0, this.books.length);
        // set new array as the books property
        this.books = newBag;
    }

    public void add(Book book) {
        if (this.numBooks == this.books.length - 1) {
            this.grow();
        }
        this.books[numBooks - 1] = book;
        this.numBooks++;
    }

    /* Do we resize the array when removing an element?
     Should the array contain a gap at the index removed or should the elements shift? */
    public boolean remove(Book book) {

    }

    public boolean checkOut(Book book) {
    }

    public boolean returns(Book book) {
    }

    public void print() {
    } // print the list of books in the bag

    public void printByDate() {
    } // print the list of books by datePublished (ascending)

    public void printByNumber() {
    } // print the list of books by number (ascending)
}
