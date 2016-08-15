package za.co.codurance.social.model.follow;

/**
 * Domain service to add new follower.
 * Collaborate with {@link FollowersRepository} for any storage requirements.
 */
public class FollowServiceImpl implements FollowService {
    private FollowersRepository followersRepository;

    public FollowServiceImpl(FollowersRepository followersRepository) {
        this.followersRepository = followersRepository;
    }

    @Override
    public void addNewFollower(String userHandle, String newFollowerHandle) {
        getFollowersRepository().addFollower(userHandle, newFollowerHandle);
    }

    private FollowersRepository getFollowersRepository() {
        return followersRepository;
    }
}
