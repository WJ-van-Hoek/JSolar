package com.jsolar.logic;

/**
 * Contains constants used for celestial and astronomical calculations.
 * <p>
 * This class provides a set of constants that are used for various calculations related to celestial mechanics,
 * including the position of the Sun and other astronomical parameters. These constants are derived from standard
 * astronomical data and are used in calculations involving Julian Days, ecliptic longitude, mean anomalies, and more.
 * </p>
 * <p>
 * All constants are public and static to allow access without instantiation of the class.
 * </p>
 */
public final class CelestialConstants {

    /**
     * The mean longitude of the Sun at J2000.0 in degrees.
     */
    public static final double MEAN_LONGITUDE_AT_J2000 = 280.46;

    /**
     * The mean daily motion of the Sun in degrees per day.
     */
    public static final double MEAN_DAILY_MOTION = 0.9856474;

    /**
     * Full circle in degrees (360 degrees).
     */
    public static final double FULL_CIRCLE_DEGREES = 360.0;

    /**
     * The mean anomaly of the Sun at J2000.0 in degrees.
     */
    public static final double MEAN_ANOMALY_AT_J2000 = 357.528;

    /**
     * The daily motion of the mean anomaly of the Sun in degrees per day.
     */
    public static final double ANOMALY_DAILY_MOTION = 0.9856003;

    /**
     * The correction to the ecliptic longitude due to the mean anomaly of the Sun.
     */
    public static final double ECLIPTIC_LONGITUDE_CORRECTION = 1.915;

    /**
     * The additional correction to the ecliptic longitude due to the mean anomaly of the Sun.
     */
    public static final double ECLIPTIC_LONGITUDE_CORRECTION_2 = 0.020;

    /**
     * The obliquity of the ecliptic at J2000.0 in degrees.
     */
    public static final double OBLIQUITY_OF_ECLIPTIC = 23.439;

    /**
     * The correction to the obliquity of the ecliptic due to the passage of time.
     */
    public static final double OBLIQUITY_CORRECTION = 0.0000004;

    /**
     * The Julian Day base reference, J2000.0.
     */
    public static final int JULIAN_DAY_BASE = 2451545;

    /**
     * Number of days in a century used for calculations of centuries since J2000.0.
     */
    public static final int DAYS_IN_CENTURY = 36525;

    /**
     * Offset in Julian Days used for calculations.
     */
    public static final double JULIAN_DAY_OFFSET = 1524.5;

    /**
     * Average number of days in a year used for Julian Day calculations.
     */
    public static final double DAYS_IN_YEAR = 365.25;

    /**
     * Average number of days in a month used for Julian Day calculations.
     */
    public static final double DAYS_IN_MONTH = 30.6001;

    /**
     * Number of seconds in a minute.
     */
    public static final int SECONDS_IN_MINUTE = 60;

    /**
     * Number of minutes in an hour.
     */
    public static final int MINUTES_IN_HOUR = 60;

    /**
     * Number of minutes in a day.
     */
    public static final int MINUTES_IN_DAY = 1440;

    /**
     * Number of hours in a day.
     */
    public static final int HOURS_IN_DAY = 24;

    /**
     * Adjustment factor for months when calculating Julian Days.
     */
    public static final int MONTH_ADJUSTMENT = 12;

    /**
     * The start year of the Gregorian calendar.
     */
    public static final int GREGORIAN_CALENDAR_START = 1582;

    /**
     * The cutoff for adjusting the year when it is before March for Julian Day calculations.
     */
    public static final int MONTHS_BEFORE_MARCH = 2;

    /**
     * The correction factor for the Gregorian calendar in Julian Day calculations.
     */
    public static final int GREGORIAN_B_CORRECTION = 2;

    /**
     * The century correction factor for the Gregorian calendar in Julian Day calculations.
     */
    public static final int GREGORIAN_CENTURY_CORRECTION = 4;

    /**
     * No correction applied for Julian calendar dates.
     */
    public static final int NO_CORRECTION = 0;

    /**
     * The divisor used to calculate the century from a year.
     */
    public static final int CENTURY_DIVISOR = 100;

    /**
     * Offset in years used for Julian Day calculations.
     */
    public static final int JULIAN_YEAR_OFFSET = 4716;

    /**
     * The reference angle used to calculate the solar altitude angle.
     * <p>
     * This is typically 90 degrees, representing the angle between the zenith and the horizon.
     * </p>
     */
    public static final double SOLAR_ALTITUDE_REFERENCE_ANGLE = 90.0;

    /**
     * The minimum value for the solar zenith angle.
     * <p>
     * The solar zenith angle must be greater than or equal to this value. A zenith angle of 0 degrees corresponds to
     * the sun being directly overhead.
     * </p>
     */
    public static final double MIN_ZENITH_ANGLE = 0.0;

    /**
     * The maximum value for the solar zenith angle.
     * <p>
     * The solar zenith angle must be less than or equal to this value. A zenith angle of 180 degrees corresponds to the
     * sun being directly below the observer, at the nadir.
     * </p>
     */
    public static final double MAX_ZENITH_ANGLE = 180.0;

    /**
     * Conversion factor to convert degrees to radians.
     * <p>
     * This constant is used to convert angle measurements from degrees to radians. The value is computed as
     * {@code Math.PI / 180.0}.
     * </p>
     */
    public static final double DEG_TO_RAD = Math.PI / 180.0;

    /**
     * The average distance from the Earth to the Sun, known as an Astronomical Unit (AU). This value is approximately
     * 149,597,870.7 kilometers.
     */
    public static final double ASTRONOMICAL_UNIT = 149597870.7;

    /**
     * The eccentricity of Earth's orbit around the Sun. This value indicates how much the orbit deviates from being
     * circular, with 0 being a perfect circle and values closer to 1 being more elongated.
     */
    public static final double ECCENTRICITY = 0.01671123;

    /**
     * The mean anomaly of the Earth's orbit at perihelion, expressed in degrees. This is the angular distance from the
     * perihelion to a hypothetical point moving at a uniform speed around the Sun, based on Earth's average orbital
     * speed. This value is used in calculations to determine the Earth's position in its orbit.
     */
    public static final double MEAN_ANOMALY_PERIHELION_DEG = 357.5291;

    /**
     * The average baseline multiplier for the Earth-Sun distance. This constant accounts for the slight average
     * variation in the Earth's distance from the Sun throughout its orbit, approximated to 1.00014.
     */
    public static final double MEAN_DISTANCE_MULTIPLIER = 1.00014;

    /**
     * The coefficient for the cosine of twice the mean anomaly in the distance calculation. This value, 0.00014,
     * adjusts the Earth's distance from the Sun to account for the second harmonic of the Earth's orbital position,
     * which introduces a minor variation in distance.
     */
    public static final double COSINE_FIRST_HARMONIC_COEFFICIENT = 0.00014;

    /**
     * The solar constant, denoted as {@code I_0}, is the average amount of solar energy received per unit area at the
     * top of Earth's atmosphere, measured in watts per square meter (W/m²).
     * <p>
     * This constant represents the flux density of solar radiation at a mean distance from the Sun (1 astronomical unit
     * or AU) and is approximately {@code 1361} W/m². The solar constant is used in various scientific and engineering
     * calculations to model solar energy and its effects on Earth's climate and energy balance.
     * </p>
     */
    public static final double I_0 = 1361;

    /**
     * The minimum optical depth for a clear sky condition.
     * <p>
     * This value represents the lower bound of the optical depth range typically observed in a clear sky, where the
     * atmosphere is relatively transparent to sunlight. The optical depth in this condition generally ranges from
     * {@code 0.1} to {@code 0.3}.
     * </p>
     * <p>
     * References:
     * <ul>
     * <li>Kasten, F., & Young, A. T. (1989). "Revised optical air mass tables and approximation formula." <i>Applied
     * Optics</i>, 28(15), 2339-2346.</li>
     * <li>Schulz, J., & Mayer, B. (2005). "A review of radiative transfer modeling in the atmosphere." <i>Journal of
     * Quantitative Spectroscopy and Radiative Transfer</i>, 93(1), 75-89.</li>
     * </ul>
     * </p>
     */
    public static final double OPTICAL_DEPTH_CLEAR_SKY_MIN = 0.1;

    /**
     * The maximum optical depth for a clear sky condition.
     * <p>
     * This value represents the upper bound of the optical depth range typically observed in a clear sky, where the
     * atmosphere is relatively transparent to sunlight. The optical depth in this condition generally ranges from
     * {@code 0.1} to {@code 0.3}.
     * </p>
     * <p>
     * References:
     * <ul>
     * <li>Kasten, F., & Young, A. T. (1989). "Revised optical air mass tables and approximation formula." <i>Applied
     * Optics</i>, 28(15), 2339-2346.</li>
     * <li>Schulz, J., & Mayer, B. (2005). "A review of radiative transfer modeling in the atmosphere." <i>Journal of
     * Quantitative Spectroscopy and Radiative Transfer</i>, 93(1), 75-89.</li>
     * </ul>
     * </p>
     */
    public static final double OPTICAL_DEPTH_CLEAR_SKY_MAX = 0.3;

    /**
     * The minimum optical depth for thin clouds.
     * <p>
     * This value represents the lower bound of the optical depth range typically observed in thin clouds, such as
     * cirrus clouds. The optical depth for thin clouds generally ranges from {@code 0.1} to {@code 1.0}.
     * </p>
     * <p>
     * References:
     * <ul>
     * <li>Clouds and the Earth's Radiant Energy System (CERES). (2008). "CERES Energy Balanced and Filled (EBF) Cloud
     * Optical Depth Product." NASA Langley Research Center.</li>
     * <li>Liou, K. N. (2002). "An Introduction to Atmospheric Radiation." <i>Academic Press</i>.</li>
     * </ul>
     * </p>
     */
    public static final double OPTICAL_DEPTH_THIN_CLOUD_MIN = 0.1;

    /**
     * The maximum optical depth for thin clouds.
     * <p>
     * This value represents the upper bound of the optical depth range typically observed in thin clouds, such as
     * cirrus clouds. The optical depth for thin clouds generally ranges from {@code 0.1} to {@code 1.0}.
     * </p>
     * <p>
     * References:
     * <ul>
     * <li>Clouds and the Earth's Radiant Energy System (CERES). (2008). "CERES Energy Balanced and Filled (EBF) Cloud
     * Optical Depth Product." NASA Langley Research Center.</li>
     * <li>Liou, K. N. (2002). "An Introduction to Atmospheric Radiation." <i>Academic Press</i>.</li>
     * </ul>
     * </p>
     */
    public static final double OPTICAL_DEPTH_THIN_CLOUD_MAX = 1.0;

    /**
     * The minimum optical depth for thick clouds.
     * <p>
     * This value represents the lower bound of the optical depth range typically observed in thick clouds, such as
     * cumulus or nimbostratus clouds. The optical depth for thick clouds generally ranges from {@code 10} to
     * {@code 50}, indicating very high levels of cloudiness and significant light attenuation.
     * </p>
     * <p>
     * References:
     * <ul>
     * <li>King, M. D., & Byrne, D. M. (1998). "The Retrieval of Cloud Optical Depth and Effective Radius from
     * Multiwavelength Satellite Radiance Measurements." <i>Journal of Climate</i>, 11(8), 1945-1960.</li>
     * <li>Brutsaert, W. (2006). "Hydrology: An Introduction." <i>Cambridge University Press</i>.</li>
     * </ul>
     * </p>
     */
    public static final double OPTICAL_DEPTH_THICK_CLOUD_MIN = 10;

    /**
     * The maximum optical depth for thick clouds.
     * <p>
     * This value represents the upper bound of the optical depth range typically observed in thick clouds, such as
     * cumulus or nimbostratus clouds. The optical depth for thick clouds generally ranges from {@code 10} to
     * {@code 50}, indicating very high levels of cloudiness and significant light attenuation.
     * </p>
     * <p>
     * References:
     * <ul>
     * <li>King, M. D., & Byrne, D. M. (1998). "The Retrieval of Cloud Optical Depth and Effective Radius from
     * Multiwavelength Satellite Radiance Measurements." <i>Journal of Climate</i>, 11(8), 1945-1960.</li>
     * <li>Brutsaert, W. (2006). "Hydrology: An Introduction." <i>Cambridge University Press</i>.</li>
     * </ul>
     * </p>
     */
    public static final double OPTICAL_DEPTH_THICK_CLOUD_MAX = 50;

    private CelestialConstants() {
        // Private constructor to prevent instantiation
    }
}
