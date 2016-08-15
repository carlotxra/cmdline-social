package za.co.codurance.social.model.follow;

/**
 * Domain service entry point for 'following' behaviour.
 * <br/>Currently I provide the ability to add a new follower {@link #addNewFollower(String, String)}
 */
public interface FollowService {
    /**
     * <em>post-condition: A new follower is added to a user identified by userHandle (even if user does not exist yet)</em>
     */
    void addNewFollower(String userHandle, String newFollowerHandle);
}
