package za.co.codurance.social.ui.console;

import org.junit.Test;
import za.co.codurance.social.ui.controller.InputController;
import za.co.codurance.social.ui.controller.SystemInputControllerImpl;

import java.io.BufferedReader;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

/**
 */
public class ConsoleTest {

    @Test
    public void inputHandlerReceivesConsoleInput() throws Exception {
        // Given
        final InputHandler inputHandler = createMock(InputHandler.class);
        final Display display = createMock(Display.class);
        final BufferedReader inputReader = createMock(BufferedReader.class);
        final InputController systemInputController = new SystemInputControllerImpl(inputHandler, display, inputReader);

        final String input = "exit";
        expect(inputReader.readLine()).andReturn(input);
        expect(inputHandler.handle(input, display)).andReturn(true);
        replay(inputHandler, inputReader);
        // When
        systemInputController.readNextLine();
        // Then
        verify(inputHandler, inputReader);
    }
}
