package za.co.codurance.social.ui.console;

/**
 *
 */
public class UnhandledInputHandler implements InputHandler {
    @Override
    public boolean handle(String input, Display display) {
        final String message = String.format("Did not understand input [%s]", input);
        display.show(message);

        return true;
    }
}
