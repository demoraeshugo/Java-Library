import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

interface ioFields {
    String startPrompt = "Library Kiosk running.";
    String endPrompt = "Kiosk session ended";
    String validDateLog = "%s added to the bag.%n";
    String invalidDateLog = "Invalid Date!";
    String validRemoveLog = "Book#%s removed%n";
    String invalidRemoveLog = "Unable to remove, the library does not have this book.";
    String validCheckOutLog = "You've checked out Book#%s. Enjoy!%n";
    String invalidCheckOutLog = "Book#%s is not available.%n";
    String validReturnLog = "Book#%s return has completed. Thanks%n";
    String invalidReturnLog = "Unable to return Book#%s%n";
    String invalidCommand = "Invalid Command";
    String quitCommand = "Q";
    String addCommand = "A";
    String removeCommand = "R";
    String checkOutCommand = "O";
    String returnCommand = "I";
    String printCommand = "PA";
    String printDateCommand = "PD";
    String printNumberCommand = "PN";
}

public class Kiosk implements ioFields {
    private final Library library;
    private String userInput;
    private String[] tokens;

    public Kiosk() {
        library = new Library();
    }

    private String[] tokenize(String input) {
        return input.split(",");
    }

    private void handleUserInput() {
        if( userInput.equals(addCommand) ) {
            handleAdd();
        } else if( userInput.equals(removeCommand) ) {
            handleRemove();
        } else if( userInput.equals(checkOutCommand) ) {
            handleCheckOut();
        } else if( userInput.equals(returnCommand) ) {
            handleReturn();
        } else if( userInput.equals(printCommand) ) {
            handlePrint();
        } else if( userInput.equals(printDateCommand) ) {
            handlePrintDate();
        } else if( userInput.equals(printNumberCommand) ) {
            handlePrintNumber();
        } else if(!userInput.equals(quitCommand)) {
            System.out.println(invalidCommand);
        }
    }

    private void handleAdd() {
        String title = tokens[1];
        Date publishDate = new Date(tokens[2]);

        if(publishDate.isValid()) {
            String serialNumber = library.getNewSerialNumber();
            library.add(new Book( serialNumber, title, publishDate ));
            System.out.printf(validDateLog, title);
        } else {
            System.out.println(invalidDateLog);
        }
    }

    private void handleRemove() {
        String serialNumber = tokens[1];
        Book targetBook = new Book(serialNumber);

        if(library.remove(targetBook)) {
            System.out.printf(validRemoveLog, serialNumber);
        } else {
            System.out.println(invalidRemoveLog);
        }
    }

    private void handleCheckOut() {
        String serialNumber = tokens[1];
        Book targetBook = new Book(serialNumber);

        if(library.checkOut(targetBook)) {
            System.out.printf(validCheckOutLog, serialNumber);
        } else {
            System.out.printf(invalidCheckOutLog, serialNumber);
        }
    }

    private void handleReturn() {
        String serialNumber = tokens[1];
        Book targetBook = new Book(serialNumber);

        if(library.returns(targetBook)) {
            System.out.printf(validReturnLog, serialNumber);
        } else {
            System.out.printf(invalidReturnLog, serialNumber);
        }
    }


    private void handlePrint() {
        // Todo
    }

    private void handlePrintDate() {
        // Todo
    }

    private void handlePrintNumber() {
        // Todo
    }

    // Manual input
    public void run() {
        Scanner scan = new Scanner(System.in);

        do {
             tokens = tokenize(scan.nextLine());
             userInput = tokens[0];
             handleUserInput();
        } while(!userInput.equals(quitCommand) );

        System.out.println(endPrompt);
    }

    // Auto input from file
    public void runTest() {
        File file = new File("p1/testCases.txt");
        System.out.println(startPrompt);

        try (Scanner sc = new Scanner(file, StandardCharsets.UTF_8.name())) {
            do {
                tokens = tokenize(sc.nextLine());
                userInput = tokens[0];
                handleUserInput();
            } while(!userInput.equals(quitCommand) && sc.hasNextLine());
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(endPrompt);
    }
}