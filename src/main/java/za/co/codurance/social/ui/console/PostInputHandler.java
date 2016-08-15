package za.co.codurance.social.ui.console;

import za.co.codurance.social.actions.PostMessage;
import za.co.codurance.social.actions.PublishPost;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 */
public class PostInputHandler extends BaseInputHandler {
    private static final String USER_HANDLE_REGEX = "^(\\w+)";

    private static final String CREATE_POST_SEPARATOR = " -> ";

    private static final String POST_CONTENT = "(.*)$";

    private static Pattern CREATE_POST_REGEX = Pattern.compile(USER_HANDLE_REGEX + CREATE_POST_SEPARATOR + POST_CONTENT);

    private PublishPost publishPost;

    public PostInputHandler(PublishPost publishPost) {
        this.publishPost = publishPost;
    }

    @Override
    protected boolean canHandle(String input) {
        return createMatcherOn(input).matches();
    }

    protected void process(String input, Display display) {
        final PostMessage postMessage = parseInput(input);
        processPostMessage(postMessage);
    }

    private PostMessage parseInput(String input) {
        final Matcher matcher = createMatcherOn(input);
        matcher.matches();
        final String aUserHandle = matcher.group(1);
        final String messageContent = matcher.group(2);
        final Date datePosted = createDatePostedTimestamp();

        return new PostMessage(aUserHandle, messageContent, datePosted);
    }

    protected Date createDatePostedTimestamp() {
        return new Date();
    }

    private void processPostMessage(PostMessage postMessage) {
        getPublishPost().execute(postMessage);
    }

    private Matcher createMatcherOn(String input) {
        return CREATE_POST_REGEX.matcher(input);
    }

    private PublishPost getPublishPost() {
        return publishPost;
    }
}
