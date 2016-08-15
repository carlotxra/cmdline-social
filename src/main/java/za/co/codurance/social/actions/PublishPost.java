package za.co.codurance.social.actions;

import za.co.codurance.social.model.post.Post;
import za.co.codurance.social.model.post.PostingService;

import java.util.Date;

/**
 * Responsible for coordinating a request to post a user's message .
 * <br/>Collaborate with domain {@link PostingService} to action the request.
 */
public class PublishPost {
    private PostingService postingService;

    public PublishPost(PostingService postingService) {
        this.postingService = postingService;
    }

    public void execute(PostMessage postMessage) {
        final Post post = createPostFrom(postMessage);
        publish(post);
    }

    private void publish(Post post) {
        getPostingService().publish(post);
    }

    private Post createPostFrom(PostMessage aPostMessage) {
        final String userHandle = aPostMessage.getUserHandle();
        final String message = aPostMessage.getMessage();
        final Date dateCreated = aPostMessage.getDatePosted();

        return new Post(userHandle, message, dateCreated);
    }

    private PostingService getPostingService() {
        return postingService;
    }
}
