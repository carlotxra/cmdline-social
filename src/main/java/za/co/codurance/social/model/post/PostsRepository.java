package za.co.codurance.social.model.post;

import java.util.List;

/**
 * I provide the ability to store and retrieve {@link Post}s for a user.
 */
public interface PostsRepository {
    List<Post> findAll(String userHandle);

    void add(Post post);
}
