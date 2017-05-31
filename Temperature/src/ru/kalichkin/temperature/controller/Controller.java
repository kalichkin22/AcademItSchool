package ru.kalichkin.temperature.controller;

import ru.kalichkin.temperature.common.*;
import ru.kalichkin.temperature.model.Celsius;
import ru.kalichkin.temperature.model.Fahrenheit;
import ru.kalichkin.temperature.model.Kelvin;

import java.util.ArrayList;
import java.util.Arrays;

public class Controller implements ViewListener {
    private final View view;
    private ArrayList<TemperatureConverter> listOfTemperature = new ArrayList<>(Arrays.asList(new Celsius(), new Kelvin(), new Fahrenheit()));

    public Controller(View view) {
        this.view = view;
    }

    @Override
    public void needConvertTemperature(double temperature, String nameFrom, String nameTo) {
        TemperatureConverter temperatureTo = searchConvert(nameTo);
        TemperatureConverter temperatureFrom = searchConvert(nameFrom);
        view.onTemperatureConverted(temperatureTo.convertFromCelsius(temperatureFrom.convertToCelsius(temperature)));
    }

    private TemperatureConverter searchConvert(String name) {
        TemperatureConverter temperatureConverter = null;
        for (TemperatureConverter e : listOfTemperature) {
            if (e.getName().equals(name)) {
                temperatureConverter = e;
                break;
            }
        }
        return temperatureConverter;
    }

    public String[] getItems() {
       return listOfTemperature.stream().map(TemperatureConverter::getName).toArray(String[]::new);
    }
}


