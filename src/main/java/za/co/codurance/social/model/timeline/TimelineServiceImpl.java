package za.co.codurance.social.model.timeline;

import za.co.codurance.social.model.follow.FollowersLink;
import za.co.codurance.social.model.follow.FollowersRepository;
import za.co.codurance.social.model.post.Post;
import za.co.codurance.social.model.post.PostsRepository;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Domain service responsible for interacting with a user's timeline.
 * <br/>Collaborate with {@link PostsRepository} for accessing {@link Post}s
 * and {@link FollowersRepository} to access a users followers ({@link FollowersLink})
 */
public class TimelineServiceImpl implements TimelineService {
    private PostsRepository postsRepository;

    private FollowersRepository followersRepository;

    public TimelineServiceImpl(PostsRepository postsRepository, FollowersRepository followersRepository) {
        this.postsRepository = postsRepository;
        this.followersRepository = followersRepository;
    }

    @Override
    public Timeline readTimelineFor(String aUserHandle) {
        final List<Post> posts = getPostsFor(aUserHandle);
        return new Timeline(posts);
    }

    @Override
    public Timeline readWallTimelineFor(String aUserHandle) {
        final Set<Post> allPosts = getWallPosts(aUserHandle);
        return new Timeline(allPosts);
    }

    private Set<Post> getWallPosts(String aUserHandle) {
        final Collection<Post> usersPosts = readTimelineFor(aUserHandle).entries();
        final Collection<Post> allFollowersPost = readPostsForAllFollowers(aUserHandle);

        final Set<Post> allPosts = new HashSet<>();
        allPosts.addAll(usersPosts);
        allPosts.addAll(allFollowersPost);
        return allPosts;
    }

    private Set<Post> readPostsForAllFollowers(String aUserHandle) {
        final FollowersLink linkForUser = getFollowersRepository().findLinkFor(aUserHandle);
        final Collection<String> followers = linkForUser.followers();
        return followers.stream().
                flatMap(s -> readTimelineFor(s).entries().stream()).
                collect(Collectors.toSet());
    }

    private List<Post> getPostsFor(String aUserHandle) {
        return getPostsRepository().findAll(aUserHandle);
    }

    private PostsRepository getPostsRepository() {
        return postsRepository;
    }

    private FollowersRepository getFollowersRepository() {
        return followersRepository;
    }
}
