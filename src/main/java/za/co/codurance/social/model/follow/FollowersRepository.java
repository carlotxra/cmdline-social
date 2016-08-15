package za.co.codurance.social.model.follow;

/**
 * Provide a set of operations to add and search for followers.
 */
public interface FollowersRepository {
    FollowersLink findLinkFor(String aUserHandle);

    void addFollower(String aUserHandle, String newFollowerHandle);
}
