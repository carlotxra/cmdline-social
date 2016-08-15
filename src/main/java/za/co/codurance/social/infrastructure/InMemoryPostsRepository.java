package za.co.codurance.social.infrastructure;

import za.co.codurance.social.model.post.Post;
import za.co.codurance.social.model.post.PostsRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class InMemoryPostsRepository implements PostsRepository {
    private Map<String, List<Post>> postsHolder = new HashMap<>();

    @Override
    public List<Post> findAll(String userHandle) {
        return getPostsHolder().getOrDefault(userHandle, Collections.emptyList());
    }

    /**
     * Adds a post to the repo for a user (even if a user does not exist).
     */
    @Override
    public void add(Post post) {
        final String userHandle = post.getUserHandle();
        List<Post> usersPosts = getPosts(userHandle);
        usersPosts.add(post);
    }

    private List<Post> getPosts(String userHandle) {
        List<Post> usersPosts = getPostsHolder().get(userHandle);
        if (usersPosts == null) {
            usersPosts = new ArrayList<>();
            getPostsHolder().put(userHandle, usersPosts);
        }
        return usersPosts;
    }

    private Map<String, List<Post>> getPostsHolder() {
        return postsHolder;
    }
}
