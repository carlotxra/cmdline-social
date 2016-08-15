package za.co.codurance.social.actions;

import java.util.Objects;

/**
 *
 */
public class ViewWallMessage {
    private String userHandle;

    public ViewWallMessage(String userHandle) {
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
        if (!(o instanceof ViewWallMessage)) {
            return false;
        }
        ViewWallMessage that = (ViewWallMessage) o;
        return Objects.equals(getUserHandle(), that.getUserHandle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserHandle());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ViewWallMessage{");
        sb.append("userHandle='").append(userHandle).append('\'');
        sb.append('}');
        return sb.toString();
    }

}
