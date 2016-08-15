package za.co.codurance.social.ui.console;

import za.co.codurance.social.actions.AddNewFollower;
import za.co.codurance.social.actions.PublishPost;
import za.co.codurance.social.actions.ReadUserTimeline;
import za.co.codurance.social.actions.ReadUserWall;
import za.co.codurance.social.infrastructure.InMemoryFollowersRepository;
import za.co.codurance.social.infrastructure.InMemoryPostsRepository;
import za.co.codurance.social.model.follow.FollowServiceImpl;
import za.co.codurance.social.model.post.PostingService;
import za.co.codurance.social.model.post.PostingServiceImpl;
import za.co.codurance.social.model.post.PostsRepository;
import za.co.codurance.social.model.timeline.TimelineServiceImpl;
import za.co.codurance.social.ui.controller.InputController;
import za.co.codurance.social.ui.controller.SystemInputControllerImpl;
import za.co.codurance.social.ui.view.DurationFormatterImpl;
import za.co.codurance.social.ui.view.TimelineFormatter;
import za.co.codurance.social.ui.view.TimelineFormatterImpl;
import za.co.codurance.social.ui.view.WallTimelineFormatterImpl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collection;

/**
 * Entry point for the app. Wires in all collaborators for the console version.
 * <br/>Users interact with the app via the command line.
 * <pre>Commands:
 *  To post something for user Alice: Alice -> The weather is great!
 *  To view Alice's timeline:               Alice
 *  To follow Bob:                              Alice follows Bob
 *  To view Alice's wall:                     Alice wall
 * </pre>
 */
public class App {

    public static void main(String[] args) {
        App app = new App();
        Console console = app.setupApp();
        console.start();
    }

    private Console setupApp() {
        final InputController inputController = createInputController();
        return new Console(inputController);
    }

    private InputController createInputController() {
        final InputHandler inputHandler = createInputHandler();
        final Display display = new SystemDisplayImpl();
        final BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
        return new SystemInputControllerImpl(inputHandler, display, inputReader);
    }

    private InputHandler createInputHandler() {
        final Collection<InputHandler> inputHandlers = createInputHandlers();
        final InputHandler defaultHandler = new UnhandledInputHandler();
        return new CompositeInputHandler(inputHandlers, defaultHandler);
    }

    private Collection<InputHandler> createInputHandlers() {
        final PostsRepository postsRepository = new InMemoryPostsRepository();
        final InMemoryFollowersRepository followersRepository = new InMemoryFollowersRepository();
        final TimelineServiceImpl timelineService = new TimelineServiceImpl(postsRepository, followersRepository);

        final ViewTimelineInputHandler viewTimelineInputHandler = createViewTimelineInputHandler(timelineService);
        final ViewWallInputHandler viewWallInputHandler = createViewWallInputHandler(timelineService);
        final PostInputHandler postInputHandler = createPostInputHandler(postsRepository);
        final CreateFollowerInputHandler createFollowerInputHandler = createCreateFollowerInputHandler(followersRepository);

        final ExitInputHandler exitInputHandler = new ExitInputHandler();

        return Arrays.asList(exitInputHandler,
                postInputHandler,
                viewTimelineInputHandler,
                viewWallInputHandler,
                createFollowerInputHandler);
    }

    private CreateFollowerInputHandler createCreateFollowerInputHandler(InMemoryFollowersRepository followersRepository) {
        final FollowServiceImpl followService = new FollowServiceImpl(followersRepository);
        final AddNewFollower addNewFollowerAction = new AddNewFollower(followService);
        return new CreateFollowerInputHandler(addNewFollowerAction);
    }

    private ViewWallInputHandler createViewWallInputHandler(TimelineServiceImpl timelineService) {
        final ReadUserWall readUserWallAction = new ReadUserWall(timelineService);
        final TimelineFormatter timelineFormatter = createWallTimelineFormatter();
        return new ViewWallInputHandler(readUserWallAction, timelineFormatter);
    }

    private TimelineFormatter createWallTimelineFormatter() {
        final DurationFormatterImpl durationFormatter = new DurationFormatterImpl();
        return new WallTimelineFormatterImpl(durationFormatter);
    }

    private ViewTimelineInputHandler createViewTimelineInputHandler(TimelineServiceImpl timelineService) {
        final ReadUserTimeline readUserTimelineAction = new ReadUserTimeline(timelineService);
        final TimelineFormatter timelineFormatter = createTimelineFormatter();
        return new ViewTimelineInputHandler(readUserTimelineAction, timelineFormatter);
    }

    private TimelineFormatter createTimelineFormatter() {
        final DurationFormatterImpl durationFormatter = new DurationFormatterImpl();
        return new TimelineFormatterImpl(durationFormatter);
    }

    private PostInputHandler createPostInputHandler(PostsRepository postsRepository) {
        final PostingService postingService = new PostingServiceImpl(postsRepository);
        final PublishPost publishPostAction = new PublishPost(postingService);
        return new PostInputHandler(publishPostAction);
    }
}
