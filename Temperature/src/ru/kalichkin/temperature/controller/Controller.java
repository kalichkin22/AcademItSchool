package ru.kalichkin.temperature.controller;

import ru.kalichkin.temperature.common.*;

public class Controller implements ViewListener {
    private final View view;

    public Controller(View view) {
        this.view = view;
    }

    @Override
    public void needConvertTemperature(double temperature, String str, String str2) {
        TemperatureConverter temperatureConverter = FindTemperature.getType(str, str2);
        view.onTemperatureConverted(temperatureConverter.convert(temperature));
    }
}


