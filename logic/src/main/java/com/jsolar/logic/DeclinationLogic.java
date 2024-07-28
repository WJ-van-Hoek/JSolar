package com.jsolar.logic;

import java.time.LocalDateTime;

/**
 * Provides methods for calculating the solar declination and Julian Day from a given date and time.
 * <p>
 * The solar declination is the angle between the rays of the Sun and the plane of the Earth's equator. This class uses
 * astronomical constants and algorithms to compute the solar declination based on a given {@link LocalDateTime} object
 * representing a specific date and time.
 * </p>
 * <p>
 * The Julian Day is a continuous count of days since the start of the Julian Period, used in astronomy for date
 * calculations. The methods in this class convert calendar dates to Julian Days and then use these values to compute
 * the solar declination.
 * </p>
 */
public final class DeclinationLogic {

    /**
     * Private constructor to prevent instantiation of this utility class.
     * <p>
     * Since this class only contains static methods and should not be instantiated, the constructor is private.
     * </p>
     */
    private DeclinationLogic() {
    }

    /**
     * Calculates the solar declination for the given date and time.
     * <p>
     * The solar declination is determined based on the Julian Day calculation, which is then used to compute the mean
     * longitude, mean anomaly, ecliptic longitude, and obliquity of the ecliptic. The result is the angle of the Sun
     * relative to the Earth's equatorial plane.
     * </p>
     *
     * @param dateTime a {@link LocalDateTime} object representing the date and time for which the solar declination is
     * to be calculated.
     * @return the solar declination in degrees.
     */
    public static double getSolarDeclination(final LocalDateTime dateTime) {
        // Calculate Julian Day
        final double julianDay = getJulianDay(dateTime);

        final double centuriesSinceJ2000 = calculateCenturiesSinceJ2000(julianDay);

        // Mean longitude of the Sun
        final double meanLongitude = calculateMeanLongitude(centuriesSinceJ2000);

        // Mean anomaly of the Sun
        final double meanAnomaly = calculateMeanAnomaly(centuriesSinceJ2000);

        // Ecliptic longitude of the Sun
        final double eclipticLongitude = calculateEclipticLongitude(meanLongitude, meanAnomaly);

        // Obliquity of the ecliptic
        final double obliquity = calculateObliquity(centuriesSinceJ2000);

        // Declination angle
        return calculateDeclination(eclipticLongitude, obliquity);
    }

    /**
     * Calculates the Julian Day for the given date and time.
     * <p>
     * The Julian Day is computed from the provided {@link LocalDateTime} object using the Gregorian or Julian calendar
     * corrections as necessary. The Julian Day is used in astronomical calculations to represent the continuous count
     * of days since the start of the Julian Period.
     * </p>
     *
     * @param dateTime a {@link LocalDateTime} object representing the date and time for which the Julian Day is to be
     * calculated.
     * @return the Julian Day as a double.
     */
    public static double getJulianDay(final LocalDateTime dateTime) {
        int year = dateTime.getYear();
        int month = dateTime.getMonthValue(); // getMonthValue() returns 1 for January, 2 for February, etc.
        final int day = dateTime.getDayOfMonth();
        final int hour = dateTime.getHour();
        final int minute = dateTime.getMinute();
        final int second = dateTime.getSecond();

        // If the month is January or February, treat it as the 13th or 14th month of the previous year
        if (month <= CelestialConstants.MONTHS_BEFORE_MARCH) {
            year--;
            month += CelestialConstants.MONTH_ADJUSTMENT;
        }

        final int calendarCorrection = calculateCalendarCorrection(year);

        return calculateJulianDay(year, month, day, hour, minute, second, calendarCorrection);
    }

    /**
     * Calculates the solar declination based on the ecliptic longitude and the obliquity of the ecliptic.
     * <p>
     * The declination is the angle between the rays of the Sun and the plane of the Earth's equator. This method uses
     * the ecliptic longitude and the obliquity of the ecliptic to determine the declination angle.
     * </p>
     *
     * @param eclipticLongitude the ecliptic longitude of the Sun in degrees.
     * @param obliquity the obliquity of the ecliptic in degrees.
     * @return the solar declination in degrees.
     */
    private static double calculateDeclination(final double eclipticLongitude, final double obliquity) {
        return Math.toDegrees(
                Math.asin(Math.sin(Math.toRadians(obliquity)) * Math.sin(Math.toRadians(eclipticLongitude))));
    }

    /**
     * Calculates the obliquity of the ecliptic for a given number of centuries since J2000.0.
     * <p>
     * The obliquity of the ecliptic is the angle between the Earth's equatorial plane and the plane of the Earth's
     * orbit. This method computes the obliquity of the ecliptic at a specific epoch based on the number of centuries
     * since J2000.0.
     * </p>
     *
     * @param centuriesSinceJ2000 the number of centuries since the epoch J2000.0.
     * @return the obliquity of the ecliptic in degrees.
     */
    private static double calculateObliquity(final double centuriesSinceJ2000) {
        return CelestialConstants.OBLIQUITY_OF_ECLIPTIC
                - CelestialConstants.OBLIQUITY_CORRECTION * centuriesSinceJ2000 * CelestialConstants.DAYS_IN_CENTURY;
    }

    /**
     * Calculates the ecliptic longitude of the Sun based on its mean longitude and mean anomaly.
     * <p>
     * The ecliptic longitude is the angle of the Sun along the ecliptic plane. This method computes the ecliptic
     * longitude using the mean longitude of the Sun and its mean anomaly, applying appropriate corrections.
     * </p>
     *
     * @param meanLongitude the mean longitude of the Sun in degrees.
     * @param meanAnomaly the mean anomaly of the Sun in degrees.
     * @return the ecliptic longitude of the Sun in degrees.
     */
    private static double calculateEclipticLongitude(final double meanLongitude, final double meanAnomaly) {
        return meanLongitude + CelestialConstants.ECLIPTIC_LONGITUDE_CORRECTION * Math.sin(Math.toRadians(meanAnomaly))
                + CelestialConstants.ECLIPTIC_LONGITUDE_CORRECTION_2 * Math.sin(2.0 * Math.toRadians(meanAnomaly));
    }

    /**
     * Calculates the mean anomaly of the Sun for a given number of centuries since J2000.0.
     * <p>
     * The mean anomaly represents the fraction of the Sun's elliptical orbit completed since the last perihelion. This
     * method computes the mean anomaly using the number of centuries since J2000.0 and applies the appropriate
     * corrections. It ensures the mean anomaly is in the range [0, 360) degrees.
     * </p>
     *
     * @param centuriesSinceJ2000 the number of centuries since the epoch J2000.0.
     * @return the mean anomaly of the Sun in degrees.
     */
    private static double calculateMeanAnomaly(final double centuriesSinceJ2000) {
        double meanAnomaly = (CelestialConstants.MEAN_ANOMALY_AT_J2000
                + CelestialConstants.ANOMALY_DAILY_MOTION * centuriesSinceJ2000 * CelestialConstants.DAYS_IN_CENTURY)
                % CelestialConstants.FULL_CIRCLE_DEGREES;
        if (meanAnomaly < 0) {
            meanAnomaly += CelestialConstants.FULL_CIRCLE_DEGREES;
        }
        return meanAnomaly;
    }

    /**
     * Calculates the mean longitude of the Sun for a given number of centuries since J2000.0.
     * <p>
     * The mean longitude represents the position of the Sun along its orbit around the Earth, adjusted for the number
     * of centuries since the epoch J2000.0. This method computes the mean longitude using the given centuries and
     * applies the appropriate corrections to ensure it is in the range [0, 360) degrees.
     * </p>
     *
     * @param centuriesSinceJ2000 the number of centuries since the epoch J2000.0.
     * @return the mean longitude of the Sun in degrees.
     */
    private static double calculateMeanLongitude(final double centuriesSinceJ2000) {
        double meanLongitude = (CelestialConstants.MEAN_LONGITUDE_AT_J2000
                + CelestialConstants.MEAN_DAILY_MOTION * centuriesSinceJ2000 * CelestialConstants.DAYS_IN_CENTURY)
                % CelestialConstants.FULL_CIRCLE_DEGREES;
        if (meanLongitude < 0) {
            meanLongitude += CelestialConstants.FULL_CIRCLE_DEGREES;
        }
        return meanLongitude;
    }

    /**
     * Calculates the number of centuries since the epoch J2000.0 based on the provided Julian Day.
     * <p>
     * The number of centuries since J2000.0 is computed by subtracting the Julian Day base from the given Julian Day,
     * and then dividing by the number of days in a century. This value is used in various astronomical calculations to
     * determine the position of celestial objects.
     * </p>
     *
     * @param julianDay the Julian Day to convert to centuries since J2000.0.
     * @return the number of centuries since J2000.0.
     */
    private static double calculateCenturiesSinceJ2000(final double julianDay) {
        // Number of centuries since J2000.0
        return (julianDay - CelestialConstants.JULIAN_DAY_BASE) / CelestialConstants.DAYS_IN_CENTURY;
    }

    private static double calculateJulianDay(final int year, final int month, final int day, final int hour,
            final int minute, final int second, final int calendarCorrection) {
        return Math.floor(CelestialConstants.DAYS_IN_YEAR * (year + CelestialConstants.JULIAN_YEAR_OFFSET))
                + Math.floor(CelestialConstants.DAYS_IN_MONTH * (month + 1)) + day + calendarCorrection
                - CelestialConstants.JULIAN_DAY_OFFSET
                + (hour + minute / CelestialConstants.SECONDS_IN_MINUTE
                        + second / (CelestialConstants.SECONDS_IN_MINUTE * CelestialConstants.MINUTES_IN_HOUR))
                        / CelestialConstants.HOURS_IN_DAY;
    }

    private static int calculateCalendarCorrection(final int year) {
        int calendarCorrection;
        final int centuryFactor = year / CelestialConstants.CENTURY_DIVISOR;
        if (year >= CelestialConstants.GREGORIAN_CALENDAR_START) {
            // Gregorian calendar correction
            calendarCorrection = CelestialConstants.GREGORIAN_B_CORRECTION - centuryFactor
                    + centuryFactor / CelestialConstants.GREGORIAN_CENTURY_CORRECTION;
        } else {
            // No correction for Julian calendar dates
            calendarCorrection = CelestialConstants.NO_CORRECTION;
        }
        return calendarCorrection;
    }
}
