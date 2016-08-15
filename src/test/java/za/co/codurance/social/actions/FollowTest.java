package za.co.codurance.social.actions;

import org.junit.Before;
import org.junit.Test;
import za.co.codurance.social.infrastructure.InMemoryFollowersRepository;
import za.co.codurance.social.model.follow.FollowersRepository;
import za.co.codurance.social.model.follow.FollowService;
import za.co.codurance.social.model.follow.FollowServiceImpl;
import za.co.codurance.social.model.follow.FollowersLink;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 */
public class FollowTest {
    private FollowService followService;

    private FollowersRepository followersRepository;

    @Before
    public void setUp() {
        followersRepository = new InMemoryFollowersRepository();
        followService = new FollowServiceImpl(followersRepository);
    }

    @Test
    public void userCanFollowAnotherUser() {
        // Given
        final String firstUserHandle = "Alice";
        final String followerOfFirstUser = "Charlie";
        final AddNewFollowerMessage addNewFollowerMessage = new AddNewFollowerMessage(firstUserHandle, followerOfFirstUser);
        // When
        followService.addNewFollower(addNewFollowerMessage.getUserHandle(), addNewFollowerMessage.getNewFollowerHandle());
        // Then
        FollowersLink followersLink = followersRepository.findLinkFor(firstUserHandle);
        final String expectUserFollowsAnotherUser = String.format("Expect [%s] to follow [%s]", followerOfFirstUser, firstUserHandle);
        assertTrue(expectUserFollowsAnotherUser, followersLink.isFollowedBy(followerOfFirstUser));
        assertEquals("Expect only one follower", 1, followersLink.followers().size());
        assertFalse("Do not expect the follower to be fallowed", followersLink.isFollowedBy(firstUserHandle));
    }

    @Test
    public void multipleUsersCanFollowAnotherUser() {
        // Given
        final String firstUserHandle = "Alice";
        final String firstFollowerOfFirstUser = "Bob";
        final String secondFollowerOfFirstUser = "Charlie";
        final AddNewFollowerMessage firstFollowLinkMessage = new AddNewFollowerMessage(firstUserHandle, firstFollowerOfFirstUser);
        final AddNewFollowerMessage secondFollowLinkMessage = new AddNewFollowerMessage(firstUserHandle, secondFollowerOfFirstUser);
        // When
        followService.addNewFollower(firstFollowLinkMessage.getUserHandle(), firstFollowLinkMessage.getNewFollowerHandle());
        followService.addNewFollower(secondFollowLinkMessage.getUserHandle(), secondFollowLinkMessage.getNewFollowerHandle());
        // Then
        final FollowersLink followersLink = followersRepository.findLinkFor(firstUserHandle);
        final List<String> allFollowersHandles = Arrays.asList(firstFollowerOfFirstUser, secondFollowerOfFirstUser);
        final boolean allFollowersAreAccountedFor = allFollowersHandles.containsAll(followersLink.followers());
        assertTrue("Expect all followers to be accounted for", allFollowersAreAccountedFor);
    }

}
