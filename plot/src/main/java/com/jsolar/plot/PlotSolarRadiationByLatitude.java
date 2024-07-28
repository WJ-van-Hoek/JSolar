package com.jsolar.plot;

import java.time.LocalDateTime;
import java.time.Month;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import com.jsolar.logic.SolarRadiationLogic;

/**
 * The {@code Main} class calculates and plots the solar radiation for multiple latitudes over
 * the course of a year. It uses an optical depth (τ) of 1 and plots the solar radiation
 * for latitudes ranging from 0 to 90 degrees in increments of 10 degrees.
 * <p>
 * The plot displays solar radiation values on the y-axis and the day of the year on the x-axis.
 * </p>
 */
public class PlotSolarRadiationByLatitude {

    /**
     * The optical depth (τ) used for the solar radiation calculations. Set to 1.
     */
    private static final double TAU = 1.0; // Optical depth

    /**
     * The latitudes to be plotted.
     */
    private static final double[] LATITUDES = {0, 10, 20, 30, 40, 50, 60, 70, 80, 90};

    /**
     * The main method that calculates and plots the solar radiation for multiple latitudes
     * over the course of the year 2024, with a daily time step.
     * <p>
     * This method iterates over each day of the year, calculates the solar radiation for
     * each specified latitude, and generates a plot with multiple series.
     * </p>
     *
     * @param args Command-line arguments (not used in this implementation).
     */
    public static void main(final String[] args) {
        // Create a dataset for the plot
        final XYSeriesCollection dataset = new XYSeriesCollection();

        // Iterate over each latitude
        for (final double latitude : LATITUDES) {
            final XYSeries series = new XYSeries("Latitude " + latitude);

            // Iterate over each day of the year 2024
            for (final Month month : Month.values()) {
                for (int day = 1; day <= month.length(true); day++) {
                    // Create the UTC date-time for each day at noon
                    final LocalDateTime localDateTime = LocalDateTime.of(2024, month, day, 12, 0);

                    // Calculate the solar radiation
                    final double solarRadiation = SolarRadiationLogic.getSolarRadiation(latitude, 0.0,
                            localDateTime, TAU);

                    // Add data to the series
                    // Use day of year as the x-axis value
                    final int dayOfYear = localDateTime.getDayOfYear();
                    series.add(dayOfYear, solarRadiation);
                }
            }

            // Add the series to the dataset
            dataset.addSeries(series);
        }

        // Create a chart
        final JFreeChart chart = ChartFactory.createXYLineChart(
                "Solar Radiation Throughout the Year by Latitude",
                "Day of Year",
                "Solar Radiation",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        // Display the chart in a window
        final JFrame frame = new JFrame("Solar Radiation Plot");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new ChartPanel(chart));
        frame.pack();
        frame.setVisible(true);
    }
}
