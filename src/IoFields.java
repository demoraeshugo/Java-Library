
/**
 * IoFields class contains String messages that are used to display info about user commands
 * that are handled by the Kiosk class
 *
 * @author Hugo De Moraes, Jonathan Dong
 *
 */
public final class IoFields {
    public static final String startPrompt = "Library Kiosk running.";
    public static final String endPrompt = "Kiosk session ended";
    public static final String validDateLog = "%s added to the bag.%n";
    public static final String invalidDateLog = "Invalid Date!";
    public static final String validRemoveLog = "Book#%s removed%n";
    public static final String invalidRemoveLog = "Unable to remove, the library does not have this book.";
    public static final String validCheckOutLog = "You've checked out Book#%s. Enjoy!%n";
    public static final String invalidCheckOutLog = "Book#%s is not available.%n";
    public static final String validReturnLog = "Book#%s return has completed. Thanks%n";
    public static final String invalidReturnLog = "Unable to return Book#%s%n";
    public static final String invalidCommandLog = "Invalid Command";
    public static final String invalidPrintLog = "Library catalog is empty!";
    public static final String printByDateLog = "**List of books by the dates published.";
    public static final String printByNumberLog = "**List of books by the book numbers.";
    public static final String printLog = "**List of books in the library.";
    public static final String printEndLog = "**End of list";
}
