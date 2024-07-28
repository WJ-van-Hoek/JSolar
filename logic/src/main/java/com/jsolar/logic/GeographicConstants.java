package com.jsolar.logic;

/**
 * Constants defining the valid range of latitude and longitude values.
 * <p>
 * These constants represent the boundaries for latitude and longitude values. They are used for validation purposes to
 * ensure that geographic coordinates fall within the acceptable ranges:
 * <ul>
 * <li>Latitude must be between {@link #MINIMAL_LATITUDE} and {@link #MAXIMAL_LATITUDE} degrees.</li>
 * <li>Longitude must be between {@link #MINIMAL_LONGITUDE} and {@link #MAXIMAL_LONGITUDE} degrees.</li>
 * </ul>
 * </p>
 */
public final class GeographicConstants {

    /**
     * The minimum allowable latitude value.
     * <p>
     * Latitude values below this threshold are considered invalid. This constant is set to -90 degrees, which
     * corresponds to the South Pole.
     * </p>
     */
    public static final double MINIMAL_LATITUDE = -90;

    /**
     * The maximum allowable latitude value.
     * <p>
     * Latitude values above this threshold are considered invalid. This constant is set to 90 degrees, which
     * corresponds to the North Pole.
     * </p>
     */
    public static final double MAXIMAL_LATITUDE = 90;

    /**
     * The minimum allowable longitude value.
     * <p>
     * Longitude values below this threshold are considered invalid. This constant is set to -180 degrees, which
     * represents the westernmost meridian.
     * </p>
     */
    public static final double MINIMAL_LONGITUDE = -180;

    /**
     * The maximum allowable longitude value.
     * <p>
     * Longitude values above this threshold are considered invalid. This constant is set to 180 degrees, which
     * represents the easternmost meridian.
     * </p>
     */
    public static final double MAXIMAL_LONGITUDE = 180;

    // Private constructor to prevent instantiation
    private GeographicConstants() {
        // Prevent instantiation
    }
}
