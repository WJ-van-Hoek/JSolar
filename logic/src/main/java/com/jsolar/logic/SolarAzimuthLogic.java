package com.jsolar.logic;

/**
 * A utility class for calculating the solar azimuth angle.
 * <p>
 * The solar azimuth angle is the compass direction from which the sunlight is coming at any specific point on the
 * earth's surface. This class provides static methods to calculate the solar azimuth angle given latitude, solar
 * altitude, solar declination, and hour angle. The class is designed to be used without instantiating it, as it
 * contains only static methods.
 * </p>
 * <p>
 * The calculations are based on standard solar position equations. The class uses internal constants for converting
 * between degrees and radians, and to ensure the resulting azimuth angle is within the standard range of [0, 360)
 * degrees.
 * </p>
 */
public final class SolarAzimuthLogic {

    /**
     * Private constructor to prevent instantiation of this utility class.
     * <p>
     * Since this class only contains static methods and should not be instantiated, the constructor is private. This
     * ensures that instances of this class cannot be created, adhering to the utility class design pattern.
     * </p>
     */
    private SolarAzimuthLogic() {
    }

    /**
     * Calculates the solar azimuth angle (Az) given:
     * <ul>
     * <li>latitude (φ) in degrees</li>
     * <li>solar altitude angle (α) in degrees</li>
     * <li>solar declination (δ) in degrees</li>
     * <li>hour angle (H) in degrees</li>
     * </ul>
     * <p>
     * The solar azimuth angle is the angle between the direction of the sunlight and the North direction, measured in
     * the plane of the local horizon. This method converts the input angles from degrees to radians, computes the
     * azimuth angle in radians, and then converts the result back to degrees.
     * </p>
     *
     * @param latitude The latitude of the location in degrees. Positive values indicate northern hemisphere, while
     * negative values indicate southern hemisphere.
     * @param solarAltitude The solar altitude angle in degrees. This is the angle between the sun and the horizontal
     * plane at the observer's location.
     * @param solarDeclination The solar declination in degrees. This is the angle between the rays of the sun and the
     * plane of the Earth's equator.
     * @param hourAngle The hour angle in degrees. This represents the time since solar noon, expressed in angular
     * distance.
     * @return The solar azimuth angle in degrees. This is the compass direction from which the sunlight is coming at
     * the given location and time.
     */
    public static double getSolarAzimuth(final double latitude, final double solarAltitude,
            final double solarDeclination, final double hourAngle) {
        // Convert inputs from degrees to radians
        final double latitudeRad = Math.toRadians(latitude);
        final double solarAltitudeRad = Math.toRadians(solarAltitude);
        final double solarDeclinationRad = Math.toRadians(solarDeclination);
        final double hourAngleRad = Math.toRadians(hourAngle);

        // Calculate and return the solar azimuth angle
        return calculateSolarAzimuth(latitudeRad, solarAltitudeRad, solarDeclinationRad, hourAngleRad);
    }

    /**
     * Calculates the solar azimuth angle (Az) in radians based on input values in radians.
     * <p>
     * This method uses internal helper methods to compute the sine and cosine of the azimuth angle and then calculates
     * the azimuth angle in radians. It also converts the angle from radians to degrees and adjusts it to ensure it
     * falls within the standard range of [0, 360) degrees.
     * </p>
     *
     * @param latitudeRad The latitude of the location in radians.
     * @param solarAltitudeRad The solar altitude angle in radians.
     * @param solarDeclinationRad The solar declination in radians.
     * @param hourAngleRad The hour angle in radians.
     * @return The solar azimuth angle in degrees.
     */
    private static double calculateSolarAzimuth(final double latitudeRad, final double solarAltitudeRad,
            final double solarDeclinationRad, final double hourAngleRad) {
        // Calculate the sine and cosine of the azimuth angle
        final double sinAz = calculateSinAz(solarAltitudeRad, solarDeclinationRad, hourAngleRad);
        final double cosAz = calculateCosAz(latitudeRad, solarAltitudeRad, solarDeclinationRad);

        // Calculate the azimuth angle in radians
        final double azimuthAngleRad = Math.atan2(sinAz, cosAz);

        // Convert the azimuth angle from radians to degrees
        double azimuthAngle = Math.toDegrees(azimuthAngleRad);

        // Ensure the azimuth angle is in the range [0, 360) degrees
        if (azimuthAngle < 0) {
            azimuthAngle += CelestialConstants.FULL_CIRCLE_DEGREES;
        }
        return azimuthAngle;
    }

    /**
     * Calculates the cosine of the solar azimuth angle (Az).
     * <p>
     * This helper method computes the cosine component of the azimuth angle using the formula:
     *
     * <pre>
     * cos(Az) = (sin(α) - sin(φ) * sin(δ)) / (cos(φ) * cos(α))
     * </pre>
     * </p>
     *
     * @param latitudeRad The latitude of the location in radians.
     * @param solarAltitudeRad The solar altitude angle in radians.
     * @param solarDeclinationRad The solar declination in radians.
     * @return The cosine of the solar azimuth angle.
     */
    private static double calculateCosAz(final double latitudeRad, final double solarAltitudeRad,
            final double solarDeclinationRad) {
        return (Math.sin(solarAltitudeRad) - Math.sin(latitudeRad) * Math.sin(solarDeclinationRad))
                / (Math.cos(latitudeRad) * Math.cos(solarAltitudeRad));
    }

    /**
     * Calculates the sine of the solar azimuth angle (Az).
     * <p>
     * This helper method computes the sine component of the azimuth angle using the formula:
     *
     * <pre>
     * sin(Az) = cos(δ) * sin(H) / cos(α)
     * </pre>
     * </p>
     *
     * @param solarAltitudeRad The solar altitude angle in radians.
     * @param solarDeclinationRad The solar declination in radians.
     * @param hourAngleRad The hour angle in radians.
     * @return The sine of the solar azimuth angle.
     */
    private static double calculateSinAz(final double solarAltitudeRad, final double solarDeclinationRad,
            final double hourAngleRad) {
        return Math.cos(solarDeclinationRad) * Math.sin(hourAngleRad) / Math.cos(solarAltitudeRad);
    }
}
