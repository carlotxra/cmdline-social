package za.co.codurance.social.ui.console;

import org.junit.Test;
import za.co.codurance.social.actions.ReadUserWall;
import za.co.codurance.social.actions.ViewWallMessage;
import za.co.codurance.social.ui.view.TimelineFormatter;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

/**
 */
public class ReadUserWallInputGestureTest {

    @Test
    public void invalidInputIsIgnoredForViewingUserWallInputHandler() throws Exception {
        // Given
        final Display mockDisplay = createMock(Display.class);
        final TimelineFormatter timelineFormatter = createMock(TimelineFormatter.class);
        final ReadUserWall readUserWall = createMock(ReadUserWall.class);
        final InputHandler inputHandler = new ViewWallInputHandler(readUserWall, timelineFormatter);

        replay(readUserWall);
        // When
        final String invalidInput = "zoo -> a";
        inputHandler.handle(invalidInput, mockDisplay);
        // Then
    }

    @Test
    public void viewWallRequestIsServiced() throws Exception {
        // Given
        final Display mockDisplay = createMock(Display.class);
        final TimelineFormatter timelineFormatter = createMock(TimelineFormatter.class);
        final ReadUserWall readUserWall = createMock(ReadUserWall.class);
        final InputHandler inputHandler = new ViewWallInputHandler(readUserWall, timelineFormatter);

        final String expectedUserHandle = "Alice";
        final ViewWallMessage expectedMessage = new ViewWallMessage(expectedUserHandle);
        expect(readUserWall.execute(expectedMessage)).andStubReturn(null);

        replay(readUserWall);
        // When
        final String input = expectedUserHandle + " wall";
        inputHandler.handle(input, mockDisplay);
        // Then
        verify(readUserWall);
    }
}
