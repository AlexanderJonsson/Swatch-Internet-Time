package se.alexanderjonsson;

import java.time.LocalTime;
import java.time.ZoneId;

public final class InternetTime {
    public static Integer getCurrentTimeAsInteger() {
        long currentTimeInMillis = getCurrentTimeInZurichAsSeconds();
        double swatchTime = (double) currentTimeInMillis / 86400 * 1000;
        return (int) Math.round(swatchTime);
    }

    public static Double getCurrentTimeAsDouble() {
        long currentTimeInMillis = getCurrentTimeInZurichAsSeconds();
        return (double) currentTimeInMillis / 86400 * 1000;
    }

    public static String getCurrentTimeAsString(TimeFormat format) {
        switch (format){
            case WITH_DECIMALS: return String.format("%06.2f",getCurrentTimeAsDouble()).replace(",",".");
            case WITHOUT_DECIMALS: return String.format("%03d",getCurrentTimeAsInteger()).replace(",",".");
            case WITHOUT_DECIMALS_WITHOUT_LEADING_ZEROES: return String.format("%s",getCurrentTimeAsInteger()).replace(",",".");
            case DECIMALS_WITHOUT_LEADING_ZEROES: return String.format("%.2f",getCurrentTimeAsDouble()).replace(",",".");
            default: return null;
        }
    }
    public static String getCurrentTimeAsString() {
        return getCurrentTimeAsString(TimeFormat.WITH_DECIMALS);
    }
    private static long getCurrentTimeInZurichAsSeconds() {
        ZoneId timezone = ZoneId.of("Europe/Zurich");
        LocalTime now = LocalTime.now(timezone);
        LocalTime midnight = LocalTime.MIDNIGHT;
        return now.toSecondOfDay() - midnight.toSecondOfDay();
    }

    @Override
    public String toString() {
        return String.format("@%s .beats", getCurrentTimeAsString(TimeFormat.WITH_DECIMALS));
    }
}
