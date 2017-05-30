package ru.kalichkin.temperature.controller;

import ru.kalichkin.temperature.common.*;
import ru.kalichkin.temperature.model.Fahrenheit;
import ru.kalichkin.temperature.model.Kelvin;

import java.util.ArrayList;
import java.util.Arrays;

public class Controller implements ViewListener {
    private final View view;

    private ArrayList<TemperatureConverter> listOfTemperature = new ArrayList<>(Arrays.asList(new Kelvin(), new Fahrenheit()));

    public Controller(View view) {
        this.view = view;
    }

    @Override
    public void needConvertTemperature(double temperature, String nameFrom, String nameTo) {
        for (TemperatureConverter temperatureName : listOfTemperature) {
            if (temperatureName.getName().equals(nameFrom)) {
                view.onTemperatureConverted(temperatureName.convertToCelsius(temperature));
            } else if (temperatureName.getName().equals(nameTo)) {
                view.onTemperatureConverted(temperatureName.convertFromCelsius(temperature));
            }
            if (temperatureName.getName().equals(nameFrom)) {
                double celsius = temperatureName.convertToCelsius(temperature);
                for (TemperatureConverter temperatureName2 : listOfTemperature) {
                    if (temperatureName2.getName().equals(nameTo)) {
                        view.onTemperatureConverted(temperatureName2.convertFromCelsius(celsius));
                    }
                }
                break;
            }
        }
    }


    public String[] getItems() {
        String[] items = new String[listOfTemperature.size() + 1];
        items[0] = "Celsius";
        for (int i = 1; i < items.length; i++) {
            items[i] = listOfTemperature.get(i - 1).getName();
        }
        return items;
    }
}


