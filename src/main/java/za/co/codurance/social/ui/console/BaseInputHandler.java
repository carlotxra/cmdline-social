package za.co.codurance.social.ui.console;

/**
 * I provide the base ability to only process the input if implementations are able to handle input.
 */
public abstract class BaseInputHandler implements InputHandler {

    /**
     * Implementations must answer true if they can handle the input
     */
    protected abstract boolean canHandle(String input);

    /**
     * Implementations must process the input.
     *
     * @param display can be used to display any content to user.
     */
    protected abstract void process(String input, Display display);

    @Override
    public boolean handle(String input, Display display) {
        if (!canHandle(input)) {
            return false;
        }

        process(input, display);
        return true;
    }

}
