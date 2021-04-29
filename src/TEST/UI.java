package TEST;

import java.util.Scanner;

/**
 * Class for showing information to and reading information from the user in the console. Used for testing purposes.
 * @author Mattias Bengtsson
 */
public class UI {
    private Scanner userInput;

    /**
     * Constructor that initializes the Scanner object.
     */
    public UI() {
        userInput = new Scanner(System.in);
    }

    /**
     * Prints a given text in the console.
     * @param text the string to print in the console.
     */
    public void printMessage(String text) {
        System.out.println(text);
    }

    /**
     * Prints an array in the console with each element on a new line.
     * @param textArray the array of strings to print in the console
     */
    public void printArray(String[] textArray) {
        for (String s : textArray) {
            System.out.println(s);
        }
    }

    /**
     * Reads the user's input.
     * @return the user's input.
     */
    public String readText() {
        return userInput.nextLine();
    }
}
