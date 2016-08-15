package za.co.codurance.social.ui.view;

import za.co.codurance.social.model.post.Post;
import za.co.codurance.social.model.timeline.Timeline;

import java.util.Date;

/**
 * A basic implementation of transforming a {@link Timeline} or {@link Post} to
 * a textual representation.
 * <br/>
 * I collaborate with a {@link DurationFormatter} to format the date posted.
 */
public class TimelineFormatterImpl implements TimelineFormatter {
    protected static final String TIMELINE_FORMAT = "%s (%s)";

    private DurationFormatter durationFormatter;

    public TimelineFormatterImpl(DurationFormatter durationFormatter) {
        this.durationFormatter = durationFormatter;
    }

    @Override
    public String format(Timeline timeline, Date asAtDate) {
        StringBuilder output = new StringBuilder();
        for (Post post : timeline.entries()) {
            final String postOutput = format(post, asAtDate);
            output.append(postOutput);
            output.append(System.lineSeparator());
        }
        return output.toString();
    }

    public String format(Post post, Date asAtDate) {
        final String messageContent = getMessageText(post);
        final String postedAtDurationText = getPostedAtDurationText(post, asAtDate);

        return formatPost(messageContent, postedAtDurationText);
    }

    protected String getPostedAtDurationText(Post post, Date asAtDate) {
        final Date dateCreated = post.getDateCreated();
        return getDurationFormatter().format(dateCreated, asAtDate);
    }

    protected String getMessageText(Post post) {
        return post.getMessage();
    }

    protected String formatPost(String messageContent, String postedAtDurationText) {
        return String.format(TIMELINE_FORMAT, messageContent, postedAtDurationText);
    }

    protected DurationFormatter getDurationFormatter() {
        return durationFormatter;
    }
}
