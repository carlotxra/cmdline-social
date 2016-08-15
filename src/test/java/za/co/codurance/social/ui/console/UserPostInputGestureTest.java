package za.co.codurance.social.ui.console;

import org.easymock.IMockBuilder;
import org.junit.Test;
import za.co.codurance.social.actions.PostMessage;
import za.co.codurance.social.actions.PublishPost;

import java.util.Date;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.createMockBuilder;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

/**
 */
public class UserPostInputGestureTest {

    @Test
    public void invalidInputIsIgnoredForUserPostInputHandler() throws Exception {
        // Given
        final Display mockDisplay = createMock(Display.class);
        final PublishPost publishPost = createMock(PublishPost.class);
        final InputHandler inputHandler = new PostInputHandler(publishPost);

        replay(publishPost);
        // When
        final String invalidInput = "Alice wall";
        inputHandler.handle(invalidInput, mockDisplay);
        // Then
    }

    @Test
    public void userPostRequestIsServiced() throws Exception {
        // Given
        final Display mockDisplay = createMock(Display.class);
        final PublishPost publishPost = createMock(PublishPost.class);
        final IMockBuilder<PostInputHandler> mockBuilder = createMockBuilder(PostInputHandler.class).withConstructor(publishPost);
        final PostInputHandler inputHandler = mockBuilder.addMockedMethod("createDatePostedTimestamp").createMock();
        final Date expectedDatePosted = new Date();
        expect(inputHandler.createDatePostedTimestamp()).andReturn(expectedDatePosted);

        final String expectedUserHandle = "Alice";
        final String expectedContent = "Weather is great";
        final PostMessage expectedMessage = new PostMessage(expectedUserHandle, expectedContent, expectedDatePosted);
        publishPost.execute(expectedMessage);

        replay(publishPost, inputHandler);
        // When
        final String input = expectedUserHandle + " -> " + expectedContent;
        inputHandler.handle(input, mockDisplay);
        // Then
        verify(publishPost, inputHandler);
    }
}
