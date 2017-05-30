package ru.kalichkin.temperature.common;


public interface TemperatureConverter {
    String getName();

    double convertFromCelsius(double temperature);

    double convertToCelsius(double temperature);
}
