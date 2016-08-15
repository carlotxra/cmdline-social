package za.co.codurance.social.actions;

import java.util.Date;
import java.util.Objects;

public class PostMessage {
    private String userHandle;

    private String message;

    private Date datePosted;

    public PostMessage(String userHandle, String message, Date datePosted) {
        this.userHandle = userHandle;
        this.message = message;
        this.datePosted = datePosted;
    }

    public String getUserHandle() {
        return userHandle;
    }

    public String getMessage() {
        return message;
    }

    public Date getDatePosted() {
        return datePosted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PostMessage)) {
            return false;
        }
        PostMessage that = (PostMessage) o;
        return Objects.equals(getUserHandle(), that.getUserHandle()) &&
                Objects.equals(getMessage(), that.getMessage()) &&
                Objects.equals(getDatePosted(), that.getDatePosted());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserHandle(), getMessage(), getDatePosted());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PostMessage{");
        sb.append("userHandle='").append(userHandle).append('\'');
        sb.append(", message='").append(message).append('\'');
        sb.append(", datePosted=").append(datePosted);
        sb.append('}');
        return sb.toString();
    }

}
