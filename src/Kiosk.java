
import java.util.Scanner;

/**
 * Kiosk class provides the interface for communicating with a Library object and handles input and output
 * * by processing commands from the console.This includes dealing with additions/removal to a Library, printing a Library,
 * * Checking out and returning books, etc.
 * *
 * * @author Hugo De Moraes, Jonathan Dong
 */
public class Kiosk {
    private final Library library;
    private String userInput;
    private String[] tokens;

    /**
     * default constructor for Kiosk
     */
    public Kiosk() {
        library = new Library();
    }

    /**
     * tokenizes a given input String
     *
     * @param input string to be tokenized
     * @return String array of tokens (Strings split with ,)
     */
    private String[] tokenize(String input) {
        return input.split(",");
    }

    /**
     * calls respective helper methods based on user input
     */
    private void handleUserInput() {
        switch (userInput) {
            case Commands.add -> handleAdd();
            case Commands.remove -> handleRemove();
            case Commands.checkOut -> handleCheckOut();
            case Commands.checkIn -> handleReturn();
            case Commands.printAll -> handlePrint();
            case Commands.printByDate -> handlePrintDate();
            case Commands.printByNumber -> handlePrintNumber();
            default -> System.out.println(IoFields.invalidCommandLog);
        }
    }

    /**
     * processes user input from command line when user wants to add Book to Library
     */
    private void handleAdd() {
        String title = tokens[1];                   //Book title given as 2nd element of tokenized array
        Date publishDate = new Date(tokens[2]);     //Book date given as 3rd element of tokenized array

        if (publishDate.isValid()) {
            library.add(new Book(title, publishDate));
            System.out.printf(IoFields.validDateLog, title);
        } else {
            System.out.println(IoFields.invalidDateLog);
        }
    }

    /**
     * processes user input from command line when user wants to remove Book from Library
     */
    private void handleRemove() {
        String serialNumber = tokens[1];
        Book targetBook = new Book(serialNumber);

        if (library.remove(targetBook)) {
            System.out.printf(IoFields.validRemoveLog, serialNumber);
        } else {
            System.out.println(IoFields.invalidRemoveLog);
        }
    }

    /**
     * processes user input from command line when user wants to checkout Book from Library
     */
    private void handleCheckOut() {
        String serialNumber = tokens[1];
        Book targetBook = new Book(serialNumber);

        if (library.checkOut(targetBook)) {
            System.out.printf(IoFields.validCheckOutLog, serialNumber);
        } else {
            System.out.printf(IoFields.invalidCheckOutLog, serialNumber);
        }
    }

    /**
     * processes user input from command line when user wants to return Book to Library
     */
    private void handleReturn() {
        String serialNumber = tokens[1];
        Book targetBook = new Book(serialNumber);

        if (library.returns(targetBook)) {
            System.out.printf(IoFields.validReturnLog, serialNumber);
        } else {
            System.out.printf(IoFields.invalidReturnLog, serialNumber);
        }
    }

    /**
     * helper method that prints contents of Library
     */
    private void handlePrint() {
        library.printDefault();
    }

    /**
     * helper method that prints contents of Library by Date
     */
    private void handlePrintDate() {
        library.printByDate();
    }

    /**
     * helper method that prints contents of Library by book number
     */
    private void handlePrintNumber() {
        library.printByNumber();
    }

    /**
     * Readies the kiosk for user input from command line
     */
    public void run() {
        Scanner scan = new Scanner(System.in);
        String quitCommand = "Q";
        System.out.println(IoFields.startPrompt);

        do {
            tokens = tokenize(scan.nextLine());         //tokenize each line of user input
            userInput = tokens[0];                      //sets userInput to command (A, I, O, R , etc)
            if (!userInput.equals(quitCommand)) {
                handleUserInput();
            }
        } while (!userInput.equals(quitCommand));

        System.out.println(IoFields.endPrompt);         //when finished with kiosk, end prompt is printed
    }
}