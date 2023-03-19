# Swatch Internet Time Java Library

This Java library provides a simple way to get the current time in Swatch Internet Time beats. Swatch Internet Time is a decimal time concept introduced by the Swatch Group in 1998, which divides the 24-hour day into 1,000 "beats", each beat lasting 1 minute and 26.4 seconds.

## Usage

To use the `InternetTime` class, first import it into your Java project:

```java
import se.alexanderjonsson.InternetTime;
```
Then, you can call one of the following static methods to get the current Swatch Internet Time:
```java
// Returns the current time as an Integer
int swatchTimeInt = InternetTime.getCurrentTimeAsInteger();

// Returns the current time as a Double with two decimal places
double swatchTimeDouble = InternetTime.getCurrentTimeAsDouble();

// Returns the current time as a formatted String
String swatchTimeStringWithDecimals = InternetTime.getCurrentTimeAsString(TimeFormat.WITH_DECIMALS); // e.g. "123.23"
String swatchTimeStringWithoutDecimals = InternetTime.getCurrentTimeAsString(TimeFormat.WITHOUT_DECIMALS); // e.g. "123"

// Returns a String representation of the current Swatch Internet Time in the format "@<time_in_beats>.beats"
String swatchTimeString = InternetTime.toString(); // e.g. "@9.23.beats"
```
Note that the getCurrentTimeAsDouble() method and the getCurrentTimeAsString() method both allow you to specify a TimeFormat enum value to control the formatting of the returned value. The TimeFormat enum has two values: WITH_DECIMALS and WITHOUT_DECIMALS. If you don't specify a TimeFormat, the default is WITH_DECIMALS.
```java
public enum TimeFormat {
WITH_DECIMALS,
WITHOUT_DECIMALS
}
```
