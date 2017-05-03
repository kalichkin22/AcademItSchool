package ru.kalichkin.temperature.common;

public interface View {

    void startApplication();

    void onTemperatureConverted(double convertedTemperature);

    void addViewListener(ViewListener listener);

    void removeViewListener(ViewListener listener);
}