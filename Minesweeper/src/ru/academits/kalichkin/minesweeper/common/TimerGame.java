package ru.academits.kalichkin.minesweeper.common;


import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class TimerGame {
    private Timer timer = new Timer();
    private String timeStop;


    public void startTimer() {
        TimerTask timerTask = new TimerTask() {
            volatile int time;
            Runnable refresher = new Runnable() {
                public void run() {
                    timeStop = String.format("%02d:%02d", time / 60, time % 60);
                }
            };

            public void run() {
                time++;
                SwingUtilities.invokeLater(refresher);
            }
        };
        timer.scheduleAtFixedRate(timerTask, 0, 1000);
    }

    public String stopTimer() {
        timer.cancel();
        return timeStop;
    }
}
