package za.co.codurance.social.ui.console;

import za.co.codurance.social.actions.ReadUserWall;
import za.co.codurance.social.actions.ViewWallMessage;
import za.co.codurance.social.model.timeline.Timeline;
import za.co.codurance.social.ui.view.TimelineFormatter;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * I can view a user's wall by interpreting user input of the form:
 * <br/><em>Alice wall</em>
 */
public class ViewWallInputHandler extends BaseInputHandler {
    private static Pattern VIEW_WALL_REGEX = Pattern.compile("^(\\w+) wall$");

    private ReadUserWall readUserWall;

    private TimelineFormatter timelineFormatter;

    public ViewWallInputHandler(ReadUserWall readUserWall, TimelineFormatter timelineFormatter) {
        this.readUserWall = readUserWall;
        this.timelineFormatter = timelineFormatter;
    }

    @Override
    protected boolean canHandle(String input) {
        return createMatcherOn(input).matches();
    }

    protected void process(String input, Display display) {
        final ViewWallMessage message = parseInput(input);
        processMessage(message, display);
    }

    private ViewWallMessage parseInput(String input) {
        final Matcher matcher = createMatcherOn(input);
        matcher.matches();
        final String aUserHandle = matcher.group(1);

        return new ViewWallMessage(aUserHandle);
    }

    private void processMessage(ViewWallMessage aMessage, Display display) {
        final Timeline timeline = getReadUserWall().execute(aMessage);
        displayTimeline(display, timeline);
    }

    private void displayTimeline(Display display, Timeline timeline) {
        final Date asAtDate = new Date();
        final String text = getTimelineFormatter().format(timeline, asAtDate);
        display.show(text);
    }

    private Matcher createMatcherOn(String input) {
        return VIEW_WALL_REGEX.matcher(input);
    }

    private ReadUserWall getReadUserWall() {
        return readUserWall;
    }

    private TimelineFormatter getTimelineFormatter() {
        return timelineFormatter;
    }
}
