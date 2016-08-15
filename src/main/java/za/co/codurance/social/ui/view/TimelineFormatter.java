package za.co.codurance.social.ui.view;

import za.co.codurance.social.model.post.Post;
import za.co.codurance.social.model.timeline.Timeline;

import java.util.Date;

/**
 * I can format either a {@link Timeline} or a {@link Post} into text based.
 */
public interface TimelineFormatter {
    /**
     * @param timeline of a particular user
     * @param asAtDate as relative date to process timeline against
     *
     * @return a formatted representation of a timeline
     */
    String format(Timeline timeline, Date asAtDate);

    /**
     * @param post     of a particular user
     * @param asAtDate as relative date to process post against
     *
     * @return a formatted representation of post
     */
    String format(Post post, Date asAtDate);
}
