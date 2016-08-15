package za.co.codurance.social.ui.console;

import org.junit.Test;
import za.co.codurance.social.actions.AddNewFollower;
import za.co.codurance.social.actions.AddNewFollowerMessage;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

/**
 */
public class FollowInputGestureTest {

    @Test
    public void invalidInputIsIgnoredForFollowerInputHandler() throws Exception {
        // Given
        final AddNewFollower addNewFollower = createMock(AddNewFollower.class);
        CreateFollowerInputHandler createFollowerInputHandler = new CreateFollowerInputHandler(addNewFollower);
        final Display display = createMock(Display.class);
        replay(addNewFollower, display);
        // When
        final String invalidInput = "zoo";
        createFollowerInputHandler.handle(invalidInput, display);
        // Then
        verify(addNewFollower, display);
    }

    @Test
    public void validFollowRequestIsServiced() throws Exception {
        // Given
        final Display mockDisplay = createMock(Display.class);
        final AddNewFollower addNewFollower = createMock(AddNewFollower.class);
        final AddNewFollowerMessage expectedFollowMessage = new AddNewFollowerMessage("Alice", "Bob");
        addNewFollower.execute(expectedFollowMessage);
        final CreateFollowerInputHandler createFollowerInputHandler = new CreateFollowerInputHandler(addNewFollower);
        replay(addNewFollower);
        // When
        final String input = "Alice follows Bob";
        createFollowerInputHandler.handle(input, mockDisplay);
        // Then
        verify(addNewFollower);
    }

}
