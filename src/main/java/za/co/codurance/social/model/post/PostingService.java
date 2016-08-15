package za.co.codurance.social.model.post;

/**
 * Posting domain service.
 * <br/>Currently I provide the ability to publish a users post {@link #publish(Post)}
 */
public interface PostingService {

    void publish(Post post);
}
