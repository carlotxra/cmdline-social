package za.co.codurance.social.actions;

import za.co.codurance.social.model.follow.FollowService;

/**
 * Responsible for coordinating a request to add a new follower for a user.
 * <br/>Collaborate with domain {@link FollowService} to action the request.
 */
public class AddNewFollower {
    private FollowService followService;

    public AddNewFollower(FollowService followService) {
        this.followService = followService;
    }

    public void execute(AddNewFollowerMessage addNewFollowerMessage) {
        final String userHandle = getUserHandle(addNewFollowerMessage);
        final String newFollowerHandle = getNewFollowerHandle(addNewFollowerMessage);
        addNewFollower(userHandle, newFollowerHandle);
    }

    private void addNewFollower(String userHandle, String newFollowerHandle) {
        getFollowService().addNewFollower(userHandle, newFollowerHandle);
    }

    private String getNewFollowerHandle(AddNewFollowerMessage addNewFollowerMessage) {
        return addNewFollowerMessage.getNewFollowerHandle();
    }

    private String getUserHandle(AddNewFollowerMessage addNewFollowerMessage) {
        return addNewFollowerMessage.getUserHandle();
    }

    private FollowService getFollowService() {
        return followService;
    }
}
