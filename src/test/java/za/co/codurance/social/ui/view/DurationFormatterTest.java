package za.co.codurance.social.ui.view;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

/**
 * <pre>
 * Spec:
 * Under a minute: 'X seconds ago'
 * Over a minute but less than an hour: 'X minutes ago'
 * Over an hour ago but less than a day: 'X hours ago'
 * Over a day ago: 'X days ago'
 * </pre>
 */
public class DurationFormatterTest extends BaseFormatterTest {
    private DurationFormatter durationFormatter;

    @Before
    public void setUp() {
        durationFormatter = new DurationFormatterImpl();
    }

    @Test
    public void aMomentAgoDurationFormatsCorrectly() {
        // Given
        final Date time = new Date();
        final Date asAtDate = add(2, TimeUnit.MICROSECONDS, time);
        // When
        String formattedDurationText = durationFormatter.format(time, asAtDate);
        // Then
        final String expectedTimeSincePostingText = "a moment ago";
        assertEquals(expectedTimeSincePostingText, formattedDurationText);
    }

    @Test
    public void lessThanAMinuteDurationSinceTimestampFormatsCorrectly() {
        // Given
        final Date time = new Date();
        final Date asAtDate = add(2, TimeUnit.SECONDS, time);
        // When
        String formattedDurationText = durationFormatter.format(time, asAtDate);
        // Then
        final String expectedTimeSincePostingText = "2 seconds ago";
        assertEquals(expectedTimeSincePostingText, formattedDurationText);
    }

    @Test
    public void oneMinuteDurationSinceFormatsCorrectly() {
        // Given
        final Date time = new Date();
        final Date asAtDate = add(1, TimeUnit.MINUTES, time);
        // When
        String formattedDurationText = durationFormatter.format(time, asAtDate);
        // Then
        final String expectedTimeSincePostingText = "1 minute ago";
        assertEquals(expectedTimeSincePostingText, formattedDurationText);
    }

    @Test
    public void inTheMinutesDurationSinceFormatsCorrectly() {
        // Given
        final Date time = new Date();
        final Date asAtDate = add(3, TimeUnit.MINUTES, time);
        // When
        String formattedDurationText = durationFormatter.format(time, asAtDate);
        // Then
        final String expectedTimeSincePostingText = "3 minutes ago";
        assertEquals(expectedTimeSincePostingText, formattedDurationText);
    }

    @Test
    public void inTheHoursDurationSinceFormatsCorrectly() {
        // Given
        final Date time = new Date();
        final Date asAtDate = add(4, TimeUnit.HOURS, time);
        // When
        String formattedDurationText = durationFormatter.format(time, asAtDate);
        // Then
        final String expectedTimeSincePostingText = "4 hours ago";
        assertEquals(expectedTimeSincePostingText, formattedDurationText);
    }

    @Test
    public void inTheDaysDurationAgoFormatsCorrectly() {
        // Given
        final Date time = new Date();
        final int fiveDaysAgoInHours = 5 * 24;
        final Date asAtDate = add(fiveDaysAgoInHours, TimeUnit.HOURS, time);
        // When
        String formattedDurationText = durationFormatter.format(time, asAtDate);
        // Then
        final String expectedTimeSincePostingText = "5 days ago";
        assertEquals(expectedTimeSincePostingText, formattedDurationText);
    }

    @Test
    public void overAMonthAdoDurationFormatsCorrectly() {
        // Given
        final Date time = new Date();
        final int overAMonthAgoInHours = 100 * 24;
        final Date asAtDate = add(overAMonthAgoInHours, TimeUnit.HOURS, time);
        // When
        String formattedDurationText = durationFormatter.format(time, asAtDate);
        // Then
        final String expectedTimeSincePostingText = "100 days ago";
        assertEquals(expectedTimeSincePostingText, formattedDurationText);
    }
}
