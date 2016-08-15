package za.co.codurance.social.ui.console;

import za.co.codurance.social.ui.controller.InputController;

import java.io.PrintStream;

/**
 * Controller responsible for reading in user input gestures.
 * Collaborate with {@link InputController} to actually read in user input.
 */
public class Console {

    public static final String INPUT_PROMPT_TEXT = "> ";

    private InputController inputController;

    public Console(InputController inputController) {
        this.inputController = inputController;
    }

    public void start() {
        while (true) {
            waitForInput();
        }
    }

    private void waitForInput() {
        display(INPUT_PROMPT_TEXT);
        getInputController().readNextLine();
    }

    private void display(String text) {
        getOutputStream().print(text);
    }

    private PrintStream getOutputStream() {
        return System.out;
    }

    private InputController getInputController() {
        return inputController;
    }
}
