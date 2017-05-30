package ru.kalichkin.temperature.common;

public interface View {

    void startApplication();

    void onTemperatureConverted(double convertedTemperature);

    void setViewListener(ViewListener listener);

}