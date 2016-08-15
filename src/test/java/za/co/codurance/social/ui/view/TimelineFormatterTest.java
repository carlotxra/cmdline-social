package za.co.codurance.social.ui.view;

import org.junit.Before;
import org.junit.Test;
import za.co.codurance.social.model.post.Post;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

/**
 */
public class TimelineFormatterTest extends BaseFormatterTest {
    private TimelineFormatter timelineFormatter;

    private TimelineFormatter wallTimelineFormatter;

    @Before
    public void setUp() {
        final DurationFormatter durationFormatter = new DurationFormatterImpl();
        timelineFormatter = new TimelineFormatterImpl(durationFormatter);
        wallTimelineFormatter = new WallTimelineFormatterImpl(durationFormatter);
    }

    @Test
    public void recentUserPostAsTimelineFormattedCorrectly() {
        // Given
        final String actualUserHandle = "Alice";
        final String actualMessageContent = "I love the weather today";
        final Date datePosted = new Date();
        final Post post = createPost(actualUserHandle, actualMessageContent, datePosted);
        // When
        final Date asAtDate = add(2, TimeUnit.SECONDS, datePosted);
        String formattedPost = timelineFormatter.format(post, asAtDate);
        // Then
        final String expectedTimeSincePosting = "2 seconds ago";
        final String expectedFormattedText = String.format("%s (%s)", actualMessageContent, expectedTimeSincePosting);
        assertEquals(expectedFormattedText, formattedPost);
    }

    @Test
    public void recentUserPostAsWallTimelineFormattedCorrectly() {
        // Given
        final String actualUserHandle = "Alice";
        final String actualMessageContent = "I love the weather today";
        final Date datePosted = new Date();
        final Post post = createPost(actualUserHandle, actualMessageContent, datePosted);
        // When
        final Date asAtDate = add(2, TimeUnit.SECONDS, datePosted);
        String formattedPost = wallTimelineFormatter.format(post, asAtDate);
        // Then
        final String expectedTimeSincePosting = "2 seconds ago";
        final String expectedFormattedText = String.format("%s - %s (%s)", actualUserHandle, actualMessageContent, expectedTimeSincePosting);
        assertEquals(expectedFormattedText, formattedPost);
    }

    private Post createPost(String userHandle, String messageContent, Date dateCreated) {
        return new Post(userHandle, messageContent, dateCreated);
    }
}
