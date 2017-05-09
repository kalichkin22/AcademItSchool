package ru.kalichkin.temperature.model;

import ru.kalichkin.temperature.common.TemperatureConverter;

public class CelsiusToFahrenheitConvert implements TemperatureConverter {
    @Override
    public double convert(double temperature) {
        return temperature * 9.0 / 5.0 + 32.0;
    }
}