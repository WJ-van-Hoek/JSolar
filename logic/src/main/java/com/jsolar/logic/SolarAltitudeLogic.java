package com.jsolar.logic;

/**
 * Provides utility methods for calculating solar altitude angles based on solar zenith angles.
 * <p>
 * The solar altitude angle is a key parameter in solar position calculations and represents the angle between the Sun
 * and the horizontal plane at a given location and time. This class provides methods to compute the solar altitude
 * angle given the solar zenith angle.
 * </p>
 */
public final class SolarAltitudeLogic {

    /**
     * Private constructor to prevent instantiation of this utility class.
     * <p>
     * Since this class only contains static methods and should not be instantiated, the constructor is private.
     * </p>
     */
    private SolarAltitudeLogic() {
    }

    /**
     * Calculates the solar altitude angle based on the provided solar zenith angle.
     * <p>
     * The solar altitude angle is determined using the formula:
     *
     * <pre>
     * α = 90° - θz
     * </pre>
     *
     * where α is the solar altitude angle, and θz is the solar zenith angle. The solar zenith angle represents the
     * angle between the Sun and the zenith (the point directly overhead), and the solar altitude angle is the
     * complement of this angle.
     * </p>
     * <p>
     * This method uses predefined constants to validate the input and perform the calculation:
     * <ul>
     * <li>{@link CelestialConstants#MIN_ZENITH_ANGLE} (0.0 degrees) - Minimum valid zenith angle.</li>
     * <li>{@link CelestialConstants#MAX_ZENITH_ANGLE} (180.0 degrees) - Maximum valid zenith angle.</li>
     * <li>{@link CelestialConstants#SOLAR_ALTITUDE_REFERENCE_ANGLE} (90.0 degrees) - Reference angle used in the
     * calculation.</li>
     * </ul>
     * </p>
     *
     * @param thetaZ The solar zenith angle in degrees. This value must be within the range of
     * {@link CelestialConstants#MIN_ZENITH_ANGLE} (0.0 degrees) and {@link CelestialConstants#MAX_ZENITH_ANGLE} (180.0
     * degrees). A zenith angle of 0 degrees corresponds to the Sun being directly overhead, while a zenith angle of 180
     * degrees corresponds to the Sun being directly below the observer.
     * @return The solar altitude angle in degrees. This value is calculated as the difference between
     * {@link CelestialConstants#SOLAR_ALTITUDE_REFERENCE_ANGLE} and the provided zenith angle. The solar altitude angle
     * represents how high the Sun is above the horizon.
     * @throws IllegalArgumentException if the provided solar zenith angle is outside the valid range, i.e., less than
     * {@link CelestialConstants#MIN_ZENITH_ANGLE} or greater than {@link CelestialConstants#MAX_ZENITH_ANGLE}. This
     * ensures that the input is within the physically meaningful range for solar zenith angles.
     */
    public static double calculateSolarAltitude(final double thetaZ) {
        // Check if thetaZ is within a valid range for a zenith angle
        if (thetaZ < CelestialConstants.MIN_ZENITH_ANGLE || thetaZ > CelestialConstants.MAX_ZENITH_ANGLE) {
            throw new IllegalArgumentException(
                    String.format("Solar zenith angle must be between %.1f and %.1f degrees.",
                            CelestialConstants.MIN_ZENITH_ANGLE, CelestialConstants.MAX_ZENITH_ANGLE));
        }

        // Calculate the solar altitude angle
        return CelestialConstants.SOLAR_ALTITUDE_REFERENCE_ANGLE - thetaZ;
    }
}
