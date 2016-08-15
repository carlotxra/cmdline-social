package za.co.codurance.social.actions;

import za.co.codurance.social.model.timeline.Timeline;
import za.co.codurance.social.model.timeline.TimelineService;

/**
 * Responsible for coordinating a request to read a user's wall.
 * <br/>Collaborate with domain {@link TimelineService} to action the request.
 */
public class ReadUserWall {
    private TimelineService timelineService;

    public ReadUserWall(TimelineService timelineService) {
        this.timelineService = timelineService;
    }

    public Timeline execute(ViewWallMessage aMessage) {
        final String userHandle = getUserHandleFrom(aMessage);
        return readWallTimeLineFor(userHandle);
    }

    private Timeline readWallTimeLineFor(String userHandle) {
        return getTimelineService().readWallTimelineFor(userHandle);
    }

    private String getUserHandleFrom(ViewWallMessage aMessage) {
        return aMessage.getUserHandle();
    }

    private TimelineService getTimelineService() {
        return timelineService;
    }
}
