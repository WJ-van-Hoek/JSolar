package com.jsolar.logic;

import java.time.LocalDateTime;

/**
 * Utility class for calculating solar radiation based on various inputs such as latitude, longitude, and time.
 * <p>
 * The class provides static methods to compute the solar radiation received at a particular location and time, taking
 * into account the atmospheric conditions and the solar constant. It follows the utility class design pattern, meaning
 * it is not intended to be instantiated and only contains static methods for its functionality.
 * </p>
 * <p>
 * The calculations involve:
 * <ul>
 * <li>Calculating the air mass, which is the amount of atmosphere the solar radiation passes through.</li>
 * <li>Determining the extraterrestrial radiation, which is the solar radiation at the outer edge of the Earth's
 * atmosphere.</li>
 * <li>Applying the exponential decay model to account for atmospheric attenuation of the solar radiation.</li>
 * </ul>
 * </p>
 */
public final class SolarRadiationLogic {

    /**
     * Private constructor to prevent instantiation of this utility class.
     * <p>
     * Since this class only contains static methods and should not be instantiated, the constructor is private. This
     * ensures that instances of this class cannot be created, adhering to the utility class design pattern.
     * </p>
     */
    private SolarRadiationLogic() {
        // Prevent instantiation
    }

    /**
     * Calculates the solar radiation at a given location and time.
     * <p>
     * This method computes the solar radiation by first determining the air mass, then calculating the extraterrestrial
     * radiation, and finally applying the exponential decay formula to account for atmospheric effects.
     * </p>
     *
     * @param latitude The latitude of the location (in degrees). Values range from -90 to 90.
     * @param longitude The longitude of the location (in degrees). Values range from -180 to 180.
     * @param utc The current time in UTC. This is used to compute the extraterrestrial radiation and air mass.
     * @param tau The optical thickness of the atmosphere, which represents the extent of atmospheric attenuation.
     * @return The calculated solar radiation (in watts per square meter) at the specified location and time.
     * @throws IllegalArgumentException if the latitude is not within the range [-90, 90] or if the longitude is not
     * within the range [-180, 180].
     */
    public static double getSolarRadiation(final double latitude, final double longitude, final LocalDateTime utc,
            final double tau) {
        if (latitude < GeographicConstants.MINIMAL_LATITUDE || latitude > GeographicConstants.MAXIMAL_LATITUDE) {
            throw new IllegalArgumentException("Latitude must be between -90 and 90 degrees.");
        }
        if (longitude < GeographicConstants.MINIMAL_LONGITUDE || longitude > GeographicConstants.MAXIMAL_LONGITUDE) {
            throw new IllegalArgumentException("Longitude must be between -180 and 180 degrees.");
        }

        final double airMass = AirMassLogic.calculateAirMass(latitude, longitude, utc);
        final double solarConstant = calculateExtraTerrestrialRadiation(utc);
        return calculateSolarRadiation(airMass, solarConstant, tau);
    }

    /**
     * Calculates the solar radiation based on air mass, solar constant, and atmospheric optical thickness.
     * <p>
     * This method uses the exponential decay model to compute the solar radiation after accounting for atmospheric
     * attenuation.
     * </p>
     *
     * @param airMass The air mass through which the solar radiation passes.
     * @param solarConstant The extraterrestrial solar radiation constant.
     * @param tau The optical thickness of the atmosphere.
     * @return The solar radiation after applying atmospheric attenuation.
     */
    private static double calculateSolarRadiation(final double airMass, final double solarConstant, final double tau) {
        return solarConstant * Math.exp(-tau * airMass);
    }

    /**
     * Calculates the extraterrestrial radiation based on the time of the year.
     * <p>
     * This method determines the amount of solar radiation received outside the Earth's atmosphere based on the
     * relative distance between the Earth and the Sun, which varies throughout the year.
     * </p>
     *
     * @param utc The current time in UTC, used to compute the relative Earth-Sun distance.
     * @return The extraterrestrial solar radiation constant (in watts per square meter).
     */
    private static double calculateExtraTerrestrialRadiation(final LocalDateTime utc) {
        final double relativeDistanceEarthSun = SunEarthDistanceLogic.getRelativeEarthSunDistance(utc);
        return CelestialConstants.I_0 * relativeDistanceEarthSun;
    }
}
