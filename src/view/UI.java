package view;

//Används bara för consolen.

import java.util.Scanner;

public class UI {
    private Scanner userInput;

    public UI() {
        userInput = new Scanner(System.in);
    }

    public void printMessage(String text) {
        System.out.println(text);
    }

    public void printArray(String[] textArray) {
        for (String s : textArray) {
            System.out.println(s);
        }
    }

    public String readText() {
        return userInput.nextLine();
    }
}
