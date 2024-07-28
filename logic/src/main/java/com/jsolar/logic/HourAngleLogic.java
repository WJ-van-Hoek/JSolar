package com.jsolar.logic;

import java.time.LocalDateTime;
import java.time.ZoneOffset;


/**
 * Utility class for calculating the Hour Angle of a celestial body given the UTC time and longitude.
 * The Hour Angle is derived from Local Solar Time (LST), which is adjusted based on the observer's longitude.
 * <p>
 * This class provides static methods to perform these calculations. It is designed to be used without instantiating
 * the class, and hence has a private constructor.
 * </p>
 */
public final class HourAngleLogic {

    /**
     * The factor used to convert longitude to time in hours.
     * This is derived from dividing 360 degrees of the Earth by 24 hours (i.e., 15 degrees per hour).
     */
    private static final double LONGITUDE_CONVERSION_FACTOR = 15.0;

    /**
     * The reference Local Solar Time (LST) value used for calculating Hour Angle.
     * It represents the solar time corresponding to 12:00 noon.
     */
    private static final double SOLAR_TIME_REF = 12.0;

    /**
     * Private constructor to prevent instantiation of this utility class.
     * <p>
     * Since this class only contains static methods and should not be instantiated, the constructor is private.
     * </p>
     */
    private HourAngleLogic() {
    }

    /**
     * Gets the Hour Angle (H) given UTC time and longitude.
     *
     * @param utcTime LocalDateTime object representing the UTC time
     * @param longitude Longitude in degrees
     * @return Hour Angle (H) in degrees
     */
    public static double getHourAngle(final LocalDateTime utcTime, final double longitude) {
        final double lst = convertUtcToLst(utcTime, longitude);
        return calculateHourAngle(lst);
    }

    /**
     * Converts UTC time to Local Solar Time (LST).
     *
     * @param utcTime LocalDateTime object representing the UTC time
     * @param longitude Longitude in degrees
     * @return Local Solar Time (LST) in hours
     */
    private static double convertUtcToLst(final LocalDateTime utcTime, final double longitude) {
        // Convert UTC time to minutes since midnight
        final long minutesSinceMidnight = utcTime.toEpochSecond(ZoneOffset.UTC) / CelestialConstants.MINUTES_IN_HOUR
                % CelestialConstants.MINUTES_IN_DAY;

        // Convert minutes to hours
        final double hoursSinceMidnight = minutesSinceMidnight / 60.0;

        // Calculate the LST
        return hoursSinceMidnight + (longitude / LONGITUDE_CONVERSION_FACTOR);
    }

    /**
     * Calculates the Hour Angle (H) based on Local Solar Time (LST).
     *
     * @param lst Local Solar Time (LST) in hours
     * @return Hour Angle (H) in degrees
     */
    private static double calculateHourAngle(final double lst) {
        return LONGITUDE_CONVERSION_FACTOR * (lst - SOLAR_TIME_REF);
    }

}
