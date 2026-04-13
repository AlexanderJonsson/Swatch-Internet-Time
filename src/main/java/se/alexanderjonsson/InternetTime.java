package se.alexanderjonsson;

import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.Objects;

public final class InternetTime {
    private static final double BEATS_PER_DAY = 1_000d;
    private static final double NANOSECONDS_PER_DAY = 86_400_000_000_000d;
    private static final ZoneOffset BIEL_MEAN_TIME_OFFSET = ZoneOffset.ofHours(1);

    private InternetTime() {}

    /**
     * This method returns the current time in Swatch .beats as an integer.
     *
     * @return an Integer representing the current time in .beats
     */
    public static Integer getCurrentTimeAsInteger() {
        return (int) Math.floor(getCurrentTimeAsDouble());
    }

    /**
     * This method returns the current time in Swatch .beats as a Double.
     *
     * @return a Double representing the current time in .beats
     */
    public static Double getCurrentTimeAsDouble() {
        return toSwatchTime(getCurrentTimeInBielMeanTime());
    }

    /**
     * This method returns the current time in Swatch .beats as a String.
     * The string can be formatted by supplying the desired TimeFormat enum
     *
     * @param format select the desired TimeFormat enum to pick how the string is formatted
     * @return a string formatted according to the chosen parameter.
     */
    public static String getCurrentTimeAsString(TimeFormat format) {
        Objects.requireNonNull(format);
        switch (format) {
            case WITH_CENTIBEATS:
                return String.format("%06.2f", getCurrentTimeAsDouble()).replace(",", ".");
            case WITHOUT_CENTIBEATS:
                return String.format("%03d", getCurrentTimeAsInteger()).replace(",", ".");
            case WITHOUT_CENTIBEATS_WITHOUT_LEADING_ZEROES:
                return String.format("%s", getCurrentTimeAsInteger()).replace(",", ".");
            case CENTIBEATS_WITHOUT_LEADING_ZEROES:
                return String.format("%.2f", getCurrentTimeAsDouble()).replace(",", ".");
            default:
                throw new AssertionError("Unhandled TimeFormat: " + format);
        }
    }

    static double toSwatchTime(LocalTime currentTime) {
        Objects.requireNonNull(currentTime);
        return currentTime.toNanoOfDay() / NANOSECONDS_PER_DAY * BEATS_PER_DAY;
    }

    /**
     * This method overloads getCurrentTimeAsString(TimeFormat format)
     *
     * @return a String representing the current time in .beats
     */
    public static String getCurrentTimeAsString() {
        return getCurrentTimeAsString(TimeFormat.WITH_CENTIBEATS);
    }

    /**
     * This method returns the current time in Biel Mean Time (UTC+01:00), which is
     * the fixed reference used for Swatch Internet Time.
     *
     * @return the current Biel Mean Time
     */
    private static LocalTime getCurrentTimeInBielMeanTime() {
        return LocalTime.now(BIEL_MEAN_TIME_OFFSET);
    }

    /**
     * @return a pretty string representation of the current time in .beats.
     */
    public static String getCurrentTimePrettyString() {
        return String.format("@%s .beats", getCurrentTimeAsString(TimeFormat.WITH_CENTIBEATS));
    }
}
