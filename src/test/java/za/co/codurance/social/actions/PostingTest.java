package za.co.codurance.social.actions;

import org.junit.Before;
import org.junit.Test;
import za.co.codurance.social.infrastructure.InMemoryPostsRepository;
import za.co.codurance.social.model.post.Post;
import za.co.codurance.social.model.post.PostingService;
import za.co.codurance.social.model.post.PostingServiceImpl;
import za.co.codurance.social.model.post.PostsRepository;

import java.util.Date;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;

/**
 */
public class PostingTest {
    private PostsRepository postsRepository;

    private PostingService postingService;

    @Before
    public void setUp() {
        postsRepository = new InMemoryPostsRepository();
        postingService = new PostingServiceImpl(postsRepository);
    }

    @Test
    public void userCanPublishMessageToEmptyTimeline() {
        // Given
        final String actualUserHandle = "Alice";
        final String actualMessageContent = "I love the weather today";
        final Post post = createPost(actualUserHandle, actualMessageContent, new Date());
        assertEquals(0, postsRepository.findAll(actualUserHandle).size());
        // When
        postingService.publish(post);
        // Then
        List<Post> posts = postsRepository.findAll(actualUserHandle);
        assertEquals("Expect only 1 actualMessageContent to be posted", 1, posts.size());
        Post posted = posts.get(0);
        assertEquals("Expect actualMessageContent to be posted by correct user", actualUserHandle, posted.getUserHandle());
        assertEquals("Expect actualMessageContent to have correct content", actualMessageContent, posted.getMessage());
    }

    @Test
    public void userCanPublishMultipleMessages() {
        // Given
        final String actualUserHandle = "Alice";
        final String actualMessageContent = "I love the weather today";
        final int numberOfTimesToPost = new Random().nextInt(25);
        final Post dummyPost = createPost(actualUserHandle, actualMessageContent, new Date());
        // When
        for (int i = 0; i < numberOfTimesToPost; i++) {
            postingService.publish(dummyPost);
        }
        // Then
        List<Post> posts = postsRepository.findAll(actualUserHandle);
        assertEquals("Expect multiple entries to be posted", numberOfTimesToPost, posts.size());
    }

    private Post createPost(String userHandle, String messageContent, Date dateCreated) {
        return new Post(userHandle, messageContent, dateCreated);
    }
}
