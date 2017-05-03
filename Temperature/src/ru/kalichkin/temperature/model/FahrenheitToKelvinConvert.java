package ru.kalichkin.temperature.model;

import ru.kalichkin.temperature.common.TemperatureConverter;

public class FahrenheitToKelvinConvert implements TemperatureConverter {
    @Override
    public double convert(double temperature) {
        return (temperature - 32) / 1.8 + 273;
    }
}
