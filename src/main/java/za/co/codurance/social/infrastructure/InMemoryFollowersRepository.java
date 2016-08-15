package za.co.codurance.social.infrastructure;

import za.co.codurance.social.model.follow.FollowersLink;
import za.co.codurance.social.model.follow.FollowersRepository;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 */
public class InMemoryFollowersRepository implements FollowersRepository {

    private Map<String, Set<String>> followers = new HashMap<>();

    public InMemoryFollowersRepository() {
    }

    @Override
    public FollowersLink findLinkFor(String aUserHandle) {
        final Set<String> followerHandles = getFollowersOf(aUserHandle);
        return createFollowersLink(aUserHandle, followerHandles);
    }

    /**
     * Add a follower to a user.
     * <br/>
     * <em>Impl note: Users do not have to exist for mapping to be created.</em>
     */
    @Override
    public void addFollower(String aUserHandle, String newFollowerHandle) {
        Set<String> followers = getFollowers().get(aUserHandle);
        if (followers == null) {
            followers = new HashSet<>();
            getFollowers().put(aUserHandle, followers);
        }

        followers.add(newFollowerHandle);
    }

    private FollowersLink createFollowersLink(String aUserHandle, Set<String> followerHandles) {
        return new FollowersLink(aUserHandle, followerHandles);
    }

    private Set<String> getFollowersOf(String aUserHandle) {
        return getFollowers().getOrDefault(aUserHandle, Collections.emptySet());
    }

    /**
     * Followers are mapped using a user handle key to a collection of followers.
     */
    private Map<String, Set<String>> getFollowers() {
        return followers;
    }
}
