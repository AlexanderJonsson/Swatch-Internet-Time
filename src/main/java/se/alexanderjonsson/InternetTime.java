package se.alexanderjonsson;

import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public final class InternetTime {
    /**
     * This method returns the current time in Swatch .beats as an integer derived from the
     * following formula: (currentTimeInSeconds / 86400 * 1000).
     *
     * @return an Integer representing the current time in .beats
     */
    public static Integer getCurrentTimeAsInteger() {
        long currentTimeInSeconds = getCurrentTimeInZurichAsSeconds();
        double swatchTime = (double) currentTimeInSeconds / 86400 * 1000;
        return (int) Math.round(swatchTime);
    }

    /**
     * This method returns the current time in Swatch .beats as a Double derived from the
     * following formula: (currentTimeInSeconds / 86400 * 1000).
     *
     * @return a Double representing the current time in .beats
     */
    public static Double getCurrentTimeAsDouble() {
        long currentTimeInSeconds = getCurrentTimeInZurichAsSeconds();
        return (double) currentTimeInSeconds / 86400 * 1000;
    }

    /**
     * This method returns the current time in Swatch .beats as a String derived from the
     * following formula: (currentTimeInSeconds / 86400 * 1000).
     * The string can be formatted by supplying the desired TimeFormat enum
     *
     * @param format select the desired TimeFormat enum to pick how the string is formatted
     * @return a string formatted according to the chosen parameter.
     */
    public static String getCurrentTimeAsString(TimeFormat format) {
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
                return getCurrentTimeAsString();
        }
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
     * This method returns the current time in Zurich as number of seconds since midnight in order to be used in the Swatch Internet Time calculations
     *
     * @return the number of seconds since midnight in Zurich
     */
    private static long getCurrentTimeInZurichAsSeconds() {
        ZoneId timezone = ZoneId.of("Europe/Zurich");
        ZonedDateTime now = ZonedDateTime.now(timezone);
        boolean isDST = now.getZone().getRules().isDaylightSavings(now.toInstant());

        if (isDST) {
            ZoneOffset offset = ZoneOffset.of("+01:00"); // Set the offset to +01:00 (Zurich time without DST)
            now = now.withZoneSameInstant(offset); // Convert to Zurich time without DST


        } else {
            now = now.withZoneSameInstant(timezone); // Convert to non-DST time zone
        }

        LocalTime localNow = now.toLocalTime();
        LocalTime midnight = LocalTime.MIDNIGHT;
        return localNow.toSecondOfDay() - midnight.toSecondOfDay();
    }

    /**
     * @return a pretty string representation of the current time in .beats.
     */
    @Override
    public String toString() {
        return String.format("@%s .beats", getCurrentTimeAsString(TimeFormat.WITH_CENTIBEATS));
    }
}
