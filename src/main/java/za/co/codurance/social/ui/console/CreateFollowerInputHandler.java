package za.co.codurance.social.ui.console;

import za.co.codurance.social.actions.AddNewFollower;
import za.co.codurance.social.actions.AddNewFollowerMessage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * I can add a new follower to a user by interpreting user input of the form:
 * <br/><em>Alice follows Bob</em>
 */
public class CreateFollowerInputHandler extends BaseInputHandler {
    private static final String USER_HANDLE_REGEX = "^(\\w+)";

    private static final String CREATE_POST_SEPARATOR_REGEX = " follows ";

    private static final String ANOTHER_USER_REGEX = "(\\w+)";

    private static Pattern CREATE_FOLLOWER_REGEX = Pattern.compile(
            USER_HANDLE_REGEX + CREATE_POST_SEPARATOR_REGEX + ANOTHER_USER_REGEX);

    private AddNewFollower addNewFollower;

    public CreateFollowerInputHandler(AddNewFollower addNewFollower) {
        this.addNewFollower = addNewFollower;
    }

    @Override
    protected boolean canHandle(String input) {
        return createMatcherOn(input).matches();
    }

    protected void process(String input, Display display) {
        final AddNewFollowerMessage message = parseInput(input);
        processMessage(message);
    }

    private AddNewFollowerMessage parseInput(String input) {
        final Matcher matcher = createMatcherOn(input);
        matcher.matches();
        final String aUserHandle = matcher.group(1);
        final String followerHandler = matcher.group(2);

        return new AddNewFollowerMessage(aUserHandle, followerHandler);
    }

    private void processMessage(AddNewFollowerMessage aMessage) {
        getAddNewFollower().execute(aMessage);
    }

    private Matcher createMatcherOn(String input) {
        return CREATE_FOLLOWER_REGEX.matcher(input);
    }

    private AddNewFollower getAddNewFollower() {
        return addNewFollower;
    }
}
