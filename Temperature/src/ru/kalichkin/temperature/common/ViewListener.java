package ru.kalichkin.temperature.common;

public interface ViewListener {
    void needConvertTemperature(double temperature, String nameFrom, String nameTo);

    String[] getItems();
}


