package za.co.codurance.social.actions;

import org.junit.Before;
import org.junit.Test;
import za.co.codurance.social.infrastructure.InMemoryFollowersRepository;
import za.co.codurance.social.infrastructure.InMemoryPostsRepository;
import za.co.codurance.social.model.post.Post;
import za.co.codurance.social.model.post.PostingService;
import za.co.codurance.social.model.post.PostingServiceImpl;
import za.co.codurance.social.model.post.PostsRepository;
import za.co.codurance.social.model.timeline.Timeline;
import za.co.codurance.social.model.timeline.TimelineServiceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 */
public class ReadTimelineTest {
    private PostingService postingService;

    private TimelineServiceImpl timelineService;

    @Before
    public void createPostService() {
        final PostsRepository postsRepository = new InMemoryPostsRepository();
        final InMemoryFollowersRepository followLinkRepository = new InMemoryFollowersRepository();
        postingService = new PostingServiceImpl(postsRepository);
        timelineService = new TimelineServiceImpl(postsRepository, followLinkRepository);
    }

    @Test
    public void userThatDoesNotExistHasEmptyTimeline() {
        // Given
        final String userHandle = "Alice";
        // When
        Timeline timeline = timelineService.readTimelineFor(userHandle);
        // Then
        assertTrue(timeline.isEmpty());
    }

    @Test
    public void userWithMessageInTimelineHasMatchingTimeline() {
        // Given
        final String userHandle = "Alice";
        final String messageContent = "I love the weather today";
        final Date datePosted = new Date();
        // When
        publishPost(userHandle, messageContent, datePosted);
        Timeline timeline = timelineService.readTimelineFor(userHandle);
        // Then
        assertFalse(timeline.isEmpty());
        final Collection<Post> entries = timeline.entries();
        assertEquals(1, entries.size());
        final Post expectedPost = createPost(userHandle, messageContent, datePosted);
        assertEquals(expectedPost, entries.iterator().next());
    }

    @Test
    public void timelineEntriesAreSortedByMostRecent() throws Exception {
        // Given
        final String userHandle = "Alice";
        final String messageContent = "I love the weather today";
        final Date earliestDatePosted = createDateFrom("20160101");
        final Date laterPostingDate = createDateFrom("20160102");
        publishPost(userHandle, messageContent, laterPostingDate);
        publishPost(userHandle, messageContent, earliestDatePosted);
        // When
        Timeline timeline = timelineService.readTimelineFor(userHandle);
        // Then
        assertFalse(timeline.isEmpty());
        final Collection<Post> entries = timeline.entries();
        assertEquals(2, entries.size());
        final Post expectedPost = createPost(userHandle, messageContent, laterPostingDate);
        assertEquals(expectedPost, entries.iterator().next());
    }

    private Date createDateFrom(String yyyyMMdd) throws ParseException {
        return new SimpleDateFormat("yyyyMMdd").parse(yyyyMMdd);
    }

    private Post createPost(String userHandle, String messageContent, Date dateCreated) {
        return new Post(userHandle, messageContent, dateCreated);
    }

    private void publishPost(String userHandle, String messageContent, Date dateCreated) {
        final Post post = createPost(userHandle, messageContent, dateCreated);
        postingService.publish(post);
    }
}
