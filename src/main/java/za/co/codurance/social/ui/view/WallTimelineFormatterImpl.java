package za.co.codurance.social.ui.view;

import za.co.codurance.social.model.post.Post;
import za.co.codurance.social.model.timeline.Timeline;

/**
 * A basic implementation of transforming a {@link Timeline} or {@link Post} to
 * a textual representation.
 * <br/>
 * I collaborate with a {@link DurationFormatter} to format the date posted.
 * I extend all functionality of {@link TimelineFormatterImpl}, the only difference is in the format of the message text {@link #getMessageText(Post)}.
 */
public class WallTimelineFormatterImpl extends TimelineFormatterImpl {

    public WallTimelineFormatterImpl(DurationFormatter durationFormatter) {
        super(durationFormatter);
    }

    protected String getMessageText(Post post) {
        return String.format("%s - %s", post.getUserHandle(), post.getMessage());
    }

}
