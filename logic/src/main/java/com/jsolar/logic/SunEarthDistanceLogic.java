package com.jsolar.logic;

import java.time.LocalDateTime;

/**
 * The {@code SunEarthDistance} class provides methods to calculate the distance between the Earth and the Sun on any
 * given date. The calculations are based on the Earth's orbital parameters and its elliptical orbit around the Sun.
 *
 * <p>
 * The main functionality includes determining the day of the year, calculating the mean anomaly for a specific date,
 * and computing the actual distance based on these values.
 * </p>
 *
 * <p>
 * This class utilizes constants defined in the {@link CelestialConstants} class to perform its calculations.
 * </p>
 */
public final class SunEarthDistanceLogic {
    /**
     * Private constructor to prevent instantiation of this utility class.
     * <p>
     * Since this class only contains static methods and should not be instantiated, the constructor is private. This
     * ensures that instances of this class cannot be created, adhering to the utility class design pattern.
     * </p>
     */
    private SunEarthDistanceLogic() {
        // Prevent instantiation
    }

    /**
     * Calculates the relative distance between the Earth and the Sun for a given date to the mean.
     *
     * @param dateTime the given dateTime for which to calculate the distance
     * @return the relative to the mean distance from the Earth to the Sun
     */
    public static double getRelativeEarthSunDistance(final LocalDateTime dateTime) {
        return getAbsoluteEarthSunDistance(dateTime) / CelestialConstants.ASTRONOMICAL_UNIT;
    }

    /**
     * Calculates the distance between the Earth and the Sun for a given date.
     *
     * @param dateTime the given dateTime for which to calculate the distance
     * @return the distance from the Earth to the Sun in kilometers
     */
    public static double getAbsoluteEarthSunDistance(final LocalDateTime dateTime) {
        final double meanAnomaly = calculateMeanAnomaly(dateTime.getDayOfYear());
        return calculateSunEarthDistance(meanAnomaly);
    }

    /**
     * Calculates the Earth-Sun distance based on the given mean anomaly.
     *
     * <p>
     * This method uses a simplified formula that accounts for the Earth's orbital eccentricity and the effects of the
     * second harmonic component on the mean anomaly.
     * </p>
     *
     * @param meanAnomaly the mean anomaly in radians
     * @return the Earth-Sun distance in kilometers
     */
    private static double calculateSunEarthDistance(final double meanAnomaly) {
        return (CelestialConstants.MEAN_DISTANCE_MULTIPLIER - CelestialConstants.ECCENTRICITY * Math.cos(meanAnomaly)
                - CelestialConstants.COSINE_FIRST_HARMONIC_COEFFICIENT * Math.cos(2 * meanAnomaly))
                * CelestialConstants.ASTRONOMICAL_UNIT;
    }

    /**
     * Calculates the mean anomaly of the Earth's orbit for a given day of the year.
     *
     * <p>
     * The mean anomaly represents the angular distance from the perihelion (the point in the orbit closest to the Sun)
     * to a hypothetical point moving at a uniform speed around the orbit.
     * </p>
     *
     * @param dayOfYear the day of the year (1-based)
     * @return the mean anomaly in radians
     */
    private static double calculateMeanAnomaly(final int dayOfYear) {
        final double meanAnomalyDeg = CelestialConstants.MEAN_ANOMALY_PERIHELION_DEG
                + (CelestialConstants.FULL_CIRCLE_DEGREES / CelestialConstants.DAYS_IN_YEAR) * dayOfYear;
        return Math.toRadians(meanAnomalyDeg % CelestialConstants.FULL_CIRCLE_DEGREES);
    }
}
