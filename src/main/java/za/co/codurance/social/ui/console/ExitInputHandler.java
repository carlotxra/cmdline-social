package za.co.codurance.social.ui.console;

/**
 * I can be used to end the interaction with a user by user entering
 * <br/><em>exit</em>
 */
public class ExitInputHandler extends BaseInputHandler {

    public static final String EXIT_COMMAND = "exit";

    @Override
    protected boolean canHandle(String input) {
        return EXIT_COMMAND.equalsIgnoreCase(input);
    }

    @Override
    protected void process(String input, Display display) {
        System.exit(0);
    }
}
