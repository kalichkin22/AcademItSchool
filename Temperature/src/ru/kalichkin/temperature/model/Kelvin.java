package ru.kalichkin.temperature.model;

import ru.kalichkin.temperature.common.TemperatureConverter;

public class Kelvin implements TemperatureConverter {

    @Override
    public String getName() {
        return "Kelvin";
    }

    @Override
    public double convertFromCelsius(double temperature) {
        return temperature + 273.15;
    }

    @Override
    public double convertToCelsius(double temperature) {
        return temperature - 273.15;
    }
}
