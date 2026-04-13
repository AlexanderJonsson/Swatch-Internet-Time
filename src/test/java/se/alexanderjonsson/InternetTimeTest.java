package se.alexanderjonsson;
import java.time.LocalTime;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class InternetTimeTest {
    @Test
    public void toSwatchTimeUsesSubSecondPrecision() {
        double beats = InternetTime.toSwatchTime(LocalTime.of(0, 0, 0, 864_000_000));
        assertEquals(0.01d, beats, 1.0e-9);
    }
    @Test
    public void toSwatchTimeMapsNoonToHalfTheDay() {
        double beats = InternetTime.toSwatchTime(LocalTime.NOON);
        assertEquals(500.0d, beats,0);
    }
}
