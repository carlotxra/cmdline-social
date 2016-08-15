package za.co.codurance.social.actions;

import org.junit.Before;
import org.junit.Test;
import za.co.codurance.social.infrastructure.InMemoryFollowersRepository;
import za.co.codurance.social.infrastructure.InMemoryPostsRepository;
import za.co.codurance.social.model.follow.FollowService;
import za.co.codurance.social.model.follow.FollowServiceImpl;
import za.co.codurance.social.model.follow.FollowersRepository;
import za.co.codurance.social.model.post.Post;
import za.co.codurance.social.model.post.PostingService;
import za.co.codurance.social.model.post.PostingServiceImpl;
import za.co.codurance.social.model.post.PostsRepository;
import za.co.codurance.social.model.timeline.Timeline;
import za.co.codurance.social.model.timeline.TimelineService;
import za.co.codurance.social.model.timeline.TimelineServiceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 */
public class WallTest {
    private FollowService followService;

    private PostingService postingService;

    private TimelineService timelineService;

    @Before
    public void setUp() {
        final FollowersRepository followersRepository = new InMemoryFollowersRepository();
        final PostsRepository postsRepository = new InMemoryPostsRepository();
        followService = new FollowServiceImpl(followersRepository);
        postingService = new PostingServiceImpl(postsRepository);
        timelineService = new TimelineServiceImpl(postsRepository, followersRepository);
    }

    @Test
    public void wallForUserWithOneFollowerShouldDisplayBothUsersPosts() throws Exception {
        // Given
        final String firstUserHandle = "Alice";
        final String followerOfFirstUser = "Charlie";
        final Post firstUsersPost = createPost(firstUserHandle, "", createDateFrom("20160101"));
        final Post secondUsersPost = createPost(followerOfFirstUser, "", createDateFrom("20160201"));
        publishPost(firstUsersPost);
        publishPost(secondUsersPost);
        follow(firstUserHandle, followerOfFirstUser);
        // When
        Timeline timeline = timelineService.readWallTimelineFor(firstUserHandle);
        // Then
        assertFalse(timeline.isEmpty());
        final List<Post> entries = timeline.entries();
        assertEquals(2, entries.size());
        assertEquals(secondUsersPost, entries.get(0));
        assertEquals(firstUsersPost, entries.get(1));
    }

    @Test
    public void checkWallHasOrderedPosts() throws Exception {
        // Given
        final String firstUserHandle = "Alice";
        final String followerOfFirstUser = "Charlie";
        final Post firstUsersPost = createPost(firstUserHandle, "", createDateFrom("20160601"));
        final Post secondUsersPost = createPost(followerOfFirstUser, "", createDateFrom("20160101"));
        publishPost(firstUsersPost);
        publishPost(secondUsersPost);
        follow(firstUserHandle, followerOfFirstUser);
        // When
        Timeline timeline = timelineService.readWallTimelineFor(firstUserHandle);
        // Then
        assertFalse(timeline.isEmpty());
        final List<Post> entries = timeline.entries();
        assertEquals(2, entries.size());
        assertEquals(firstUsersPost, entries.get(0));
        assertEquals(secondUsersPost, entries.get(1));
    }

    private void publishPost(Post firstUsersPost) {
        postingService.publish(firstUsersPost);
    }

    private Post createPost(String userHandle, String messageContent, Date dateCreated) {
        return new Post(userHandle, messageContent, dateCreated);
    }

    private Date createDateFrom(String yyyyMMdd) throws ParseException {
        return new SimpleDateFormat("yyyyMMdd").parse(yyyyMMdd);
    }

    private void follow(String firstUserHandle, String followerOfFirstUser) {
        final AddNewFollowerMessage addNewFollowerMessage = new AddNewFollowerMessage(firstUserHandle, followerOfFirstUser);
        followService.addNewFollower(addNewFollowerMessage.getUserHandle(), addNewFollowerMessage.getNewFollowerHandle());
    }
}
