package ru.kalichkin.temperature.model;

import ru.kalichkin.temperature.common.TemperatureConverter;

public class Fahrenheit implements TemperatureConverter {

    @Override
    public String getName() {
        return "Fahrenheit";
    }

    @Override
    public double convertFromCelsius(double temperature) {
        return temperature * 9.0 / 5.0 + 32.0;
    }

    @Override
    public double convertToCelsius(double temperature) {
        return 0.56 * (temperature - 32);
    }

}
