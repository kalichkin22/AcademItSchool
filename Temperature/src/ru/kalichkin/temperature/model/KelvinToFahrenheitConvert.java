package ru.kalichkin.temperature.model;

import ru.kalichkin.temperature.common.TemperatureConverter;

public class KelvinToFahrenheitConvert implements TemperatureConverter {
    @Override
    public double convert(double temperature) {
        return 1.8 * (temperature - 273) + 32;
    }
}
