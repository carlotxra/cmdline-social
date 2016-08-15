package za.co.codurance.social.model.post;

/**
 * Domain service responsible for publishing posts.
 * <br/>Collaborate with {@link PostsRepository} for storage of posts.
 */
public class PostingServiceImpl implements PostingService {
    private PostsRepository postsRepository;

    public PostingServiceImpl(PostsRepository postsRepository) {
        this.postsRepository = postsRepository;
    }

    public void publish(Post post) {
        getPostsRepository().add(post);
    }

    private PostsRepository getPostsRepository() {
        return postsRepository;
    }
}
