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
        TemperatureConverter temperatureFrom = searchConvertFrom(nameFrom);
        TemperatureConverter temperatureTo = searchConvertTo(nameTo);
        view.onTemperatureConverted(temperatureFrom.convertFromCelsius(temperatureTo.convertToCelsius(temperature)));



    }


    public String[] getItems() {
        String[] items = new String[listOfTemperature.size()];
        for (int i = 0; i < items.length; i++) {
            items[i] = listOfTemperature.get(i).getName();
        }
        return items;
    }


    private TemperatureConverter searchConvertFrom(String nameFrom) {
        TemperatureConverter temperatureConverter = null;
        for (TemperatureConverter a : listOfTemperature) {
            if (a.getName().equals(nameFrom)) {
                temperatureConverter = a;
                break;
            }
        }
        return temperatureConverter;
    }

    private TemperatureConverter searchConvertTo(String nameTo) {
        TemperatureConverter temperatureConverter = null;
        for (TemperatureConverter a : listOfTemperature) {
            if (a.getName().equals(nameTo)) {
                temperatureConverter = a;
                break;
            }
        }
        return temperatureConverter;
    }
}


