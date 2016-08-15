package za.co.codurance.social.ui.controller;

import za.co.codurance.social.ui.console.Display;
import za.co.codurance.social.ui.console.InputHandler;

import java.io.BufferedReader;
import java.io.IOError;
import java.io.IOException;

/**
 * I am a controller/coordinator responsible for receiving user input and requesting it to be processed.
 * <br/>
 * <pre>I collaborate with:
 * {@link BufferedReader} to receive the input.
 * {@link InputHandler} to actually handle the input received.
 * {@link Display} to allow handlers to interact with app user..
 *  </pre>
 */
public class SystemInputControllerImpl implements InputController {
    private InputHandler inputHandler;

    private Display display;

    private BufferedReader inputReader;

    public SystemInputControllerImpl(InputHandler inputHandler, Display display, BufferedReader inputReader) {
        this.inputHandler = inputHandler;
        this.display = display;
        this.inputReader = inputReader;
    }

    @Override
    public void readNextLine() {
        try {
            final String nextLine = getInputReader().readLine();
            processNextLine(nextLine);
        } catch (IOException e) {
            throw new IOError(e);
        }
    }

    private void processNextLine(String nextLine) {
        getInputHandler().handle(nextLine, getDisplay());
    }

    private Display getDisplay() {
        return display;
    }

    private InputHandler getInputHandler() {
        return inputHandler;
    }

    private BufferedReader getInputReader() {
        return inputReader;
    }
}
