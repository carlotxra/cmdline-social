package za.co.codurance.social.ui.view;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.EnumMap;
import java.util.List;

/**
 * I am responsible for formatting a date relative to another.
 * <br/>
 * <pre>Examples:
 * Under a minute: 'X seconds ago'
 * Over a minute but less than an hour: 'X minutes ago'
 * Over an hour ago but less than a day: 'X hours ago'
 * Over a day ago: 'X days ago'
 * </pre>
 */
public class DurationFormatterImpl implements DurationFormatter {
    private static final String MOMENT_AGO_DURATION_TEXT = "a moment ago";

    private EnumMap<ChronoUnit, String> unitToTextDictionary = new EnumMap<ChronoUnit, String>(ChronoUnit.class) {
        {
            put(ChronoUnit.DAYS, "day");
            put(ChronoUnit.HOURS, "hour");
            put(ChronoUnit.MINUTES, "minute");
            put(ChronoUnit.SECONDS, "second");
        }
    };

    private List<ChronoUnit> units;

    @Override
    public String format(Date time, Date asAtDate) {
        return formatDurationBetween(time, asAtDate);
    }

    private String formatDurationBetween(Date fromDate, Date toDate) {
        final Instant fromInstant = fromDate.toInstant();
        final Instant toInstant = toDate.toInstant();
        for (ChronoUnit unit : getUnitsInDescendingOrder()) {
            long duration = unit.between(fromInstant, toInstant);
            final boolean hasAtLeastOneUnit = duration > 0;
            if (hasAtLeastOneUnit) {
                final String unitText = determineUnitText(unit);
                return format(duration, unitText);
            }
        }

        return MOMENT_AGO_DURATION_TEXT;
    }

    private String determineUnitText(ChronoUnit durationUnit) {
        return unitToTextDictionary.getOrDefault(durationUnit, MOMENT_AGO_DURATION_TEXT);
    }

    private List<ChronoUnit> getUnitsInDescendingOrder() {
        if (units == null) {
            List<ChronoUnit> allUnits = new ArrayList<>(unitToTextDictionary.keySet());
            Collections.sort(allUnits);
            Collections.reverse(allUnits);
            units = allUnits;
        }
        return units;
    }

    private String format(long duration, String unitText) {
        final String pluralSuffix = determinePluralSuffix(duration);
        return String.format("%s %s%s ago", duration, unitText, pluralSuffix);
    }

    private String determinePluralSuffix(long duration) {
        final boolean durationIsZeroOrOne = duration == 0 || duration > 1;
        return durationIsZeroOrOne ? "s" : "";
    }

}
