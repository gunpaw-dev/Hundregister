
import java.util.Scanner;

public class InputReader {

    private static boolean isInstantiated;
    private Scanner scanner;

    public InputReader(Scanner scanner) {
        this.scanner = scanner;
        if (isInstantiated) {
            throw new IllegalStateException("Duplicates of the class InputReader are not allowed.");
        }
        isInstantiated = true;
    }

    public InputReader() {
        this(new Scanner(System.in)); //"this" anropar den andra konstruktorn som tar en Scanner.
    }

    public String readString(String text) {
        boolean success = false;
        String textInput = "";
        printText(text);
        while (!success) {
            String userInput = scanner.nextLine().trim();
            if (userInput.isEmpty() || userInput.isBlank()) {
                printErrorText("must not be empty or blank.");
                printText(text);
                continue;
            } else {
                textInput = userInput;
                success = true;
            }
        }
        return textInput;
    }

    public double readDouble(String text) {
        boolean success = false;
        double doubleInput = -1.0;
        printText(text);
        while (!success) {
            if (!scanner.hasNextDouble()) {
                printErrorText("must be a number.");
                scanner.nextLine();
                continue;
            } else {
                doubleInput = scanner.nextDouble();
                scanner.nextLine();
            }

            if (doubleInput < 0) {
                printErrorText("must not be negative.");
                continue;
            } else {
                success = true;
            }
        }
        return doubleInput;

    }

    public int readInt(String text) {
        boolean success = false;
        int intInput = -1;
        printText(text);
        while (!success) {
            if (!scanner.hasNextInt()) {
                printErrorText("must be a number.");
                printText(text);
                scanner.nextLine();
                continue;
            } else {
                intInput = scanner.nextInt();
                scanner.nextLine();
            }

            if (intInput < 0) {
                printErrorText("must not be negative.");
                printText(text);
                continue;
            } else {
                success = true;
            }
        }
        return intInput;
    }

    private void printText(String text) {
        System.out.print(text + "?>");
    }

    private void printErrorText(String text) {
        System.out.println("Error: " + text);
    }

    public void close() {
        scanner.close();
    }
}
