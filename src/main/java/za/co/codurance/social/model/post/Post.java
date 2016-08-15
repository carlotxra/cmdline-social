package za.co.codurance.social.model.post;

import java.util.Date;
import java.util.Objects;

/**
 * Model of a user's post.
 */
public class Post {
    private String userHandle;

    private String message;

    private Date dateCreated;

    public Post(String userHandle, String message, Date dateCreated) {
        this.userHandle = userHandle;
        this.message = message;
        this.dateCreated = dateCreated;
    }

    public String getUserHandle() {
        return userHandle;
    }

    public String getMessage() {
        return message;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Post)) {
            return false;
        }
        Post post = (Post) o;
        return Objects.equals(getUserHandle(), post.getUserHandle()) &&
                Objects.equals(getMessage(), post.getMessage()) &&
                Objects.equals(getDateCreated(), post.getDateCreated());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserHandle(), getMessage(), getDateCreated());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Post{");
        sb.append("userHandle='").append(userHandle).append('\'');
        sb.append(", message='").append(message).append('\'');
        sb.append(", dateCreated=").append(dateCreated);
        sb.append('}');
        return sb.toString();
    }

}
