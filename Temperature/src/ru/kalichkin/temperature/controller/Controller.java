package ru.kalichkin.temperature.controller;

import ru.kalichkin.temperature.common.*;
import ru.kalichkin.temperature.model.Fahrenheit;
import ru.kalichkin.temperature.model.Kelvin;

import java.util.ArrayList;
import java.util.Arrays;

public class Controller implements ViewListener {
    private final View view;

    private ArrayList<TemperatureConverter> list = new ArrayList<>(Arrays.asList(new Kelvin(), new Fahrenheit()));

    private final String[] items = {"Celsius", "Fahrenheit", "Kelvin"};

    public Controller(View view) {
        this.view = view;
    }

    @Override
    public void needConvertTemperature(double temperature, String nameFrom, String nameTo) {
        for (TemperatureConverter aList : list) {
            if (aList.getName().equals(nameFrom)) {
                view.onTemperatureConverted(aList.convertToCelsius(temperature));
            } else if (nameFrom.equals("Celsius") && aList.getName().equals(nameTo)) {
                view.onTemperatureConverted(aList.convertFromCelsius(temperature));
            }
            if (aList.getName().equals(nameFrom)) {
                double celsius = aList.convertToCelsius(temperature);
                for (TemperatureConverter aList1 : list) {
                    if (aList1.getName().equals(nameTo)) {
                        view.onTemperatureConverted(aList1.convertFromCelsius(celsius));
                    }
                }
            }
        }
    }


    public String[] getItems() {
        return items;
    }
}


