package za.co.codurance.social.ui.console;

import org.junit.Test;
import za.co.codurance.social.actions.ViewTimelineMessage;
import za.co.codurance.social.actions.ReadUserTimeline;
import za.co.codurance.social.ui.view.TimelineFormatter;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

/**
 */
public class ReadUserTimelineInputGestureTest {

    @Test
    public void invalidInputIsIgnoredForViewingUserInputHandler() throws Exception {
        // Given
        final Display mockDisplay = createMock(Display.class);
        final TimelineFormatter timelineFormatter = createMock(TimelineFormatter.class);
        final ReadUserTimeline readUserTimeline = createMock(ReadUserTimeline.class);
        final InputHandler viewTimelineInputHandler = new ViewTimelineInputHandler(readUserTimeline, timelineFormatter);

        replay(readUserTimeline);
        // When
        final String invalidInput = "zoo -> a";
        viewTimelineInputHandler.handle(invalidInput, mockDisplay);
        // Then
    }

    @Test
    public void viewTimelineRequestIsServiced() throws Exception {
        // Given
        final Display mockDisplay = createMock(Display.class);
        final TimelineFormatter mockTimelineFormatter = createMock(TimelineFormatter.class);
        final ReadUserTimeline readUserTimeline = createMock(ReadUserTimeline.class);
        final InputHandler inputHandler = new ViewTimelineInputHandler(readUserTimeline, mockTimelineFormatter);

        final String expectedUserHandle = "Alice";
        final ViewTimelineMessage expectedMessage = new ViewTimelineMessage(expectedUserHandle);
        expect(readUserTimeline.execute(expectedMessage)).andStubReturn(null);

        replay(readUserTimeline);
        // When
        final String input = expectedUserHandle;
        inputHandler.handle(input, mockDisplay);
        // Then
        verify(readUserTimeline);
    }
}
