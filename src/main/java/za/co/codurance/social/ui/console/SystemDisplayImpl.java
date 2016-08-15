package za.co.codurance.social.ui.console;

import java.io.PrintStream;

/**
 *
 */
public class SystemDisplayImpl implements Display {
    @Override
    public Display show(String text) {
        getOutputStream().print(text);
        return this;
    }

    private PrintStream getOutputStream() {
        return System.out;
    }
}
