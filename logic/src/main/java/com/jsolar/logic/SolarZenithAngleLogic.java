package com.jsolar.logic;

/**
 * Utility class for calculating the solar zenith angle.
 * <p>
 * The solar zenith angle is the angle between the direction of the Sun and the vertical direction. It is useful in
 * various fields such as solar energy calculations, meteorology, and astronomy.
 * </p>
 * <p>
 * This class provides static methods to calculate the solar zenith angle based on the latitude, solar declination, and
 * hour angle of the Sun.
 * </p>
 * <p>
 * The class is final and cannot be instantiated. All methods are static and can be accessed directly via the class.
 * </p>
 */
public final class SolarZenithAngleLogic {

    /**
     * Private constructor to prevent instantiation of this utility class.
     * <p>
     * Since this class only contains static methods and should not be instantiated, the constructor is private.
     * </p>
     */
    private SolarZenithAngleLogic() {
    }

    /**
     * Calculates the solar zenith angle.
     *
     * @param latitude Latitude of the location in degrees. This is the angular distance of the location north or south
     * of the Equator.
     * @param declination Solar declination in degrees. This is the angle between the rays of the Sun and the plane of
     * the Earth's equator.
     * @param hourAngleDouble Hour angle in degrees. This is the measure of time since solar noon, expressed in angular
     * distance.
     * @return The solar zenith angle in degrees. This is the angle between the direction of the Sun and the vertical
     * direction at the given location and time.
     * @throws IllegalArgumentException if the latitude, declination, or hour angle are outside the valid range of
     * values.
     * @see #getSolarZenithAngle(double, double, double)
     */
    public static double getSolarZenithAngle(final double latitude, final double declination,
            final double hourAngleDouble) {
        // Convert input angles from degrees to radians
        final double phi = Math.toRadians(latitude);
        final double delta = Math.toRadians(declination);
        final double hourAngleRad = Math.toRadians(hourAngleDouble);

        // Calculate and return the solar zenith angle in degrees
        return limit(calculateSolarZenithAngle(phi, delta, hourAngleRad));
    }

    /**
     * Calculates the solar zenith angle in radians.
     *
     * @param phi Latitude of the location in radians.
     * @param delta Solar declination in radians.
     * @param hourAngleRad Hour angle in radians.
     * @return The solar zenith angle in radians.
     * @see #calculateThetaZ(double, double, double)
     */
    private static double calculateSolarZenithAngle(final double phi, final double delta, final double hourAngleRad) {
        final double thetaZ = calculateThetaZ(phi, delta, hourAngleRad);
        return Math.toDegrees(thetaZ);
    }

    /**
     * Calculates the solar zenith angle in radians.
     *
     * @param phi Latitude of the location in radians.
     * @param delta Solar declination in radians.
     * @param hourAngleRad Hour angle in radians.
     * @return The solar zenith angle in radians.
     * @see #calculateNormalizedCosThetaZ(double, double, double)
     */
    private static double calculateThetaZ(final double phi, final double delta, final double hourAngleRad) {
        final double cosThetaZ = calculateNormalizedCosThetaZ(phi, delta, hourAngleRad);
        return Math.acos(cosThetaZ);
    }

    /**
     * Calculates the normalized cosine of the solar zenith angle.
     *
     * @param phi Latitude of the location in radians.
     * @param delta Solar declination in radians.
     * @param hourAngleRad Hour angle in radians.
     * @return The normalized cosine of the solar zenith angle.
     * @see #calculateCosThetaZ(double, double, double)
     */
    private static double calculateNormalizedCosThetaZ(final double phi, final double delta,
            final double hourAngleRad) {
        double cosThetaZ = calculateCosThetaZ(phi, delta, hourAngleRad);
        cosThetaZ = normalizeCosThetaZ(cosThetaZ);
        return cosThetaZ;
    }

    /**
     * Normalizes the cosine of the solar zenith angle to ensure it is within the valid range for the acos function.
     * <p>
     * The value is clamped to the range [-1, 1] to avoid potential floating-point precision errors.
     * </p>
     *
     * @param cosThetaZ The cosine of the solar zenith angle.
     * @return The normalized cosine of the solar zenith angle.
     */
    private static double normalizeCosThetaZ(final double cosThetaZ) {
        return Math.max(-1.0, Math.min(1.0, cosThetaZ));
    }

    /**
     * Calculates the cosine of the solar zenith angle.
     *
     * @param phi Latitude of the location in radians.
     * @param delta Solar declination in radians.
     * @param hourAngleRad Hour angle in radians.
     * @return The cosine of the solar zenith angle.
     */
    private static double calculateCosThetaZ(final double phi, final double delta, final double hourAngleRad) {
        return Math.sin(phi) * Math.sin(delta) + Math.cos(phi) * Math.cos(delta) * Math.cos(hourAngleRad);
    }

    /**
     * Utility method to limit an angle to the range [0, 90] degrees.
     * <p>
     * This method ensures that the angle is clamped within the valid range. If the angle is greater than 90 degrees,
     * it will be set to 90 degrees. If the angle is less than 0 degrees, it will be set to 0 degrees.
     * </p>
     *
     * @param angle The angle to be limited, in degrees.
     * @return The angle clamped to the range [0, 90] degrees.
     */
    private static double limit(final double angle) {
        // Clamp the angle to the range [0, 90] degrees
        final double newAngle = Math.min(angle, 90.0);
        return Math.max(0, newAngle);
    }

}
