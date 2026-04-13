# Swatch Internet Time Java Library

This library provides a small Java API for reading the current Swatch Internet Time in `.beats`.

Swatch Internet Time divides a day into 1,000 beats, where each beat is 86.4 seconds. The library uses Biel Mean Time (`UTC+01:00`), the fixed reference timezone used by Swatch Internet Time.

## Usage

Import the library classes:

```java
import se.alexanderjonsson.InternetTime;
import se.alexanderjonsson.TimeFormat;
```

Read the current Internet Time in a few different formats:

```java
// Integer beat value, for example 123
int swatchTimeInt = InternetTime.getCurrentTimeAsInteger();

// Beat value with centibeat precision, for example 123.45
double swatchTimeDouble = InternetTime.getCurrentTimeAsDouble();

// Default string format: WITH_CENTIBEATS, for example "123.45"
String swatchTimeDefault = InternetTime.getCurrentTimeAsString();

// Explicit string formats
String withCentibeats = InternetTime.getCurrentTimeAsString(TimeFormat.WITH_CENTIBEATS); // "123.45"
String withoutCentibeats = InternetTime.getCurrentTimeAsString(TimeFormat.WITHOUT_CENTIBEATS); // "123"
String noLeadingZeroesWithCentibeats =
        InternetTime.getCurrentTimeAsString(TimeFormat.CENTIBEATS_WITHOUT_LEADING_ZEROES); // "3.45"
String noLeadingZeroesWithoutCentibeats =
        InternetTime.getCurrentTimeAsString(TimeFormat.WITHOUT_CENTIBEATS_WITHOUT_LEADING_ZEROES); // "3"

// Pretty string representation, for example "@123.45 .beats"
String pretty = InternetTime.getCurrentTimePrettyString();
```

## Available Formats

```java
public enum TimeFormat {
    WITH_CENTIBEATS,
    WITHOUT_CENTIBEATS,
    CENTIBEATS_WITHOUT_LEADING_ZEROES,
    WITHOUT_CENTIBEATS_WITHOUT_LEADING_ZEROES
}
```

`InternetTime.getCurrentTimeAsString()` defaults to `TimeFormat.WITH_CENTIBEATS`.
