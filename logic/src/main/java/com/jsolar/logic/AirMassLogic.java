package com.jsolar.logic;

import java.time.LocalDateTime;

/**
 * The {@code AirMass} class provides methods to calculate the air mass based on the solar zenith angle. The air mass
 * represents the relative path length that sunlight travels through the Earth's atmosphere, which varies with the angle
 * of incidence.
 * <p>
 * This class is designed as a utility class and should not be instantiated. Therefore, the constructor is private to
 * prevent instantiation.
 * </p>
 * <p>
 * The class contains a method to calculate the air mass either directly from the solar zenith angle or indirectly using
 * the latitude, longitude, and UTC time to first compute the solar zenith angle.
 * </p>
 * <p>
 * The air mass is calculated using two different models depending on the zenith angle:
 * </p>
 * <ul>
 * <li>For zenith angles less than or equal to 60 degrees, the simple geometric formula {@code 1 / cos(θ)} is used.</li>
 * <li>For zenith angles greater than 60 degrees, the Kasten and Young empirical model is applied, which includes
 * adjustments for atmospheric refraction and other effects.</li>
 * </ul>
 */
public final class AirMassLogic {

    /**
     * Private constructor to prevent instantiation of this utility class.
     * <p>
     * Since this class only contains static methods and should not be instantiated, the constructor is private. This
     * ensures that instances of this class cannot be created, adhering to the utility class design pattern.
     * </p>
     */
    private AirMassLogic() {
        // Prevent instantiation
    }

    // Constants for the Kasten and Young model
    /**
     * The maximum zenith angle (in degrees) for which the simple air mass formula {@code 1 / cos(θ)} is used. For
     * zenith angles greater than this, the Kasten and Young model is applied.
     */
    private static final double MAX_KASTEN_YOUNG_ZENITH_ANGLE = 60.0;

    /**
     * The constant term used in the Kasten and Young model for air mass calculation. This term is used in conjunction
     * with the zenith angle to compute the correction factor for air mass calculation.
     */
    private static final double KASTEN_YOUNG_TERM = 96.07995;

    /**
     * The correction factor applied in the Kasten and Young model to adjust the air mass calculation for zenith angles
     * greater than {@link #MAX_KASTEN_YOUNG_ZENITH_ANGLE}. This factor accounts for atmospheric refraction and other
     * effects not captured by the simple model.
     */
    private static final double KASTEN_YOUNG_CORRECTION_FACTOR = 0.50572;

    /**
     * The exponent used in the Kasten and Young model to calculate the air mass correction factor based on the zenith
     * angle. This exponent determines how the correction factor scales with changes in the zenith angle.
     */
    private static final double KASTEN_YOUNG_EXPONENT = -1.6364;

    // Constants for angle validation
    /**
     * The minimum valid value for the solar zenith angle, in degrees. This value represents the sun being directly
     * overhead (zenith), where the air mass is 1.
     */
    private static final double MIN_VALID_ZENITH_ANGLE = 0.0;

    /**
     * The maximum valid value for the solar zenith angle, in degrees. This value represents the sun being at the
     * horizon, where the air mass is at its maximum.
     */
    private static final double MAX_VALID_ZENITH_ANGLE = 90.0;

    /**
     * Calculates the air mass based on the given latitude, longitude, and UTC time.
     * <p>
     * This method computes the solar declination, hour angle, and solar zenith angle from the provided latitude,
     * longitude, and UTC time, and then uses the solar zenith angle to determine the air mass.
     * </p>
     *
     * @param latitude The latitude of the observer in degrees. Must be between -90 and 90.
     * @param longitude The longitude of the observer in degrees. Must be between -180 and 180.
     * @param utc The UTC time for which the air mass is to be calculated.
     * @return The air mass, a dimensionless quantity representing the path length of sunlight through the atmosphere
     * relative to the vertical path.
     */
    public static double calculateAirMass(final double latitude, final double longitude, final LocalDateTime utc) {
        final double declination = DeclinationLogic.getSolarDeclination(utc);
        final double hourAngle = HourAngleLogic.getHourAngle(utc, longitude);
        final double solarZenithAngle = SolarZenithAngleLogic.getSolarZenithAngle(latitude, declination, hourAngle);
        return calculateAirMass(solarZenithAngle);
    }

    /**
     * Calculates the air mass based on the solar zenith angle.
     * <p>
     * The air mass is a dimensionless quantity representing the relative path length that sunlight travels through the
     * Earth's atmosphere. It is defined as 1 when the sun is directly overhead and increases as the sun approaches the
     * horizon, due to the longer atmospheric path length.
     * </p>
     * <p>
     * The calculation distinguishes between two regimes:
     * </p>
     * <ul>
     * <li>For zenith angles less than or equal to {@link #MAX_KASTEN_YOUNG_ZENITH_ANGLE} degrees, a simple geometric
     * formula {@code 1 / cos(θ)} is used.</li>
     * <li>For zenith angles greater than {@link #MAX_KASTEN_YOUNG_ZENITH_ANGLE} degrees, the Kasten and Young empirical
     * model is used, which includes a correction factor for atmospheric effects.</li>
     * </ul>
     *
     * @param zenithAngle The solar zenith angle in degrees. Must be between {@link #MIN_VALID_ZENITH_ANGLE} and
     * {@link #MAX_VALID_ZENITH_ANGLE} inclusive.
     * @return The air mass, a dimensionless quantity representing the path length of sunlight through the atmosphere
     * relative to the vertical path.
     * @throws IllegalArgumentException if the zenith angle is outside the valid range ({@link #MIN_VALID_ZENITH_ANGLE}
     * to {@link #MAX_VALID_ZENITH_ANGLE} degrees).
     */
    public static double calculateAirMass(final double zenithAngle) {
        if (zenithAngle < MIN_VALID_ZENITH_ANGLE || zenithAngle > MAX_VALID_ZENITH_ANGLE) {
            throw new IllegalArgumentException("Zenith angle must be between " + MIN_VALID_ZENITH_ANGLE + " and "
                    + MAX_VALID_ZENITH_ANGLE + " degrees.");
        }

        // Convert zenith angle to radians
        final double theta = Math.toRadians(zenithAngle);

        // For zenith angles <= MAX_KASTEN_YOUNG_ZENITH_ANGLE, use the simple formula
        if (zenithAngle <= MAX_KASTEN_YOUNG_ZENITH_ANGLE) {
            return 1 / Math.cos(theta);
        }

        // For zenith angles > MAX_KASTEN_YOUNG_ZENITH_ANGLE, use the Kasten and Young model
        final double term = KASTEN_YOUNG_TERM - zenithAngle;
        final double correctionFactor = KASTEN_YOUNG_CORRECTION_FACTOR * Math.pow(term, KASTEN_YOUNG_EXPONENT);
        return 1 / (Math.cos(theta) + correctionFactor);
    }
}
