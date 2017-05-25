package ru.academits.kalichkin.minesweeper.model;


import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class TimerGame extends JLabel{
    private Timer timer = new Timer();
    private String timeOut;

    public String startTimer() {
        TimerTask timerTask = new TimerTask() {
            volatile int time;
            Runnable refresher = new Runnable() {
                public void run() {
                    int multiplyTime = 60;
                    timeOut = String.format("%02d:%02d", time / multiplyTime, time % multiplyTime);
                }
            };

            public void run() {
                time++;
                SwingUtilities.invokeLater(refresher);
            }
        };
        int period = 1000;
        timer.scheduleAtFixedRate(timerTask, 0, period);
        return timeOut;
    }

    public String getTime() {
        return timeOut;
    }

    public String stopTimer() {
        timer.cancel();
        return timeOut;
    }
}
