
package ru.kalichkin.temperature.model;

import ru.kalichkin.temperature.common.TemperatureConverter;

public class FahrenheitToCelsiusConvert implements TemperatureConverter {
    @Override
    public double convert(double temperature) {
        return 0.56 * (temperature - 32);
    }
}