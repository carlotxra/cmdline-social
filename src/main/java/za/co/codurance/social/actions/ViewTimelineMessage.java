package za.co.codurance.social.actions;

import java.util.Objects;

/**
 *
 */
public class ViewTimelineMessage {
    private String userHandle;

    public ViewTimelineMessage(String userHandle) {
        this.userHandle = userHandle;
    }

    public String getUserHandle() {
        return userHandle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ViewTimelineMessage)) {
            return false;
        }
        ViewTimelineMessage that = (ViewTimelineMessage) o;
        return Objects.equals(getUserHandle(), that.getUserHandle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserHandle());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ViewTimelineMessage{");
        sb.append("userHandle='").append(userHandle).append('\'');
        sb.append('}');
        return sb.toString();
    }

}
