package za.co.codurance.social.model.follow;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Model of a users set of followers.
 */
public class FollowersLink {
    private String aUserHandle;

    private Set<String> followers = new HashSet<>();

    public FollowersLink(String aUserHandle, Set<String> followers) {
        this.aUserHandle = aUserHandle;
        this.followers = followers;
    }

    public boolean isFollowedBy(String aUserHandle) {
        return getFollowers().contains(aUserHandle);
    }

    public Collection<String> followers() {
        return getFollowers();
    }

    private Set<String> getFollowers() {
        return followers;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("FollowersLink{");
        sb.append("aUserHandle='").append(aUserHandle).append('\'');
        sb.append(", followers=").append(followers);
        sb.append(", llowers=").append(followers());
        sb.append('}');
        return sb.toString();
    }
}
