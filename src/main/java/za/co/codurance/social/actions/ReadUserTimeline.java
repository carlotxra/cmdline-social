package za.co.codurance.social.actions;

import za.co.codurance.social.model.timeline.Timeline;
import za.co.codurance.social.model.timeline.TimelineService;

/**
 * Responsible for coordinating a request to read a user's timeline.
 * <br/>Collaborate with domain {@link TimelineService} to action the request.
 */
public class ReadUserTimeline {
    private TimelineService timelineService;

    public ReadUserTimeline(TimelineService timelineService) {
        this.timelineService = timelineService;
    }

    public Timeline execute(ViewTimelineMessage aMessage) {
        final String userHandle = getUserHandleFrom(aMessage);
        return readTimeLineFor(userHandle);
    }

    private Timeline readTimeLineFor(String userHandle) {
        return getTimelineService().readTimelineFor(userHandle);
    }

    private String getUserHandleFrom(ViewTimelineMessage aMessage) {
        return aMessage.getUserHandle();
    }

    private TimelineService getTimelineService() {
        return timelineService;
    }
}
