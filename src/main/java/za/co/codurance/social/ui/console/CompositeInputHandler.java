package za.co.codurance.social.ui.console;

import java.util.ArrayList;
import java.util.Collection;

/**
 * This composite handler holds all the {@link InputHandler}s to service a set of inputs.
 * A default handler can be registered to process any requests that can not be processed by the
 * configured handlers.
 */
public class CompositeInputHandler implements InputHandler {
    private Collection<InputHandler> inputHandlers;

    private InputHandler defaultHandler;

    /**
     * @param inputHandlers  to handle user input
     * @param defaultHandler to handle any input that is not understood by inputHandlers
     */
    public CompositeInputHandler(Collection<InputHandler> inputHandlers, InputHandler defaultHandler) {
        this.inputHandlers = inputHandlers;
        this.defaultHandler = defaultHandler;
    }

    @Override
    public boolean handle(String input, Display display) {
        for (InputHandler inputHandler : getInputHandlers()) {
            final boolean wasHandled = inputHandler.handle(input, display);
            if (wasHandled) {
                return true;
            }
        }

        return processDefaultHandler(input, display);
    }

    private boolean processDefaultHandler(String input, Display display) {
        return getDefaultHandler().handle(input, display);
    }

    private Collection<InputHandler> getInputHandlers() {
        if (inputHandlers == null) {
            inputHandlers = new ArrayList<>();
        }
        return inputHandlers;
    }

    private InputHandler getDefaultHandler() {
        return defaultHandler;
    }
}
