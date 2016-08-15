package za.co.codurance.social.ui.view;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 */
public abstract class BaseFormatterTest {

    protected Date add(long duration, TimeUnit durationTimeUnit, Date aDate) {
        final int durationAsSeconds = (int) durationTimeUnit.toSeconds(duration);
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(aDate);
        calendar.add(Calendar.SECOND, durationAsSeconds);
        return calendar.getTime();
    }
}
