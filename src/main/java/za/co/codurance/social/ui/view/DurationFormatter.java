package za.co.codurance.social.ui.view;

import java.util.Date;

/**
 * I am responsible for formatting a timestamp based on when it occurred relative to another date.
 */
public interface DurationFormatter {
    String format(Date time, Date asAtDate);
}
