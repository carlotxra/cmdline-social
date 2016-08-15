package za.co.codurance.social.ui.console;

/**
 * Presentation controller handler which takes in user input gestures and can display output back to user.
 */
public interface InputHandler {
    /**
     * Handle any user input entered.
     *
     * @param input   entered by user
     * @param display to output any text to user.
     *
     * @return true if this handler has processed the input.
     */
    boolean handle(String input, Display display);
}
