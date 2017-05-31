package ru.kalichkin.temperature.model;

import ru.kalichkin.temperature.common.TemperatureConverter;

public class Celsius implements TemperatureConverter{
    @Override
    public String getName() {
        return "Celsius";
    }

    @Override
    public double convertFromCelsius(double temperature) {
        return temperature;
    }

    @Override
    public double convertToCelsius(double temperature) {
        return temperature;
    }
}
