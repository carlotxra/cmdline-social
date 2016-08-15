package za.co.codurance.social.model.timeline;

import za.co.codurance.social.model.post.Post;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Model a timeline of {@link Post}s for a user.
 * <br/>{@link Post}s are sorted by most recently date posted.
 */
public class Timeline {
    private static final PostComparator postComparator = new PostComparator();

    private List<Post> posts = new ArrayList<>();

    public Timeline(Collection<Post> posts) {
        this.posts.addAll(posts);
        Collections.sort(this.posts, postComparator.reversed());
    }

    public boolean isEmpty() {
        return getPosts().isEmpty();
    }

    /**
     * Answer a sorted list of entries (sorted by most recently posted)
     *
     * @see za.co.codurance.social.model.timeline.Timeline.PostComparator
     */
    public List<Post> entries() {
        return getPosts();
    }

    private List<Post> getPosts() {
        return posts;
    }

    private static class PostComparator implements Comparator<Post> {
        @Override
        public int compare(Post o1, Post o2) {
            return o1.getDateCreated().compareTo(o2.getDateCreated());
        }
    }
}
