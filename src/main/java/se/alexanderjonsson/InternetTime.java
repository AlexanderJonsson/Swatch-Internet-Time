package se.alexanderjonsson;

import java.text.DecimalFormat;
import java.time.LocalTime;
import java.time.ZoneId;

public class InternetTime {
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
        String decimalFormattedString;
        if (format == TimeFormat.WITH_DECIMALS) {
            decimalFormattedString = new DecimalFormat("#.##").format(getCurrentTimeAsDouble());
        } else {
            decimalFormattedString = new DecimalFormat("#").format(getCurrentTimeAsDouble());
        }
        return decimalFormattedString.replace(",", ".");
    }

    private static long getCurrentTimeInZurichAsSeconds() {
        ZoneId timezone = ZoneId.of("Europe/Zurich");
        LocalTime now = LocalTime.now(timezone);
        LocalTime midnight = LocalTime.MIDNIGHT;
        return now.toSecondOfDay() - midnight.toSecondOfDay();
    }

    @Override
    public String toString() {
        return String.format("@%s.beats", getCurrentTimeAsString(TimeFormat.WITH_DECIMALS));
    }
}
