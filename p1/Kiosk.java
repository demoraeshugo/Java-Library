import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Kiosk {
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
        switch (userInput) {
            case "A" -> handleAdd();
            case "R" -> handleRemove();
            case "O" -> handleCheckOut();
            case "I" -> handleReturn();
            case "PA" -> handlePrint();
            case "PD" -> handlePrintDate();
            case "PN" -> handlePrintNumber();
            default -> System.out.println(IoFields.invalidCommand);
        }
    }

    private void handleAdd() {
        String title = tokens[1];
        Date publishDate = new Date(tokens[2]);

        if(publishDate.isValid()) {
            String serialNumber = library.getNewSerialNumber();
            library.add(new Book( serialNumber, title, publishDate ));
            System.out.printf(IoFields.validDateLog, title);
        } else {
            System.out.println(IoFields.invalidDateLog);
        }
    }

    private void handleRemove() {
        String serialNumber = tokens[1];
        Book targetBook = new Book(serialNumber);

        if(library.remove(targetBook)) {
            System.out.printf(IoFields.validRemoveLog, serialNumber);
        } else {
            System.out.println(IoFields.invalidRemoveLog);
        }
    }

    private void handleCheckOut() {
        String serialNumber = tokens[1];
        Book targetBook = new Book(serialNumber);

        if(library.checkOut(targetBook)) {
            System.out.printf(IoFields.validCheckOutLog, serialNumber);
        } else {
            System.out.printf(IoFields.invalidCheckOutLog, serialNumber);
        }
    }

    private void handleReturn() {
        String serialNumber = tokens[1];
        Book targetBook = new Book(serialNumber);

        if(library.returns(targetBook)) {
            System.out.printf(IoFields.validReturnLog, serialNumber);
        } else {
            System.out.printf(IoFields.invalidReturnLog, serialNumber);
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
        String quitCommand = "Q";

        do {
             tokens = tokenize(scan.nextLine());
             userInput = tokens[0];
            if(!userInput.equals(quitCommand)){
                handleUserInput();
            }
        } while(!userInput.equals(quitCommand) );

        System.out.println(IoFields.endPrompt);
    }

    // Auto input from file
    public void runTest() {
        File file = new File("p1/testCases.txt");
        System.out.println(IoFields.startPrompt);
        String quitCommand = "Q";

        try (Scanner sc = new Scanner(file, StandardCharsets.UTF_8.name())) {
            do {
                tokens = tokenize(sc.nextLine());
                userInput = tokens[0];
                if(!userInput.equals(quitCommand)){
                    handleUserInput();
                }
            } while(!userInput.equals(quitCommand) && sc.hasNextLine());
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(IoFields.endPrompt);
    }
}