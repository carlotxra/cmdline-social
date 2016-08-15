package za.co.codurance.social.model.timeline;

/**
 * Domain service to interact with a user's timeline.
 */
public interface TimelineService {
    /**
     * Read a users timeline
     */
    Timeline readTimelineFor(String aUserHandle);

    /**
     * Read a users timeline including all postings of users that the requested user follows.
     */
    Timeline readWallTimelineFor(String aUserHandle);
}
