package ru.kalichkin.temperature.controller;

import ru.kalichkin.temperature.common.*;
import ru.kalichkin.temperature.model.*;

public class Controller implements ViewListener {
    private TemperatureConverter temperatureConverter;
    private final View view;

    public Controller(View view) {
        this.view = view;
    }

    @Override
    public void needConvertTemperature(double temperature, String str, String str2) {
        if (str.equals("Celsius") && str2.equals("Fahrenheit")) {
            this.temperatureConverter = new CelsiusToFahrenheitConvert();
        } else if (str.equals("Fahrenheit") && str2.equals("Celsius")) {
            this.temperatureConverter = new FahrenheitToCelsiusConvert();
        } else if (str.equals("Kelvin") && str2.equals("Celsius")) {
            this.temperatureConverter = new KelvinToCelsiusConvert();
        } else if (str.equals("Kelvin") && str2.equals("Fahrenheit")) {
            this.temperatureConverter = new KelvinToFahrenheitConvert();
        } else if (str.equals("Celsius") && str2.equals("Kelvin")) {
            this.temperatureConverter = new CelsiusToKelvinConvert();
        } else if (str.equals("Fahrenheit") && str2.equals("Kelvin")) {
            this.temperatureConverter = new FahrenheitToKelvinConvert();
        }

        view.onTemperatureConverted(temperatureConverter.convert(temperature));
    }
}


