package ru.kalichkin.temperature.model;

import ru.kalichkin.temperature.common.TemperatureConverter;

public class CelsiusToKelvinConvert implements TemperatureConverter {
    @Override
    public double convert(double temperature) {
        return temperature + 273.15;
    }
}
