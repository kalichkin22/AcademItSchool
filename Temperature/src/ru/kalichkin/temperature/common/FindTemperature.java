package ru.kalichkin.temperature.common;

import ru.kalichkin.temperature.model.*;

public class FindTemperature {
    public static final String[] items = {"Celsius", "Fahrenheit", "Kelvin"};

    public static TemperatureConverter getType(String str, String str2) {
        if (str.equals("Celsius") && str2.equals("Fahrenheit")) {
            return new CelsiusToFahrenheitConvert();
        } else if (str.equals("Fahrenheit") && str2.equals("Celsius")) {
            return new FahrenheitToCelsiusConvert();
        } else if (str.equals("Kelvin") && str2.equals("Celsius")) {
            return new KelvinToCelsiusConvert();
        } else if (str.equals("Kelvin") && str2.equals("Fahrenheit")) {
            return new KelvinToFahrenheitConvert();
        } else if (str.equals("Celsius") && str2.equals("Kelvin")) {
            return new CelsiusToKelvinConvert();
        } else {
            return new FahrenheitToKelvinConvert();
        }
    }
}
