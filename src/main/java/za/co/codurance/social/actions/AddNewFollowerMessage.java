package za.co.codurance.social.actions;

import java.util.Objects;

/**
 *
 */
public class AddNewFollowerMessage {
    private String userHandle;

    private String newFollowerHandle;

    public AddNewFollowerMessage(String aUserHandle, String newFollowerHandle) {
        this.userHandle = aUserHandle;
        this.newFollowerHandle = newFollowerHandle;
    }

    public String getUserHandle() {
        return userHandle;
    }

    public String getNewFollowerHandle() {
        return newFollowerHandle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AddNewFollowerMessage)) {
            return false;
        }
        AddNewFollowerMessage that = (AddNewFollowerMessage) o;
        return Objects.equals(getUserHandle(), that.getUserHandle()) &&
                Objects.equals(getNewFollowerHandle(), that.getNewFollowerHandle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserHandle(), getNewFollowerHandle());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AddNewFollowerMessage{");
        sb.append("userHandle='").append(userHandle).append('\'');
        sb.append(", newFollowerHandle='").append(newFollowerHandle).append('\'');
        sb.append('}');
        return sb.toString();
    }

}
