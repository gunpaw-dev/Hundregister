
import java.util.Scanner;

public class InputReader {

    private Scanner scanner;
    private static boolean isInstantiated = false;

    public InputReader(Scanner scanner) {
        this.scanner = scanner;
        if (isInstantiated) {
            throw new IllegalStateException("Duplicates of the class InputReader are not allowed.");
        }
        isInstantiated = true;
    }

    public InputReader() {
        this(new Scanner(System.in)); //Anropar den andra konstruktorn som tar en Scanner.
    }

    public String readString(String text) {
        boolean success = false;
        String textInput = "";
        printText(text);
        while (!success) {
            String userInput = scanner.nextLine().trim();
            if (userInput.isEmpty() || userInput.isBlank()) {
                System.out.print("Error: must not be empty or blank?]");
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
            if (scanner.hasNextDouble()) {
                doubleInput = scanner.nextDouble();
                scanner.nextLine();
            } else {
                System.out.print("Error: must be a number.");
            }
            if (doubleInput < 0) {
                System.out.print("Error: must not be negative.");
            } else {
                success = true;
            }
            scanner.reset();
        }
        return doubleInput;
    }

    public int readInt(String text) {
        boolean success = false;
        int intInput = -1;
        printText(text);
        while (!success) {
            if (scanner.hasNextInt()) {
                intInput = scanner.nextInt();
                scanner.nextLine();
            } else {
                System.out.print("Error: must be a number.");
            }
            if (intInput < 0) {
                System.out.print("Error, must not be negative.");
            } else {
                success = true;
            }
        }
        scanner.reset();
        return intInput;
    }

    private void printText(String text) {
        System.out.print(text + "?>");
    }

}
