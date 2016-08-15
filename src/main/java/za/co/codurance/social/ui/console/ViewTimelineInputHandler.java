package za.co.codurance.social.ui.console;

import za.co.codurance.social.actions.ViewTimelineMessage;
import za.co.codurance.social.actions.ReadUserTimeline;
import za.co.codurance.social.model.timeline.Timeline;
import za.co.codurance.social.ui.view.TimelineFormatter;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * I can view a user's timeline by interpreting user input of the form:
 * <br/><em>Alice</em>
 */
public class ViewTimelineInputHandler extends BaseInputHandler {
    private static final String JUST_USER_HANDLE_REGEX = "^(\\w+)$";

    private static Pattern VIEW_TIMELINE_REGEX = Pattern.compile(JUST_USER_HANDLE_REGEX);

    private ReadUserTimeline readUserTimeline;

    private TimelineFormatter timelineFormatter;

    public ViewTimelineInputHandler(ReadUserTimeline readUserTimeline, TimelineFormatter timelineFormatter) {
        this.readUserTimeline = readUserTimeline;
        this.timelineFormatter = timelineFormatter;
    }

    @Override
    protected boolean canHandle(String input) {
        return createMatcherOn(input).matches();
    }

    protected void process(String input, Display display) {
        final ViewTimelineMessage message = parseInput(input);
        processMessage(message, display);
    }

    private ViewTimelineMessage parseInput(String input) {
        final Matcher matcher = createMatcherOn(input);
        matcher.matches();
        final String aUserHandle = matcher.group(1);

        return new ViewTimelineMessage(aUserHandle);
    }

    private void processMessage(ViewTimelineMessage aMessage, Display display) {
        final Timeline timeline = getReadUserTimeline().execute(aMessage);
        displayTimeline(display, timeline);
    }

    private void displayTimeline(Display display, Timeline timeline) {
        final Date asAtDate = new Date();
        final String text = getTimelineFormatter().format(timeline, asAtDate);
        display.show(text);
    }

    private Matcher createMatcherOn(String input) {
        return VIEW_TIMELINE_REGEX.matcher(input);
    }

    private ReadUserTimeline getReadUserTimeline() {
        return readUserTimeline;
    }

    private TimelineFormatter getTimelineFormatter() {
        return timelineFormatter;
    }
}
